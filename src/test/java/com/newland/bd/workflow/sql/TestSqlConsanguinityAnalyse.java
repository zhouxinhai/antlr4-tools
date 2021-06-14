package com.newland.bd.workflow.sql;

import com.newland.bd.workflow.sql.api.SqlConsanguinityAnalyse;
import com.newland.bd.workflow.sql.bean.consanguinity.DstTableDetailBean;
import com.newland.bd.workflow.sql.bean.consanguinity.DstTableDetailBeanList;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Create by Howard on 2018/10/20
 */
public class TestSqlConsanguinityAnalyse {


    @Test
    void testSet(){
        String sql = "set a=1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        Assert.assertEquals(stringListPair.getRight().get(0).tablename, DstTableDetailBean.NO_DST_TABLE);
    }


    @Test
    void testSimple(){
        String sql = "SELECT C1,T1.C2,1,'nihao' FROM TA T1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"TA.C1","TA.C2","1","'NIHAO'");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"ta");
    }


   @Test
    void testJoin1(){

        String sql = "SELECT T1.C1 COL1,T2.C2 COL2,T3.C3 COL3 FROM TA PARTITION(AA) T1,TB T2,TC T3 " +
                "WHERE T1.USER_ID = T2.USER_ID AND T2.USER_ID = T3.USER_ID";

       Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
       TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
       TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"TA.C1","TB.C2","TC.C3");
       TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"ta","tb","tc");
    }

    @Test
    void testJoin2(){
        String sql = "SELECT A.C1,B.C2,C.C3,D.C4 FROM TABLE1 a \n" +
                "INNER JOIN TABLE2 B\n" +
                "ON  (A.COL1=B.USER_ID)\n" +
                "LEFT JOIN TABLE3 C\n" +
                "ON (C.COL1=B.USER_ID)" +
                "LEFT JOIN TABLE4 D\n" +
                "ON (D.COL1=C.USER_ID)";

        System.out.println(sql);
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE1.C1","TABLE2.C2","TABLE3.C3","TABLE4.C4");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"table1","table2","table3","table4");
    }

    @Test
    void testSubQueryInSelectItem(){

        String sql = "SELECT A.C1, C2, " +
                "(select t2.b1,t2.b2 from table2 T2) M," +
                "(select t3.q1,t3.q2 from table3 T3) N," +
                "C3" +
                " FROM TABLE1 T1";
        System.out.println(sql);
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE1.C1","TABLE1.C2","TABLE2.B1","TABLE3.Q1","TABLE1.C3");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"table1","table2","table3");
    }

     @Test
    void testFunctionInSelectItem1(){
        String sql = "SELECT FUNC1(T1.C1,FUNC2(T2.C2,FUNC3(T1.C3)),FUNC4(C4)) FROM TABLE1 T1,TABLE2 T2";
         Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
         TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
         TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"FUNC1(TABLE1.C1 , FUNC2(TABLE2.C2 , FUNC3(TABLE1.C3)) , FUNC4(UNKNOWN.C4))");
         TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"table1","table2");
    }

    @Test
    void testFunctionInSelectItem2(){
        String sql = "CREATE TABLE DSTTABLE(N1,N2) AS SELECT FUNC1(T1.C1,FUNC2(T2.C2,FUNC3(T1.C3)),FUNC4(T2.C4+3)),SUBSTR(T2.C3,2,4) FROM TABLE1 T1 inner join TABLE2 T2 ON TABLE1.C1 = TABLE2.C2";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"FUNC1(TABLE1.C1 , FUNC2(TABLE2.C2 , FUNC3(TABLE1.C3)) , FUNC4((TABLE2.C4 + 3)))","SUBSTR(TABLE2.C3 , 2 , 4)");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"table1","table2");
    }

    @Test
    void testFunctionInSelectItem3(){
        String sql = "CREATE TABLE DSTTABLE(N1) AS SELECT FUNC1(C1,(SELECT C2,C3 FROM TABLE2)) FROM TABLE1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"FUNC1(TABLE1.C1 , TABLE2.C2)");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"table1","table2");
    }

    @Test
    void testFunctionInSelectItem4(){
        String sql = "SELECT LISTAGG(T.ENAME, ',') WITHIN GROUP (ORDER BY T.ENAME) ENAME1, MGR FROM EMP T WHERE MGR =7902";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"LISTAGG(EMP.ENAME , ',')","EMP.MGR");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"EMP");
    }

    @Test
    void testGroupby(){
        String sql = "CREATE TABLE DSTTABLE(N1,N2,N3) AS SELECT T1.C1,T2.C2,COUNT(*) FROM TABLE1 T1,TABLE2 T2 WHERE T1.USER_ID = T2.USER_ID GROUP BY T1.C1,T2.C2";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE1.C1","TABLE2.C2","COUNT(UNKNOWN.*)");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"table1","table2");
    }

    @Test
    void testUnion1(){
        String sql = "CREATE TABLE DSTTABLE(N1,N2) AS SELECT C1_1,C1_2 FROM TABLE1 UNION ALL SELECT C2_1,C2_2,C2_3 FROM TABLE2 MINUS SELECT C3_1+1,C3_2+2,C3_3 FROM TABLE3";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"UNION ALL{TABLE1.C1_1 , MINUS{TABLE2.C2_1 , (TABLE3.C3_1 + 1)}}","UNION ALL{TABLE1.C1_2 , MINUS{TABLE2.C2_2 , (TABLE3.C3_2 + 2)}}");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE1","TABLE2","TABLE3");
    }

    @Test
    void testUnion2(){
        String sql =
                "insert into blood_test_table_04(coltest1,coltest2,coltest3,coltest4,coltest5)\n" +
                        "select t5.col1,t5.col2,t5.col3,t5.col4,t5.col5 from\n" +
                        "(\n" +
                        "select t1.col1,t1.col2,t1.col3,t1.col4,t1.col5 from blood_test_table_01 t1\n" +
                        "union\n" +
                        "select t2.col1,t2.col2,t2.col3,t2.col4,t2.col5 from blood_test_table_02 t2\n" +
                        "union\n" +
                        "select t3.col1,t3.col2,t3.col3,t3.col4,t3.col5 from blood_test_table_03 t2\n" +
                        ") t5";
        System.out.println(sql);
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"UNION{UNION{BLOOD_TEST_TABLE_01.COL1 , BLOOD_TEST_TABLE_02.COL1} , BLOOD_TEST_TABLE_03.COL1}",
                "UNION{UNION{BLOOD_TEST_TABLE_01.COL2 , BLOOD_TEST_TABLE_02.COL2} , BLOOD_TEST_TABLE_03.COL2}",
                "UNION{UNION{BLOOD_TEST_TABLE_01.COL3 , BLOOD_TEST_TABLE_02.COL3} , BLOOD_TEST_TABLE_03.COL3}",
                "UNION{UNION{BLOOD_TEST_TABLE_01.COL4 , BLOOD_TEST_TABLE_02.COL4} , BLOOD_TEST_TABLE_03.COL4}",
                "UNION{UNION{BLOOD_TEST_TABLE_01.COL5 , BLOOD_TEST_TABLE_02.COL5} , BLOOD_TEST_TABLE_03.COL5}");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"BLOOD_TEST_TABLE_01","BLOOD_TEST_TABLE_02","BLOOD_TEST_TABLE_03");

    }

    @Test
    void testUnion3(){
        String sql =
                "insert into blood_test_table_05(coltest1,coltest2,coltest3,coltest4,coltest5)\n" +
                        "select t4.col1,t4.col2,t5.col3,t5.col4,t5.col5 from\n" +
                        "(\n" +
                        "select t1.col1,t1.col2,t1.col3,t1.col4,t1.col5 from blood_test_table_01 t1\n" +
                        "union\n" +
                        "select t2.col1,t2.col2,t2.col3,t2.col4,t2.col5 from blood_test_table_02 t2\n" +
                        "union\n" +
                        "select t3.col1,t3.col2,t3.col3,t3.col4,t3.col5 from blood_test_table_03 t2\n" +
                        ") t4, blood_test_table_04 t5";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),
                "UNION{UNION{BLOOD_TEST_TABLE_01.COL1 , BLOOD_TEST_TABLE_02.COL1} , BLOOD_TEST_TABLE_03.COL1}",
                "UNION{UNION{BLOOD_TEST_TABLE_01.COL2 , BLOOD_TEST_TABLE_02.COL2} , BLOOD_TEST_TABLE_03.COL2}",
                "BLOOD_TEST_TABLE_04.COL3",
                "BLOOD_TEST_TABLE_04.COL4",
                "BLOOD_TEST_TABLE_04.COL5");

    }

    @Test
    void testTableOfSubQuery(){
        String sql = "CREATE TABLE DSTTABLE(N1) AS SELECT FUNC1(C1,FUNC2(C2,C3)) FROM (SELECT C1_1 C1,C1_2 C2,C1_3 C3 FROM TABLE1) T1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"FUNC1(TABLE1.C1_1 , FUNC2(TABLE1.C1_2 , TABLE1.C1_3))");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE1");

    }

    @Test
    void testArithmetic(){
        String sql = "CREATE TABLE DSTTABLE(N1) AS SELECT C1 % 3+1 A FROM TABLE1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"((TABLE1.C1 % 3) + 1)");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE1");
    }

    @Test
    void testCreateTableAsSelect(){
        String sql = "CREATE TABLE TABLE1(N1,N2,N3) AS SELECT T1.C1 COL1,T2.C2 COL2,T3.C3 COL3 FROM TA T1,TB T2,TC T3 WHERE T1.COL1=T2.COL2 AND T2.COL2=T3.COL3";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"TA.C1","TB.C2","TC.C3");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"TA","TB","TC");
    }

    @Test
    void testInsertIntoSelect(){
        String sql = "INSERT INTO TABLE1(N1,N2,N3) SELECT T1.C1 COL1,T2.C2 COL2,T3.C3 COL3 FROM TA T1,TB T2,TC T3";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"TA.C1","TB.C2","TC.C3");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"TA","TB","TC");
    }

    @Test
    void testUpdateBySet(){
        String sql = "UPDATE TABLE1 SET " +
                "(A1,A2) = (SELECT FUNC1(B1+1),FUNC2(B2+2) FROM TABLE2 WHERE TABLE1.A5 = TABLE2.B5)," +
                "(A3,A4) = (SELECT FUNC3(B3+3),FUNC4(B4+4) FROM TABLE2 WHERE TABLE1.A5 = TABLE2.B5), " +
                "A5 = FUNC5(A5+5,6)" +
                "WHERE A5 IN (SELECT A5 FROM TABLE2 WHERE  TABLE1.A5 = TABLE2.B5)";
        System.out.println(sql);
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"FUNC1((TABLE2.B1 + 1))",
                "FUNC2((TABLE2.B2 + 2))",
                "FUNC3((TABLE2.B3 + 3))",
                "FUNC4((TABLE2.B4 + 4))",
                "FUNC5((TABLE1.A5 + 5) , 6)");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE1","TABLE2");
    }

    @Test
    void testInsertIntoValues(){
        String sql = "INSERT INTO TABLE1(C1,C2,C3) VALUES((SELECT -B1,B2 FROM TABLE2),2,3),((SELECT D1,D2 FROM TABLE3),4,5)";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0)," -TABLE2.B1","2","3");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE2");
    }

    @Test
    void testInsertIntoValuesInHive(){
        String sql = "INSERT INTO  ZLX_P_TEST PARTITION (SEX='MAN1') (NAME,NATION)   VALUES('AAAAAA','ZH')";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"'AAAAAA'","'ZH'");
    }

    @Test
    void testInsertPartition(){
        String sql = "insert into table1 partition(p1) select col1 from table2";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE2.COL1");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE2");
    }

    @Test
    void testInsertOverwrite(){
        String sql = "insert overwrite table table1 partition(p1) select col1 from table2";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE2.COL1");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE2");
    }


    @Test
    void testCreateTable(){
        String sql = "CREATE TABLE USER_INFO\n" +
                "(\n" +
                "USER_ID VARCHAR(15),\n" +
                "NAME VARCHAR(20),\n" +
                "MSISDN NUMBER(20)\n" +
                ")";
        System.out.println(sql);
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0));

    }

    @Test
    void testCSH1(){
        String sql = "UPDATE V_PRDMIDDLE.TMP_DETAILBILL_RECORD B\n" +
                "SET    GROUP_PAYMENT_FLAG = 1\n" +
                "WHERE  EXISTS (\n" +
                "SELECT A\n" +
                "FROM   (SELECT 1 FROM V_PRDMIDDLE.TMP_GROUP_MEM_ACCTBK WHERE PAYMENT_TYPE = 1 AND ACCTBK_ID <> 0 ) A\n" +
                "WHERE  B.ACCTBK_ID = A.ACCTBK_ID\n" +
                "AND B.PAYMENT_TYPE <> 1 )";
        //System.out.println(sql);
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"1");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0));

    }

    @Test
    void testCaseWhen1(){
        String sql = "SELECT SUM (DECODE((CASE WHEN TOTAL_OWING < 0 THEN 0 ELSE TOTAL_OWING END), NULL, 0, (CASE WHEN TOTAL_OWING < 0 THEN 0 ELSE TOTAL_OWING+1 END))) AS OWING_AMOUNT FROM TABLE1";
//        String sql = "select Sum (Decode(total_owing, NULL, 0, total_owing+1)) AS owing_amount from table1";
//        String sql = "SELECT (CASE WHEN TOTAL_OWING < 0 THEN 0 ELSE TOTAL_OWING+1 END) FROM TABLE1";
//        String sql = "SELECT (TOTAL_OWING < ALL (SELECT C1,C2 FROM TABLE2) ) FROM TABLE1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"SUM(DECODE((CASE WHEN (TABLE1.TOTAL_OWING < 0) THEN 0 ELSE TABLE1.TOTAL_OWING END) , NULL , 0 , (CASE WHEN (TABLE1.TOTAL_OWING < 0) THEN 0 ELSE (TABLE1.TOTAL_OWING + 1) END)))");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"TABLE1");

    }

    @Test
    void testCaseWhen2(){
        String sql = "SELECT U.SEX,\n" +
                "(CASE U.SEX--你好\n" +
                "WHEN 1 THEN 'MALE'\n" +
                "WHEN 2 THEN 'FEMALE'\n" +
                "ELSE 'NULL'\n" +
                "END\n" +
                ")SEX\n" +
                "FROM USERS U";
        System.out.println(sql);
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"USERS.SEX","(CASE USERS.SEX WHEN 1 THEN 'MALE' WHEN 2 THEN 'FEMALE' ELSE 'NULL' END)");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"USERS");

    }

    @Test
    void testCaseWhenOfIn(){
        String sql = "SELECT (CASE WHEN TOTAL_OWING NOT IN (0,1) THEN 0 WHEN TOTAL_OWING  IN (C1,C2) THEN 1 WHEN TOTAL_OWING  IN (SELECT B1,B2 FROM TABLE2) THEN 2 ELSE TOTAL_OWING+1 END) FROM TABLE1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"(CASE WHEN TABLE1.TOTAL_OWING NOT IN(0 , 1) THEN 0 WHEN TABLE1.TOTAL_OWING IN(TABLE1.C1 , TABLE1.C2) THEN 1 WHEN TABLE1.TOTAL_OWING IN(TABLE2.B1 , TABLE2.B2) THEN 2 ELSE (TABLE1.TOTAL_OWING + 1) END)");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"table1");

    }

    @Test
    void testCaseWhenOfBetween(){
        String sql = "select (CASE WHEN total_owing not between 1 and 2 THEN 0 ELSE total_owing END) from table1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"(CASE WHEN TABLE1.TOTAL_OWING NOT BETWEEN 1 AND 2 THEN 0 ELSE TABLE1.TOTAL_OWING END)");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"table1");

    }

    @Test
    void testCaseWhenOfLike(){
        String sql = "select (CASE WHEN total_owing not like '%ABC%' THEN 0 ELSE total_owing END) from table1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"(CASE WHEN TABLE1.TOTAL_OWING NOT LIKE '%ABC%' THEN 0 ELSE TABLE1.TOTAL_OWING END)");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"table1");

    }


    @Test
    void testCaseWhenIsNull(){
        String sql = "select (CASE WHEN total_owing is not null THEN 0 ELSE total_owing END) from table1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"(CASE WHEN TABLE1.TOTAL_OWING IS NOT NULL THEN 0 ELSE TABLE1.TOTAL_OWING END)");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"table1");

    }

    @Test
    void testCX1(){
        String sql = "insert into blood_test_table_01(col1,col2,col3,col4,col5)\n" +
                "select t2.col1*t2.col2,t2.col1+t2.col2,t2.col1||t2.col2, count(t2.col1),'123' from blood_test_table_02 t2\n" +
                "union\n" +
                "select t2.col1,sum(t2.col1),sum(decode(t2.col1,'1',1,'0',0)),\n" +
                "(case when t2.col1>1 then 1 when t2.col1=0 then t2.col2 else 0 end) as case_condition,\n" +
                "listagg(t2.col2,',')within group(order by t2.col1)\n" +
                "from blood_test_table_02 t2\n" +
                "group by t2.col1\n" +
                "union all\n" +
                "select ${state_date},$[state_date],t2.col1,t2.col2,t2.col3 \n" +
                "from $[fJ_bishow].blood_test_table_02 partition(par1) t2,blood_test_table_03 t3 \n" +
                "left join blood_test_table_03 t3 on t2.col1=t3.col1 \n" +
                "inner join blood_test_table_03 t3 on t2.col1=t3.col1\n" +
                "join (\n" +
                "select t2.col1,t2.col2,t2.col3 from blood_test_table_02 t2\n" +
                "union\n" +
                "select t3.col1,t3.col2,t3.col3 from blood_test_table_03 t3)";
        System.out.println(sql);
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),
                "UNION ALL{UNION{(BLOOD_TEST_TABLE_02.COL1 * BLOOD_TEST_TABLE_02.COL2) , BLOOD_TEST_TABLE_02.COL1} , UNKNOWN.${STATE_DATE}}",
                "UNION ALL{UNION{(BLOOD_TEST_TABLE_02.COL1 + BLOOD_TEST_TABLE_02.COL2) , SUM(BLOOD_TEST_TABLE_02.COL1)} , UNKNOWN.$[STATE_DATE]}",
                "UNION ALL{UNION{(BLOOD_TEST_TABLE_02.COL1 || BLOOD_TEST_TABLE_02.COL2) , SUM(DECODE(BLOOD_TEST_TABLE_02.COL1 , '1' , 1 , '0' , 0))} , $[FJ_BISHOW].BLOOD_TEST_TABLE_02.COL1}",
                "UNION ALL{UNION{COUNT(BLOOD_TEST_TABLE_02.COL1) , (CASE WHEN (BLOOD_TEST_TABLE_02.COL1 > 1) THEN 1 WHEN (BLOOD_TEST_TABLE_02.COL1 = 0) THEN BLOOD_TEST_TABLE_02.COL2 ELSE 0 END)} , $[FJ_BISHOW].BLOOD_TEST_TABLE_02.COL2}",
                "UNION ALL{UNION{'123' , LISTAGG(BLOOD_TEST_TABLE_02.COL2 , ',')} , $[FJ_BISHOW].BLOOD_TEST_TABLE_02.COL3}");
        TestConsanguinityAnalyseCommonMethod.assertInputEntityOfDstTableDetailBean(stringListPair.getRight().get(0),"BLOOD_TEST_TABLE_02","$[FJ_BISHOW].BLOOD_TEST_TABLE_02");

    }


    @Test
    void testCX2(){
        String sql = "INSERT INTO BISHOW.TMP_MKT_DTL_RES_ASS_SITE_DAY02\n" +
                "(STATION_ID,\n" +
                "TYPE_SCENE_TOWER,\n" +
                "SCENE_LOWER_TOWER,\n" +
                "SCENE_UPPER_TOWER,\n" +
                "IS_ABNORMAL_TOWER,\n" +
                "TOWER_CNT)\n" +
                "SELECT t.STATION_ID,\n" +
                "       t.TYPE_SCENE_TOWER,\n" +
                "       t.SCENE_LOWER_TOWER,\n" +
                "       t.SCENE_UPPER_TOWER,\n" +
                "       CASE\n" +
                "         WHEN t.TYPE_SCENE_TOWER = 1 AND t.TOWER_CNT >= t.SCENE_LOWER_TOWER AND\n" +
                "              t.TOWER_CNT <= t.SCENE_UPPER_TOWER THEN\n" +
                "          0\n" +
                "         WHEN t.TYPE_SCENE_TOWER = 2 AND t.TOWER_CNT >= t.SCENE_LOWER_TOWER AND\n" +
                "              t.TOWER_CNT <= t.SCENE_UPPER_TOWER THEN\n" +
                "          0\n" +
                "         WHEN t.TYPE_SCENE_TOWER = 3 AND t.TYPE_ID_C >= t.SCENE_LOWER_TOWER AND\n" +
                "              t.TYPE_ID_C <= t.SCENE_UPPER_TOWER THEN\n" +
                "          0\n" +
                "         WHEN t.TYPE_SCENE_TOWER = 4 THEN\n" +
                "          0\n" +
                "         ELSE\n" +
                "          1\n" +
                "       END IS_ABNORMAL_TOWER, --是否铁塔数量异常(0-正常,1-异常)\n" +
                "       t.TOWER_CNT\n" +
                "  FROM (SELECT t1.STATION_ID,\n" +
                "               t1.TYPE_SCENE_TOWER,\n" +
                "               CASE t1.TYPE_SCENE_TOWER\n" +
                "                 WHEN 1 THEN\n" +
                "                  1 --场景1 对应下限数量1\n" +
                "                 WHEN 2 THEN\n" +
                "                  1 --场景2 对应下限数量1\n" +
                "                 WHEN 3 THEN\n" +
                "                  1 --场景3 对应下限数量1\n" +
                "                 WHEN 4 THEN\n" +
                "                  0 --场景4 对应下限数量0\n" +
                "               END SCENE_LOWER_TOWER, --铁塔标配下限数量\n" +
                "               CASE t1.TYPE_SCENE_TOWER\n" +
                "                 WHEN 1 THEN\n" +
                "                  1 --场景1 对应上限数量1\n" +
                "                 WHEN 2 THEN\n" +
                "                  18 --场景2 对应上限数量18\n" +
                "                 WHEN 3 THEN\n" +
                "                  1 --场景3 对应上限数量1\n" +
                "                 WHEN 4 THEN\n" +
                "                  0 --场景4 对应上限数量0\n" +
                "               END SCENE_UPPER_TOWER, --铁塔标配上限数量\n" +
                "               t1.TOWER_CNT,\n" +
                "               t1.TYPE_ID_A,\n" +
                "               t1.TYPE_ID_B,\n" +
                "               t1.TYPE_ID_C\n" +
                "          FROM (SELECT b.STATION_ID,\n" +
                "                       b.TOWER_CNT,\n" +
                "                       b.TYPE_ID_A,\n" +
                "                       b.TYPE_ID_B,\n" +
                "                       b.TYPE_ID_C,\n" +
                "                       CASE\n" +
                "                         WHEN b.TYPE_ID_A = 0 AND b.TYPE_ID_B = 0 THEN\n" +
                "                          1 --场景1\n" +
                "                         WHEN b.TYPE_ID_C = 0 THEN\n" +
                "                          2 --场景2\n" +
                "                         WHEN (b.TYPE_ID_A > 0 AND b.TYPE_ID_C > 0) OR\n" +
                "                              (b.TYPE_ID_B > 0 AND b.TYPE_ID_C > 0) THEN\n" +
                "                          3 --场景3\n" +
                "                         WHEN b.TYPE_ID_A IS NULL AND b.TYPE_ID_B IS NULL AND\n" +
                "                              b.TYPE_ID_C IS NULL THEN\n" +
                "                          4 --场景4\n" +
                "                       END TYPE_SCENE_TOWER --铁塔细分类型场景\n" +
                "                  FROM (SELECT a.STATION_ID,\n" +
                "                               COUNT( TOWER_CNT) TOWER_CNT, --站址下铁塔数\n" +
                "                               SUM (TYPE_ID_A) TYPE_ID_A, --楼面抱杆数量\n" +
                "                       SUM (TYPE_ID_B) TYPE_ID_B, --楼面美化天线外罩的铁塔数\n" +
                "               SUM(CASE\n" +
                "                     WHEN a.TYPE_ID NOT IN (88430221, 88430220) THEN\n" +
                "                      1\n" +
                "                     ELSE\n" +
                "                      0\n" +
                "                   END) TYPE_ID_C --其它细分类数量                  \n" +
                "          FROM BIMID.MD_BS_TOWER_INFO_DAY_${three_month} a\n" +
                "         WHERE a.DAY_NUMBER = ${day_number_cc}\n" +
                "           AND a.STAT_DATE = ${stat_date}\n" +
                "         GROUP BY a.STATION_ID) b) t1) t";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
//        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"");
    }

    @Test
    void testCX3(){
        String sql = "insert into mid.MD_BS_PMS_PROCEDUCE_DAY_${three_month} partition\n" +
                "  (day_number = ${day_number_cc})\n" +
                "  SELECT ${stat_date} STAT_DATE,\n" +
                "         A1.PRJ_ID,\n" +
                "         MIN(TAKI_CON_START_TIME),\n" +
                "         MAX(TAKI_CON_END_TIME),\n" +
                "         MIN(ROOM_CON_START_TIME),\n" +
                "         MAX(ROOM_CON_END_TIME),\n" +
                "         MIN(TOWER_CON_START_TIME),\n" +
                "         MAX(TOWER_CON_END_TIME),\n" +
                "         MIN(PT_CON_START_TIME),\n" +
                "         MAX(PT_CON_END_TIME),\n" +
                "         MIN(POWER_CON_START_TIME),\n" +
                "         MAX(POWER_CON_END_TIME)\n" +
                "    FROM (SELECT O.PRJ_ID,\n" +
                "                 CASE\n" +
                "                   WHEN E.PROCEDUCE_MAGOR = '铁塔基础' THEN\n" +
                "                    regexp_replace(substr(O.REAL_END_TIME, 1, 10), '-', '')\n" +
                "                 END TAKI_CON_START_TIME,\n" +
                "                 CASE\n" +
                "                   WHEN E.PROCEDUCE_MAGOR = '机房和机柜土建' THEN\n" +
                "                    regexp_replace(substr(O.REAL_END_TIME, 1, 10), '-', '')\n" +
                "                 END ROOM_CON_START_TIME,\n" +
                "                 CASE\n" +
                "                   WHEN E.PROCEDUCE_MAGOR = '铁塔安装' THEN\n" +
                "                    regexp_replace(substr(O.REAL_END_TIME, 1, 10), '-', '')\n" +
                "                 END TOWER_CON_START_TIME,\n" +
                "                 CASE\n" +
                "                   WHEN E.PROCEDUCE_MAGOR = '动力配套安装' THEN\n" +
                "                    regexp_replace(substr(O.REAL_END_TIME, 1, 10), '-', '')\n" +
                "                 END PT_CON_START_TIME,\n" +
                "                 CASE\n" +
                "                   WHEN E.PROCEDUCE_MAGOR = '动力配套安装' THEN\n" +
                "                    regexp_replace(substr(O.REAL_END_TIME, 1, 10), '-', '')\n" +
                "                 END POWER_CON_START_TIME\n" +
                "            FROM (select A.PRJ_ID, A.REAL_END_TIME, A.PROCEDUCE_ID\n" +
                "                    from BASE.BS_PMS_T_PROCEDUCE_INFO A\n" +
                "                   where A.sum_date = ${stat_date}) O,\n" +
                "                 (select B.PROCEDUCE_MAGOR, B.PROCEDUCE_ID\n" +
                "                    from BASE.BS_PMS_T_STANDARD_PROCEDUCE B\n" +
                "                   where B.sum_date = ${stat_date}) E\n" +
                "           WHERE O.PROCEDUCE_ID = E.PROCEDUCE_ID) A1\n" +
                "   GROUP BY A1.PRJ_ID";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"UNKNOWN.${STAT_DATE}",
                "BASE.BS_PMS_T_PROCEDUCE_INFO.PRJ_ID",
                "MIN((CASE WHEN (BASE.BS_PMS_T_STANDARD_PROCEDUCE.PROCEDUCE_MAGOR = '铁塔基础') THEN REGEXP_REPLACE(SUBSTR(BASE.BS_PMS_T_PROCEDUCE_INFO.REAL_END_TIME , 1 , 10) , '-' , '') END))",
                "MAX(UNKNOWN.TAKI_CON_END_TIME)",
                "MIN((CASE WHEN (BASE.BS_PMS_T_STANDARD_PROCEDUCE.PROCEDUCE_MAGOR = '机房和机柜土建') THEN REGEXP_REPLACE(SUBSTR(BASE.BS_PMS_T_PROCEDUCE_INFO.REAL_END_TIME , 1 , 10) , '-' , '') END))",
                "MAX(UNKNOWN.ROOM_CON_END_TIME)",
                "MIN((CASE WHEN (BASE.BS_PMS_T_STANDARD_PROCEDUCE.PROCEDUCE_MAGOR = '铁塔安装') THEN REGEXP_REPLACE(SUBSTR(BASE.BS_PMS_T_PROCEDUCE_INFO.REAL_END_TIME , 1 , 10) , '-' , '') END))",
                "MAX(UNKNOWN.TOWER_CON_END_TIME)",
                "MIN((CASE WHEN (BASE.BS_PMS_T_STANDARD_PROCEDUCE.PROCEDUCE_MAGOR = '动力配套安装') THEN REGEXP_REPLACE(SUBSTR(BASE.BS_PMS_T_PROCEDUCE_INFO.REAL_END_TIME , 1 , 10) , '-' , '') END))",
                "MAX(UNKNOWN.PT_CON_END_TIME)",
                "MIN((CASE WHEN (BASE.BS_PMS_T_STANDARD_PROCEDUCE.PROCEDUCE_MAGOR = '动力配套安装') THEN REGEXP_REPLACE(SUBSTR(BASE.BS_PMS_T_PROCEDUCE_INFO.REAL_END_TIME , 1 , 10) , '-' , '') END))",
                "MAX(UNKNOWN.POWER_CON_END_TIME)");
    }

    @Test
    void testGrouping(){
        String sql = "SELECT \n" +
                "CASE WHEN T1.PROVINCE1 IS NULL AND grouping(T1.PROVINCE2)=0 THEN '990000' ELSE NVL(T1.PROVINCE3,'100000') END AS PROVINCE\n" +
                "FROM T1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),
                "(CASE WHEN (T1.PROVINCE1 IS NULL AND (GROUPING(T1.PROVINCE2) = 0)) THEN '990000' ELSE NVL(T1.PROVINCE3 , '100000') END)");
    }

    @Test
    void testGroupingSets(){
        String sql = "SELECT A , B FROM T1 GROUP BY GROUPING SETS(A, B)";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),
                "T1.A","T1.B");
    }

    @Test
    void testHiveMultiInsert() {
        String sql = "from t_day01\n" +
                "insert overwrite  table t_day01_lt_400 partition(day='2')\n" +
                "select ip,url,staylong where staylong < 400\n" +
                "insert overwrite table t_day01_gt_400 partition(day='2')\n" +
                "select ip,url,staylong where staylong > 400";
        System.out.println(sql);
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),
                "T_DAY01.IP",
                "T_DAY01.URL","T_DAY01.STAYLONG");
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(1),
                "T_DAY01.IP",
                "T_DAY01.URL","T_DAY01.STAYLONG");
    }

    @Test
    void testHiveCreateTable(){
        String sql = "CREATE TABLE IF NOT EXISTS  `snapshot_task_sub` (\n" +
                "  `task_sub_id` INT COMMENT '任务扩展子表ID',\n" +
                "  `task_id` INT COMMENT '任务ID',\n" +
                "  `car_series` INT COMMENT '车系ID',\n" +
                "  PRIMARY KEY(AA) DISABLE NOVALIDATE,\n" +
                "  CONSTRAINT FK1 FOREIGN KEY(BB) REFERENCES TB2(CC) DISABLE NOVALIDATE\n" +
                ")COMMENT 'nihao'\n" +
                "PARTITIONED BY(AA STRING,BB STRING)\n" +
                "CLUSTERED BY (AA,BB) SORTED BY (AA,DD)  ASC INTO 1 BUCKETS\n" +
                "SKEWED BY (AA,BB) ON (AA,BB)  STORED AS '/home/zxh'\n" +
                "ROW FORMAT SERDE 'org.openx.data.jsonserde.JsonSerDe'\n" +
                "WITH SERDEPROPERTIES (\"case.insensitive\" = \"true\", \"ignore.malformed.json\"=\"true\")\n" +
                "STORED AS SEQUENCEFILE\n" +
                "LOCATION 'hdfs://hadoop102:9000/user/hive/warehouse/fdm_sor.db/mytest_tmp1'\n" +
                "TBLPROPERTIES (\"case.insensitive\" = \"true\", \"ignore.malformed.json\"=\"true\")\n" +
                "AS SELECT C1 ,C2, C3 FROM T1";

        System.out.println(sql);

        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),
                "T1.C1",
                "T1.C2","T1.C3");
    }

    @Test
    void testGbaseCreateTableAs(){
        String sql = "CREATE TABLE t8(r1,r2) NOCOPIES AS SELECT c1,c2 FROM t7";

        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),
                "t7.C1", "t7.C2");
    }

    @Test
    void testHiveCreateView(){
        String sql = "create view view1(c1,c2) as select a1,a2 from t1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),"T1.A1",
                "T1.A2");

    }

    @Test
    void testOverlayFunction(){
        String sql = "select overlay('12        \\31\\          ' placing 'hom' from 2 for 4) from cs1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
    }

    @Test
    void testTrimFunction(){
        String sql = "select  trim (both 'x' from 'xTomxx') from cs1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
    }

    @Test
    void testExtractFunction(){
        String sql = "select extract(hour from timestamp '2001-02-16 20:38:40') from cs1";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
    }

    @Test
    void testWith(){
        String sql = "with aa as\n" +
                "(select c1+1 c2 from t1),\n" +
                "bb as\n" +
                "(select func(c2) c3 from aa)\n" +
                "select c3+2 from bb\n";
        System.out.println(sql);
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),
                                                                                   "(FUNC((T1.C1 + 1)) + 2)");
    }

    @Test
    void testWith1() {
        String sql = "insert into bssdw.ta_lb_us_new_busi_merge_m\n" + "  (statis_month,\n" + "   user_id,\n" + "   serv_no,\n" + "   is_sing_active_andpkg_user)\n" + "  with aa as\n" + "   (select user_id,\n" + "           serv_no,\n" + "           sum(andpkg_active_cnt) andpkg_active_cnt\n" + "      from bssdw.tc_ub_new_busi_merge_situation_d\n" + "     where statis_date between 20190801 and 20190831\n" + "     group by user_id, serv_no),\n" + "  bb as\n" + "   (select user_id,\n" + "           serv_no,\n" + "           max(case\n" + "                 when andpkg_active_cnt >= 2 then\n" + "                  1\n" + "                 else\n" + "                  0\n" + "               end) andpkg_active_cnt\n" + "      from aa\n" + "     group by user_id, serv_no)\n" + "  select 201908,\n" + "         user_id,\n" + "         serv_no,\n" + "         case\n" + "           when andpkg_active_cnt = 1 then\n" + "            1\n" + "           else\n" + "            0\n" + "         end andpkg_active_cnt\n" + "    from bb\n" + "  ";
        System.out.println(sql);
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),
                                                                                   "201908",
                                                                                   "bssdw.tc_ub_new_busi_merge_situation_d.user_id",
                                                                                   "bssdw.tc_ub_new_busi_merge_situation_d.serv_no",
                                                                                   "(CASE WHEN (max((CASE WHEN (sum(bssdw.tc_ub_new_busi_merge_situation_d.andpkg_active_cnt) >= 2) THEN 1 ELSE 0 END)) = 1) THEN 1 ELSE 0 END)");
    }

    @Test
    void testTest(){
        String sql = "alter table $[_CONN_FJ_EDC_MART].rpt_grid_tag_daily_test drop partition(cur_date=$[_data_date])";
        Pair<String, DstTableDetailBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyse(sql);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(stringListPair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertConvertRuleOfDstTableDetailBean(stringListPair.getRight().get(0),
                "");
    }




}
