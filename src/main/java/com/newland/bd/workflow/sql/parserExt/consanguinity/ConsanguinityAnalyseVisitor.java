package com.newland.bd.workflow.sql.parserExt.consanguinity;

import com.newland.bd.workflow.sql.bean.consanguinity.*;
import com.newland.bd.workflow.sql.parser.EntireSqlBaseVisitor;
import com.newland.bd.workflow.sql.parser.EntireSqlParser;
import com.newland.bd.workflow.sql.parserExt.HsqlRecognitionException;
import org.antlr.v4.runtime.TokenStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Create by Howard on 0/19
 */
public class ConsanguinityAnalyseVisitor extends EntireSqlBaseVisitor<Object> {
    private enum Status{
        SELECTITEM,
        TABLE,
        FUNCTION,
        UPDATE_SET_KEY,
        UPDATE_SET_VALUE,
        INSERT_KEY,
        INSERT_VALUE
    }

    private TokenStream tokenStream;
    private String hiveMultiInsertFromTableName = null;  //hiveMultiInsert中from后面跟者的表名
    private List<TableBean> dstTableBeanList = new ArrayList<>(); //update的表信息，存放到TableBean中
    private List<TableBean> curQueryTableBeansList = null;  //在当前的QuerySpecification中的List<TableBean>
    private Stack<List<TableBean>> queryTableBeansListStack = new Stack<>();
    private Stack<Status> statusStack = new Stack<Status>();
    public String srcDqlWhereExpression = "";    //距离目标表最近的dql语句where条件后面的内容
    private List<TableBean> nameQueryTableBeansList = new ArrayList<>();


    private List<ColumnBean> createColumnBeanList(){
        return new ArrayList<ColumnBean>();
    }
    private List<TableBean> createTableBeanList(){
        return new ArrayList<TableBean>();
    }

    public ConsanguinityAnalyseVisitor(EntireSqlParser parser){
        tokenStream = parser.getTokenStream();
    }

    /**
     * 单条sql语句的入口
     * @param ctx
     * @return
     */
    @Override
    public Object visitSingleStatement(EntireSqlParser.SingleStatementContext ctx) {
        nameQueryTableBeansList.clear();
        Object object =  visitChildren(ctx);
        if(object instanceof  DstTableDetailBeanList){
            return object;
        }

        DstTableDetailBeanList dstTableDetailBeans = new DstTableDetailBeanList();

        if(object instanceof DstTableDetailBean){
            DstTableDetailBean dstTableDetailBean=(DstTableDetailBean)object;
            dstTableDetailBean.srcDqlWhereExpression = srcDqlWhereExpression;
            dstTableDetailBeans.add(dstTableDetailBean);
            return dstTableDetailBeans;
        }


        DstTableDetailBean dstTableDetailBean = new DstTableDetailBean();
        //dstTableDetailBean.tablename = "no source table";
        if(object instanceof List){
            dstTableDetailBean.sourceColumnBeanList = (List<ColumnBean>)object;
        }

        dstTableDetailBeans.add(dstTableDetailBean);

        return dstTableDetailBeans;
    }

    ///--update语句的入口////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * UPDATE tableName=qualifiedName SET
     *         primaryExpression EQ valueExpression
     *         (',' primaryExpression EQ valueExpression)*
     *         (WHERE booleanExpression)?
     * @param ctx
     * @return
     */
    @Override
    public Object visitUpdate(EntireSqlParser.UpdateContext ctx) {

        DstTableDetailBean dstTableDetailBean = new DstTableDetailBean();
        dstTableDetailBean.tablename = ctx.tableName.getText();
        dstTableDetailBean.sourceColumnBeanList = new ArrayList<>();

        TableBean tableBean = new TableBean();
        tableBean.tableName = dstTableDetailBean.tablename;
        dstTableBeanList.add(tableBean);


        List<List<String>> primaryExpressionList = new ArrayList<>();
        for(EntireSqlParser.PrimaryExpressionContext keyOfSet:ctx.primaryExpression()){
            statusStack.push(Status.UPDATE_SET_KEY);
            Object visit = visit(keyOfSet);
            statusStack.pop();
            List<String> dstColumnNameList = new ArrayList<>();
            if(visit instanceof List){  //返回的是List<ColumnBean>
                for(Object object:(List)visit){
                    ColumnBean columnBean = (ColumnBean)object;
                    dstColumnNameList.add(columnBean.columnName);
                }
            }
            if(visit instanceof ColumnBean){//返回的是ColumnBean
                ColumnBean columnBean = (ColumnBean)visit;
                dstColumnNameList.add(columnBean.columnName);
            }
            primaryExpressionList.add(dstColumnNameList);
        }


        List<List<ColumnBean>> valueExpressionList = new ArrayList<>();
        for(EntireSqlParser.ValueExpressionContext valueOfSet:ctx.valueExpression()){
            statusStack.push(Status.UPDATE_SET_VALUE);
            Object visit = visit(valueOfSet);
            statusStack.pop();
            List<ColumnBean> sourceColumnBeanList = new ArrayList<>();
            if(visit instanceof List){  //返回的是List<ColumnBean>
                for(Object object:(List)visit){
                    ColumnBean columnBean = (ColumnBean)object;
                    sourceColumnBeanList.add(columnBean);
                }
            }
            if(visit instanceof ColumnBean){//返回的是ColumnBean
                ColumnBean columnBean = (ColumnBean)visit;
                sourceColumnBeanList.add(columnBean);
            }
            valueExpressionList.add(sourceColumnBeanList);
        }

        for(List<String> primaryExpression:primaryExpressionList){
            dstTableDetailBean.dstColumnNameList.addAll(primaryExpression);
        }
        for(List<ColumnBean> valueExpression:valueExpressionList){
            dstTableDetailBean.sourceColumnBeanList.addAll(valueExpression);
        }

        dstTableBeanList.clear();

        return dstTableDetailBean;
    }

    /**
     *
     * @param ctx
     * @return
     */
    @Override
    public Object visitRowConstructor(EntireSqlParser.RowConstructorContext ctx) {

        List<ColumnBean> columnBeanList = new ArrayList<>();
        for(EntireSqlParser.ExpressionContext expression:ctx.expression()){
            Object visit = visit(expression);
            if(visit instanceof ColumnBean){
                columnBeanList.add((ColumnBean)visit);
            }
            if(visit instanceof List){
                columnBeanList.add(((List<ColumnBean>)visit).get(0));
            }

        }
        return columnBeanList;
    }

    ///--insertIntoValues语句的入口////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * INSERT INTO qualifiedName columnAliases? insertValues
     * @param ctx
     * @return
     */
    @Override
    public Object visitInsertInto(EntireSqlParser.InsertIntoContext ctx) {

        DstTableDetailBean dstTableDetailBean = new DstTableDetailBean();
        dstTableDetailBean.tablename = ctx.qualifiedName().getText();
        TableBean tableBean = new TableBean();
        tableBean.tableName = dstTableDetailBean.tablename;

        dstTableBeanList.add(tableBean);

        if(ctx.columnAliases() != null){
            List<EntireSqlParser.IdentifierContext> identifierList = ctx.columnAliases().identifier();
            for(EntireSqlParser.IdentifierContext identifier : identifierList){
                dstTableDetailBean.dstColumnNameList.add(identifier.getText());
            }
        }

        Object visit = visit(ctx.insertValues());
        if(visit instanceof List){
            dstTableDetailBean.sourceColumnBeanList = (List<ColumnBean>)visit;
        }

        dstTableBeanList.clear();

        return dstTableDetailBean;
    }

    @Override
    public Object visitInsertValues(EntireSqlParser.InsertValuesContext ctx) {

        List<ColumnBean> columnBeanList = createColumnBeanList();
        for(EntireSqlParser.ExpressionContext expression:ctx.expression()){
            statusStack.push(Status.INSERT_VALUE);
            Object object = visit(expression);
            statusStack.pop();
            if(object instanceof ColumnBean){
                columnBeanList.add((ColumnBean)object);
            }else if(object instanceof List){
                columnBeanList.addAll((List<ColumnBean>)object);
            }
            break; //为了保证insert的字段和values后面对象一一对应的关系，只解析一个expression的血缘
        }
        return columnBeanList;
    }



    ///--insertIntoSelect语句的入口////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * {!isHiveType()}? INSERT INTO TABLE? qualifiedName
     *         columnAliases?
     *         query
     * @param ctx
     * @return
     */
    @Override
    public Object visitInsertIntoSelect(EntireSqlParser.InsertIntoSelectContext ctx) {
        //只处理query部分
        List<ColumnBean> columnBeanList = (List<ColumnBean>)visit(ctx.query());

        DstTableDetailBean dstTableDetailBean = new DstTableDetailBean();
        dstTableDetailBean.tablename = ctx.qualifiedName().getText();
        dstTableDetailBean.sourceColumnBeanList = columnBeanList;

        if(ctx.columnAliases() != null){
            List<EntireSqlParser.IdentifierContext> identifierList = ctx.columnAliases().identifier();
            for(EntireSqlParser.IdentifierContext identifier : identifierList){
                dstTableDetailBean.dstColumnNameList.add(identifier.getText());
            }
        }
        return dstTableDetailBean;
    }


    ///--hiveInsertIntoSelect语句的入口////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Object visitHiveInsertIntoSelect(EntireSqlParser.HiveInsertIntoSelectContext ctx) {
        //只处理query部分
        List<ColumnBean> columnBeanList = (List<ColumnBean>)visit(ctx.query());

        DstTableDetailBean dstTableDetailBean = new DstTableDetailBean();
        dstTableDetailBean.tablename = ctx.qualifiedName().getText();
        dstTableDetailBean.sourceColumnBeanList = columnBeanList;

        if(ctx.columnAliases() != null){
            List<EntireSqlParser.IdentifierContext> identifierList = ctx.columnAliases().identifier();
            for(EntireSqlParser.IdentifierContext identifier : identifierList){
                dstTableDetailBean.dstColumnNameList.add(identifier.getText());
            }
        }

        return dstTableDetailBean;
    }



    ///--hiveMultiInsert语句入口////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 解析 FROM tableName=qualifiedName (insertStm)+
     * @param ctx
     * @return
     */
    @Override
    public Object visitHiveMultiInsert(EntireSqlParser.HiveMultiInsertContext ctx) {
        String tablename = ctx.tableName.getText();
        hiveMultiInsertFromTableName = tablename;

        DstTableDetailBeanList dstTableDetailBeanList = new DstTableDetailBeanList();
        List<EntireSqlParser.InsertStmContext> insertStmList = ctx.insertStm();
        for(EntireSqlParser.InsertStmContext insertStm:insertStmList){
            Object visit = visit(insertStm);
            DstTableDetailBean dstTableDetailBean = (DstTableDetailBean)visit;
            dstTableDetailBeanList.add(dstTableDetailBean);
        }

        hiveMultiInsertFromTableName = null;

        return dstTableDetailBeanList;
    }

    ///--createTable语句的入口////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 解析
     * CREATE TABLE (IF NOT EXISTS)? qualifiedName columnAliases?
     *         (COMMENT string)?
     *         (WITH properties)? AS (query | '('query')')
     *         (WITH (NO)? DATA)?
     * @param ctx
     * @return
     */
    @Override
    public Object visitCreateTableAsSelect(EntireSqlParser.CreateTableAsSelectContext ctx) {
        //先处理query部分
        List<ColumnBean> columnBeanList = (List<ColumnBean>)visit(ctx.query());

        DstTableDetailBean dstTableDetailBean = new DstTableDetailBean();
        dstTableDetailBean.tablename = ctx.qualifiedName().getText();
        dstTableDetailBean.sourceColumnBeanList = columnBeanList;

        if(ctx.columnAliases() != null){
            List<EntireSqlParser.IdentifierContext> identifierList = ctx.columnAliases().identifier();
            for(EntireSqlParser.IdentifierContext identifier : identifierList){
                dstTableDetailBean.dstColumnNameList.add(identifier.getText());
            }
        }
        return dstTableDetailBean;
    }

    /**
     * 解析
     * CREATE TABLE (IF NOT EXISTS)? qualifiedName
     *         '(' tableElement (',' tableElement)* ')'
     *          (COMMENT string)?
     *          (WITH properties)?
     * @param ctx
     * @return
     */
    @Override
    public Object visitCreateTable(EntireSqlParser.CreateTableContext ctx) {
        DstTableDetailBean dstTableDetailBean = new DstTableDetailBean();
        dstTableDetailBean.tablename = ctx.qualifiedName().getText();
        dstTableDetailBean.isCreateTableType = true;

        if(ctx.TEMPORARY() != null){
            dstTableDetailBean.isTemporaryTable = true;
        }

        List<EntireSqlParser.TableElementContext> tableElementContexts = ctx.tableElement();
        for(EntireSqlParser.TableElementContext tableElementContext:tableElementContexts){
            EntireSqlParser.ColumnDefinitionContext columnDefinitionContext = tableElementContext.columnDefinition();
            String columnName = columnDefinitionContext.columnName.getText();
            dstTableDetailBean.dstColumnNameList.add(columnName);
        }

        return dstTableDetailBean;
    }


    /**
     * hive数据库create table的语句
     * @param ctx
     * @return
     */
    @Override
    public Object visitHiveCreateTable(EntireSqlParser.HiveCreateTableContext ctx) {

        DstTableDetailBean dstTableDetailBean = new DstTableDetailBean();
        dstTableDetailBean.tablename = ctx.tablename.getText();
        dstTableDetailBean.isCreateTableType = true;
        if(ctx.TEMPORARY() != null){
            dstTableDetailBean.isTemporaryTable = true;
        }

        if(ctx.query() != null){
            List<ColumnBean> columnBeanList = (List<ColumnBean>)visit(ctx.query());
            dstTableDetailBean.sourceColumnBeanList = columnBeanList;
        }

        EntireSqlParser.TableElementsContext tableElementsContext = ctx.tableElements().get(0);
        for(EntireSqlParser.TableElementContext tableElementContext:tableElementsContext.tableElement()){
            EntireSqlParser.ColumnDefinitionContext columnDefinitionContext = tableElementContext.columnDefinition();
            String columnName = columnDefinitionContext.columnName.getText();
            dstTableDetailBean.dstColumnNameList.add(columnName);
        }


        return dstTableDetailBean;
    }


    /**
     * gp数据库create table的语句
     * @param ctx
     * @return
     */
    @Override
    public Object visitGpCreateTable(EntireSqlParser.GpCreateTableContext ctx) {
        DstTableDetailBean dstTableDetailBean = new DstTableDetailBean();
        dstTableDetailBean.tablename = ctx.tablename.getText();
        dstTableDetailBean.isCreateTableType = true;
        if(ctx.TEMPORARY() != null){
            dstTableDetailBean.isTemporaryTable = true;
        }

        if(ctx.query() != null){
            List<ColumnBean> columnBeanList = (List<ColumnBean>)visit(ctx.query());
            dstTableDetailBean.sourceColumnBeanList = columnBeanList;
        }

        EntireSqlParser.TableElementsContext tableElementsContext = ctx.tableElements();
        for(EntireSqlParser.TableElementContext tableElementContext:tableElementsContext.tableElement()){
            EntireSqlParser.ColumnDefinitionContext columnDefinitionContext = tableElementContext.columnDefinition();
            String columnName = columnDefinitionContext.columnName.getText();
            dstTableDetailBean.dstColumnNameList.add(columnName);
        }

        return dstTableDetailBean;
    }

    /**
     * gbase数据库create table的语句
     * @param ctx
     * @return
     */
    @Override
    public Object visitGBaseCreateTableAs(EntireSqlParser.GBaseCreateTableAsContext ctx) {
        //先处理query部分
        List<ColumnBean> columnBeanList = (List<ColumnBean>)visit(ctx.query());

        DstTableDetailBean dstTableDetailBean = new DstTableDetailBean();
        dstTableDetailBean.tablename = ctx.tableName.getText();
        dstTableDetailBean.sourceColumnBeanList = columnBeanList;

        if(ctx.columnAliases() != null){
            List<EntireSqlParser.IdentifierContext> identifierList = ctx.columnAliases().identifier();
            for(EntireSqlParser.IdentifierContext identifier : identifierList){
                dstTableDetailBean.dstColumnNameList.add(identifier.getText());
            }
        }
        return dstTableDetailBean;
    }

    @Override
    public Object visitHiveCreateView(EntireSqlParser.HiveCreateViewContext ctx) {
        DstTableDetailBean dstTableDetailBean = new DstTableDetailBean();
        dstTableDetailBean.tablename = ctx.viewName.getText();
        dstTableDetailBean.isCreateTableType = true;

        if(ctx.query() != null){
            List<ColumnBean> columnBeanList = (List<ColumnBean>)visit(ctx.query());
            dstTableDetailBean.sourceColumnBeanList = columnBeanList;
        }

        List<EntireSqlParser.ColumnDefinitionNoTypeContext> columnDefinitionNoTypeContexts = ctx.columnDefinitionNoType();
        for(EntireSqlParser.ColumnDefinitionNoTypeContext columnDefinitionNoTypeContext:columnDefinitionNoTypeContexts){
            String columnName = columnDefinitionNoTypeContext.identifier().getText();
            dstTableDetailBean.dstColumnNameList.add(columnName);
        }

        return dstTableDetailBean;
    }

    /**
     * db2 create view语句
     * @param ctx
     * @return
     */
    @Override
    public Object visitDb2CreateView(EntireSqlParser.Db2CreateViewContext ctx) {
        DstTableDetailBean dstTableDetailBean = new DstTableDetailBean();
        dstTableDetailBean.tablename = ctx.viewName.getText();
        dstTableDetailBean.isCreateTableType = true;

        if(ctx.query() != null){
            List<ColumnBean> columnBeanList = (List<ColumnBean>)visit(ctx.query());
            dstTableDetailBean.sourceColumnBeanList = columnBeanList;
        }

        List<EntireSqlParser.ColumnDefinitionNoTypeContext> columnDefinitionNoTypeContexts = ctx.columnDefinitionNoType();
        for(EntireSqlParser.ColumnDefinitionNoTypeContext columnDefinitionNoTypeContext:columnDefinitionNoTypeContexts){
            String columnName = columnDefinitionNoTypeContext.identifier().getText();
            dstTableDetailBean.dstColumnNameList.add(columnName);
        }

        return dstTableDetailBean;
    }

    ///--查询语句的入口////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Object visitQuery(EntireSqlParser.QueryContext ctx) {
        EntireSqlParser.WithContext with = ctx.with();
        if(with!=null){
            visit(with);
        }

        EntireSqlParser.QueryNoWithContext queryNoWithContext = ctx.queryNoWith();
        return visit(queryNoWithContext);
    }

    @Override public Object visitWith(EntireSqlParser.WithContext ctx) {
        List<EntireSqlParser.NamedQueryContext> namedQueryContexts = ctx.namedQuery();
        for (EntireSqlParser.NamedQueryContext namedQueryContext : namedQueryContexts) {
            Object nameQuery = visit(namedQueryContext);
            TableBean tableBean = (TableBean) nameQuery;
            nameQueryTableBeansList.add(tableBean);
        }
        return null;
    }

    @Override
    public Object visitNamedQuery(EntireSqlParser.NamedQueryContext ctx) {
        TableBean tableBean = new TableBean();
        String tableAlias = ctx.name.getText();
        Object object = visit(ctx.query());
        if (object instanceof List) {//说明这个table是一个子查询,object一定是一个List<ColumnBean>类型
            //把List<ColumnBean>转换成TableBean
            tableBean.tableType = TableType.SUB_QUERY;
            tableBean.tableName = "<" + tableBean.tableType.toString() + ">";
            tableBean.columnBeans = (List<ColumnBean>) object;
            tableBean.tableAlias = tableAlias;
        }

        return tableBean;
    }

    /**
     * 解析
     * SELECT setQuantifier? selecItem (',' selectItem)*
     *       (FROM relation (',' relation)*)?
     *       (WHERE where=booleanExpression)?
     *       (GROUP BY groupBy)?
     *       (HAVING having=booleanExpression)?
     * @param ctx
     * @return
     */
    @Override
    public Object visitQuerySpecification(EntireSqlParser.QuerySpecificationContext ctx) {

        srcDqlWhereExpression = "";
        List<TableBean> tableBeans = createTableBeanList();
        //开始遍历from后面的表
        for(EntireSqlParser.RelationContext relation:ctx.relation()){
            statusStack.push(Status.TABLE);
            Object visit = visit(relation);
            statusStack.pop();
            if(visit instanceof List){//正常情况下，visit relation一定是返回一个List<TableBean>
                List<TableBean> tableBeans1 = (List<TableBean>) visit;
                tableBeans.addAll(tableBeans1);
            }
        }
        if(tableBeans.size() == 0){
            if(hiveMultiInsertFromTableName != null){
                TableBean tableBean = new TableBean();
                tableBean.tableName = hiveMultiInsertFromTableName;
                tableBeans.add(tableBean);
            }
        }
        queryTableBeansListStack.push(tableBeans);

        curQueryTableBeansList = queryTableBeansListStack.peek();

        List<ColumnBean> columnBeans = createColumnBeanList();
        //开始遍历selectItem
        for(EntireSqlParser.SelectItemContext selectItem:ctx.selectItem()){
            statusStack.push(Status.SELECTITEM);
            Object visit = visit(selectItem);
            statusStack.pop();
            if(visit instanceof  ColumnBean){//正常情况下，每个selectItem，总是返回一个ColumnBean对象
                columnBeans.add((ColumnBean)visit);
            }
        }
        queryTableBeansListStack.pop();
        if(queryTableBeansListStack.size() == 0){
            curQueryTableBeansList = null;
        }else{
            curQueryTableBeansList = queryTableBeansListStack.peek();
        }


        if(ctx.where != null){
            srcDqlWhereExpression = tokenStream.getText(ctx.where);
        }
        return columnBeans;
    }

    ///--查询语句中selectitem域部分的解析////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * select 语句中出现的字段解析
     * @param ctx
     * @return
     */
    @Override
    public Object visitColumnReference(EntireSqlParser.ColumnReferenceContext ctx) {

        if(statusStack.size() > 0 &&
                (statusStack.peek() == Status.SELECTITEM
                || statusStack.peek() == Status.UPDATE_SET_KEY
                || statusStack.peek() == Status.UPDATE_SET_VALUE
                || statusStack.peek() == Status.INSERT_VALUE)){
            ColumnBean columnBean = new ColumnBean();
            columnBean.columnName = ctx.getText();
            columnBean.columnAlias = columnBean.columnName;
            columnBean = ConsanguinityAnalyseUtil.promotionColumnBean(columnBean,curQueryTableBeansList,dstTableBeanList);
            return ConsanguinityAnalyseUtil.promotionColumnBeanWithAs(columnBean, nameQueryTableBeansList);

        }
        return null;
    }

    /**
     * select 语句中出现的字段解析
     * @param ctx
     * @return
     */
    @Override
    public Object visitDereference(EntireSqlParser.DereferenceContext ctx) {
        if(statusStack.size() > 0 &&
                (statusStack.peek() == Status.SELECTITEM
                || statusStack.peek() == Status.UPDATE_SET_KEY
                || statusStack.peek() == Status.UPDATE_SET_VALUE
                || statusStack.peek() == Status.INSERT_VALUE)){
            ColumnBean columnBean = new ColumnBean();
            columnBean.columnName = ctx.fieldName.getText();
            columnBean.columnAlias = columnBean.columnName;
            columnBean.belongToTableAlias = ctx.primaryExpression().getText();
            columnBean = ConsanguinityAnalyseUtil.promotionColumnBean(columnBean,curQueryTableBeansList,dstTableBeanList);
            return ConsanguinityAnalyseUtil.promotionColumnBeanWithAs(columnBean, nameQueryTableBeansList);

        }
        return null;
    }

    /**
     * select 语句中出现的字段解析
     * @param ctx
     * @return
     */
    @Override
    public Object visitNumericLiteral(EntireSqlParser.NumericLiteralContext ctx) {
        if(statusStack.size() > 0 &&
                (statusStack.peek() == Status.SELECTITEM
                || statusStack.peek() == Status.UPDATE_SET_KEY
                || statusStack.peek() == Status.UPDATE_SET_VALUE
                || statusStack.peek() == Status.INSERT_VALUE)){
            ColumnBean columnBean = new ColumnBean();
            columnBean.columnName = ctx.getText();
            columnBean.columnType = ColumnType.NUMERIC;
            return columnBean;
        }
        return null;
    }

    /**
     * select 语句中出现的字段解析
     * @param ctx
     * @return
     */
    @Override
    public Object visitNullLiteral(EntireSqlParser.NullLiteralContext ctx) {
        if(statusStack.size() > 0 &&
                (statusStack.peek() == Status.SELECTITEM
                        || statusStack.peek() == Status.UPDATE_SET_KEY
                        || statusStack.peek() == Status.UPDATE_SET_VALUE
                        || statusStack.peek() == Status.INSERT_VALUE)){
            ColumnBean columnBean = new ColumnBean();
            columnBean.columnName = ctx.getText();
            columnBean.columnType = ColumnType.NULL;
            return columnBean;
        }
        return null;
    }


    /**
     * select 语句中出现的字段解析
     * @param ctx
     * @return
     */
    @Override
    public Object visitStringLiteral(EntireSqlParser.StringLiteralContext ctx) {
        if(statusStack.size() > 0 &&
                (statusStack.peek() == Status.SELECTITEM
                || statusStack.peek() == Status.UPDATE_SET_KEY
                || statusStack.peek() == Status.UPDATE_SET_VALUE
                || statusStack.peek() == Status.INSERT_VALUE)){
            ColumnBean columnBean = new ColumnBean();
            columnBean.columnName = ctx.getText();
            columnBean.columnType = ColumnType.STRING;
            return columnBean;
        }
        return null;
    }


    /**
     * 如果是在Stack.SELECTITEM和Stack.FUNCTION状态下，当booleanExpression返回的是一个List<ColumnBeans>的时候，只取第一个ColumnBeans元素
     * @param ctx
     * @return
     */
    @Override
    public Object visitExpression(EntireSqlParser.ExpressionContext ctx) {
        ColumnBean columnBean = null;
        Object visit  = visit(ctx.booleanExpression());
        if(statusStack.size() > 0 &&
                (statusStack.peek() == Status.SELECTITEM
                || statusStack.peek() == Status.FUNCTION)){
            if(visit instanceof List){//说明当前的expression是一个子查询,我们只能取子查询返回的第一个字段
                List columnBeans = (List) visit;
                //只能取第一个元素
                if(columnBeans.get(0) instanceof ColumnBean){
                    columnBean = (ColumnBean)(columnBeans.get(0));
//                    columnBean.columnType = ColumnType.SUB_QUERY;
                    return columnBean;
                }
            }
        }
        return visit;

    }

    /**
     * 将单目运算符升级为双目运算符
     * @param ctx
     * @return
     */
    @Override
    public Object visitPredicated(EntireSqlParser.PredicatedContext ctx) {
        if(ctx.predicate() != null){
            Object predicate = visit(ctx.predicate());
            ColumnBean columnBean = null;
            if(predicate instanceof ColumnBean){
                columnBean = (ColumnBean) predicate;
            } else if(predicate instanceof List){
                columnBean = ((List<ColumnBean>)predicate).get(0);
            }

            if(columnBean != null){
                if(columnBean.columnType.equals(ColumnType.UNARYOPT)){//单目运算符升级为双目运算符
                    columnBean.columnType = ColumnType.BINARYOPT;
                    Object visit = visit(ctx.valueExpression);
                    if(visit == null){
                        throw new HsqlRecognitionException(ctx.start,ctx.valueExpression.getText()+"解析操作结果为空");
                    }
                    columnBean.relationColumns.add(0,(ColumnBean)visit);

                }
                if(columnBean.columnType.equals(ColumnType.IN_LIST)
                        || columnBean.columnType.equals(ColumnType.BETWEEN)
                        || columnBean.columnType.equals(ColumnType.LIKE)
                        || columnBean.columnType.equals(ColumnType.IS_NULL)){
                    Object visit = visit(ctx.valueExpression);
                    if(visit == null){
                        throw new HsqlRecognitionException(ctx.start,ctx.valueExpression.getText()+"解析操作结果为空");
                    }
                    columnBean.relationColumns.add(0,(ColumnBean)visit);
                }
                return columnBean;
            }

        }
        return visit(ctx.valueExpression);
    }


    @Override
    public Object visitLogicalBinary(EntireSqlParser.LogicalBinaryContext ctx) {
        ColumnBean columnBean = new ColumnBean();
        columnBean.convertRule = ctx.operator.getText();
        columnBean.columnType = ColumnType.BINARYOPT;

        Object left = visit(ctx.left);
        if(left == null){
            throw new HsqlRecognitionException(ctx.left.getText()+"解析操作结果为空");
        }
        Object right = visit(ctx.right);
        if(right == null){
            throw new HsqlRecognitionException(ctx.right.getText()+"解析操作结果为空");
        }
        columnBean.relationColumns = createColumnBeanList();
        columnBean.relationColumns.add((ColumnBean)left);
        columnBean.relationColumns.add((ColumnBean)right);

        return columnBean;
    }




    /**
     * 为ColumnBean补充字段别名
     * @param ctx
     * @return
     */
    @Override
    public Object visitSelectSingle(EntireSqlParser.SelectSingleContext ctx) {

        ColumnBean columnBean = null;

        Object visit = visit(ctx.expression());
        if(visit instanceof  ColumnBean){//说明当前的selectItem只是一个字段，不是子查询
            columnBean = (ColumnBean)visit;
        }

        if(ctx.aliasName()!=null){
            if(columnBean != null){
                columnBean.columnAlias = ctx.aliasName().getText();
            }
        }

        return columnBean;
    }

    ///--查询语句中表名域部分的解析////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 给TableBean补充表名信息
     * @param ctx
     * @return
     */
    @Override
    public Object visitTableName(EntireSqlParser.TableNameContext ctx) {
        TableBean tableBean = new TableBean();
        tableBean.tableName = ctx.qualifiedName().getText();
        return tableBean;
    }

    /**
     * 把子查询返回的List<ColumnBean>转换成TableBean
     * 给TableBean补充表别名信息
     * @param ctx
     * @return
     */
    @Override
    public Object visitAliasedRelation(EntireSqlParser.AliasedRelationContext ctx) {
        Object object = visit(ctx.relationPrimary());
        TableBean tableBean = null;

        if(object instanceof TableBean){
            tableBean = (TableBean) object;
        }
        if(object instanceof List){//说明这个table是一个子查询,object一定是一个List<ColumnBean>类型
            tableBean = new TableBean();
            //把List<ColumnBean>转换成TableBean
            tableBean.tableType = TableType.SUB_QUERY;
            tableBean.tableName = "<"+tableBean.tableType.toString()+">";
            tableBean.columnBeans = (List<ColumnBean>) object;
        }

        //给TableBean补充表别名信息
        if(ctx.identifier()!=null){
            if (tableBean != null){
                tableBean.tableAlias = ctx.identifier().getText();
            }
        }
        return tableBean;
    }

    /**
     * 把TableBean组装成一个List<TableBean>
     * @param ctx
     * @return
     */
    @Override
    public Object visitSampledRelation(EntireSqlParser.SampledRelationContext ctx) {
        Object object = visit(ctx.aliasedRelation());
        if(object instanceof TableBean){
            List<TableBean> tableBeanList = createTableBeanList();
            tableBeanList.add((TableBean)object);
            return tableBeanList;
        }
        return null;
    }


    /**
     * 把所有的join关联的表组合成一个新的List<TableBean>
     * @param ctx
     * @return
     */
    @Override
    public Object visitJoinRelation(EntireSqlParser.JoinRelationContext ctx) {
        List<TableBean> leftTableBeans = null;
        Object relation = visit(ctx.left);
        if(relation instanceof List){
            leftTableBeans = (List<TableBean>) relation;
        }

        List<TableBean> rightTableBeans = null;
        relation = visit(ctx.sampledRelation());
        if(relation instanceof List){
            rightTableBeans = (List<TableBean>) relation;
        }

        List<TableBean> tableBeanList = createTableBeanList();
        if(leftTableBeans!=null){
            tableBeanList.addAll(leftTableBeans);
        }
        if(rightTableBeans!=null){
            tableBeanList.addAll(rightTableBeans);
        }

        return tableBeanList;
    }

    ///--UNION、EXCEPT类型的解析////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Object visitSetOperation(EntireSqlParser.SetOperationContext ctx) {
        List<ColumnBean> leftColumnList = null;
        List<ColumnBean> rightColumnList = null;

        Object left = visit(ctx.left);
        if(left instanceof  List){
            leftColumnList = (List<ColumnBean>) left;
        }

        Object right = visit(ctx.right);
        if(right instanceof  List){
            rightColumnList = (List<ColumnBean>) right;
        }

        List<ColumnBean> newColumnBeanList = createColumnBeanList();
        if(leftColumnList!= null && leftColumnList.size()>0){
            for(int i = 0; i< leftColumnList.size(); i++){
                if(rightColumnList != null && rightColumnList.size() > i){
                    ColumnBean columnBean = new ColumnBean();
                    columnBean.columnName = leftColumnList.get(i).columnName;   //merge类型的columnBean的字段名继承merge左边的字段名
                    columnBean.columnAlias = leftColumnList.get(i).columnAlias; //merge类型的columnBean的字段别名继承merge左边的字段别名
                    columnBean.columnType = ColumnType.MERGE;
                    columnBean.convertRule = ctx.operator.getText() +  (ctx.setQuantifier()==null?"":" "+ctx.setQuantifier().getText());
                    columnBean.relationColumns = createColumnBeanList();
                    columnBean.relationColumns.add(leftColumnList.get(i));
                    columnBean.relationColumns.add(rightColumnList.get(i));
                    newColumnBeanList.add(columnBean);
                }
            }
        }
        return newColumnBeanList;
    }

    ///--字段是函数类型的解析////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Object visitFunctionCall(EntireSqlParser.FunctionCallContext ctx) {

        ColumnBean columnBean = null;
        if (statusStack.size() > 0 &&
                (statusStack.peek() == Status.SELECTITEM
                || statusStack.peek() == Status.UPDATE_SET_VALUE
                || statusStack.peek() == Status.INSERT_VALUE)){
            columnBean = new ColumnBean();
            columnBean.columnType = ColumnType.FUNCTION;
            columnBean.belongToTableName = "<NONE>";
            columnBean.belongToTableAlias = "<NONE>";
            columnBean.convertRule = ctx.qualifiedName().getText();

            columnBean.relationColumns = new ArrayList<ColumnBean>();
            if(ctx.ASTERISK() != null){//func(*)的形式
                ColumnBean columnBean1 = new ColumnBean();
                columnBean1.columnName = "*";
                columnBean1 = ConsanguinityAnalyseUtil.promotionColumnBean(columnBean1,curQueryTableBeansList,dstTableBeanList);
                columnBean.relationColumns.add(ConsanguinityAnalyseUtil.promotionColumnBeanWithAs(columnBean1, nameQueryTableBeansList));
            }else{//func(c1,c2,.....)的形式
                if(ctx.expression()!=null){
                    for(EntireSqlParser.ExpressionContext expression:ctx.expression() ){
                        Object object = visit(expression);
                        if(object instanceof  ColumnBean){
                            columnBean.relationColumns.add((ColumnBean)object);
                        }
                    }
                }
            }
        }
        return columnBean;
    }

    /**
     * 把grouping当作函数来处理
     * @param ctx
     * @return
     */
    @Override
    public Object visitGroupingOperation(EntireSqlParser.GroupingOperationContext ctx) {
        ColumnBean columnBean = null;
        if (statusStack.size() > 0 &&
                (statusStack.peek() == Status.SELECTITEM
                        || statusStack.peek() == Status.UPDATE_SET_VALUE
                        || statusStack.peek() == Status.INSERT_VALUE)){
            columnBean = new ColumnBean();
            columnBean.columnType = ColumnType.FUNCTION;
            columnBean.belongToTableName = "<NONE>";
            columnBean.belongToTableAlias = "<NONE>";
            columnBean.convertRule = ctx.GROUPING().getText();

            columnBean.relationColumns = new ArrayList<ColumnBean>();
            if(ctx.qualifiedName()!=null){
                for(EntireSqlParser.QualifiedNameContext qualifiedName:ctx.qualifiedName() ){
                    Object object = visit(qualifiedName);
                    if(object instanceof  ColumnBean){
                        columnBean.relationColumns.add((ColumnBean)object);
                    }
                }
            }
        }
        return columnBean;
    }

    @Override
    public Object visitFunctionCallListAgg(EntireSqlParser.FunctionCallListAggContext ctx) {
        ColumnBean columnBean = null;
        if (statusStack.size() > 0 && statusStack.peek() == Status.SELECTITEM){
            columnBean = new ColumnBean();
            columnBean.columnType = ColumnType.FUNCTION;
            columnBean.belongToTableName = "<NONE>";
            columnBean.belongToTableAlias = "<NONE>";
            columnBean.convertRule = ctx.LISTAGG().getText();
            columnBean.relationColumns = new ArrayList<ColumnBean>();

            if(ctx.expression(0)!=null){
                Object object = visit(ctx.expression(0));
                if(object instanceof  ColumnBean){
                    columnBean.relationColumns.add((ColumnBean)object);
                }
            }

            if(ctx.primaryExpression()!=null){
                Object object = visit(ctx.primaryExpression());
                if(object instanceof  ColumnBean){
                    columnBean.relationColumns.add((ColumnBean)object);
                }
            }
        }
        return columnBean;
    }

    private Object vistitFunctionTemplate(List<EntireSqlParser.ExpressionContext> expressionLst, String functionName){
        ColumnBean columnBean = null;
        if (statusStack.size() > 0 && statusStack.peek() == Status.SELECTITEM){
            columnBean = new ColumnBean();
            columnBean.columnType = ColumnType.FUNCTION;
            columnBean.belongToTableName = "<NONE>";
            columnBean.belongToTableAlias = "<NONE>";
            columnBean.convertRule = functionName;
            columnBean.relationColumns = new ArrayList<ColumnBean>();

            for(EntireSqlParser.ExpressionContext exp: expressionLst ){
                Object object = visit(exp);
                if(object instanceof  ColumnBean){
                    columnBean.relationColumns.add((ColumnBean)object);
                }
            }
        }
        return columnBean;
    }

    @Override
    public Object visitGpOverLayFunction(EntireSqlParser.GpOverLayFunctionContext ctx) {
        return vistitFunctionTemplate(ctx.expression(), ctx.OVERLAY().getText());
    }

    @Override
    public Object visitGpTrimFunction(EntireSqlParser.GpTrimFunctionContext ctx) {
        return vistitFunctionTemplate(ctx.expression(), ctx.TRIM().getText());
    }

    @Override
    public Object visitGpExtractFunction(EntireSqlParser.GpExtractFunctionContext ctx) {
        return vistitFunctionTemplate(Arrays.asList(ctx.expression()), ctx.EXTRACT().getText());
    }




    ///--when case解析////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Object visitSimpleCase(EntireSqlParser.SimpleCaseContext ctx) {
        ColumnBean columnBean = new ColumnBean();
        columnBean.columnType = ColumnType.CASE;
        columnBean.caseWhenColumnBean = new CaseWhenColumnBean();

        Object visit = visit(ctx.valueExpression());
        if(visit instanceof ColumnBean){
            columnBean.caseWhenColumnBean.caseExpression = (ColumnBean)visit;
        }
        for(EntireSqlParser.WhenClauseContext whenClauseContext:ctx.whenClause()){
            Object whenClause = visit(whenClauseContext);
            if(whenClause instanceof ColumnBean){
                columnBean.caseWhenColumnBean.whenClauses.add((ColumnBean)whenClause);
            }
        }
        if(ctx.elseExpression != null){
            Object elseExpression = visit(ctx.elseExpression);
            if(elseExpression instanceof ColumnBean){
                columnBean.caseWhenColumnBean.elseExpression = (ColumnBean)elseExpression;
            }
        }

        return columnBean;
    }

    @Override
    public Object visitSearchedCase(EntireSqlParser.SearchedCaseContext ctx) {
        ColumnBean columnBean = new ColumnBean();
        columnBean.columnType = ColumnType.CASE;
        columnBean.caseWhenColumnBean = new CaseWhenColumnBean();

        for(EntireSqlParser.WhenClauseContext whenClauseContext:ctx.whenClause()){
            Object whenClause = visit(whenClauseContext);
            if(whenClause instanceof ColumnBean){
                columnBean.caseWhenColumnBean.whenClauses.add((ColumnBean)whenClause);
            }
        }
        if(ctx.elseExpression != null){
            Object elseExpression = visit(ctx.elseExpression);
            if(elseExpression instanceof ColumnBean){
                columnBean.caseWhenColumnBean.elseExpression = (ColumnBean)elseExpression;
            }
        }

        return columnBean;
    }

    @Override
    public Object visitWhenClause(EntireSqlParser.WhenClauseContext ctx) {
        ColumnBean columnBean = new ColumnBean();
        columnBean.columnType = ColumnType.WHEN_THEN;
        Object condition = visit(ctx.condition);
        Object result = visit(ctx.result);
        columnBean.relationColumns = createColumnBeanList();
        columnBean.relationColumns.add((ColumnBean)condition);
        columnBean.relationColumns.add((ColumnBean)result);


        return columnBean;
    }




    ///--字段是连接类型的解析，目前包括的函数是有visitArithmeticUnary、visitArithmeticBinary、visitConcatenation、visitComparison////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Object visitArithmeticUnary(EntireSqlParser.ArithmeticUnaryContext ctx) {
        Object visit = visit(ctx.valueExpression());
        if(visit instanceof  ColumnBean){
            ColumnBean columnBean = new ColumnBean();
            columnBean.columnType = ColumnType.UNARYOPT;
            columnBean.convertRule = ctx.operator.getText();
            columnBean.relationColumns = createColumnBeanList();
            columnBean.relationColumns.add((ColumnBean)visit);

            return columnBean;
        }

        return null;
    }

    @Override public Object visitArithmeticBinary(EntireSqlParser.ArithmeticBinaryContext ctx) {
        Object leftVisit = visit(ctx.left);
        Object rightVisit = visit(ctx.right);
        if(leftVisit instanceof  ColumnBean  || rightVisit instanceof  ColumnBean){
            ColumnBean columnBean = new ColumnBean();
            columnBean.columnType = ColumnType.BINARYOPT;
            columnBean.convertRule = ctx.operator.getText();
            columnBean.relationColumns = createColumnBeanList();
            columnBean.relationColumns.add((ColumnBean)leftVisit);
            columnBean.relationColumns.add((ColumnBean)rightVisit);

            return columnBean;
        }

        return null;
    }

    @Override
    public Object visitConcatenation(EntireSqlParser.ConcatenationContext ctx) {
        Object leftVisit = visit(ctx.left);
        Object rightVisit = visit(ctx.right);
        if(leftVisit instanceof  ColumnBean  || rightVisit instanceof  ColumnBean){
            ColumnBean columnBean = new ColumnBean();
            columnBean.columnType = ColumnType.BINARYOPT;
            columnBean.convertRule = ctx.CONCAT().getText();
            columnBean.relationColumns = createColumnBeanList();
            columnBean.relationColumns.add((ColumnBean)leftVisit);
            columnBean.relationColumns.add((ColumnBean)rightVisit);

            return columnBean;
        }

        return null;
    }

    ///--predicate作用域的解析/////////////////////////////////////////////////////////
    @Override
    public Object visitComparison(EntireSqlParser.ComparisonContext ctx) {
        Object visit = visit(ctx.valueExpression());
        if(visit instanceof  ColumnBean){
            ColumnBean columnBean = new ColumnBean();
            columnBean.columnType = ColumnType.UNARYOPT;
            columnBean.convertRule = ctx.comparisonOperator().getText();
            columnBean.relationColumns = createColumnBeanList();
            columnBean.relationColumns.add((ColumnBean)visit);

            return columnBean;
        }
        if(visit instanceof  List){  //比较操作符的右侧，如果返回的是List<ColumnBean>，暂时只返回第一个ColumnBean元素
            ColumnBean columnBean = new ColumnBean();
            columnBean.columnType = ColumnType.UNARYOPT;
            columnBean.convertRule = ctx.comparisonOperator().getText();
            columnBean.relationColumns = createColumnBeanList();
            columnBean.relationColumns.add(((List<ColumnBean>)visit).get(0));

            return columnBean;
        }

        return null;
    }

    @Override
    public Object visitQuantifiedComparison(EntireSqlParser.QuantifiedComparisonContext ctx) {
        Object visit = visit(ctx.query());
        if(visit instanceof  ColumnBean){
            ColumnBean columnBean = new ColumnBean();
            columnBean.columnType = ColumnType.UNARYOPT;
            columnBean.convertRule = ctx.comparisonOperator().getText() + " " + ctx.comparisonQuantifier().getText() + " ";
            columnBean.relationColumns = createColumnBeanList();
            columnBean.relationColumns.add((ColumnBean)visit);

            return columnBean;
        }
        if(visit instanceof  List){  //比较操作符的右侧，如果返回的是List<ColumnBean>，暂时只返回第一个ColumnBean元素
            ColumnBean columnBean = new ColumnBean();
            columnBean.columnType = ColumnType.UNARYOPT;
            columnBean.convertRule = ctx.comparisonOperator().getText() + " " + ctx.comparisonQuantifier().getText() + " ";
            columnBean.relationColumns = createColumnBeanList();
            columnBean.relationColumns.add(((List<ColumnBean>)visit).get(0));

            return columnBean;
        }

        return null;
    }

    @Override
    public Object visitInList(EntireSqlParser.InListContext ctx) {
        ColumnBean columnBean = new ColumnBean();
        columnBean.columnType = ColumnType.IN_LIST;
        columnBean.relationColumns = createColumnBeanList();
        columnBean.convertRule = ctx.NOT()==null?"IN":"NOT IN";
        for(EntireSqlParser.ExpressionContext expressionContext:ctx.expression()){
            Object expression = visit(expressionContext);
            if(expression instanceof ColumnBean){
                columnBean.relationColumns.add((ColumnBean)expression);
            }
        }
        return columnBean;
    }


    @Override
    public Object visitInSubquery(EntireSqlParser.InSubqueryContext ctx) {
        ColumnBean columnBean = new ColumnBean();
        columnBean.columnType = ColumnType.IN_LIST;
        columnBean.relationColumns = createColumnBeanList();
        columnBean.convertRule = ctx.NOT()==null?"IN":"NOT IN";
        Object visit = visit(ctx.query());
        if(visit instanceof ColumnBean){
            columnBean.relationColumns.add((ColumnBean)visit);
        }else if(visit instanceof List){
            columnBean.relationColumns.addAll((List<ColumnBean>)visit);
        }
        return columnBean;
    }

    @Override
    public Object visitBetween(EntireSqlParser.BetweenContext ctx) {
        ColumnBean columnBean = new ColumnBean();
        columnBean.columnType = ColumnType.BETWEEN;
        columnBean.relationColumns = createColumnBeanList();
        columnBean.convertRule = ctx.NOT()==null?"BETWEEN":"NOT BETWEEN";
        Object lower = visit(ctx.lower);
        Object upper = visit(ctx.upper);
        if(lower instanceof ColumnBean){
            columnBean.relationColumns.add((ColumnBean)lower);
        }
        if(upper instanceof ColumnBean){
            columnBean.relationColumns.add((ColumnBean)upper);
        }
        return columnBean;
    }


    @Override
    public Object visitLike(EntireSqlParser.LikeContext ctx) {
        ColumnBean columnBean = new ColumnBean();
        columnBean.columnType = ColumnType.LIKE;
        columnBean.relationColumns = createColumnBeanList();
        columnBean.convertRule = ctx.NOT()==null?"LIKE":"NOT LIKE";
        Object visit = visit(ctx.pattern);
        if(visit instanceof ColumnBean){
            columnBean.relationColumns.add((ColumnBean)visit);
        }

        return columnBean;
    }

    @Override
    public Object visitRegexp(EntireSqlParser.RegexpContext ctx) {
        ColumnBean columnBean = new ColumnBean();
        columnBean.columnType = ColumnType.LIKE;
        columnBean.relationColumns = createColumnBeanList();
        columnBean.convertRule = ctx.NOT()==null?"REGEXP":"NOT REGEXP";
        Object visit = visit(ctx.pattern);
        if(visit instanceof ColumnBean){
            columnBean.relationColumns.add((ColumnBean)visit);
        }

        return columnBean;
    }

    @Override
    public Object visitRlike(EntireSqlParser.RlikeContext ctx) {
        ColumnBean columnBean = new ColumnBean();
        columnBean.columnType = ColumnType.LIKE;
        columnBean.relationColumns = createColumnBeanList();
        columnBean.convertRule = ctx.NOT()==null?"RLIKE":"NOT RLIKE";
        Object visit = visit(ctx.pattern);
        if(visit instanceof ColumnBean){
            columnBean.relationColumns.add((ColumnBean)visit);
        }

        return columnBean;
    }

    @Override
    public Object visitNullPredicate(EntireSqlParser.NullPredicateContext ctx) {
        ColumnBean columnBean = new ColumnBean();
        columnBean.columnType = ColumnType.IS_NULL;
        columnBean.relationColumns = createColumnBeanList();
        columnBean.convertRule = ctx.NOT()==null?"IS NULL":"IS NOT NULL";
        return columnBean;
    }



    @Override
    protected Object aggregateResult(Object aggregate, Object nextResult) {
        if (nextResult != null){
            return nextResult;
        }else{
            return aggregate;
        }
    }


}
