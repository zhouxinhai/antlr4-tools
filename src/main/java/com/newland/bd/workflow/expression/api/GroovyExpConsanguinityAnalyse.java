package com.newland.bd.workflow.expression.api;

import com.newland.bd.workflow.expression.bean.ExpDetialBean;
import com.newland.bd.workflow.expression.parser.GroovyExpLexer;
import com.newland.bd.workflow.expression.parser.GroovyExpParser;
import com.newland.bd.workflow.expression.parserExt.GroovyExpConsanguinityAnalyseVisitor;
import com.newland.bd.workflow.sql.api.JsonUtil;
import com.newland.bd.workflow.sql.parserExt.ExtErrorListener;
import com.newland.bd.workflow.sql.parserExt.HsqlRecognitionException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Create by zhouxinhai on 2020/2/3
 */
public class GroovyExpConsanguinityAnalyse {
    public static Pair<String, ExpDetialBean> consanguinityAnalyse(String expression) {
        try {
            ANTLRInputStream input = new ANTLRInputStream(expression);
            GroovyExpLexer lexer = new GroovyExpLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GroovyExpParser parser = new GroovyExpParser(tokens);
            ExtErrorListener errorListener = new ExtErrorListener();
            parser.addErrorListener(errorListener);

            ParseTree tree = parser.statement();
            if (errorListener.getErrLexerMsg() != null) {
                return Pair.of(JsonUtil.responseJson(false, "表达式血缘分析中，语法前置校验失败", errorListener.getErrLexerMsg()), null);
            }
            //调用血缘分析的访问器
            GroovyExpConsanguinityAnalyseVisitor groovyExpConsanguinityAnalyseVisitor = new GroovyExpConsanguinityAnalyseVisitor(parser, tokens);
            Object obj = groovyExpConsanguinityAnalyseVisitor.visit(tree);

            return Pair.of(JsonUtil.responseJson(true, "表达式血缘分析成功", null), (ExpDetialBean) obj);
        } catch (HsqlRecognitionException e) {
            return Pair.of(JsonUtil.responseJson(false, e.getMessage(), e.getErrLexerMsg()), null);
        }
    }
}
