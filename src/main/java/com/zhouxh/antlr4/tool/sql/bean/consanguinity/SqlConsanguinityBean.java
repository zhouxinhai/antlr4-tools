package com.zhouxh.antlr4.tool.sql.bean.consanguinity;

/**
 * Create by Howard on 2019/3/14
 */
public class SqlConsanguinityBean {
    public String dstTablename;    //目标表名
    public String dstColumnName;   //目标表字段
    public String srcTablename;    //源表名
    public String srcColumnName;   //源表字段
    public String convertRule;     //源字段到目标字段的转换规则

}
