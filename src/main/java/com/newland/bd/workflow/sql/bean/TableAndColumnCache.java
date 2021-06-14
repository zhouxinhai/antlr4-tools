package com.newland.bd.workflow.sql.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Howard on 2018/10/7
 */
public class TableAndColumnCache {
    public List<TableInfo> tableInfoList = new ArrayList<TableInfo>();
    public List<ColumnInfo> columnInfoList = new ArrayList<ColumnInfo>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TableAndColumnCache{");
        sb.append("tableInfoList=").append(tableInfoList);
        sb.append(", columnInfoList=").append(columnInfoList);
        sb.append('}');
        return sb.toString();
    }
}
