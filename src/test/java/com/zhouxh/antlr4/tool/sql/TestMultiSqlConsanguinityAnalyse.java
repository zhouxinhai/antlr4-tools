package com.zhouxh.antlr4.tool.sql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouxh.antlr4.tool.sql.api.SqlConsanguinityAnalyse;
import com.zhouxh.antlr4.tool.sql.api.SqlSplit;
import com.zhouxh.antlr4.tool.sql.bean.consanguinity.DstTableDetailBean;
import com.zhouxh.antlr4.tool.sql.bean.consanguinity.DstTableDetailBeanList;
import com.zhouxh.antlr4.tool.sql.parserExt.consanguinity.MultiSqlConsanguinityAnalyseUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Create by Howard on 2018/11/12
 */
public class TestMultiSqlConsanguinityAnalyse {

    private List<DstTableDetailBean> dstTableDetailBeanList = new ArrayList<>();



    private void displayDstTableDetailBeanList(DstTableDetailBeanList dstTableDetailBeanList){

        for(DstTableDetailBean dstTableDetailBean:dstTableDetailBeanList){
            System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
            TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(dstTableDetailBean);
        }

    }

    private void displayDstTableDetailBean(DstTableDetailBean dstTableDetailBean,String... expectColumnString){
        System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(dstTableDetailBean,expectColumnString);

    }


    @Test
    void testGetCreateTableListFromSqls(){
        String sqls = "create table cx (a VARCHAR, b int);\n" +
                "INSERT into cx SELECT HIVE_output_node9662.stature FROM HIVE_output_node9662;";

        Map<String, List<String>> createTableListFromSqls = SqlConsanguinityAnalyse.getCreateTableListFromSqls(sqls);
        for (Map.Entry<String,List<String>> entry : createTableListFromSqls.entrySet()){
            System.out.println("tablename:"+entry.getKey());

            for (String colname: entry.getValue()){
                System.out.print(colname + " ");
            }
            System.out.println();
            System.out.println("--------------------------------");

        }


    }


    @Test
    void test1(){
        String sqls = "create table cx (a VARCHAR);\n" +
                "INSERT into cx SELECT HIVE_output_node9662.stature FROM HIVE_output_node9662;";

        Pair<String, DstTableDetailBeanList> pair = SqlConsanguinityAnalyse.multiSqlConsanguinityAnalyse(sqls);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
        //displayDstTableDetailBeanList(pair.getRight());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(pair.getRight().get(1),"HIVE_OUTPUT_NODE9662.STATURE");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(pair.getRight().get(1),"HIVE_OUTPUT_NODE9662");
    }

    @Test
    void testAllTableTrace(){
        String sqls = "create temporary table Ta (A1 VARCHAR,a2 VARCHAR) as SELECT b1+1,b2+2 FROM Tb;" +
                "insert into Tc(c1,c2) select a1*1,a2*2 from Ta;"+
                "create table Td (d1 VARCHAR,d2 VARCHAR) as SELECT e1+1,e2+2 FROM Te;" +
                "insert into Tf(f1,f2) select d1*1,d2*2 from Td;" +
                "insert into Tg(g1,g2) select t1.f1, t2.c2 from Tf t1,Tc t2 ;"
                ;


        Pair<String, DstTableDetailBeanList> pair = SqlConsanguinityAnalyse.multiSqlConsanguinityAnalyse(sqls);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());

        DstTableDetailBeanList dstTableDetailBeanList = pair.getRight();
        //displayDstTableDetailBeanList(pair.getRight());
        Assert.assertEquals(dstTableDetailBeanList.size(),5);
        displayDstTableDetailBean(dstTableDetailBeanList.get(0),"(Tb.b1 + 1)","(Tb.b2 + 2)");
        displayDstTableDetailBean(dstTableDetailBeanList.get(1),"((Tb.b1 + 1) * 1)","((Tb.b2 + 2) * 2)");
        displayDstTableDetailBean(dstTableDetailBeanList.get(2),"(Te.e1 + 1)","(Te.e2 + 2)");
        displayDstTableDetailBean(dstTableDetailBeanList.get(3),"((Te.e1 + 1) * 1)","((Te.e2 + 2) * 2)");
        displayDstTableDetailBean(dstTableDetailBeanList.get(4),"((Te.e1 + 1) * 1)","((Tb.b2 + 2) * 2)");

    }

    @Test
    void testOnlyTemporaryTableTrace(){
        String sqls = "create temporary table Ta (A1 VARCHAR,A2 VARCHAR) as SELECT b1+1,b2+2 FROM Tb;" +
                "insert into Tc(c1,c2) select a1*1,a2*2 from Ta;"+
                "create table Td (d1 VARCHAR,d2 VARCHAR) as SELECT e1+1,e2+2 FROM Te;" +
                "insert into Tf(f1,f2) select d1*1,d2*2 from Td;" +  //td不是临时表，不需要进行溯源。
                "insert into Tg(g1,g2) select t1.f1, t2.c2 from Tf t1,Tc t2 ;"
                ;


        List<String> statementList = SqlSplit.getStatementList(sqls);
        DstTableDetailBeanList dstTableDetailBeanList = new DstTableDetailBeanList();
        //在会话中创建的临时表set
        Set<String> temporaryTableSet = new HashSet<>();
        for(String sql:statementList){
            Pair<String, DstTableDetailBeanList> pair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
            String retJson =  pair.getLeft();
            JSONObject jsonObject = JSON.parseObject(retJson);
            boolean res = jsonObject.getBoolean("success");
            String output = jsonObject.getString("output");

            if(!res){//sql血缘分析失败
                System.err.println(output);
                String errMsg = output;
                throw new RuntimeException(errMsg);
            }

            for (DstTableDetailBean dstTableDetailBean:pair.getRight()){
                if(dstTableDetailBean.isTemporaryTable){
                    temporaryTableSet.add(dstTableDetailBean.tablename.toUpperCase());
                }
            }

            dstTableDetailBeanList.addAll(pair.getRight());
        }
        DstTableDetailBeanList finalDstTableDetailBeanList = MultiSqlConsanguinityAnalyseUtil.multiSqlColumnBeanTrace(dstTableDetailBeanList,temporaryTableSet);
//        displayDstTableDetailBeanList(finalDstTableDetailBeanList);
        Assert.assertEquals(dstTableDetailBeanList.size(),5);
        displayDstTableDetailBean(dstTableDetailBeanList.get(0),"(Tb.b1 + 1)","(Tb.b2 + 2)");
        displayDstTableDetailBean(dstTableDetailBeanList.get(1),"((Tb.b1 + 1) * 1)","((Tb.b2 + 2) * 2)");
        displayDstTableDetailBean(dstTableDetailBeanList.get(2),"(Te.e1 + 1)","(Te.e2 + 2)");
        displayDstTableDetailBean(dstTableDetailBeanList.get(3),"(Td.d1 * 1)","(Td.d2 * 2)");
        displayDstTableDetailBean(dstTableDetailBeanList.get(4),"Tf.f1","Tc.c2");

    }

    @Test
    void test(){
        String sqls = "insert into test_cyh_2019(id1)\n" +
                "select HIVE_IN_string from test_hive_in;\n";
        List<String> statementList = SqlSplit.getStatementList(sqls);
        DstTableDetailBeanList dstTableDetailBeanList = new DstTableDetailBeanList();
        //在会话中创建的临时表set
        Set<String> temporaryTableSet = new HashSet<>();
        for(String sql:statementList){
            Pair<String, DstTableDetailBeanList> pair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
            String retJson =  pair.getLeft();
            JSONObject jsonObject = JSON.parseObject(retJson);
            boolean res = jsonObject.getBoolean("success");
            String output = jsonObject.getString("output");

            if(!res){//sql血缘分析失败
                System.err.println(output);
                String errMsg = output;
                throw new RuntimeException(errMsg);
            }

            for (DstTableDetailBean dstTableDetailBean:pair.getRight()){
                if(dstTableDetailBean.isTemporaryTable){
                    temporaryTableSet.add(dstTableDetailBean.tablename.toUpperCase());
                }
            }

            dstTableDetailBeanList.addAll(pair.getRight());
        }
        DstTableDetailBeanList finalDstTableDetailBeanList = MultiSqlConsanguinityAnalyseUtil.multiSqlColumnBeanTrace(dstTableDetailBeanList,temporaryTableSet);

        for(DstTableDetailBean dstTableDetailBean:finalDstTableDetailBeanList){
            if(dstTableDetailBean.tablename.equals(DstTableDetailBean.NO_DST_TABLE)){

                continue;
            }
        }
    }
}
