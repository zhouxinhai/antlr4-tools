package com.newland.bd.workflow.sql.api;

import com.newland.bd.workflow.sql.bean.ColumnAndAliasName;
import com.newland.bd.workflow.sql.parser.EntireSqlLexer;
import com.newland.bd.workflow.sql.parser.EntireSqlParser;
import com.newland.bd.workflow.sql.parserExt.ExtErrorListener;
import com.newland.bd.workflow.sql.parserExt.HsqlRecognitionException;
import com.newland.bd.workflow.sql.parserExt.QueryColumnAliasFillingListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Create by Howard on 2018/10/12
 */
public class SqlQueryColumnAliasFilling {

    public static Pair<String,List<ColumnAndAliasName>> aliasFilling(String partialSql){

        //将部分的sql，拼接成完整的sql，便于语法解析
        String completeSql = "select "+partialSql;


        try{
            ANTLRInputStream input = new ANTLRInputStream(completeSql);
            EntireSqlLexer lexer = new EntireSqlLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            EntireSqlParser parser = new EntireSqlParser(tokens);
            ExtErrorListener errorListener =new ExtErrorListener();
            parser.addErrorListener(errorListener);

            ParseTree tree = parser.singleStatement();
            if(errorListener.getErrLexerMsg()!= null) {
                return Pair.of(JsonUtil.responseJson(false,"",errorListener.getErrLexerMsg()),null);
            }

            //监听器遍历
            QueryColumnAliasFillingListener listener = new QueryColumnAliasFillingListener(parser);
            ParseTreeWalker walker =new  ParseTreeWalker();  //新建一个标准遍历器
            walker.walk(listener,tree);

            if(errorListener.getErrLexerMsg()!= null) {
                return Pair.of(JsonUtil.responseJson(false,"",errorListener.getErrLexerMsg()),null);
            }

            return Pair.of(JsonUtil.responseJson(true,"",null),listener.getColumnAndAliasNameList());
        }catch(HsqlRecognitionException e){
            return Pair.of(JsonUtil.responseJson(false,e.getMessage(),e.getErrLexerMsg()),null);
        }

    }

}
