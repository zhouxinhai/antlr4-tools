package com.newland.bd.workflow.sql.bean;

/**
 * Create by Howard on 2018/10/7
 */
public class TableInfo {
    public String tableName ="";//表名(要求大写)
    public String tableAlias =""; //表别名(要求大写)
    public boolean isSubQuery = false; //这个表是否是子查询的形式，比如SELECT T2.C1,T2.C2 FROM (SELECT A1,A2 FROM TABLE1) T1, TABLE2 T2 WHERE T1.A1 = T2.C1; 其中的T1表就是子查询表

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TableInfo{");
        sb.append("tableName='").append(tableName).append('\'');
        sb.append(", tableAlias='").append(tableAlias).append('\'');
        sb.append(", isSubQuery=").append(isSubQuery);
        sb.append('}');
        return sb.toString();
    }
}
