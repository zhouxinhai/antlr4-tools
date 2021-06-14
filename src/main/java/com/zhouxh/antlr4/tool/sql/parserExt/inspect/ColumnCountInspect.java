package com.zhouxh.antlr4.tool.sql.parserExt.inspect;

import com.zhouxh.antlr4.tool.sql.parser.EntireSqlBaseVisitor;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import com.zhouxh.antlr4.tool.sql.parserExt.HsqlRecognitionException;

import java.util.List;

/**
 * 校验sql语句中的字段个数是否左右相等
 * Create by Howard on 2018/10/16
 */
public class ColumnCountInspect extends EntireSqlBaseVisitor<Integer> {

    /**
     * 保证UNION、EXCEPT、INTERSECT、MINUSES左右两边的select语句字段个数相等
     * @param ctx
     * @return
     */
    @Override
    public Integer visitSetOperation(EntireSqlParser.SetOperationContext ctx) {
        Integer leftSelectItemCnt = visit(ctx.left);
        Integer rightSelectItemCnt = visit(ctx.right);

        if(!leftSelectItemCnt.equals(rightSelectItemCnt)){
            throw new HsqlRecognitionException(ctx.operator, ctx.operator.getText()+"左右两边的的select语句字段个数不相等(左边"+leftSelectItemCnt+"个，右边"+rightSelectItemCnt+"个)");
        }
        return leftSelectItemCnt;
    }

    /**
     * 返回select中selectitem的个数
     * @param ctx
     * @return
     */
    @Override
    public Integer visitQuerySpecification(EntireSqlParser.QuerySpecificationContext ctx) {

        visitChildren(ctx);
        return ctx.selectItem().size();
    }

    /**
     * insert into select形式的sql语句，insert的字段个数和select的字段个数的校验
     * @param ctx
     * @return
     */
    @Override
    public Integer visitInsertIntoSelect(EntireSqlParser.InsertIntoSelectContext ctx) {
        EntireSqlParser.ColumnAliasesContext columnAliasesContext = ctx.columnAliases();
        Integer queryColumnCnt = visit(ctx.query());
        if(columnAliasesContext != null){//如果insert中有指定字段，才需要对字段个数进行校验
            List<EntireSqlParser.IdentifierContext> insertColumns = columnAliasesContext.identifier();
            if(insertColumns.size() != queryColumnCnt){
                throw new HsqlRecognitionException(columnAliasesContext.start, "insert into select形式的sql语句，insert的字段个数("+insertColumns.size()+"个)和select的字段个数("+queryColumnCnt+"个)不一致");
            }
        }
        return null;
    }

    /**
     * insert into values形式的sql语句，insert的字段个数和values的字段个数的校验
     * @param ctx
     * @return
     */
    @Override
    public Integer visitInsertInto(EntireSqlParser.InsertIntoContext ctx) {
        EntireSqlParser.ColumnAliasesContext columnAliasesContext = ctx.columnAliases();
        Integer valuesColumnCnt = visit(ctx.insertValues());
        if(columnAliasesContext != null){//如果insert中有指定字段，才需要对values字段个数进行校验
            List<EntireSqlParser.IdentifierContext> insertColumns = columnAliasesContext.identifier();
            if(insertColumns.size() != valuesColumnCnt){
                throw new HsqlRecognitionException(columnAliasesContext.start, "insert into values形式的sql语句，insert的字段个数("+insertColumns.size()+"个)和values的字段个数("+valuesColumnCnt+"个)不一致");
            }
        }
        return null;
    }

    /**
     * insert into values后存在多个用逗号分隔的()表达式，每个()表达式中的字段个数要相等
     * @param ctx
     * @return
     */
    @Override
    public Integer visitInsertValues(EntireSqlParser.InsertValuesContext ctx) {
        //insert into values后存在多个用逗号分隔的()表达式，每个()表达式中的字段个数要相等
        List<EntireSqlParser.ExpressionContext> valuesExpressions = ctx.expression();
        int lastValuesExpressionColumnCnt = -1;

        for(int i = 0; i< valuesExpressions.size();i++){
            Integer valuesExpressionColumnCnt = visit(valuesExpressions.get(i));
            if(lastValuesExpressionColumnCnt == -1){
                lastValuesExpressionColumnCnt = valuesExpressionColumnCnt;
            }else{
                if(valuesExpressionColumnCnt != lastValuesExpressionColumnCnt){
                    throw new HsqlRecognitionException(ctx.start, "insert into values后存在"+valuesExpressions.size()+"个用逗号分隔的括号表达式，每个括号表达式中的字段个数要相等，发现第"
                            +i+"个括号表达式和第"+(i+1)+"个括号表达式的字段个数不一致");
                }
            }
        }

        return lastValuesExpressionColumnCnt;
    }


    /**
     * 返回insert into values中，values的字段个数
     * @param ctx
     * @return
     */
    @Override
    public Integer visitRowConstructor(EntireSqlParser.RowConstructorContext ctx) {
        visitChildren(ctx);
        return ctx.expression().size();
    }



    @Override
    protected Integer aggregateResult(Integer aggregate, Integer nextResult) {
        if (nextResult != null){
            return nextResult;
        }else{
            return aggregate;
        }

    }


}
