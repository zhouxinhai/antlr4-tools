package com.zhouxh.antlr4.tool.sql.conv;

import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * Create by Howard on 2018/10/8
 */
public class HsqlColumnConvert {
    private static class ColumnHiveConverter extends HiveConverterBase{
        public void columnConvert(EntireSqlParser.ColumnReferenceContext ctx, TokenStreamRewriter tokenStreamRewriter){
            if(ctx.getText().equalsIgnoreCase("SYSDATE")){
                tokenStreamRewriter.replace(ctx.getStart(),ctx.getStop(),"CURRENT_DATE");
                setConvertedProperty(ctx,tokenStreamRewriter);
            }
        }
    }

    private static ColumnHiveConverter columnHiveConverter ;
    static{
        columnHiveConverter = new ColumnHiveConverter();
    }


    public static void columnConvert(EntireSqlParser.ColumnReferenceContext ctx, TokenStreamRewriter tokenStreamRewriter){
        columnHiveConverter.columnConvert(ctx,tokenStreamRewriter);
    }
}
