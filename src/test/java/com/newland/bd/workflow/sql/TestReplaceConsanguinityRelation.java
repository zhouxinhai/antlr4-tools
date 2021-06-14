package com.newland.bd.workflow.sql;

import com.newland.bd.workflow.sql.parserExt.consanguinity.ConsanguinityAnalyseUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Create by Howard on 2018/11/9
 */
public class TestReplaceConsanguinityRelation {

    String testReplaceConsanguinityRelation(String consanguinityRelation,String oldTableColumnName,String newTableColumnName,String expectNewConsanguinityRelation){
        String actualNewConsanguinityRelation = ConsanguinityAnalyseUtil.replaceConsanguinityRelation(consanguinityRelation, oldTableColumnName, newTableColumnName);
        System.out.println(actualNewConsanguinityRelation);
        Assert.assertEquals(actualNewConsanguinityRelation,expectNewConsanguinityRelation);
        return actualNewConsanguinityRelation ;
    }


    @Test void test1(){
        String consanguinityRelation = "table1.c1";
        String oldTableColumnName = "table1.c1";
        String newTableColumnName = "table2.col2";
        String expectNewConsanguinityRelation = "table2.col2";
        testReplaceConsanguinityRelation(consanguinityRelation,oldTableColumnName,newTableColumnName,expectNewConsanguinityRelation);
    }

    @Test void test2(){
        String consanguinityRelation = "(table1.c1)";
        String oldTableColumnName = "table1.c1";
        String newTableColumnName = "table2.col2";
        String expectNewConsanguinityRelation = "(table2.col2)";
        testReplaceConsanguinityRelation(consanguinityRelation,oldTableColumnName,newTableColumnName,expectNewConsanguinityRelation);
    }

    @Test void test3(){
        String consanguinityRelation = "table1.c1)";
        String oldTableColumnName = "table1.c1";
        String newTableColumnName = "table2.col2";
        String expectNewConsanguinityRelation = "table2.col2)";
        testReplaceConsanguinityRelation(consanguinityRelation,oldTableColumnName,newTableColumnName,expectNewConsanguinityRelation);
    }

    @Test void test4(){
        String consanguinityRelation = "(table1.c1";
        String oldTableColumnName = "table1.c1";
        String newTableColumnName = "table2.col2";
        String expectNewConsanguinityRelation = "(table2.col2";
        testReplaceConsanguinityRelation(consanguinityRelation,oldTableColumnName,newTableColumnName,expectNewConsanguinityRelation);
    }

    @Test void test5(){
        String consanguinityRelation = "${table1}.$[column]";
//        String oldTableColumnName = "\\$\\{table1\\}\\.\\$\\[column\\]";
        String oldTableColumnName = "${table1}.$[column]";
        String newTableColumnName = "table2.col2";
        String expectNewConsanguinityRelation = "table2.col2";
        testReplaceConsanguinityRelation(consanguinityRelation,oldTableColumnName,newTableColumnName,expectNewConsanguinityRelation);
    }

    @Test void test6(){
        String consanguinityRelation = "(CASE WHEN (SUM(BIMID.MD_BS_TOWER_INFO_DAY_${THREE_MONTH}.TYPE_ID_B)=0) THEN 1 WHEN (SUM((CASE WHEN BIMID.MD_BS_TOWER_INFO_DAY_${THREE_MONTH}.TYPE_ID NOT IN(88430221 , 88430220) THEN 1 ELSE 0 END))=0) THEN 2 WHEN (SUM((CASE WHEN BIMID.MD_BS_TOWER_INFO_DAY_${THREE_MONTH}.TYPE_ID NOT IN(88430221 , 88430220) THEN 1 ELSE 0 END))>0) THEN 3 WHEN SUM((CASE WHEN BIMID.MD_BS_TOWER_INFO_DAY_${THREE_MONTH}.TYPE_ID NOT IN(88430221 , 88430220) THEN 1 ELSE 0 END)) IS NULL THEN 4 END)";
        String oldTableColumnName = "BIMID.MD_BS_TOWER_INFO_DAY_${THREE_MONTH}.TYPE_ID";
        String newTableColumnName = "table1.c1";
        String expectNewConsanguinityRelation = "(CASE WHEN (SUM(BIMID.MD_BS_TOWER_INFO_DAY_${THREE_MONTH}.TYPE_ID_B)=0) THEN 1 WHEN (SUM((CASE WHEN table1.c1 NOT IN(88430221 , 88430220) THEN 1 ELSE 0 END))=0) THEN 2 WHEN (SUM((CASE WHEN table1.c1 NOT IN(88430221 , 88430220) THEN 1 ELSE 0 END))>0) THEN 3 WHEN SUM((CASE WHEN table1.c1 NOT IN(88430221 , 88430220) THEN 1 ELSE 0 END)) IS NULL THEN 4 END)";

        consanguinityRelation = testReplaceConsanguinityRelation(consanguinityRelation,oldTableColumnName,newTableColumnName,expectNewConsanguinityRelation);
        oldTableColumnName = "BIMID.MD_BS_TOWER_INFO_DAY_${THREE_MONTH}.TYPE_ID_B";
        newTableColumnName = "table2.c2";
        expectNewConsanguinityRelation = "(CASE WHEN (SUM(table2.c2)=0) THEN 1 WHEN (SUM((CASE WHEN table1.c1 NOT IN(88430221 , 88430220) THEN 1 ELSE 0 END))=0) THEN 2 WHEN (SUM((CASE WHEN table1.c1 NOT IN(88430221 , 88430220) THEN 1 ELSE 0 END))>0) THEN 3 WHEN SUM((CASE WHEN table1.c1 NOT IN(88430221 , 88430220) THEN 1 ELSE 0 END)) IS NULL THEN 4 END)";
        testReplaceConsanguinityRelation(consanguinityRelation,oldTableColumnName,newTableColumnName,expectNewConsanguinityRelation);
    }

}
