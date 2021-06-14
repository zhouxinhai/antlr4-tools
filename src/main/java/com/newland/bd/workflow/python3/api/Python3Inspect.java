package com.newland.bd.workflow.python3.api;

import com.newland.bd.workflow.python3.parser.Python3Lexer;
import com.newland.bd.workflow.python3.parser.Python3Parser;
import com.newland.bd.workflow.sql.api.JsonUtil;
import com.newland.bd.workflow.sql.parserExt.ExtErrorListener;
import com.newland.bd.workflow.sql.parserExt.HsqlRecognitionException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Create by zhouxinhai on 2020/2/27
 */
public class Python3Inspect {

    public static String inspect(CharStream codes) {
        try {

            Python3Lexer lexer = new Python3Lexer(codes);
            ExtErrorListener errorListener = new ExtErrorListener();
            lexer.removeErrorListeners();
            lexer.addErrorListener(errorListener);

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            Python3Parser parser = new Python3Parser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);
            parser.file_input();

            if (errorListener.getErrLexerMsg() != null) {
                return JsonUtil.responseJson(false, "python3语法校验失败", errorListener.getErrLexerMsg());
            }

            return JsonUtil.responseJson(true, "python3语法校验成功", null);
        } catch (HsqlRecognitionException e) {
            return JsonUtil.responseJson(false, e.getMessage(), e.getErrLexerMsg());
        }
    }

    public static String inspect(File file) throws IOException {
        return inspect(new ANTLRInputStream(new FileInputStream(file)));
    }

    public static String inspect(String codes) {
        return inspect(CharStreams.fromString(codes));
    }
}
