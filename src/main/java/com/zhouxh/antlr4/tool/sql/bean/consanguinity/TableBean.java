package com.zhouxh.antlr4.tool.sql.bean.consanguinity;

import java.util.List;

/**
 * Create by Howard on 2018/10/19
 */
public class TableBean {
    public String tableName ;//表名(要求大写)
    public String tableAlias ; //表别名(要求大写)
    public TableType tableType = TableType.NORMAL; //如果table是由一个子查询构成，那么tableType为"SUB_QUERY"
    public List<ColumnBean>  columnBeans ;    //如果这个table是由一个子查询构成，里面将存放这个子查询的字段信息

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TableBean{");
        sb.append("tableName='").append(tableName).append('\'');
        sb.append(", tableAlias='").append(tableAlias).append('\'');
        sb.append(", columnBeans=").append(columnBeans);
        sb.append('}');
        return sb.toString();
    }
}
