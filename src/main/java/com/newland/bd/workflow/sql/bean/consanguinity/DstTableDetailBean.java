package com.newland.bd.workflow.sql.bean.consanguinity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Create by Howard on 2018/10/22
 */
public class DstTableDetailBean {
    public static final String  NO_DST_TABLE = "no dst table";   //没有目标输出表
    public static final String  NO_DST_COLUMN = "no dst column";   //没有目标字段
    public String tablename = NO_DST_TABLE;
    public List<String> dstColumnNameList = new ArrayList<>(); //目标表字段名list
    public List<ColumnBean>  sourceColumnBeanList;   //每个dstColumnName对应一个sourceColumnBean，dstColumnNameList和sourceColumnBeanList的个数要一致
    public String srcDqlWhereExpression = "";    //距离目标表最近的dql语句where条件后面的内容

    public boolean isCreateTableType = false;
    public boolean isTemporaryTable =false;    //tablename是否是临时表
}
