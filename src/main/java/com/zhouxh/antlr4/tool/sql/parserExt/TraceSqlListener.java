package com.zhouxh.antlr4.tool.sql.parserExt;

import com.zhouxh.antlr4.tool.sql.bean.*;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlBaseListener;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**parser
 * Create ard on 2018/10/7
 */
public class TraceSqlListener extends EntireSqlBaseListener {
    private TokenStream tokenStream;
    private EntireSqlParser parser;
    //enterCrud的时候，将ctx入栈，exitCrud的时候，将ctx出栈
    private Stack<ParserRuleContext> crudCtxStack = new Stack<ParserRuleContext>();
    //在exitCrud，ctx出栈前，将当前栈的表和字段信息保存 
    public ParseTreePropertyExt<TableAndColumnCache> crudTraceCache = new ParseTreePropertyExt<TableAndColumnCache>();
    //在exitQuerySpecification，ctx出栈时候，把当前栈的queryTraceCache整理后，输出到其中
    public List<TableAndColumnRelation>  tableAndColumnRelationList = new ArrayList<TableAndColumnRelation>();

    public TraceSqlListener(EntireSqlParser parser){
        this.parser = parser;
        tokenStream = parser.getTokenStream();
    }

    private TableAndColumnCache getTableAndColumnCache(){
        try{
            ParserRuleContext crudCtx = crudCtxStack.peek();
            TableAndColumnCache tableAndColumnCache = crudTraceCache.get(crudCtx);
            if(tableAndColumnCache == null){
                tableAndColumnCache = new TableAndColumnCache();
            }
            crudTraceCache.put(crudCtx,tableAndColumnCache);

            return tableAndColumnCache;

        }catch (EmptyStackException e){
            //说明ctxStack目前是空的
            return null;
        }
    }

    public void cache2RelationList(TableAndColumnCache tableAndColumnCache,TableOpt tableOpt){
        for (TableInfo tableInfo:tableAndColumnCache.tableInfoList){
            TableAndColumnRelation tableAndColumnRelation = new TableAndColumnRelation();
            tableAndColumnRelation = new TableAndColumnRelation();
            tableAndColumnRelation.tableName = tableInfo.tableName;
            tableAndColumnRelation.tableOpt = tableOpt;
            tableAndColumnRelation.isSubQuery =  tableInfo.isSubQuery;
            for(ColumnInfo columnInfo:tableAndColumnCache.columnInfoList){
                if(columnInfo.belongToTableAlias.equals(tableInfo.tableAlias)||
                        columnInfo.belongToTableAlias.equals(tableInfo.tableName) ||
                        tableAndColumnCache.tableInfoList.size() == 1){
                    tableAndColumnRelation.columnList.add(columnInfo.colunmName);
                }
            }
            tableAndColumnRelationList.add(tableAndColumnRelation);
        }
    }

    public void clear(){
        crudCtxStack.clear();
        crudTraceCache.clear();
        tableAndColumnRelationList.clear();
    }

    ////////select部分//////////////////////////////////////////////////////////////////////////////////
    /**
     * 开始解析一个select查询语句
     * @param ctx
     */
    @Override
    public void enterQuerySpecification(EntireSqlParser.QuerySpecificationContext ctx) {
        crudCtxStack.push(ctx);
    }

    /**
     * 结束解析一个select查询语句
     * @param ctx
     */
    @Override
    public void exitQuerySpecification(EntireSqlParser.QuerySpecificationContext ctx) {
        ParserRuleContext queryCtx = crudCtxStack.pop();
        if(queryCtx!=ctx){
            throw new HsqlRecognitionException("exitQuerySpecification不符合深度遍历的规则");
        }
        TableAndColumnCache tableAndColumnCache = crudTraceCache.removeFrom(ctx);
        cache2RelationList(tableAndColumnCache,TableOpt.SELECT);
    }

    /**
     * 把select语句中的字段名信息放到crudTraceCache中
     * @param ctx
     */
    @Override
    public void enterColumnReference(EntireSqlParser.ColumnReferenceContext ctx) {


        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            if(!(ctx.getParent() instanceof EntireSqlParser.DereferenceContext)){
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.colunmName = ctx.identifier().getText();
                tableAndColumnCache.columnInfoList.add(columnInfo);
            }
        }


    }

    /**
     * 把select语句中的字段名信息放到crudTraceCache中
     * @param ctx
     */
    @Override
    public void enterDereference(EntireSqlParser.DereferenceContext ctx) {
        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.belongToTableAlias = ctx.base.getText();
            columnInfo.colunmName=ctx.fieldName.getText();

            tableAndColumnCache.columnInfoList.add(columnInfo);
        }
    }

    /**
     * 把select语句中的*字段信息放到crudTraceCache中
     * @param ctx

    @Override
    public void enterSelectAll(EntireSqlParser.SelectAllContext ctx) {
        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            ColumnInfo columnInfo = new ColumnInfo();
            if(ctx.qualifiedName() != null){
                columnInfo.belongToTableAlias = ctx.qualifiedName().getText();
            }
            columnInfo.colunmName = "*";
            tableAndColumnCache.columnInfoList.add(columnInfo);
        }
    }*/


    /**
     * 把select语句中的表名信息放到crudTraceCache中
     * @param ctx
     */
    @Override
    public void enterTableName(EntireSqlParser.TableNameContext ctx) {
        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = ctx.getText();
            EntireSqlParser.IdentifierContext identifier = ((EntireSqlParser.AliasedRelationContext) ctx.getParent()).identifier();
            if(identifier !=null ){
                tableInfo.tableAlias = identifier.getText();
            }

            tableAndColumnCache.tableInfoList.add(tableInfo);
        }
    }

    /**
     * 把select语句中的子查询形式的表名信息放到crudTraceCache中
     * @param ctx
     */
    @Override public void enterSubqueryRelation(EntireSqlParser.SubqueryRelationContext ctx) {
        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = tokenStream.getText(ctx);
            tableInfo.isSubQuery = true;
            EntireSqlParser.IdentifierContext identifier = ((EntireSqlParser.AliasedRelationContext) ctx.getParent()).identifier();
            if(identifier !=null ){
                tableInfo.tableAlias = identifier.getText();
            }

            tableAndColumnCache.tableInfoList.add(tableInfo);
        }
    }

    ////////create部分//////////////////////////////////////////////////////////////////////////////////
    /**
     * 开始解析一个create table语句
     * @param ctx
     */
    @Override
    public void enterCreateTable(EntireSqlParser.CreateTableContext ctx) {
        crudCtxStack.push(ctx);

        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = ctx.qualifiedName().getText();
            tableAndColumnCache.tableInfoList.add(tableInfo);
        }

    }

    /**
     * 结束解析一个create table语句
     * @param ctx
     */
    @Override
    public void exitCreateTable(EntireSqlParser.CreateTableContext ctx) {
        ParserRuleContext queryCtx = crudCtxStack.pop();
        if(queryCtx!=ctx){
            throw new HsqlRecognitionException("exitCreateTableAsSelect不符合深度遍历的规则");
        }
        TableAndColumnCache tableAndColumnCache = crudTraceCache.removeFrom(ctx);
        cache2RelationList(tableAndColumnCache,TableOpt.CREATE_TABLE);
    }

    /**
     * 开始解析一个create table as语句
     * @param ctx
     */
    @Override
    public void enterCreateTableAsSelect(EntireSqlParser.CreateTableAsSelectContext ctx) {
        crudCtxStack.push(ctx);

        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = ctx.qualifiedName().getText();
            tableAndColumnCache.tableInfoList.add(tableInfo);
        }
    }

    /**
     * 结束解析一个create table as语句
     * @param ctx
     */
    @Override
    public void exitCreateTableAsSelect(EntireSqlParser.CreateTableAsSelectContext ctx) {
        ParserRuleContext crudCtx = crudCtxStack.pop();
        if(crudCtx!=ctx){
            throw new HsqlRecognitionException("exitCreateTableAsSelect不符合深度遍历的规则");
        }
        TableAndColumnCache tableAndColumnCache = crudTraceCache.removeFrom(ctx);
        cache2RelationList(tableAndColumnCache,TableOpt.CREATE_TABLE);
    }

    /**
     * create table时候用到的字段
     * @param ctx
     */
    @Override
    public void enterTableElement(EntireSqlParser.TableElementContext ctx) {
        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            if(ctx.columnDefinition()!=null){
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.colunmName = ctx.columnDefinition().columnName.getText();
                tableAndColumnCache.columnInfoList.add(columnInfo);
            }
        }
    }


    ////////delete部分//////////////////////////////////////////////////////////////////////////////////
    /**
     * 开始解析一个delete语句
     * @param ctx
     */
    @Override
    public void enterDelete(EntireSqlParser.DeleteContext ctx) {
        crudCtxStack.push(ctx);

        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = ctx.qualifiedName().getText();
            tableAndColumnCache.tableInfoList.add(tableInfo);
        }
    }

    /**
     * 结束解析一个delete语句
     * @param ctx
     */
    @Override
    public void exitDelete(EntireSqlParser.DeleteContext ctx) {
        ParserRuleContext crudCtx = crudCtxStack.pop();
        if(crudCtx!=ctx){
            throw new HsqlRecognitionException("exitDelete不符合深度遍历的规则");
        }
        TableAndColumnCache tableAndColumnCache = crudTraceCache.removeFrom(ctx);
        cache2RelationList(tableAndColumnCache,TableOpt.DELETE);
    }

    ////////update部分//////////////////////////////////////////////////////////////////////////////////
    /**
     * 开始解析一个update语句
     * @param ctx
     */
    @Override
    public void enterUpdate(EntireSqlParser.UpdateContext ctx) {
        crudCtxStack.push(ctx);

        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = ctx.qualifiedName().getText();
            tableAndColumnCache.tableInfoList.add(tableInfo);
        }
    }

    /**
     * 结束解析一个update语句
     * @param ctx
     */
    @Override
    public void exitUpdate(EntireSqlParser.UpdateContext ctx) {
        ParserRuleContext crudCtx = crudCtxStack.pop();
        if(crudCtx!=ctx){
            throw new HsqlRecognitionException("exitUpdate不符合深度遍历的规则");
        }
        TableAndColumnCache tableAndColumnCache = crudTraceCache.removeFrom(ctx);
        cache2RelationList(tableAndColumnCache,TableOpt.UPDATE);
    }

    ////////insert部分//////////////////////////////////////////////////////////////////////////////////
    /**
     * 开始解析一个insert into t(c1,c2,c3) values(1,2,3)语句
     * @param ctx
     */
    @Override
    public void enterInsertInto(EntireSqlParser.InsertIntoContext ctx) {
        crudCtxStack.push(ctx);

        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = ctx.qualifiedName().getText();
            tableAndColumnCache.tableInfoList.add(tableInfo);
        }
    }


    /**
     * 结束解析一个insert into t(c1,c2,c3) values(1,2,3)语句
     * @param ctx
     */
    @Override
    public void exitInsertInto(EntireSqlParser.InsertIntoContext ctx) {
        ParserRuleContext crudCtx = crudCtxStack.pop();
        if(crudCtx!=ctx){
            throw new HsqlRecognitionException("exitInsertInto不符合深度遍历的规则");
        }
        TableAndColumnCache tableAndColumnCache = crudTraceCache.removeFrom(ctx);
        cache2RelationList(tableAndColumnCache,TableOpt.INSERT);
    }

    /**
     * 开始解析一个insert into t  select c1,c2 from t语句
     * @param ctx
     */
    @Override
    public void enterInsertIntoSelect(EntireSqlParser.InsertIntoSelectContext ctx) {
        crudCtxStack.push(ctx);

        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = ctx.qualifiedName().getText();
            tableAndColumnCache.tableInfoList.add(tableInfo);
        }
    }

    /**
     * 结束解析一个insert into t  select c1,c2 from t语句
     * @param ctx
     */
    @Override
    public void exitInsertIntoSelect(EntireSqlParser.InsertIntoSelectContext ctx) {
        ParserRuleContext crudCtx = crudCtxStack.pop();
        if(crudCtx!=ctx){
            throw new HsqlRecognitionException("exitInsertIntoSelect不符合深度遍历的规则");
        }
        TableAndColumnCache tableAndColumnCache = crudTraceCache.removeFrom(ctx);
        cache2RelationList(tableAndColumnCache,TableOpt.INSERT);
    }


    /**
     * insert时候用到的字段
     * @param ctx
     */
    @Override
    public void enterColumnAliases(EntireSqlParser.ColumnAliasesContext ctx) {
        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            List<EntireSqlParser.IdentifierContext> identifierList = ctx.identifier();
            for(EntireSqlParser.IdentifierContext identifier:identifierList){
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.colunmName = identifier.getText();
                tableAndColumnCache.columnInfoList.add(columnInfo);
            }
        }
    }

    ////////drop table部分//////////////////////////////////////////////////////////////////////////////////

    /**
     * 开始解析一个drop语句
     * @param ctx
     */
    @Override
    public void enterDropTable(EntireSqlParser.DropTableContext ctx) {
        crudCtxStack.push(ctx);

        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = ctx.qualifiedName().getText();
            tableAndColumnCache.tableInfoList.add(tableInfo);
        }
    }

    /**
     * 结束解析一个drop语句
     * @param ctx
     */
    @Override
    public void exitDropTable(EntireSqlParser.DropTableContext ctx) {
        ParserRuleContext crudCtx = crudCtxStack.pop();
        if(crudCtx!=ctx){
            throw new HsqlRecognitionException("exitDropTable不符合深度遍历的规则");
        }
        TableAndColumnCache tableAndColumnCache = crudTraceCache.removeFrom(ctx);
        cache2RelationList(tableAndColumnCache,TableOpt.DROP_TABLE);
    }

    ////////drop table部分//////////////////////////////////////////////////////////////////////////////////
    /**
     * 开始解析一个truncate语句
     * @param ctx
     */
    @Override
    public void enterTruncateTable(EntireSqlParser.TruncateTableContext ctx) {
        crudCtxStack.push(ctx);

        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = ctx.qualifiedName().getText();
            tableAndColumnCache.tableInfoList.add(tableInfo);
        }
    }

    /**
     * 结束解析一个truncate语句
     * @param ctx
     */
    @Override
    public void exitTruncateTable(EntireSqlParser.TruncateTableContext ctx) {
        ParserRuleContext crudCtx = crudCtxStack.pop();
        if(crudCtx!=ctx){
            throw new HsqlRecognitionException("exitTruncateTable不符合深度遍历的规则");
        }
        TableAndColumnCache tableAndColumnCache = crudTraceCache.removeFrom(ctx);
        cache2RelationList(tableAndColumnCache,TableOpt.TRUNCATE);
    }

    ////////add column部分//////////////////////////////////////////////////////////////////////////////////
    /**
     * 开始解析一个add column语句
     * @param ctx
     */
    @Override
    public void enterAddColumn(EntireSqlParser.AddColumnContext ctx) {
        crudCtxStack.push(ctx);

        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = ctx.qualifiedName().getText();
            tableAndColumnCache.tableInfoList.add(tableInfo);

            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.colunmName = ctx.columnDefinition().columnName.getText();
            columnInfo.belongToTableAlias = ctx.qualifiedName().getText();
            tableAndColumnCache.columnInfoList.add(columnInfo);
        }
    }

    /**
     * 结束解析一个add column语句
     * @param ctx
     */
    @Override
    public void exitAddColumn(EntireSqlParser.AddColumnContext ctx) {
        ParserRuleContext crudCtx = crudCtxStack.pop();
        if(crudCtx!=ctx){
            throw new HsqlRecognitionException("exitAddColumn不符合深度遍历的规则");
        }
        TableAndColumnCache tableAndColumnCache = crudTraceCache.removeFrom(ctx);
        cache2RelationList(tableAndColumnCache,TableOpt.ADD_COLUMN);
    }

    ////////drop column部分//////////////////////////////////////////////////////////////////////////////////
    /**
     * 开始解析一个drop column语句
     * @param ctx
     */
    @Override
    public void enterDropColumn(EntireSqlParser.DropColumnContext ctx) {
        crudCtxStack.push(ctx);

        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = ctx.tableName.getText();
            tableAndColumnCache.tableInfoList.add(tableInfo);

            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.colunmName = ctx.column.getText();
            columnInfo.belongToTableAlias = ctx.tableName.getText();
            tableAndColumnCache.columnInfoList.add(columnInfo);
        }
    }

    /**
     * 结束解析一个drop column语句
     * @param ctx
     */
    @Override
    public void exitDropColumn(EntireSqlParser.DropColumnContext ctx) {
        ParserRuleContext crudCtx = crudCtxStack.pop();
        if(crudCtx!=ctx){
            throw new HsqlRecognitionException("exitDropColumn不符合深度遍历的规则");
        }
        TableAndColumnCache tableAndColumnCache = crudTraceCache.removeFrom(ctx);
        cache2RelationList(tableAndColumnCache,TableOpt.DROP_COLUMN);
    }

    ////////rename column部分//////////////////////////////////////////////////////////////////////////////////

    /**
     * 开始解析一个rename column语句
     * @param ctx
     */
    @Override
    public void enterRenameColumn(EntireSqlParser.RenameColumnContext ctx) {
        crudCtxStack.push(ctx);

        TableAndColumnCache tableAndColumnCache = getTableAndColumnCache();
        if(tableAndColumnCache!=null){
            TableInfo tableInfo = new TableInfo();
            tableInfo.tableName = ctx.tableName.getText();
            tableAndColumnCache.tableInfoList.add(tableInfo);

            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.colunmName = ctx.from.getText();
            columnInfo.belongToTableAlias = ctx.tableName.getText();
            tableAndColumnCache.columnInfoList.add(columnInfo);
        }
    }
    /**
     * 结束解析一个rename column语句
     * @param ctx
     */
    @Override
    public void exitRenameColumn(EntireSqlParser.RenameColumnContext ctx) {
        ParserRuleContext crudCtx = crudCtxStack.pop();
        if(crudCtx!=ctx){
            throw new HsqlRecognitionException("exitRenameColumn不符合深度遍历的规则");
        }
        TableAndColumnCache tableAndColumnCache = crudTraceCache.removeFrom(ctx);
        cache2RelationList(tableAndColumnCache,TableOpt.ALTER);
    }

}
