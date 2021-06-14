package com.zhouxh.antlr4.tool.sql.bean.consanguinity;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Howard on 2018/12/19
 */
public class TableAndPartitionInfo {
    public String tablename;
    public List<ColumnNameAndType> partitionInfos = new ArrayList<>();


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TableAndPartitionInfo{");
        sb.append("tablename='").append(tablename).append('\'');
        sb.append(", partitionInfos=").append(partitionInfos);
        sb.append('}');
        return sb.toString();
    }
}
