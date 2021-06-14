package com.zhouxh.antlr4.tool.sql.parserExt.inspect;

import com.zhouxh.antlr4.tool.sql.bean.AggregateFunctionName;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlBaseVisitor;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import com.zhouxh.antlr4.tool.sql.parserExt.HsqlRecognitionException;
import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * Create by Howard on 2018/10/16
 */
public class GroupBySqlInspectVisitor extends EntireSqlBaseVisitor<Void> {
    private enum Status{
        SELECTITEM,
        WHERE,
        HAVING,
        GROUPBY,
        AGGREGATE_FUNC,
        NON_AGGREGATE_FUNC
    }

    private static class EveryQuerySpecification{
        private Set<String> groupByItems = new HashSet<>();
        private List<Pair<String, ParserRuleContext>> havingItems = new ArrayList<>();
        private List<Pair<String, ParserRuleContext>> selectItems = new ArrayList<>();
//        private List<Pair<String, ParserRuleContext>> fuctionParas = new ArrayList<>();
        public void clear(){
            groupByItems.clear();
            selectItems.clear();
            havingItems.clear();
//            fuctionParas.clear();
        }
}


    private Stack<Status> statusStack = new Stack<Status>();
    private Stack<EveryQuerySpecification> everyQuerySpecificationStack = new Stack<>();

    /**
     * 是group by查询语句校验的入口
     * @param ctx
     * @return
     */
    @Override
    public Void visitQuerySpecification(EntireSqlParser.QuerySpecificationContext ctx) {

        if(ctx.groupBy() == null){//不是group by语句，就不要处理了
            return visitChildren(ctx);
        }
        everyQuerySpecificationStack.add(new EveryQuerySpecification());

        if(ctx.groupBy() != null){ //进入group by部分解析
            statusStack.push(Status.GROUPBY);
            visit(ctx.groupBy());
            statusStack.pop();
        }
        if(ctx.where != null){//进入where部分的解析
            statusStack.push(Status.WHERE);
            visit(ctx.where);
            statusStack.pop();
        }
        if(ctx.having != null){//进入having部分的解析
            statusStack.push(Status.HAVING);
            visit(ctx.having);
            statusStack.pop();
        }


        List<EntireSqlParser.SelectItemContext> selectItemContexts = ctx.selectItem();
        for(EntireSqlParser.SelectItemContext selectItemContext:selectItemContexts){
            statusStack.push(Status.SELECTITEM);
            visit(selectItemContext);
            statusStack.pop();
        }


        EveryQuerySpecification pop = everyQuerySpecificationStack.pop();
        //selectItems和groupByItems中的内容做校验
        for(Pair<String, ParserRuleContext> selectItem:pop.selectItems){
            if(!pop.groupByItems.contains(selectItem.getLeft().toUpperCase())){
                //说明 selectItem中的字段不符合group by语句的规范
                throw new HsqlRecognitionException(selectItem.getRight().start, selectItem.getRight().getText()+"不是group by表达式");
            }
        }
        //havingItems和groupByItems中的内容做校验
        for(Pair<String, ParserRuleContext> havingItem:pop.havingItems){
            if(!pop.groupByItems.contains(havingItem.getLeft().toUpperCase())){
                //说明 selectItem中的字段不符合group by语句的规范
                throw new HsqlRecognitionException(havingItem.getRight().start, havingItem.getRight().getText()+"不是group by表达式");
            }
        }

        for(EntireSqlParser.RelationContext relation :ctx.relation()){
            //其他无需再visit了，只要visit relation就可以，因为relation中有可能还有group by子句
            visit(relation);
        }
        return  null;
    }


    /**
     * group by后面不能出现子查询
     * @param ctx
     */
    @Override
    public Void visitSubqueryExpression(EntireSqlParser.SubqueryExpressionContext ctx) {
        if(statusStack.size() > 0 && statusStack.peek() == Status.GROUPBY){
            throw new HsqlRecognitionException(ctx.start, "这里不允许出现子查询表达式");
        }
        return visitChildren(ctx);
    }


    @Override
    public Void visitValueExpressionDefault(EntireSqlParser.ValueExpressionDefaultContext ctx) {
        if(everyQuerySpecificationStack.size() == 0){
            return visitChildren(ctx);
        }

        EveryQuerySpecification peek = everyQuerySpecificationStack.peek();
        if(statusStack.size() > 0 && statusStack.peek() == Status.SELECTITEM){//说明是selectitem
            if (ctx.primaryExpression() instanceof EntireSqlParser.FunctionCallContext) {
                EntireSqlParser.FunctionCallContext functionCallContext = (EntireSqlParser.FunctionCallContext) ctx.primaryExpression();
                return _functionCallOpt(ctx,peek,functionCallContext);
            }
            _columnOpt(ctx.primaryExpression(),peek.selectItems);
        }

        if(statusStack.size() > 0 && statusStack.peek() == Status.GROUPBY){//说明是group by后面的属性
            //把groupby中所有groupingElement的都收集起来
            peek.groupByItems.add(ctx.getText().toUpperCase());

        }

        if(statusStack.size() > 0 && statusStack.peek() == Status.HAVING){//说明是having后面的属性
            if (ctx.primaryExpression() instanceof EntireSqlParser.FunctionCallContext) {
                EntireSqlParser.FunctionCallContext functionCallContext = (EntireSqlParser.FunctionCallContext) ctx.primaryExpression();
                return _functionCallOpt(ctx,peek,functionCallContext);
            }
            _columnOpt(ctx.primaryExpression(),peek.havingItems);
        }
        return visitChildren(ctx);
    }


    @Override
    public Void visitFunctionCall(EntireSqlParser.FunctionCallContext ctx) {
        if(statusStack.size() > 0 && statusStack.peek() == Status.AGGREGATE_FUNC){
            //如果是聚合函数，则不对其参数进行校验
            visitChildren(ctx);
        }else if(statusStack.size() > 0 && statusStack.peek() == Status.NON_AGGREGATE_FUNC){
            //如果不是聚合函数，则要求参数（字符串，数字除外），必须是groupByItems中的元素
            statusStack.push(Status.SELECTITEM);
            visitChildren(ctx);
            statusStack.pop();
        }

        return null;
    }


    private Void _functionCallOpt(EntireSqlParser.ValueExpressionDefaultContext ctx,EveryQuerySpecification peek, EntireSqlParser.FunctionCallContext functionCallContext){
        //如果函数名加参数属于groupByItems中的元素则不用再进行递归操作
        if(peek.groupByItems.contains(functionCallContext.getText().toUpperCase())){
            return null;
        }
        if(AggregateFunctionName.isAggregateFunctionName(functionCallContext.qualifiedName().getText())){
            statusStack.push(Status.AGGREGATE_FUNC);
        }else{
            statusStack.push(Status.NON_AGGREGATE_FUNC);
        }
        visitChildren(ctx);
        statusStack.pop();
        return null;
    }

    private void _columnOpt(EntireSqlParser.PrimaryExpressionContext ctx,List<Pair<String, ParserRuleContext>> items){
        if (ctx instanceof EntireSqlParser.DereferenceContext) {//带表别名的的字段形式，如：t1.col1
            EntireSqlParser.DereferenceContext dereferenceContext = (EntireSqlParser.DereferenceContext) ctx;
            items.add(Pair.of(dereferenceContext.getText(),(ParserRuleContext)dereferenceContext));
        }
        if (ctx instanceof EntireSqlParser.ColumnReferenceContext) {//不带表别名的的字段形式，如：col1
            EntireSqlParser.ColumnReferenceContext columnReferenceContext = (EntireSqlParser.ColumnReferenceContext) ctx;
            items.add(Pair.of(columnReferenceContext.getText(),(ParserRuleContext)columnReferenceContext));
        }
    }


}
