package com.zhouxh.antlr4.tool.sql;

import com.zhouxh.antlr4.tool.sql.api.SqlQuerySplit;
import com.zhouxh.antlr4.tool.sql.parserExt.QuerySplitListener;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Create by zhouxinhai on 2019/10/31
 */
public class TestSqlQuerySplit {
    @Test
    public void querySplitTest() {
        String sql = " select  /*+ index(t t_ind) */ distinct ta.c1 as a1,c2 a2,c3, func(c4*c5),(1+2),ta.c6  from ta where c3=3 order by ta.c1 + c2, func(c3+ta.c4),c5";
        Pair<String, QuerySplitListener.SqlStruct> pair = SqlQuerySplit.querySplit(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
        QuerySplitListener.SqlStruct sqlStruct = pair.getRight();

        Assert.assertEquals(sqlStruct.getSortItems().get(0),"ta.c1");
        Assert.assertEquals(sqlStruct.getSortItems().get(1),"c2");
        Assert.assertEquals(sqlStruct.getSortItems().get(2),"c3");
        Assert.assertEquals(sqlStruct.getSortItems().get(3),"ta.c4");
        Assert.assertEquals(sqlStruct.getSortItems().get(4),"c5");


        Assert.assertEquals(sqlStruct.getSelectItemList().get(0).getColumnName(), "ta.c1");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(0).getAlias(), "a1");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(0).getFullColumnName(), "ta.c1 as a1");

        Assert.assertEquals(sqlStruct.getSelectItemList().get(1).getColumnName(), "c2");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(1).getAlias(), "a2");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(1).getFullColumnName(), "c2 a2");

        Assert.assertEquals(sqlStruct.getSelectItemList().get(2).getColumnName(), "c3");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(2).getAlias(), "");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(2).getFullColumnName(), "c3");

        Assert.assertEquals(sqlStruct.getSelectItemList().get(3).getColumnName(), "func(c4*c5)");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(3).getAlias(), "");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(3).getFullColumnName(), "func(c4*c5)");

        Assert.assertEquals(sqlStruct.getSelectItemList().get(4).getColumnName(), "(1+2)");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(4).getAlias(), "");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(4).getFullColumnName(), "(1+2)");

        Assert.assertEquals(sqlStruct.getSelectItemList().get(5).getColumnName(), "ta.c6");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(5).getAlias(), "");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(5).getFullColumnName(), "ta.c6");
    }

    @Test
    public void test1() {
        String sql = "select  distinct c1 a1,c2 a2,c3 a3,(select f1 from tb) from ta where c3=3 order by c2";

        Pair<Boolean, String> pair = SqlQuerySplit.regroupQuerySql(sql, null);
        Assert.assertEquals(new Boolean(true), pair.getLeft());
        Assert.assertEquals(sql, pair.getRight());

        int[] orders = new int[4];
        orders[0] = 3;
        orders[1] = 2;
        orders[2] = 1;
        orders[3] = 0;
        pair = SqlQuerySplit.regroupQuerySql(sql, orders);
        Assert.assertEquals(new Boolean(true), pair.getLeft());
        Assert.assertEquals(pair.getRight(), "select  distinct (select f1 from tb),c3 a3,c2 a2,c1 a1 from ta where c3=3 order by c2");
    }

    @Test
    public void test2() {
        String sql = "drop table ta";
        Pair<Boolean, String> pair = SqlQuerySplit.regroupQuerySql(sql, null);
        System.out.println(pair.getRight());
        Assert.assertEquals(new Boolean(false), pair.getLeft());
    }

    @Test
    public void test3() {
        String sql = "select * from tab1";
        Pair<Boolean, String> pair = SqlQuerySplit.regroupQuerySql(sql, null);
        System.out.println(pair.getRight());
        Assert.assertEquals(new Boolean(false), pair.getLeft());
    }

    @Test
    public void test4() {
        String sql = "select FUNC1(C1,FUNC2(C2,C3)),C3 FROM (SELECT C1_1 C1,C1_2 C2,C1_3 C3 FROM TABLE1) T1";
        int[] orders = new int[2];
        orders[0] = 1;
        orders[1] = 0;
        Pair<Boolean, String> pair = SqlQuerySplit.regroupQuerySql(sql, orders);
        Assert.assertEquals(new Boolean(true), pair.getLeft());
        Assert.assertEquals(pair.getRight(), "select C3,FUNC1(C1,FUNC2(C2,C3)) from (SELECT C1_1 C1,C1_2 C2,C1_3 C3 FROM TABLE1) T1");
    }

    @Test
    public void test5() {
        String sql = "select (CASE  when a.modify_time > b.SUBMIT_TIME   or (b .submit_time is NULL and a.MODIFY_TIME is NOT null) then '1' else  '0' end )status\n" + "from\n" + "  (select modify_time,task_id from udap_dev_schedule_info where task_id = '7bf5ae78a8724b2e8c537c1d4a0fe60c') a\n" + "LEFT JOIN\n" + "  (select submit_time ,task_id from udap_dev_task_submit WHERE task_id = '7bf5ae78a8724b2e8c537c1d4a0fe60c')  b\n" + "  ON\n" + "    a.task_id = b.task_id";
        System.out.println(sql);
        Pair<String, QuerySplitListener.SqlStruct> pair = SqlQuerySplit.querySplit(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
        Assert.assertEquals(pair.getRight().getSelectItemList().size(),1);
    }

    @Test
    public void test6() {
        String sql = "select f1,substr(f2,3,6),to_char(f3,'YYYY-MM-DD') as count3,${xx_date},a.f4 from A,left join B";
        Pair<String, QuerySplitListener.SqlStruct> pair = SqlQuerySplit.querySplit(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
        Assert.assertEquals(pair.getRight().getSelectItemList().size(),5);

        QuerySplitListener.SqlStruct sqlStruct = pair.getRight();
        Assert.assertEquals(sqlStruct.getSelectItemList().get(0).getColumnName(), "f1");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(0).getAlias(), "");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(0).getFullColumnName(), "f1");

        Assert.assertEquals(sqlStruct.getSelectItemList().get(1).getColumnName(), "substr(f2,3,6)");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(1).getAlias(), "");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(1).getFullColumnName(), "substr(f2,3,6)");

        Assert.assertEquals(sqlStruct.getSelectItemList().get(2).getColumnName(), "to_char(f3,'YYYY-MM-DD')");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(2).getAlias(), "count3");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(2).getFullColumnName(), "to_char(f3,'YYYY-MM-DD') as count3");

        Assert.assertEquals(sqlStruct.getSelectItemList().get(3).getColumnName(), "${xx_date}");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(3).getAlias(), "");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(3).getFullColumnName(), "${xx_date}");

        Assert.assertEquals(sqlStruct.getSelectItemList().get(4).getColumnName(), "a.f4");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(4).getAlias(), "");
        Assert.assertEquals(sqlStruct.getSelectItemList().get(4).getFullColumnName(), "a.f4");



    }


}
