package com.zhouxh.antlr4.tool.sql.parserExt;

import com.zhouxh.antlr4.tool.sql.conv.HsqlColumnConvert;
import com.zhouxh.antlr4.tool.sql.conv.HsqlFunctionConvert;
import com.zhouxh.antlr4.tool.sql.conv.HsqlTypeConvert;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlBaseListener;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * Create by Howard on 2018/9/28
 */
public class TranslateSqlListener extends EntireSqlBaseListener {
    private EntireSqlParser parser;
    private BufferedTokenStream tokens;
    public TokenStreamRewriter tokenStreamRewriter;
    public TranslateSqlListener(EntireSqlParser parser, BufferedTokenStream tokens){
        this.parser = parser;
        this.tokens = tokens;
        tokenStreamRewriter = new TokenStreamRewriter(tokens);
    }


    /**
     * 测试用
     * @param ctx
     */
    @Override
    public void enterTableName(EntireSqlParser.TableNameContext ctx) {
/*
        System.out.println("表名:"+ctx.qualifiedName().getText());
        TokenStream tokenStream = parser.getTokenStream();
        System.out.println("表名:"+tokenStream.getText(ctx.qualifiedName()));
*/

    }

    /**
     * 测试用
     * @param ctx
     */
    @Override
    public void exitTableName(EntireSqlParser.TableNameContext ctx) {
/*        Token startToken = ctx.getStart();
        int startTokenIndex = startToken.getTokenIndex();

        Token endToken = ctx.getStop();
        int endTokenIndex = endToken.getTokenIndex();

        tokenStreamRewriter.replace(endTokenIndex,"zxh_table");
        tokenStreamRewriter.insertBefore(startTokenIndex,"插入的内容 ");*/
    }


    /**
     * 修改数据类型
     * @param ctx
     */
    @Override
    public void exitType(EntireSqlParser.TypeContext ctx) {


        /*if(ctx.baseType() != null){
            //System.out.println(ctx.baseType().getText());
            //字段类型进行转换
            String dstBaseType = HsqlTypeConvert.baseTypeConvert(ctx.getText());
            if(dstBaseType != null){
                tokenStreamRewriter.replace(ctx.getStart(),ctx.getStop(),dstBaseType);
            }
        }*/

        HsqlTypeConvert.typeConvert(ctx,tokenStreamRewriter);
    }

    /**
     * 修改函数名字,把函数名中的改成FUNCTION,并把他的两个输入参数进行调换
     * @param ctx
     */
    @Override
    public void exitFunctionCall(EntireSqlParser.FunctionCallContext ctx) {
        HsqlFunctionConvert.functionConvert(ctx,tokenStreamRewriter);
    }

    /**
     * 校验UNION、EXCEPT、MINUS、INTERSECT
     * @param ctx
     */
    @Override
    public void enterSetOperation(EntireSqlParser.SetOperationContext ctx) {
        if(ctx.operator.getText().equalsIgnoreCase("EXCEPT") ||
                ctx.operator.getText().equalsIgnoreCase("MINUS") ||
                ctx.operator.getText().equalsIgnoreCase("INTERSECT") ){

            throw new HsqlRecognitionException(ctx.operator,
                    "在hsql中, 无法解析符号 "+ctx.operator.getText()+"");
        }
    }

    /**
     * 修改
     * ALTER TABLE table [ * ]  ADD [<!COLUMN>] column type
     * 为
     * ALTER TABLE table [ * ] ADD [<!COLUMNS>] (column type)
     * @param ctx
     */
    @Override
    public void exitAddColumn(EntireSqlParser.AddColumnContext ctx) {
        //把关键字COLUMN改为COLUMNS
        tokenStreamRewriter.replace(ctx.COLUMN().getSymbol(),"COLUMNS");
        //在column type的左边加上(
        tokenStreamRewriter.insertBefore(ctx.columnDefinition().start,"(");
        //在column type的右边加上)
        tokenStreamRewriter.insertAfter(ctx.columnDefinition().stop,")");
    }

    /**
     * 修改
     * ALTER TABLE table [ * ] RENAME [<!COLUMN>] column TO newcolumn [TYPE]
     * 为
     * ALTER TABLE table [ * ] CHANGE [<!COLUMN>] column newcolumn type
     * @param ctx
     */
    @Override
    public void exitRenameColumn(EntireSqlParser.RenameColumnContext ctx) {
        tokenStreamRewriter.replace(ctx.RENAME().getSymbol(),"CHANGE");
        tokenStreamRewriter.replace(ctx.TO().getSymbol(),"");
        if(ctx.type() == null){
            tokenStreamRewriter.insertAfter(ctx.to.stop," STRING");
        }
    }

    /**
     * 修改
     * ALTER TABLE table [ * ] MODIFY column type
     * 为
     * ALTER TABLE table [ * ] CHANGE [<!COLUMN>] column column type
     * @param ctx
     */
    @Override
    public void exitModifyTable(EntireSqlParser.ModifyTableContext ctx) {
        tokenStreamRewriter.replace(ctx.MODIFY().getSymbol(),"CHANGE COLUMN");
        tokenStreamRewriter.insertAfter(ctx.column.start," "+ctx.column.getText());
    }

    /**
     * 对一些特殊的字段进行转义处理
     * @param ctx
     */
    @Override
    public void exitColumnReference(EntireSqlParser.ColumnReferenceContext ctx) {
        HsqlColumnConvert.columnConvert(ctx,tokenStreamRewriter);
    }


}
