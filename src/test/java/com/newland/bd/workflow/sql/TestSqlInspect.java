package com.newland.bd.workflow.sql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newland.bd.workflow.sql.api.SqlInspect;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Create by Howard on 2018/10/8
 */
public class TestSqlInspect {

    void jsonResAssertSuccess(String retJson){
        JSONObject jsonObject = JSON.parseObject(retJson);
        boolean res = jsonObject.getBoolean("success");
        Assert.assertEquals(res,true);
    }

    void jsonResAssertFailed(String retJson){
        JSONObject jsonObject = JSON.parseObject(retJson);
        boolean res = jsonObject.getBoolean("success");
        Assert.assertEquals(res,false);
    }


    @Test
    void testDCL(){
        String sql = "commit;rollback";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }



    @Test
    void testQuery1(){
        String sql = "select c1,location from user_product\n" +
                "minus\n" +
                "select c1,c2 from user_product_buckets;";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);

    }

    @Test
    void testQuery2(){
        String sql = "select 1 from ft_mid_user_daily  inner join  ft_mid_user_month on ft_mid_user_daily.user_id = ft_mid_user_month.user_id where ft_mid_user_month.user_id  =  1 and ft_mid_user_month.user_id <>  123 or ft_mid_user_month.user_name  not like  '%123%'";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testQuery3(){
        String sql = "select 1 from ft_mid_user_daily  inner join  ft_mid_user_month  where ft_mid_user_month.user_id  =  1 and ft_mid_user_month.user_id <>  123 or ft_mid_user_month.user_name  not like  '%123%'";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testInsert1(){
        String sql = "insert into user_product_buckets (user_id,product_id,year) values(\"001\",\"p001\",\"2018\"),(\"002\",\"p002\",\"2018\")";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testInsert2(){
        String sql = "insert into test_cyh(c1,c2,c3) select test_int,test_string,test_string from test_hive_input;";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testInsert3(){
        String sql = "insert into test_cyh(c1,c2) (SELECT C1,C2 FROM T2 UNION SELECT C1,C2 FROM T3) ";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testInsert4(){
        String sql = "insert into table LMQ_TEST(id,name,address)\n" +
                "select '11','22','33' from LMQ_TEST";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testInsert5(){
        String sql = "insert into user_product_buckets values(\"001\",\"p001\",\"2018\"),(\"002\",\"p002\",\"2018\"),(\"003\",\"p003\",\"2018\")";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void addColumnTest(){
        String sql  = "alter table user_product add column c1 varchar2(100)";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void truncateTest(){
        String sql  = "truncate table t1";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testMultiLineSql(){
        String sql  = "SELECT c1 FROM USER_PRODUCT; \n" +
                "SELECT c2 FROM USER_PRODUCT INNER JOIN USER_INFO ON(USER_PRODUCT.USER_ID = USER_INFO.USER_ID);" +
                " truncate table t1";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testTableAlias(){
        String sql = "SELECT A.C1 FROM NEWTABLE A \n" +
                "INNER JOIN USER_INFO B\n" +
                "ON ( A.COL1=B.USER_ID)\n" +
                "LEFT JOIN NEWTABLE C\n" +
                "ON (C.COL1=B.USER_ID);";
        System.out.println(sql);
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }


    @Test
    void testVariableIdentifier(){
        String sql = "select t1.c1 from t_a t1,t_b t2 where t2.c2 >= $[_task_id]";
        String retJson = SqlInspect.inspect(sql);
//        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testGroupBy(){
        String sql = "SELECT  B.PATH_ID,HOST_IP,COUNT(*),MAX(TEST_FLAG),SUM(TEST_FLAG),ABS(PATH_TYPE),COUNT(PATH_TYPE),'111',func(host_ip) FROM BILL.RATING_CONTROL A, (SELECT C1,C2,COUNT(*) FROM T1 GROUP BY C1,C2) B  WHERE B.PATH_NAME = 0 GROUP BY B.PATH_ID,HOST_IP,ABS(PATH_TYPE) HAVING B.PATH_ID > 1 AND host_ip = 1";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testUnion(){
        String sql = "SELECT (SELECT M1 FROM TT1 MINUS SELECT M2 FROM TT2),C2 FROM T2 UNION SELECT C1,C2 FROM T3;\n"
                +"SELECT (SELECT M1 FROM TT1 MINUS SELECT M2 FROM TT2),C2 FROM T2 UNION ALL SELECT C1,C2 FROM T3;\n";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testPrepositionVariable(){
        String sql = "SELECT A_$[B]  FROM DUAL";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testPostpositionVariable(){
        String sql = "SELECT ${B}_A  FROM DUAL";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testpositionVariable(){
        String sql = "SELECT A_${B}_C  FROM DUAL";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }


    @Test
    void testVariable(){
        String sql = "SELECT ${A}_${B}_${C}  FROM DUAL";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testRepairTable(){
        String sql = "MSCK REPAIR TABLE table_name";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testShowPartitions(){
        String sql = "show partitions primitives_parquet_p";
        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testSet(){
        String sql = "set hive.auto.convert.join=false";
        String retJson = SqlInspect.inspect(sql);
        jsonResAssertSuccess(retJson);

        sql = "set hive.auto.convert.left=false";
        retJson = SqlInspect.inspect(sql);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testColumnAlias(){
        String sql = "select c1 \"字段别名\" from dual;";
        String retJson = SqlInspect.inspect(sql);
        jsonResAssertSuccess(retJson);

        sql = "select c1 '字段别名' from dual";
        retJson = SqlInspect.inspect(sql);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testOracleJoinType(){
        String sql = "Select 1 from t_A a,t_B b where a.id=b.id(+);";
        String retJson = SqlInspect.inspect(sql);
        jsonResAssertSuccess(retJson);

        sql = "Select 1 from t_A a,t_B b where a.id(+)=b.id;";
        retJson = SqlInspect.inspect(sql);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void testNologging(){
        String sql = "create table ods_list_t nologging as select c1 from ods_list;";
        String retJson = SqlInspect.inspect(sql);
        jsonResAssertSuccess(retJson);

        sql = "insert /*+ append, parallel */ into ods_list_t nologging\n" +
                "select c1 from ods_list;";
        retJson = SqlInspect.inspect(sql);
        jsonResAssertSuccess(retJson);
    }

}
