package com.zhouxh.antlr4.tool.sql.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Create by Howard on 2018/10/7
 */
public class TableAndColumnRelation {
    public String tableName ;//表名(要求大写)
    public TableOpt tableOpt = TableOpt.UNKNOWN;
    public boolean isSubQuery = false; //这个表是否是子查询的形式，比如SELECT T2.C1,T2.C2 FROM (SELECT A1,A2 FROM TABLE1) T1, TABLE2 T2 WHERE T1.A1 = T2.C1; 其中的T1表就是子查询表
    public Set<String> columnList = new HashSet<String>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TableAndColumnRelation{");
        sb.append("tableName='").append(tableName).append('\'');
        sb.append(", tableOpt=").append(tableOpt);
        sb.append(", isSubQuery=").append(isSubQuery);
        sb.append(", columnList=").append(columnList);
        sb.append('}');
        return sb.toString();
    }
}
