package com.zhouxh.antlr4.tool.sql.api;

import com.zhouxh.antlr4.tool.sql.bean.DbType;
import com.zhouxh.antlr4.tool.sql.bean.TableNameAndOpt;
import com.zhouxh.antlr4.tool.sql.bean.consanguinity.TableAndPartitionInfo;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlLexer;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import com.zhouxh.antlr4.tool.sql.parserExt.ExtErrorListener;
import com.zhouxh.antlr4.tool.sql.parserExt.HsqlRecognitionException;
import com.zhouxh.antlr4.tool.sql.parserExt.inspect.ColumnCountInspect;
import com.zhouxh.antlr4.tool.sql.parserExt.inspect.GroupBySqlInspectVisitor;
import com.zhouxh.antlr4.tool.sql.parserExt.inspect.InspectEntireSqlListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Create by Howard on 2018/10/8
 */
public class SqlInspect {

    private static ThreadLocal<List<TableAndPartitionInfo>> hiveTablePartitionInfoList = new ThreadLocal<List<TableAndPartitionInfo>>();   //hive建表语句，表名和分区信息的对应关系

    /**
     * hive建表语句，表名和分区信息的对应关系
     * @return
     */
    public static List<TableAndPartitionInfo> getHiveTablePartitionInfoList() {
        return hiveTablePartitionInfoList.get();
    }



    public static String inspect(String sql){
        init();
        try{
            ANTLRInputStream input = new ANTLRInputStream(sql);
            EntireSqlLexer lexer = new EntireSqlLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            EntireSqlParser parser = new EntireSqlParser(tokens);
            ExtErrorListener errorListener =new ExtErrorListener();
            parser.addErrorListener(errorListener);
            parser.setAllTypeTrue();

            //parser.setErrorHandler(new BailErrorStrategy());
            ParseTree tree = parser.multiStatement();
            if(errorListener.getErrLexerMsg()!= null) {
                return JsonUtil.responseJson(false,"语法前置校验失败",errorListener.getErrLexerMsg());
            }

            //监听器遍历
            InspectEntireSqlListener inspectSqlListener = new InspectEntireSqlListener(parser);
            ParseTreeWalker walker =new  ParseTreeWalker();  //新建一个标准遍历器
            walker.walk(inspectSqlListener,tree);
            if(errorListener.getErrLexerMsg()!= null) {
                return JsonUtil.responseJson(false,"语法后置校验失败",errorListener.getErrLexerMsg());
            }

            //group by语法访问器，用来检查group by语法的正确性
            GroupBySqlInspectVisitor groupBySqlVisitor = new GroupBySqlInspectVisitor();
            groupBySqlVisitor.visit(tree);
            //字段个数相等校验器
            ColumnCountInspect columnCountInspect = new ColumnCountInspect();
            columnCountInspect.visit(tree);



            return JsonUtil.responseJson(true,"语法校验成功",null);
        }catch(HsqlRecognitionException e){
            return JsonUtil.responseJson(false,e.getMessage(),e.getErrLexerMsg());
        }
    }



    public static Pair<String, List<TableNameAndOpt>> inspect(String sqls , DbType dbType){
        return inspect(sqls,dbType,false);
    }

    public static Pair<String, List<TableNameAndOpt>> inspect(String sqls , DbType dbType,boolean isExplore){
        init();
        try{
            ANTLRInputStream input = new ANTLRInputStream(sqls);
            EntireSqlLexer lexer = new EntireSqlLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            EntireSqlParser parser = new EntireSqlParser(tokens);
            ExtErrorListener errorListener =new ExtErrorListener();
            parser.addErrorListener(errorListener);
            if(DbType.HIVE.equals(dbType)){
                parser.hiveType = true;
            }
            if(DbType.ORACLE.equals(dbType)){
                parser.oracleType = true;
            }
            if(DbType.MYSQL.equals(dbType)){
                parser.mysqlType = true;
            }
            if(DbType.GP.equals(dbType)){
                parser.gpType = true;
            }
            if(DbType.GBASE.equals(dbType)){
                parser.gbaseType = true;
            }
            if(DbType.DB2.equals(dbType)){
                parser.db2Type = true;
            }
            if(DbType.SQLSERVER.equals(dbType)){
                parser.sqlserverType = true;
            }
            if(DbType.AllType.equals(dbType)){
                parser.setAllTypeTrue();
            }
            parser.exploreType = isExplore;



            ParseTree tree = parser.multiStatement();
            if(errorListener.getErrLexerMsg()!= null) {
                return Pair.of(JsonUtil.responseJson(false,"语法前置校验失败",errorListener.getErrLexerMsg()),null);
            }

            //监听器遍历
            InspectEntireSqlListener inspectSqlListener = new InspectEntireSqlListener(parser);
            inspectSqlListener.init();
            ParseTreeWalker walker =new  ParseTreeWalker();  //新建一个标准遍历器
            walker.walk(inspectSqlListener,tree);
            if(errorListener.getErrLexerMsg()!= null) {
                return Pair.of(JsonUtil.responseJson(false,"语法后置校验失败",errorListener.getErrLexerMsg()),null);
            }
            List<TableNameAndOpt> tableNameAndOptList = inspectSqlListener.getTableNameAndOptList();
            hiveTablePartitionInfoList.set(inspectSqlListener.getHiveTablePartitionInfoList());


            return Pair.of(JsonUtil.responseJson(true,"语法校验成功",null),tableNameAndOptList);
        }catch(HsqlRecognitionException e){
            return Pair.of(JsonUtil.responseJson(false,e.getMessage(),e.getErrLexerMsg()),null);
        }
    }

    private  static void init(){
        hiveTablePartitionInfoList.remove();
    }


}
