package com.zhouxh.antlr4.tool.sql.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouxh.antlr4.tool.sql.bean.DbType;
import com.zhouxh.antlr4.tool.sql.bean.TableNameAndOpt;
import com.zhouxh.antlr4.tool.sql.bean.consanguinity.*;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlLexer;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import com.zhouxh.antlr4.tool.sql.parserExt.ExtErrorListener;
import com.zhouxh.antlr4.tool.sql.parserExt.HsqlRecognitionException;
import com.zhouxh.antlr4.tool.sql.parserExt.consanguinity.ConsanguinityAnalyseUtil;
import com.zhouxh.antlr4.tool.sql.parserExt.consanguinity.ConsanguinityAnalyseVisitor;
import com.zhouxh.antlr4.tool.sql.parserExt.consanguinity.MultiSqlConsanguinityAnalyseUtil;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by Howard on 2018/10/20
 */
public class SqlConsanguinityAnalyse {

    private static boolean isSqlValid(String retJson){
        JSONObject jsonObject = JSON.parseObject(retJson);
        boolean res = jsonObject.getBoolean("success");
        String output = jsonObject.getString("output");
        return res;
    }

    /**
     *
     * @param dstTableDetailBeanList
     * @param entityCols
     * @param autoAddDstColumn  如果目标字段不存在是否需要自动补充
     * @return
     */
    private static SqlConsanguinityBeanList convertToSqlConsanguinityBeanList(DstTableDetailBeanList dstTableDetailBeanList,Map<String, List<String>> entityCols, boolean autoAddDstColumn){
        SqlConsanguinityBeanList sqlConsanguinityBeanList = new SqlConsanguinityBeanList();
        for(DstTableDetailBean dstTableDetailBean:dstTableDetailBeanList){
            if(dstTableDetailBean.tablename.equals(DstTableDetailBean.NO_DST_TABLE)){
                continue;
            }

            if(dstTableDetailBean.dstColumnNameList.isEmpty() && !autoAddDstColumn){//如果输出目标实体，没有指明输出字段，则进行自动补充
                if(entityCols == null){
                    continue;
                }
                List<String> oColumnList = entityCols.get(dstTableDetailBean.tablename.toUpperCase());
                if(oColumnList == null || oColumnList.isEmpty() ){
                    continue;
                }
                int indexLimit = oColumnList.size();
                if(oColumnList.size() > dstTableDetailBean.sourceColumnBeanList.size()){
                    indexLimit = dstTableDetailBean.sourceColumnBeanList.size();
                }
                for(int i = 0; i< indexLimit; i++){
                    dstTableDetailBean.dstColumnNameList.add(oColumnList.get(i));
                }
            }


            int colunmCnt = dstTableDetailBean.sourceColumnBeanList.size();
            if (!autoAddDstColumn) {
                colunmCnt = Math.min(dstTableDetailBean.dstColumnNameList.size(), dstTableDetailBean.sourceColumnBeanList.size());
            }

            for(int i= 0; i< colunmCnt; i++){
                List<ColumnBean> atomColumnBeanList =
                        ConsanguinityAnalyseUtil.columnBeanToAtomColumnBeansByRemoveRepetition(dstTableDetailBean.sourceColumnBeanList.get(i));
                String columnBeanString = ConsanguinityAnalyseUtil.toConsanguinityFormat(dstTableDetailBean.sourceColumnBeanList.get(i));
                for(ColumnBean atomColumnBean :atomColumnBeanList){
                    boolean isNormalColumn  = true;
                    if(!atomColumnBean.columnType.equals(ColumnType.NORMAL)){
                        isNormalColumn = false;
                    }

                    if(isNormalColumn){
                        SqlConsanguinityBean sqlConsanguinityBean = new SqlConsanguinityBean();
                        sqlConsanguinityBean.dstTablename = dstTableDetailBean.tablename;
                        if(i < dstTableDetailBean.dstColumnNameList.size()){
                            sqlConsanguinityBean.dstColumnName = dstTableDetailBean.dstColumnNameList.get(i);
                        }else{
                            sqlConsanguinityBean.dstColumnName = DstTableDetailBean.NO_DST_COLUMN;
                        }

                        sqlConsanguinityBean.srcTablename = atomColumnBean.belongToTableName;
                        sqlConsanguinityBean.srcColumnName = atomColumnBean.columnName;
                        sqlConsanguinityBean.convertRule = columnBeanString;
                        sqlConsanguinityBeanList.add(sqlConsanguinityBean);
                    }
                }
            }
        }
        return sqlConsanguinityBeanList;
    }


    /**
     * 湖南特供接口，返回SqlConsanguinityBeanList血缘结构
     * @param sql
     * @param entityCols    表名和字段的对应关系
     * @param autoAddDstColumn     如果目标字段不存在是否需要自动补充
     * @return
     */
    public static Pair<String, SqlConsanguinityBeanList> consanguinityAnalyseExt(String sql,Map<String, List<String>> entityCols, boolean autoAddDstColumn){
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        if(!isSqlValid(stringListPair.getLeft())){
            return Pair.of(stringListPair.getLeft(),null);
        }
        return Pair.of(stringListPair.getLeft(),convertToSqlConsanguinityBeanList(stringListPair.getRight(),entityCols, autoAddDstColumn));
    }


    /**
     * 单条sql语句的血缘解析
     * @param sql  单条sql语句
     * @return Pair<解析结构json返回,DstTableDetailBean>
     */
    public static Pair<String, DstTableDetailBeanList> consanguinityAnalyse(String sql){
        try{
            ANTLRInputStream input = new ANTLRInputStream(sql);
            EntireSqlLexer lexer = new EntireSqlLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            EntireSqlParser parser = new EntireSqlParser(tokens);
            ExtErrorListener errorListener =new ExtErrorListener();
            parser.addErrorListener(errorListener);
            parser.setAllTypeTrue();

            //parser.setErrorHandler(new BailErrorStrategy());
            ParseTree tree = parser.singleStatement();
            if(errorListener.getErrLexerMsg()!= null) {
                return Pair.of(JsonUtil.responseJson(false,"血缘分析中，语法前置校验失败",errorListener.getErrLexerMsg()),null);
            }

            //调用血缘分析的访问器
            ConsanguinityAnalyseVisitor consanguinityAnalyseVisitor = new ConsanguinityAnalyseVisitor(parser);
            Object object = consanguinityAnalyseVisitor.visit(tree);
            ConsanguinityAnalyseUtil.standardizeDstTableDetailBean((DstTableDetailBeanList)object);

            return Pair.of(JsonUtil.responseJson(true,"血缘分析成功",null),(DstTableDetailBeanList)object);
        }catch(HsqlRecognitionException e){
            return Pair.of(JsonUtil.responseJson(false,e.getMessage(),e.getErrLexerMsg()),null);
        }
    }

    /**
     * 多条sql语句的血缘解析
     * @param multiSql
     * @return
     */
    public static Pair<String, DstTableDetailBeanList> multiSqlConsanguinityAnalyse(String multiSql){
        DstTableDetailBeanList dstTableDetailBeanList = new DstTableDetailBeanList();
        List<String> sqlList = SqlSplit.getStatementList(multiSql);

        for(int i = 0;i<sqlList.size();i++){
            Pair<String, DstTableDetailBeanList> pair = consanguinityAnalyse(sqlList.get(i));
            String retJson =  pair.getLeft();
            JSONObject jsonObject = JSON.parseObject(retJson);
            boolean res = jsonObject.getBoolean("success");

            if(!res){
                String output = jsonObject.getString("output");
                return Pair.of(JsonUtil.responseJson(false,"第"+(i+1)+"句sql"+output,null),null);
            }
            dstTableDetailBeanList.addAll(pair.getRight());
        }

        DstTableDetailBeanList finalDstTableDetailBeanList = MultiSqlConsanguinityAnalyseUtil.multiSqlColumnBeanTrace(dstTableDetailBeanList);
        return Pair.of(JsonUtil.responseJson(true,"血缘分析成功",null),finalDstTableDetailBeanList);
    }

    /**
     * 从多条sql语句中，获得create table的表信息
     * @param multiSql
     * @return Map<表名，List<列名>>
     */
    public static Map<String,List<String>> getCreateTableListFromSqls(String multiSql){
        Map<String,List<String>> createTableInfos = new HashMap<>();
        Pair<String, DstTableDetailBeanList> pair = multiSqlConsanguinityAnalyse(multiSql);
        JSONObject jsonObject = JSON.parseObject(pair.getLeft());
        boolean res = jsonObject.getBoolean("success");
        if(!res){
            return createTableInfos;
        }
        DstTableDetailBeanList dstTableDetailBeanList = pair.getRight();
        for(DstTableDetailBean dstTableDetailBean:dstTableDetailBeanList){
            if(!dstTableDetailBean.isCreateTableType){
                continue;
            }
            createTableInfos.put(dstTableDetailBean.tablename,dstTableDetailBean.dstColumnNameList);
        }

        return createTableInfos;
    }

    /**
     * 湖南特供接口，返回TableConsanguinityBeanList表血缘结构
     * @param sql
     * @return
     */
    public static Pair<String,TableConsanguinityBeanList> tableConsanguinityAnalyse(String sql) {
        TableConsanguinityBeanList tableConsanguinityBeans = new TableConsanguinityBeanList();
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.AllType, true);
        if (!isSqlValid(pair.getLeft())) {
            return Pair.of(pair.getLeft(), null);
        }
        String dstTable = null;
        List<String> srcTables = new ArrayList<>();
        for (TableNameAndOpt tableNameAndOpt : pair.getRight()) {
            if (tableNameAndOpt.getTableOpt().isDCL() || tableNameAndOpt.getTableOpt().isDDL() || tableNameAndOpt.getTableOpt().isDML()) {
                dstTable = tableNameAndOpt.getTableName();
            } else {
                srcTables.add(tableNameAndOpt.getTableName());
            }
        }
        if (dstTable != null) {
            for (String srcTable : srcTables) {
                TableConsanguinityBean tableConsanguinityBean = new TableConsanguinityBean();
                tableConsanguinityBean.dstTablename = dstTable;
                tableConsanguinityBean.srcTablename = srcTable;
                tableConsanguinityBeans.add(tableConsanguinityBean);
            }
        }

        return Pair.of(pair.getLeft(), tableConsanguinityBeans);
    }



}
