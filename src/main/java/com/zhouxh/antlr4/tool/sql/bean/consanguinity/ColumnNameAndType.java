package com.zhouxh.antlr4.tool.sql.bean.consanguinity;

/**
 * Create by Howard on 2018/12/19
 */
public class ColumnNameAndType {
    public String columnName;
    public String columnType;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ColumnNameAndType{");
        sb.append("columnName='").append(columnName).append('\'');
        sb.append(", columnType='").append(columnType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
