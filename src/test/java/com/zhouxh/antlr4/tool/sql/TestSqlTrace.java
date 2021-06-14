package com.zhouxh.antlr4.tool.sql;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouxh.antlr4.tool.sql.api.SqlTrace;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Create by Howard on 2018/10/8
 */
public class TestSqlTrace {

    void jsonResAssertSuccess(String retJson){
        JSONObject jsonObject = JSON.parseObject(retJson);
        boolean res = jsonObject.getBoolean("success");
        Assert.assertEquals(res,true);
    }

    @Test
    void testSimpleQuery(){
        String sql = "select c1,t1.c2,c3 from user_product t1";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testUnionQuery(){
        String sql = "select t1.c1,t1.c2 from user_product t1\n" +
                "minus\n" +
                "select t2.m1,t2.m2 from user_product_buckets t2";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testJoinQuery1(){
        String sql = "select t1.product_id,t2.user_id from user_product t1 inner join user_info t2 on(t1.user_id = t2.user_id)";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testJoinQuery2(){
        String sql = "select t1.c1,t2.c3 from table1 t1, table2 t2 where t1.c1= t2.c1";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testNestingQuery1(){
        String sql = "select t2.c1,t2.c2 from (select a1,a2 from table1) t1, table2 t2 where t1.a1 = t2.c1";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testNestingQuery2(){
        String sql = "select t1.c1,t1.c2 from table1 t1 where c3 in (select a1 from table2)";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    void testCreateTable1(){
        String sql = "CREATE TABLE USER_INFO\n" +
                "(\n" +
                "USER_ID VARCHAR(15),\n" +
                "NAME VARCHAR(20),\n" +
                "MSISDN NUMBER(20)\n" +
                ")";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testCreateTable2(){
        String sql = "CREATE TABLE TAB_ZXH AS SELECT T2.C1,T2.C2 FROM (SELECT A1,A2 FROM TABLE1) T1, TABLE2 T2 WHERE T1.A1 = T2.C1";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testInsertTable(){
        String sql = "INSERT INTO TAB_ZXH(B1,B2,B3)  SELECT T2.C1,T2.C2 FROM (SELECT A1,A2 FROM TABLE1) T1, TABLE2 T2 WHERE T1.A1 = T2.C1";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testDelete(){
        String sql = "DELETE FROM ZXH_TBL WHERE USER_ID in (select user_id from user_info)";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testUpdate(){
        String sql = "update zxh_tb1 set c1=(select a1 from zxh_tb2),c2=2 where c3=3";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testDropTable(){
        String sql = "drop table zxh_tbl";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testTruncateTable(){
        String sql = "truncate table zxh_tbl";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testAddColumn(){
        String sql = "ALTER TABLE USER_PRODUCT ADD COLUMN C1 VARCHAR2(100)";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testDropColumn(){
        String sql = "ALTER TABLE USER_PRODUCT DROP COLUMN C1";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }


    @Test
    void testRenameColumn(){
        String sql = "ALTER TABLE USER_PRODUCT  RENAME COLUMN C1 TO C2";
        String retJson = SqlTrace.trace(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }
}
