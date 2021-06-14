package com.zhouxh.antlr4.tool.sql.api;

import com.zhouxh.antlr4.tool.sql.bean.TableAndColumnRelation;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlLexer;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import com.zhouxh.antlr4.tool.sql.parserExt.ExtErrorListener;
import com.zhouxh.antlr4.tool.sql.parserExt.HsqlRecognitionException;
import com.zhouxh.antlr4.tool.sql.parserExt.TraceSqlListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.List;

/**
 * Create by Howard on 2018/10/8
 */
public class SqlTrace {

    private static void displayTraceResult(TraceSqlListener listener){
        List<TableAndColumnRelation> tableAndColumnRelationList = listener.tableAndColumnRelationList;
        for(TableAndColumnRelation tableAndColumnRelation:tableAndColumnRelationList){
            System.out.println(tableAndColumnRelation.toString());
            System.out.println("---------------------------------");
        }
    }

    public static String trace(String sql){
        try{
            ANTLRInputStream input = new ANTLRInputStream(sql);
            EntireSqlLexer lexer = new EntireSqlLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            EntireSqlParser parser = new EntireSqlParser(tokens);
            ExtErrorListener errorListener =new ExtErrorListener();
            parser.addErrorListener(errorListener);
            parser.setAllTypeTrue();

            ParseTree tree = parser.singleStatement();
            if(errorListener.getErrLexerMsg()!= null) {
                return JsonUtil.responseJson(false,"",errorListener.getErrLexerMsg());
            }

            //监听器遍历
            TraceSqlListener listener = new TraceSqlListener(parser);
            ParseTreeWalker walker =new  ParseTreeWalker();  //新建一个标准遍历器
            walker.walk(listener,tree);

            if(errorListener.getErrLexerMsg()!= null) {
                return JsonUtil.responseJson(false,"",errorListener.getErrLexerMsg());
            }


            displayTraceResult(listener);
            return JsonUtil.responseJson(true,"",null);
        }catch(HsqlRecognitionException e){
            return JsonUtil.responseJson(false,e.getMessage(),e.getErrLexerMsg());
        }
    }


}
