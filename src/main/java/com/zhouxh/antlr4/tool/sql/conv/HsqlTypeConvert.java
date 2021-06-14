package com.zhouxh.antlr4.tool.sql.conv;

import com.zhouxh.antlr4.tool.sql.conv.type.HiveTypeConverter;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * 将sql中的语句中的标准数据类型转换成Hsql中的数据类型
 * Create by Howard on 2018/9/28
 */
public class HsqlTypeConvert  {
    private static HiveTypeConverter hsqlTypeConverter = new HiveTypeConverter();



/*    public static void main(String[] args) {
        String str = "ddBINARY(10)";
        System.out.println("匹配结果："+str.matches(".*BINARY\\(.*\\)"));

        str = str.replaceFirst("BINARY\\(.*\\)","BINARY");
        System.out.println(str);


        String tips = "2018年的春节是哪一天";
        String yearPrefix = "[n1][y1]";
        tips = tips.replaceFirst("(\\d{4})", "1112");
        System.out.println(tips);
    }*/


    public static void typeConvert(EntireSqlParser.TypeContext typeContext, TokenStreamRewriter tokenStreamRewriter) {
        hsqlTypeConverter.typeConvert(typeContext,tokenStreamRewriter);
    }
}
