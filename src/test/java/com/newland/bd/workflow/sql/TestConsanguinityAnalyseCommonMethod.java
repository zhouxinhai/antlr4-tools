package com.newland.bd.workflow.sql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newland.bd.workflow.sql.api.JsonUtil;
import com.newland.bd.workflow.sql.bean.SqlHandleResult;
import com.newland.bd.workflow.sql.bean.TableNameAndOpt;
import com.newland.bd.workflow.sql.bean.consanguinity.ColumnBean;
import com.newland.bd.workflow.sql.bean.consanguinity.DstTableDetailBean;
import com.newland.bd.workflow.sql.parserExt.consanguinity.ConsanguinityAnalyseUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Howard on 2018/11/12
 */
public class TestConsanguinityAnalyseCommonMethod {

    public static void assertRes(Pair<String, List<TableNameAndOpt>> pair, TableNameAndOpt... expectedTableNameAndOpts ){
        //验证解析是否正确
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());

        //把期望的表名全部转为大写
//        for(TableNameAndOpt expectedTableNameAndOpt:expectedTableNameAndOpts){
//            expectedTableNameAndOpt.setTableName(expectedTableNameAndOpt.getTableName().toUpperCase());
//        }

        //打印出TableNameAndOpt
        List<TableNameAndOpt> tableNameAndOptList = pair.getRight();
        for(TableNameAndOpt tableNameAndOpt:tableNameAndOptList){
            System.out.println(tableNameAndOpt);
        }

        if(expectedTableNameAndOpts == null){
            return;
        }

        //验证TableNameAndOpt与期望是否相同
        Assert.assertEquals(tableNameAndOptList.size(),expectedTableNameAndOpts.length);
        Assert.assertEqualsNoOrder(tableNameAndOptList.toArray(),expectedTableNameAndOpts);
        System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
    }

    public static void jsonResAssertSuccess(String retJson){

        SqlHandleResult sqlHandleResult = JsonUtil.convertToSqlHandleResult(retJson);


        if(!sqlHandleResult.getSuccess()){
            System.err.println(sqlHandleResult.getOutput());
        }
        Assert.assertEquals(true,(boolean)sqlHandleResult.getSuccess());
    }

    public static void jsonResAssertFail(String retJson){
        JSONObject jsonObject = JSON.parseObject(retJson);
        boolean res = jsonObject.getBoolean("success");
        String output = jsonObject.getString("output");
        if(!res){
            System.err.println(output);
        }
        Assert.assertEquals(res,false);
    }

    public static void assertInputEntityOfDstTableDetailBean(DstTableDetailBean dstTableDetailBean, String... expectInputEntitys){
        List<String> inputEntitys = ConsanguinityAnalyseUtil.getInputEntity(dstTableDetailBean);

        List<String> realList = new ArrayList<>();
        for(String inputEntity:inputEntitys){
            realList.add(inputEntity.toUpperCase());
        }

        List<String> expectList = new ArrayList<>();
        for(String inputEntity:expectInputEntitys){
            expectList.add(inputEntity.toUpperCase());
        }
        Assert.assertEqualsNoOrder(realList.toArray(),expectList.toArray());


    }


    public static void assertConvertRuleOfDstTableDetailBean(DstTableDetailBean dstTableDetailBean, String... expectColumnString){
        System.out.println("-开始血缘目标信息展示-----------------------------------");
        System.out.println("    目标表名:"+dstTableDetailBean.tablename);
        for(String dstColumnName : dstTableDetailBean.dstColumnNameList){
            System.out.println("        目标表字段名:"+dstColumnName);
        }
        System.out.println("-结束血缘目标信息展示--------------------------------- --");
        if(dstTableDetailBean.sourceColumnBeanList!=null){
            assertColumnBeanList(dstTableDetailBean.sourceColumnBeanList,expectColumnString);
        }
    }

    private static void assertColumnBeanList(List<ColumnBean> columnBeans, String... expectColumnString){
        List<String> columnStringList = new ArrayList<String>();
        if(columnBeans.size() > 0){
            System.out.println("-开始血缘原始信息展示-----------------------------------");
        }
        for(ColumnBean columnBean : columnBeans){
            String columnBeanString = ConsanguinityAnalyseUtil.toConsanguinityFormat(columnBean);
            columnStringList.add(columnBeanString);
            System.out.println("    "+columnBeanString);
        }
        if(columnBeans.size() > 0){
            System.out.println("-结束血缘原始信息展示-----------------------------------");
        }

        if(columnBeans.size() > 0){
            System.out.println("-开始ColumnBean原子字段展示-----------------------------------");
        }
        int i =0;
        for(ColumnBean columnBean : columnBeans){
            System.out.println("    "+"第"+ ++i + "个字段对应的原子字段list");
            List<ColumnBean> atomColumnBeanList =  ConsanguinityAnalyseUtil.columnBeanToAtomColumnBeansByRemoveRepetition(columnBean);
            for(ColumnBean atomColumnBean:atomColumnBeanList){
                System.out.println("        "+atomColumnBean);
            }
        }
        if(columnBeans.size() > 0){
            System.out.println("-结束ColumnBean原子字段展示-----------------------------------");
        }

        if(expectColumnString.length != 0){
            Assert.assertEquals(columnStringList.size(),expectColumnString.length);
            for(int j=0; j<expectColumnString.length; j++){
                Assert.assertEquals(columnStringList.get(j).toUpperCase(),expectColumnString[j].toUpperCase());
            }
        }

    }

}
