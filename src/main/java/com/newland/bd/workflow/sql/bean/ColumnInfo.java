package com.newland.bd.workflow.sql.bean;

/**
 * Create by Howard on 2018/10/7
 */


public class ColumnInfo {
    public String colunmName = "";    //字段名字(要求大写)
    public String belongToTableAlias = ""; //该字段归属于表的别名的名字(要求大写)

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ColumnInfo{");
        sb.append("colunmName='").append(colunmName).append('\'');
        sb.append(", belongToTableAlias='").append(belongToTableAlias).append('\'');
        sb.append('}');
        return sb.toString();
    }
}


