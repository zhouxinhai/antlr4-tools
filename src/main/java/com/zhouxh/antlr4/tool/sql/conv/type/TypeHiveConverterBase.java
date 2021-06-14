package com.zhouxh.antlr4.tool.sql.conv.type;

import com.zhouxh.antlr4.tool.sql.conv.HiveConverterBase;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import org.antlr.v4.runtime.TokenStreamRewriter;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Howard on 2018/9/30
 */
public abstract class TypeHiveConverterBase extends HiveConverterBase {

    //精确匹配的转换规则
    private static Map<String,String> accurateMatchSql2HsqlBaseType = new HashMap<String,String>();
    static {
        accurateMatchSql2HsqlBaseType.put("VARCHAR2","VARCHAR");
        accurateMatchSql2HsqlBaseType.put("NVARCHAR","VARCHAR");
        accurateMatchSql2HsqlBaseType.put("TEXT","STRING");
        accurateMatchSql2HsqlBaseType.put("CLOB","STRING");
        accurateMatchSql2HsqlBaseType.put("LONG","BIGINT");
        accurateMatchSql2HsqlBaseType.put("BIT","BOOLEAN");
        accurateMatchSql2HsqlBaseType.put("REAL","DOUBLE");
        accurateMatchSql2HsqlBaseType.put("NUMBER","DECIMAL");
        accurateMatchSql2HsqlBaseType.put("NUMERIC","DECIMAL");
        accurateMatchSql2HsqlBaseType.put("BLOB","BINARY");
        accurateMatchSql2HsqlBaseType.put("CHAR","CHAR(1)");   //如果char没有指定size，加上默认长度0，否则会报错
    }

    //模糊匹配的转换规则
    private static Map<String,String> fuzzyMatchql2HsqlBaseType = new HashMap<String,String>();
    static {

        fuzzyMatchql2HsqlBaseType.put("FLOAT\\(.*\\)","FLOAT");  //验证通过
        fuzzyMatchql2HsqlBaseType.put("DOUBLE\\(.*\\)","DOUBLE");  //验证通过
        fuzzyMatchql2HsqlBaseType.put("BINARY\\(.*\\)","BINARY");  //验证通过
//        fuzzyMatchql2HsqlBaseType.put("VARCHAR2\\(.*\\)","VARCHAR");
//        fuzzyMatchql2HsqlBaseType.put("NVARCHAR\\(.*\\)","VARCHAR");
        fuzzyMatchql2HsqlBaseType.put("NUMBER\\((.*)\\)","DECIMAL($1)");
        fuzzyMatchql2HsqlBaseType.put("NUMERIC\\((.*)\\)","DECIMAL($1)");
        fuzzyMatchql2HsqlBaseType.put("VARCHAR2\\((.*)\\)","VARCHAR($1)");
        fuzzyMatchql2HsqlBaseType.put("NVARCHAR\\((.*)\\)","VARCHAR($1)");

    }


    /**
     * 对原始数据类型进行转换
     * @param srcBaseType 原始的数据类型
     * @return 返回转换后的类型，如果放回null，说明无需转换
     */
    static String baseTypeConvert(String srcBaseType){

        //先尝试进行精确匹配的规则的转化
        String dstBaseType = accurateMatchSql2HsqlBaseType.get(srcBaseType);
        if(dstBaseType != null){
            return dstBaseType;
        }

        //再尝试进行模的规则的转化
        for(Map.Entry<String,String> entry:fuzzyMatchql2HsqlBaseType.entrySet()){
            dstBaseType=srcBaseType.replaceFirst(entry.getKey(), entry.getValue());
            if(!dstBaseType.equals(srcBaseType)){
                return dstBaseType;
            }
        }



        return null;
    }

    /**
     * 对原始的函数进行转换
     * @param typeContext   原始的类型节点
     * @param tokenStreamRewriter  token重写器
     */
    abstract public void typeConvert(EntireSqlParser.TypeContext typeContext, TokenStreamRewriter tokenStreamRewriter);
}
