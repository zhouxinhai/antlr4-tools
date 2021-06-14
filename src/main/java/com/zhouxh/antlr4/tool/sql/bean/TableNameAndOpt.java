package com.zhouxh.antlr4.tool.sql.bean;

import java.util.Objects;

/**
 * Create by Howard on 2018/12/5
 */
public class TableNameAndOpt {
    private String tableName ="";  //用户模式.表名(大写)   或者是一些资源的名字，如：数据库名字，索引名字等等
    private TableOpt tableOpt= TableOpt.UNKNOWN;

    public TableNameAndOpt(String tableName, TableOpt tableOpt) {
        this.tableName = tableName;
        this.tableOpt = tableOpt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableNameAndOpt that = (TableNameAndOpt) o;
        return tableName.equalsIgnoreCase(that.tableName) &&
                tableOpt == that.tableOpt;


    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, tableOpt);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public TableOpt getTableOpt() {
        return tableOpt;
    }

    public void setTableOpt(TableOpt tableOpt) {
        this.tableOpt = tableOpt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TableNameAndOpt{");
        sb.append("tableName='").append(tableName).append('\'');
        sb.append(", tableOpt=").append(tableOpt);
        sb.append('}');
        return sb.toString();
    }
}
