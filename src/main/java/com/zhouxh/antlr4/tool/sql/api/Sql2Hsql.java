package com.zhouxh.antlr4.tool.sql.api;

import com.zhouxh.antlr4.tool.sql.parser.EntireSqlLexer;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import com.zhouxh.antlr4.tool.sql.parserExt.ExtErrorListener;
import com.zhouxh.antlr4.tool.sql.parserExt.HsqlRecognitionException;
import com.zhouxh.antlr4.tool.sql.parserExt.TranslateSqlListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Create by Howard on 2018/9/30
 */
public class Sql2Hsql {

    public static String convert(String sql){
        try{
            ANTLRInputStream input = new ANTLRInputStream(sql);
            EntireSqlLexer lexer = new EntireSqlLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            EntireSqlParser parser = new EntireSqlParser(tokens);
            ExtErrorListener errorListener =new ExtErrorListener();
            parser.addErrorListener(errorListener);
            parser.setAllTypeTrue();

            ParseTree tree = parser.multiStatement();
            if(errorListener.getErrLexerMsg()!= null) {
                return JsonUtil.responseJson(false,"语法前置校验失败",errorListener.getErrLexerMsg());
            }

            //监听器遍历
            TranslateSqlListener listener = new TranslateSqlListener(parser,tokens);
            ParseTreeWalker walker =new  ParseTreeWalker();  //新建一个标准遍历器
            walker.walk(listener,tree);

            if(errorListener.getErrLexerMsg()!= null) {
                return JsonUtil.responseJson(false,"语法后置校验失败",errorListener.getErrLexerMsg());
            }

            return JsonUtil.responseJson(true,listener.tokenStreamRewriter.getText(),null);
        }catch(HsqlRecognitionException e){
            return JsonUtil.responseJson(false,e.getMessage(),e.getErrLexerMsg());
        }

    }

}
