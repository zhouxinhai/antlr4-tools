package com.zhouxh.antlr4.tool.sql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouxh.antlr4.tool.sql.api.SqlQueryColumnAliasFilling;
import com.zhouxh.antlr4.tool.sql.bean.ColumnAndAliasName;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 *
 * Create by Howard on 2018/10/12
 */
public class TestSqlQueryColumnAliasFilling {

    void jsonResAssertSuccess(String retJson){
        JSONObject jsonObject = JSON.parseObject(retJson);
        boolean res = jsonObject.getBoolean("success");
        Assert.assertEquals(res,true);
    }

    /**
     * 输入字段 T1.COL1,COL1,COL2,T2.COL2,T3.COL2,COL3,'ZHOU','XIN','HAI',"A STUDENT","A TEACHER", (SELECT 1 FROM DUAL),  (SELECT 2 FROM DUAL), FUNCTION1(1,FUNCTION2(2)),FUNCTION2(4)
     * 输出别名：COL1,COL1_2,COL2,COL2_2,COL2_3,COL3,STR,STR_2STR_3,STR_3,STR_4,STR_5,SUBQUERY,SUBQUERY_2,FUNC,FUNC_2
     */
    @Test
    void test(){
        String partialSql = "distinct t1.col1,col1,col2,t2.col2,t3.col2,col3,'zhou','xin','hai',\"a student\",\"a teacher\", (select 1 from dual),  (select 2 from dual), function1(1,function2(2)),function2(4)";
        Pair<String, List<ColumnAndAliasName>> pair = SqlQueryColumnAliasFilling.aliasFilling(partialSql);
        System.out.println("解析报文："+pair.getLeft());
        System.out.println("==========================================");
        System.out.println("别名bean list："+pair.getRight());
        jsonResAssertSuccess(pair.getLeft());

    }

    @Test
    void testChinesAnnotation(){
        String partialSql = "A \"中文1\",B \"中文2\", (SELECT 1  FROM T1)  \"中文3\" FROM DUAL";
        System.out.println(partialSql);
        Pair<String, List<ColumnAndAliasName>> pair = SqlQueryColumnAliasFilling.aliasFilling(partialSql);

        System.out.println("解析报文："+pair.getLeft());
        System.out.println("==========================================");
        System.out.println("别名bean list："+pair.getRight());
        jsonResAssertSuccess(pair.getLeft());
    }

    @Test
    void testVariable(){
        String partialSql = "ETL_SDC_FROM_THE_ACCOUNT_INFORMATION_${double_year}.DAY_NUMBER     , ETL_SDC_FROM_THE_ACCOUNT_INFORMATION_${double_year}.SLACCT_ACCOUNT_ID\n";
        Pair<String, List<ColumnAndAliasName>> pair = SqlQueryColumnAliasFilling.aliasFilling(partialSql);
        System.out.println("解析报文："+pair.getLeft());
        System.out.println("==========================================");
        System.out.println("别名bean list："+pair.getRight());
        jsonResAssertSuccess(pair.getLeft());
    }


    @Test
    void test2(){
        String partialSql = "f1,substr(f2,3,6),to_char(f3,'YYYY-MM-DD') as count3,${xx_date},a.f4,1+2";
        Pair<String, List<ColumnAndAliasName>> pair = SqlQueryColumnAliasFilling.aliasFilling(partialSql);
        System.out.println("解析报文："+pair.getLeft());
        System.out.println("==========================================");
        System.out.println("别名bean list："+pair.getRight());
        jsonResAssertSuccess(pair.getLeft());
    }

}
