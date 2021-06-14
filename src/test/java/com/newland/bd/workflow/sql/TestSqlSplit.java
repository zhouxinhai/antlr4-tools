package com.newland.bd.workflow.sql;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newland.bd.workflow.sql.api.SqlConsanguinityAnalyse;
import com.newland.bd.workflow.sql.api.SqlInspect;
import com.newland.bd.workflow.sql.api.SqlSplit;
import com.newland.bd.workflow.sql.bean.DbType;
import com.newland.bd.workflow.sql.bean.TableNameAndOpt;
import com.newland.bd.workflow.sql.bean.consanguinity.DstTableDetailBeanList;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Create by Howard on 2018/10/9
 */
public class TestSqlSplit {

    void jsonResAssertSuccess(String retJson){
        JSONObject jsonObject = JSON.parseObject(retJson);
        boolean res = jsonObject.getBoolean("success");
        Assert.assertEquals(res,true);
    }

    void assertRes(List<String> sqlList, int expectedSize){
        int i = 1;
        for(String sql :sqlList){
            System.out.println(i++ + " "+sql);
        }
        Assert.assertEquals(sqlList.size(),expectedSize);
    }

    @Test
    void testEmptyLine(){
        String sqls = "set A =B;;select 1 from test_hive_input_001  inner join  test_hadoop_output_001 on test_hive_input_001.test_int = test_hadoop_output_001.test_int";
        List<String> sqlList = SqlSplit.getStatementList(sqls, false);
        assertRes(sqlList,2);
        sqlList = SqlSplit.getStatementList(sqls, true);
        assertRes(sqlList,3);
    }



    @Test
    void testSplit1(){
//        List<String> sqlList = SqlSplit.getStatementList(";;;select 1 from test_hive_input_001  inner join  test_hadoop_output_001 on test_hive_input_001.test_int = test_hadoop_output_001.test_int;select 1 from test_hive_input_001  inner join  test_hadoop_output_001 on test_hive_input_001.test_int = test_hadoop_output_001.test_int where test_hive_output.test_int > 1;select 1 from test_hive_input_001  inner join  test_hadoop_output_001 on test_hive_input_001.test_int = test_hadoop_output_001.test_int group by test_hive_output.test_string , test_hadoop_output_001.test_int;select 1 from test_hive_input_001  inner join  test_hadoop_output_001 on test_hive_input_001.test_int = test_hadoop_output_001.test_int group by test_hive_output.test_string , test_hadoop_output_001.test_int having min(   test_hadoop_output_001.test_string  );select 1 from test_hive_input_001  inner join  test_hadoop_output_001 on test_hive_input_001.test_int = test_hadoop_output_001.test_int order by test_hive_output.test_float desc , avg( , test_hadoop_output_001.test_int asc) , test_hadoop_output_001.test_string desc;insert into final_out select 1 from test_hive_input_001  inner join  test_hadoop_output_001 on test_hive_input_001.test_int = test_hadoop_output_001.test_int;select test_hive_input_001.test_int from test_hive_input_001  inner join  test_hadoop_output_001 on test_hive_input_001.test_int = test_hadoop_output_001.test_int;insert into final_out select test_hive_input_001.test_int from test_hive_input_001  inner join  test_hadoop_output_001 on test_hive_input_001.test_int = test_hadoop_output_001.test_int where test_hive_output.test_int > 1 group by test_hive_output.test_string , test_hadoop_output_001.test_int having min(   test_hadoop_output_001.test_string  ) order by test_hive_output.test_float desc , avg( , test_hadoop_output_001.test_int asc) , test_hadoop_output_001.test_string desc;\n",true);
        List<String> sqlList = SqlSplit.getStatementList("set A =B;select 1 from test_hive_input_001  inner join  test_hadoop_output_001 on test_hive_input_001.test_int = test_hadoop_output_001.test_int;", false);
        assertRes(sqlList,2);

    }

    @Test
    void testSplit2(){

        String sql = "select\n  PRODUCT_ID,\n  sum(basic_fee)\nfrom\n  $[_CONN_NL_ORACLE_MIDDLE_001_SJKF].FT_MID_PRODUCT_INFO_DAILY\ngroup by\n  PRODUCT_ID\n  ";

        System.out.println(sql);
        List<String> sqlList = SqlSplit.getStatementList(sql);
        assertRes(sqlList,1);

        String retJson = SqlInspect.inspect(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);

    }

    @Test
    void testSplit3(){
        String sqls = "select kpi_code from DEFAULT.tc_sf_activity_d;  /*aaaa*/; /*bbbbb*/";
        List<String> sqlList = SqlSplit.getStatementList(sqls, false);
        assertRes(sqlList,1);
        sqlList = SqlSplit.getStatementList(sqls, true);
        assertRes(sqlList,3);

    }


    @Test
    void testComment(){
        List<String> sqlList = SqlSplit.getStatementList("set A =B; --comment; /*select 1 from tab1;*/\n" +
                                                     "select 1 from dual;select 2 from dual", false);
        assertRes(sqlList,3);

        for(String sql:sqlList){
            Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE, true);
            assert Boolean.TRUE;
        }

        sqlList = SqlSplit.getStatementList("set A =B; --comment; /*select 1 from tab1;*/\n" +
                                                    "select 1 from dual;select 2 from dual", false);
        assertRes(sqlList,3);

        for(String sql:sqlList){
            Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE, true);
            assert Boolean.TRUE;
        }
    }

    @Test
    void test1(){
        List<String> sqlList = SqlSplit.getStatementList("alter table test1.order_created_partition\n" +
                "  add partition (event_month='2018-01;');\n" +
                "LOAD DATA LOCAL INPATH \"/tmp/order_created.txt\" \n" +
                "OVERWRITE INTO TABLE order_created_partition\n" +
                "PARTITION (event_month='2018-01');", ";", false);

        assertRes(sqlList,2);
    }

    @Test
    void test2(){
        List<String> sqlList = SqlSplit.getStatementList(
                "select 1 from dual",";",false);
        assertRes(sqlList,1);

        sqlList = SqlSplit.getStatementList("--移动退租租户数\n" +
                "select 1 from dual", ";", false);
        assertRes(sqlList,1);

        sqlList = SqlSplit.getStatementList("--移动退租租户数 select 1 from dual", ";", false);
        assertRes(sqlList,0);

        sqlList = SqlSplit.getStatementList("set A =B; --comment; /*select 1 from tab1;*/", ";", false);
        assertRes(sqlList,1);

        sqlList = SqlSplit.getStatementList("--注释1\n" +
                "select 1 from dual;\n" +
                "--注释2\n" +
                "select 2 from dual;\n" +
                "--注释3\n" +
                "select 3 from dual", ";", false);
        assertRes(sqlList,3);
    }


    @Test
    void test3(){
        String sqls = "--少的地方\n" +
                "\n" +
                "\n" +
                "insert into sm2_org(org_id) select col1 from blood_test_table_01;   --你好\n" +
                "\n" +
                "\n" +
                "--注释";

        List<String> statementList = SqlSplit.getStatementList(sqls);
        int curSqlIndex = 0;
        for(String sql:statementList){
            Pair<String, DstTableDetailBeanList> pair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
            String retJson =  pair.getLeft();
            JSONObject jsonObject = JSON.parseObject(retJson);
            boolean res = jsonObject.getBoolean("success");
            String output = jsonObject.getString("output");

            if(!res){//sql血缘分析失败
                String errMsg = "第"+(++curSqlIndex)+"句sql，"+output;
                throw new RuntimeException(errMsg);
            }
        }
    }

}
