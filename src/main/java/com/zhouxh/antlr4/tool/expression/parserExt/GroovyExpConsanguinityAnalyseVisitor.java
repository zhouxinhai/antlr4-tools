package com.zhouxh.antlr4.tool.expression.parserExt;

import com.zhouxh.antlr4.tool.expression.bean.ExpDetialBean;
import com.zhouxh.antlr4.tool.expression.parser.GroovyExpBaseVisitor;
import com.zhouxh.antlr4.tool.expression.parser.GroovyExpParser;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * Create by zhouxinhai on 2020/2/3
 */
public class GroovyExpConsanguinityAnalyseVisitor extends GroovyExpBaseVisitor<Object> {
    private TokenStream tokenStream;
    private ExpDetialBean expDetialBean = new ExpDetialBean();
    private static final String COLUMN_REGEX = "\\$\\$\\[(.*)\\]";
    private TokenStreamRewriter tokenStreamRewriter;

    public GroovyExpConsanguinityAnalyseVisitor(GroovyExpParser parser, BufferedTokenStream tokens) {
        tokenStream = parser.getTokenStream();
        tokenStreamRewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public Object visitStatement(GroovyExpParser.StatementContext ctx) {
        visitChildren(ctx);
        expDetialBean.setConvertRule(tokenStreamRewriter.getText());
        return expDetialBean;
    }

    @Override
    public Object visitColumnReference(GroovyExpParser.ColumnReferenceContext ctx) {
        String text = tokenStream.getText(ctx);
        if (text.matches(COLUMN_REGEX)) {
            text = text.replaceFirst(COLUMN_REGEX, "$1");
            expDetialBean.getSrcColumns().add(text);
            tokenStreamRewriter.replace(ctx.getStart(), ctx.getStop(), text);
        }

        return null;
    }

    @Override
    public Object visitDereference(GroovyExpParser.DereferenceContext ctx) {
        String text = tokenStream.getText(ctx);
        if (text.matches(COLUMN_REGEX)) {
            text = text.replaceFirst(COLUMN_REGEX, "$1");
            expDetialBean.getSrcColumns().add(text);
            tokenStreamRewriter.replace(ctx.getStart(), ctx.getStop(), text);
        }

        return null;
    }
}
