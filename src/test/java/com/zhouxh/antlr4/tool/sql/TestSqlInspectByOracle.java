package com.zhouxh.antlr4.tool.sql;


import com.zhouxh.antlr4.tool.sql.api.SqlInspect;
import com.zhouxh.antlr4.tool.sql.bean.DbType;
import com.zhouxh.antlr4.tool.sql.bean.TableNameAndOpt;
import com.zhouxh.antlr4.tool.sql.bean.TableOpt;
import com.zhouxh.antlr4.tool.sql.bean.consanguinity.TableAndPartitionInfo;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Create by Howard on 2019/2/19
 */
public class TestSqlInspectByOracle {


    @Test
    void testContraint(){
        String sql = "create table lz_test12 (\n" +
                "  home_city varchar2(10) not null,\n" +
                "  user_id number not null,\n" +
                "  age char(10),\n" +
                "  constraint K_lz_test2 primary key (user_id)\n" +
                ");";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lz_test12", TableOpt.CREATE_TABLE));

        sql = "create table lz_test6(id int unique, name number(10))";
        pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lz_test6", TableOpt.CREATE_TABLE));

    }

    @Test
    void testRangePartition(){
        String sql = "CREATE TABLE lz_test11 (\n" +
                "  home_city varchar2(10) not null,\n" +
                "  user_id number not null,\n" +
                "  age char(10)\n" +
                ") partition by Range(home_city) (\n" +
                "  partition aa values less than (1000),\n" +
                "  partition bb values less than (2000)\n" +
                ");";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lz_test11", TableOpt.CREATE_TABLE));

        TableAndPartitionInfo tableAndPartitionInfo = SqlInspect.getHiveTablePartitionInfoList().get(0);
        Assert.assertEquals(tableAndPartitionInfo.tablename,"lz_test11");
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.size(),1);
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.get(0).columnName,"home_city");
    }

    @Test
    void testListPartition(){
        String sql = "CREATE TABLE lz_test11 (\n" +
                "  home_city varchar2(10) not null,\n" +
                "  user_id number not null,\n" +
                "  age char(10)\n" +
                ") partition by List(home_city) (\n" +
                "  partition aa values (1000,2000),\n" +
                "  partition bb values (3000,4000)\n" +
                ");";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lz_test11", TableOpt.CREATE_TABLE));

        TableAndPartitionInfo tableAndPartitionInfo = SqlInspect.getHiveTablePartitionInfoList().get(0);
        Assert.assertEquals(tableAndPartitionInfo.tablename,"lz_test11");
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.size(),1);
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.get(0).columnName,"home_city");
    }

    @Test
    void testHashPartition(){
        String sql = "CREATE TABLE lz_test11 (\n" +
                "  home_city varchar2(10) not null,\n" +
                "  user_id number not null,\n" +
                "  age char(10)\n" +
                ") partition by hash(home_city) (\n" +
                "  partition aa tablespace t1,\n" +
                "  partition bb tablespace t1\n" +
                ");";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lz_test11", TableOpt.CREATE_TABLE));
        TableAndPartitionInfo tableAndPartitionInfo = SqlInspect.getHiveTablePartitionInfoList().get(0);
        Assert.assertEquals(tableAndPartitionInfo.tablename,"lz_test11");
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.size(),1);
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.get(0).columnName,"home_city");

        sql = "create table lz_test12 (\n" +
                "  home_city varchar2(10) not null,\n" +
                "  user_id number not null,\n" +
                "  age char(10),\n" +
                "  constraint K_lz_test2 primary key (user_id)\n" +
                ")partition by hash(home_city) (\n" +
                "  partition 4 store in (p1,p2)\n" +
                ");";
        pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lz_test12", TableOpt.CREATE_TABLE));
        tableAndPartitionInfo = SqlInspect.getHiveTablePartitionInfoList().get(0);
        Assert.assertEquals(tableAndPartitionInfo.tablename,"lz_test12");
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.size(),1);
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.get(0).columnName,"home_city");
    }


    @Test
    void testCompositePartition() {
        String sql = "CREATE TABLE composite_sales\n" +
                "    ( prod_id        NUMBER(6)\n" +
                "    , cust_id        NUMBER\n" +
                "    , time_id        DATE\n" +
                "    , channel_id     CHAR(1)\n" +
                "    , promo_id       NUMBER(6)\n" +
                "    , quantity_sold  NUMBER(3)\n" +
                "    , amount_sold         NUMBER(10,2)\n" +
                "    ) \n" +
                "PARTITION BY RANGE (time_id) SUBPARTITION BY HASH (channel_id)\n" +
                "  (PARTITION SALES_Q1_1998 VALUES LESS THAN (TO_DATE('01-APR-1998','DD-MON-YYYY')),\n" +
                "   PARTITION SALES_Q2_1998 VALUES LESS THAN (TO_DATE('01-JUL-1998','DD-MON-YYYY')),\n" +
                "   PARTITION SALES_Q3_1998 VALUES LESS THAN (TO_DATE('01-OCT-1998','DD-MON-YYYY')),\n" +
                "   PARTITION SALES_Q4_1998 VALUES LESS THAN (TO_DATE('01-JAN-1999','DD-MON-YYYY')),\n" +
                "   PARTITION SALES_Q1_1999 VALUES LESS THAN (TO_DATE('01-APR-1999','DD-MON-YYYY')),\n" +
                "   PARTITION SALES_Q2_1999 VALUES LESS THAN (TO_DATE('01-JUL-1999','DD-MON-YYYY')),\n" +
                "   PARTITION SALES_Q3_1999 VALUES LESS THAN (TO_DATE('01-OCT-1999','DD-MON-YYYY')),\n" +
                "   PARTITION SALES_Q4_1999 VALUES LESS THAN (TO_DATE('01-JAN-2000','DD-MON-YYYY')),\n" +
                "   PARTITION SALES_Q1_2000 VALUES LESS THAN (TO_DATE('01-APR-2000','DD-MON-YYYY')),\n" +
                "   PARTITION SALES_Q2_2000 VALUES LESS THAN (TO_DATE('01-JUL-2000','DD-MON-YYYY'))\n" +
                "      SUBPARTITIONS 8,\n" +
                "   PARTITION SALES_Q3_2000 VALUES LESS THAN (TO_DATE('01-OCT-2000','DD-MON-YYYY'))\n" +
                "     (SUBPARTITION ch_c,\n" +
                "      SUBPARTITION ch_i,\n" +
                "      SUBPARTITION ch_p,\n" +
                "      SUBPARTITION ch_s,\n" +
                "      SUBPARTITION ch_t),\n" +
                "   PARTITION SALES_Q4_2000 VALUES LESS THAN (MAXVALUE)\n" +
                "      SUBPARTITIONS 4)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("composite_sales", TableOpt.CREATE_TABLE));
        TableAndPartitionInfo tableAndPartitionInfo = SqlInspect.getHiveTablePartitionInfoList().get(0);
        Assert.assertEquals(tableAndPartitionInfo.tablename,"composite_sales");
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.size(),2);
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.get(0).columnName,"time_id");
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.get(1).columnName,"channel_id");
    }


    @Test
    void testTemporaryTable1() {
        String sql = "create Temporary table tmp\n" +
                "(\n" +
                "t1 NUMBER ,\n" +
                "t2 NUMBER \n" +
                ")\n" +
                "On Commit Preserve Rows;";

        System.out.println(sql);

        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair);
    }

    @Test
    void testTemporaryTable2() {
        String sql = "create Temporary table tmp\n" +
                "(\n" +
                "t1 NUMBER ,\n" +
                "t2 NUMBER \n" +
                ")\n" +
                "On Commit Delete Rows;";

        System.out.println(sql);

        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair);
    }

    @Test
    void testDropTable(){
        String sql = "drop table GIM_CLOUD_MINIPROGRAM_CARD;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("GIM_CLOUD_MINIPROGRAM_CARD", TableOpt.DROP_TABLE));
    }

    @Test
    void testCreateTable1(){
        String sql = "CREATE TABLE s_emp\n" +
                "(id NUMBER(7)\n" +
                "CONSTRAINT s_emp_id_pk PRIMARY KEY,\n" +
                "last_name VARCHAR2(25)\n" +
                "CONSTRAINT s_emp_last_name_nn NOT NULL,\n" +
                "first_name VARCHAR2(25),\n" +
                "userid VARCHAR2(8)\n" +
                "CONSTRAINT s_emp_userid_nn NOT NULL\n" +
                "CONSTRAINT s_emp_userid_uk UNIQUE,\n" +
                "start_date DATE DEFAULT SYSDATE,\n" +
                "comments VARCHAR2(25),\n" +
                "manager_id NUMBER(7),\n" +
                "title VARCHAR2(25),\n" +
                "dept_id NUMBER(7)\n" +
                "CONSTRAINT s_emp_dept_id_fk REFERENCES s_dept(id),\n" +
                "salary NUMBER(11,2),\n" +
                "commission_pct NUMBER(4,2)\n" +
                "CONSTRAINT s_emp_commission_pct_ck CHECK\n" +
                "(commission_pct IN(10,12.5,15,17.5,20)));\n";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("s_emp", TableOpt.CREATE_TABLE));
    }



    @Test
    void testCreateTable2(){
        String sql =
                "create global temporary table GIM_CLOUD_MINIPROGRAM_CARD\n" +
                        "(\n" +
                        "  ID_GIM_CLOUD_MINIPROGRAM_CARD     VARCHAR2(32) not null,\n" +
                        "  TEMPLATE_NO                   VARCHAR2(50) not null\n" +
                        ")on commit preserve rows;\n" +
                        "-- Add comments to the table \n" +
                        "comment on table GIM_CLOUD_MINIPROGRAM_CARD\n" +
                        "  is '小程序卡片模板表';\n" +
                        "-- Add comments to the columns \n" +
                        "comment on column GIM_CLOUD_MINIPROGRAM_CARD.ID_GIM_CLOUD_MINIPROGRAM_CARD\n" +
                        "  is '主键';\n";

        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair);
    }

}
