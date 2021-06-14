package com.newland.bd.workflow.sql;

import com.newland.bd.workflow.sql.api.SqlInspect;
import com.newland.bd.workflow.sql.bean.DbType;
import com.newland.bd.workflow.sql.bean.TableNameAndOpt;
import com.newland.bd.workflow.sql.bean.TableOpt;
import com.newland.bd.workflow.sql.bean.consanguinity.TableAndPartitionInfo;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Create by Howard on 2018/10/8
 */
public class TestSqlInspectExt {

    @Test
    void testSortBy() {
        String sql = "select 1 From LZ_TEST1230 SORT BY USER_ID DESC";
        String retJson = SqlInspect.inspect(sql);
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("LZ_TEST1230", TableOpt.SELECT));
    }

    @Test
    void testDCL() {
        String sql = "commit;rollback";
        String retJson = SqlInspect.inspect(sql);
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertFail(pair.getLeft());
    }

    @Test
    void testQuery1() {
        String sql = "select c1,c2 from user_product\n" + "minus\n" + "select c1,c2m from user_product_buckets;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("user_product", TableOpt.SELECT),
                                                       new TableNameAndOpt("user_product_buckets", TableOpt.SELECT));
    }

    @Test
    void testJoin1() {
        String sql = "SELECT T1.C1 COL1,T2.C2 COL2,T3.C3 COL3 FROM TA PARTITION(AA) T1,TB T2,bill.TC T3 " + "WHERE T1.USER_ID = T2.USER_ID AND T2.USER_ID = " + "T3.USER_ID";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("ta", TableOpt.SELECT),
                                                       new TableNameAndOpt("tb", TableOpt.SELECT),
                                                       new TableNameAndOpt("bill.tc", TableOpt.SELECT));
    }

    @Test
    void testJoin2() {
        String sql = "SELECT A.C1,B.C2,C.C3,D.C4 FROM TABLE1 A \n" + "INNER JOIN TABLE2 B\n" + "ON  (A.COL1=B.USER_ID)\n" + "LEFT JOIN TABLE3 C\n" + "ON (C" + ".COL1=B.USER_ID)" + "LEFT JOIN TABLE4 D\n" + "ON (D.COL1=C.USER_ID)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("TABLE1", TableOpt.SELECT),
                                                       new TableNameAndOpt("TABLE2", TableOpt.SELECT),
                                                       new TableNameAndOpt("TABLE3", TableOpt.SELECT),
                                                       new TableNameAndOpt("TABLE4", TableOpt.SELECT));
    }

    @Test
    void testJoin3() {
        String sql = "select a.user_id as auser , a.acct_name as aname from LZ_TEST1230 a  left semi  join LZ_TEST1222 b on a.user_id = b.user_id";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("LZ_TEST1230", TableOpt.SELECT),
                                                       new TableNameAndOpt("LZ_TEST1222", TableOpt.SELECT));
    }

    @Test
    void testJoin4() {
        String sql = "select ${state_date},$[state_date],t2.col1,t2.col2,t2.col3 \n" + "from blood_test_table_02 t2 \n" + "left join blood_test_table_03 t3  " + "\n";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.AllType);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("blood_test_table_02", TableOpt.SELECT),
                                                       new TableNameAndOpt("blood_test_table_03", TableOpt.SELECT));

        pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("blood_test_table_02", TableOpt.SELECT),
                                                       new TableNameAndOpt("blood_test_table_03", TableOpt.SELECT));

        pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertFail(pair.getLeft());
    }

    @Test
    void testJoin5() {
        String sql = "Select 1 from t_A a,t_B b where a.id(+)=b.id";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.ORACLE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("t_A", TableOpt.SELECT),
                                                       new TableNameAndOpt("t_B", TableOpt.SELECT));

        sql = "Select 1 from t_A a,t_B b where a.id(+)=b.id";
        pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertFail(pair.getLeft());
    }

    @Test
    void testSubQueryInSelectItem() {
        String sql = "SELECT A.C1, C2, " + "(select t2.b1,t2.b2 from table2 T2) M," + "(select t3.q1,t3.q2 from table3 T3) N," + "C3" + " FROM TABLE1 T1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("TABLE1", TableOpt.SELECT),
                                                       new TableNameAndOpt("TABLE2", TableOpt.SELECT),
                                                       new TableNameAndOpt("TABLE3", TableOpt.SELECT));
    }

    @Test
    void testTableOfSubQuery() {
        String sql = "SELECT FUNC1(C1,FUNC2(C2,C3)) " + "FROM (SELECT C1_1 C1,C1_2 C2,C1_3 C3 FROM TABLE1) T1";

        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("TABLE1", TableOpt.SELECT));
    }

    @Test
    void testUpdateBySet() {
        String sql = "UPDATE TABLE1 SET " + "(A1,A2) = (SELECT FUNC1(B1+1),FUNC2(B2+2) FROM TABLE2 WHERE TABLE1.A5 = TABLE2.B5)," + "(A3,A4) = (SELECT FUNC3" + "(B3+3),FUNC4(B4+4) FROM TABLE2 WHERE TABLE1.A5 = TABLE2.B5), " + "A5 = FUNC5(A5+5,6)" + "WHERE A5 IN (SELECT A5 FROM TABLE2 WHERE  " + "TABLE1.A5" + " = TABLE2.B5)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("TABLE1", TableOpt.UPDATE),
                                                       new TableNameAndOpt("TABLE2", TableOpt.SELECT),
                                                       new TableNameAndOpt("TABLE2", TableOpt.SELECT),
                                                       new TableNameAndOpt("TABLE2", TableOpt.SELECT));
    }

    @Test
    void testSet() {
        String sql = "set hive.a.a=false";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("", TableOpt.OPTIMIZE));
    }

    @Test
    void testCreateTable1() {
        String sql = "CREATE TABLE USER_INFO\n" + "(\n" + "USER_ID VARCHAR(15),\n" + "NAME VARCHAR(20),\n" + "MSISDN NUMBER(20)\n" + ")";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("USER_INFO", TableOpt.CREATE_TABLE));

        sql = "CREATE TEMPORARY TABLE USER_INFO\n" + "(\n" + "USER_ID VARCHAR(15),\n" + "NAME VARCHAR(20),\n" + "MSISDN NUMBER(20)\n" + ")";
        System.out.println(sql);
        pair = SqlInspect.inspect(sql, DbType.HIVE);

        Assert.assertEquals(pair.getRight().size(), 0);
    }

    @Test
    void testCreateTable2() {
        String sql = "CREATE EXTERNAL TABLE lz_test02 (\n" + " HOME_CITY CHAR (12),\n" + " HOME_AREA_CODE VARCHAR (12),\n" + " BRAND_ID INT,\n" + " KPI_VALUE" + " INT,\n" + " KPI_CODE DOUBLE,\n" + " CUR_DATE DATE,\n" + " TX_DATE DATE,\n" + " MONTH_ID BIGINT,\n" + " STATE STRING\n" + ") PARTITIONED " + "BY " + "(HOME_CODE INT) clustered BY (HOME_CITY)  sorted BY (HOME_CITY ASC) INTO 3 buckets  STORED AS orcfile LOCATION \"/home/data/fj\";\n";
        System.out.println(sql);
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("LZ_TEST02", TableOpt.CREATE_TABLE));
    }

    @Test
    void testCreateTable3() {
        String sql = "CREATE TABLE tb_ch_radio_m (\n" + "  media_rate_list array <struct<media_rate:string,\n" + "  download_bytes:string>>\n" + ") STORED " + "AS" + " orcfile";
        System.out.println(sql);
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("tb_ch_radio_m", TableOpt.CREATE_TABLE));
    }

    @Test
    void testCreateTable4() {
        String sql = "CREATE TABLE default.tc_vw_nu_wan_03_m (ts_list array<struct<cdn_addr :string,avg_cdn_download_thr_put:string," + "avg_download_thr_put" + ":string,max_cdn_download_thr_put:string,max_download_thr_put:string,min_cdn_download_thr_put:string," + "min_download_thr_put:string," + "avg_cdn_tcp_connect_cost:string,avg_connect_cost:string,max_cdn_tcp_connect_cost:string,max_connect_cost:string," + "min_cdn_tcp_connect_cost:string,min_connect_cost:string,cdn_tcp_connect_cnt:bigint,tcp_connect_cnt:bigint,cdn_tcp_connect_fail_cnt:bigint," + "tcp_connect_fail_cnt:bigint,ts_req_cnt:bigint,ts_req_timeout_cnt:bigint,cdn_ts_req_timeout_cnt:bigint,ts_req_err_cnt:bigint," + "cdn_ts_req_err_cnt:bigint,ts_req_non_resp_cnt:bigint,cdn_ts_req_non_resp_cnt:bigint,avg_ts_session_cost:string," + "avg_cdn_ts_session_cost:string,max_ts_session_cost:string,max_ts_slide_window_size:string,avg_ts_slide_window_size:string," + "min_ts_slide_window_size:string>>,wifi_info_list array<struct<ssid_mac:string,channel:string,ssid_enable:string,ssid_encryption_mode:string," + "ssid_name:string,ssid_std:string,ssid:string,ssid_ad:string>>)PARTITIONED BY ( multicast_timeout_list array<struct<multicast_addr:string," + "timeout_cnt:bigint>>) STORED AS orcfile";
        System.out.println(sql);
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("default.tc_vw_nu_wan_03_m", TableOpt.CREATE_TABLE));
    }

    @Test
    void testCreateTableAsSelect() {
        String sql = "CREATE TABLE TABLE1(N1,N2,N3) " + "AS SELECT T1.C1 COL1,T2.C2 COL2,T3.C3 COL3 FROM TA T1,TB T2,TC T3";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("TABLE1", TableOpt.CREATE_TABLE),
                                                       new TableNameAndOpt("TA", TableOpt.SELECT),
                                                       new TableNameAndOpt("TB", TableOpt.SELECT),
                                                       new TableNameAndOpt("TC", TableOpt.SELECT));
    }

    @Test
    void testHiveMultiInsert() {
        String sql = "from t_day01\n" + "insert overwrite  table t_day01_lt_400 partition(day='2')\n" + "select ip,url,staylong where staylong < 400\n" + "insert overwrite table t_day01_gt_400 partition(day='2')\n" + "select ip,url,staylong where staylong > 400;";
        System.out.println(sql);
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("t_day01", TableOpt.SELECT),
                                                       new TableNameAndOpt("t_day01_lt_400", TableOpt.INSERT),
                                                       new TableNameAndOpt("t_day01_gt_400", TableOpt.INSERT));
    }

    @Test
    void testHiveWriteDataSingle() {
        String sql = "INSERT OVERWRITE LOCAL DIRECTORY '/HOME/BASE' SELECT C1 FROM TABLE1 ";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("table1", TableOpt.SELECT));
    }

    @Test
    void testWriteDataMulti() {
        String sql = "from table1 " + "INSERT OVERWRITE LOCAL DIRECTORY '/HOME/BASE1' SELECT C1 " + "INSERT OVERWRITE LOCAL DIRECTORY '/HOME/BASE2' SELECT C2";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("table1", TableOpt.SELECT));
    }

    @Test
    void testHiveDropTable() {
        String sql = "DROP TABLE IF EXISTS table1 PURGE";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("table1", TableOpt.DROP_TABLE));
    }

    @Test
    void testHiveTruncateTable() {
        String sql = "truncate table table1 partition (partcol1=val1, partcol2=val2)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("table1", TableOpt.TRUNCATE));
    }

    @Test
    void testHiveCreateTable() {
        String sql = "CREATE TABLE IF NOT EXISTS  snapshot_task_sub (\n" + "  `task_sub_id` INT COMMENT '任务扩展子表ID',\n" + "  `task_id` INT COMMENT '任务ID',\n" + "  " + "`car_series` INT COMMENT '车系ID',\n" + "  PRIMARY KEY(AA) DISABLE NOVALIDATE,\n" + "  CONSTRAINT FK1 FOREIGN KEY(BB) REFERENCES " + "TB2" + "(CC) DISABLE NOVALIDATE\n" + ")COMMENT 'nihao'\n" + "PARTITIONED BY(AA STRING,BB STRING)\n" + "CLUSTERED BY (AA,BB) SORTED " + "BY (AA," + "DD)  ASC INTO 1 BUCKETS\n" + "SKEWED BY (AA,BB) ON (AA,BB)  STORED AS '/home/zxh'\n" + "ROW FORMAT SERDE 'org.openx.data" + ".jsonserde" + ".JsonSerDe'\n" + "WITH SERDEPROPERTIES (\"case.insensitive\" = \"true\", \"ignore.malformed.json\"=\"true\")\n" + "STORED AS " + "SEQUENCEFILE\n" + "LOCATION 'hdfs://hadoop102:9000/user/hive/warehouse/fdm_sor.db/mytest_tmp1'\n" + "TBLPROPERTIES " + "(\"case" + ".insensitive\" = \"true\", \"ignore.malformed.json\"=\"true\")\n" + "AS SELECT C1 ,C2, C3 FROM T1;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("snapshot_task_sub", TableOpt.CREATE_TABLE),
                                                       new TableNameAndOpt("T1", TableOpt.SELECT));

        TableAndPartitionInfo tableAndPartitionInfo = SqlInspect.getHiveTablePartitionInfoList().get(0);
        Assert.assertEquals(tableAndPartitionInfo.tablename, "snapshot_task_sub");

        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.get(0).columnName, "AA");
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.get(0).columnType, "STRING");
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.get(1).columnName, "BB");
        Assert.assertEquals(tableAndPartitionInfo.partitionInfos.get(1).columnType, "STRING");
    }

    @Test
    void testInsertIntoPartition1() {
        String sql = "INSERT INTO  ZLX_P_TEST PARTITION (SEX='MAN1') (NAME,NATION)   VALUES('AAAAAA','ZH')";
        System.out.println(sql);
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("ZLX_P_TEST", TableOpt.INSERT));
    }

    @Test
    void testInsertIntoPartition2() {
        String sql = "insert into table zlx_p_test partition(sex='name') (name,nation) values ('sfdgga','dsdadad')\n";
        System.out.println(sql);
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("ZLX_P_TEST", TableOpt.INSERT));
    }

    @Test
    void testInsertIntoPartition3() {
        String sql = "INSERT INTO  ZLX_P_TEST(c1,c2) select c1,c2 from tab1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("ZLX_P_TEST", TableOpt.INSERT),
                                                       new TableNameAndOpt("tab1", TableOpt.SELECT));
    }

    @Test
    void testInsertIntoPartition4() {
        String sql = "INSERT INTO TC_US_USER_DISC_GRPT_M\n" + "partition(STATIS_MONTH=${DATE_STRING})\n" + "(\n" + "city_code\n" + ")\n" + "SELECT\n" + "a" + ".city_code\n" + "FROM TB_SU_USER_DISC_M A";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("TC_US_USER_DISC_GRPT_M", TableOpt.INSERT),
                                                       new TableNameAndOpt("TB_SU_USER_DISC_M", TableOpt.SELECT));
    }

    @Test
    void testAsterisk() {
        String sql = "select * from tab1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertFail(pair.getLeft());
    }

    @Test
    void testAsteriskByExplore() {
        String sql = "select * from tab1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE, true);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
    }

    @Test
    void testRenameTable() {
        String sql = "ALTER TABLE oldtable RENAME TO newtable";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE, true);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("OLDTABLE", TableOpt.ALTER_RENAME_FROMTABLE),
                                                       new TableNameAndOpt("NEWTABLE", TableOpt.ALTER_RENAME_TOTABLE));
    }

    @Test
    void testInsertIntoSelect() {
        String sql = "insert into bishow.blood_test_table_01(col1,col2,col3,col4,col5)\n" + "SELECT t2.col1*t2.col2,t2.col1+t2.col2,t2.col1||t2.col2,count" + "(t2" + ".col1),'123' from blood_test_table_02 t2\n" + "union\n" + "select t2.col1,sum(t2.col1),sum(decode(t2.col1,'1',1,'0',0)),\n" + "(case" + " when t2" + ".col1>1 then 1 \n" + "\t\t\twhen t2.col1=0 then t2.col1 else 0 end) as case_condition,\n" + "listagg(t2.col1," + "',') within group" + "(order by t2" + ".col1)\n" + "from blood_test_table_02 t2\n" + "group by t2.col1\n" + "union all\n" + "select " + "$[_data_date] ,$[_data_mon] ," + "t2.col1,t2.col2,t2" + ".col3 from blood_test_table_02 partition(par1) t2," + "blood_test_table_03 t3 " + "\n" + "left join blood_test_table_03 t3 on " + "t2.col1=t3.col1 \n" + "inner join blood_test_table_03 t3" + " on t2.col1=t3.col1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL, true);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("bishow.blood_test_table_01", TableOpt.INSERT),
                                                       new TableNameAndOpt("blood_test_table_02", TableOpt.SELECT),
                                                       new TableNameAndOpt("blood_test_table_02", TableOpt.SELECT),
                                                       new TableNameAndOpt("blood_test_table_03", TableOpt.SELECT),
                                                       new TableNameAndOpt("blood_test_table_02", TableOpt.SELECT),
                                                       new TableNameAndOpt("blood_test_table_03", TableOpt.SELECT),
                                                       new TableNameAndOpt("blood_test_table_03", TableOpt.SELECT));
    }

    @Test
    void testInsertIntoSelect1() {
        String sql = "insert into ${_task_id}.UDAP_TEST_HIVE_TABLE1(name,age) select id,sex from udap_test_mysql_table";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE, true);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("${_task_id}.UDAP_TEST_HIVE_TABLE1", TableOpt.INSERT),
                                                       new TableNameAndOpt("udap_test_mysql_table", TableOpt.SELECT));
    }

    @Test
    void testIgnoreTemporaryTableTrace() {
        String sql = "create Temporary table tmp\n" + "(\n" + "t1 NUMBER \n" + ");\n" + "\n" + "insert into tmp(t1)\n" + "select HIVE_IN_int from " + "test_hive_in;\n";
        Pair pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("test_hive_in", TableOpt.SELECT));
    }

    @Test
    void testDB2Func() {
        String sql = "select RIGHT('hallo world',2) from temp_test;\n" + "select left('hallo world',2) from temp_test";
        Pair pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("temp_test", TableOpt.SELECT),
                                                       new TableNameAndOpt("temp_test", TableOpt.SELECT));

        sql = "select INSERT('hallo',2,3,'ell') from lf_DIM_CITY_AREA;";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lf_DIM_CITY_AREA", TableOpt.SELECT));

        sql = "truncate table lf_DIM_CITY_AREA;";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lf_DIM_CITY_AREA", TableOpt.TRUNCATE));

        sql = "select truncate('hallo',2,3,'ell') from lf_DIM_CITY_AREA;";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lf_DIM_CITY_AREA", TableOpt.SELECT));
    }

    @Test
    void testDB2CurrentTimeFunc() {
        String sql = "select timestampdiff(256,char(current timestamp - timestamp('2010-01-01-00.00.00'))) from EMP;";
        Pair pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("EMP", TableOpt.SELECT));

        sql = "select timestampdiff(256,char(current time - timestamp('2010-01-01-00.00.00'))) from EMP;";
        pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertFail((String) pair.getLeft());

        sql = "select timestampdiff(256,char(current date - timestamp('2010-01-01-00.00.00'))) from EMP;";
        pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertFail((String) pair.getLeft());

        sql = "select timestampdiff(256,char(current timestamp - timestamp('2010-01-01-00.00.00'))) from EMP;";
        pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertFail((String) pair.getLeft());
    }

    @Test
    void testWith(){
        String sql = "with aa as\n" +
                "(select c1+1 c2 from t1),\n" +
                "bb as\n" +
                "(select func(c2) c3 from aa)\n" +
                "select c3+2 from bb\n";
        System.out.println(sql);
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("t1", TableOpt.SELECT));
    }

    @Test
    void testTest() {
        String sql = "select\n" + "  kpi_code\n" + "from\n" + "  DEFAULT.tc_sf_activity_d;\n" + "\n" + "\n" + "/*--结果表插入当日信息化收入数据\n" + "insert into\n" + "  PUBLIC_SOFT.KPI_GRP_INC_INFO_DAILY_CSH (\n" + "    home_city,\n" + "    home_area_code,\n" + "    brand_id,\n" + "    kpi_code,\n" + "    kpi_value,\n" + "    cur_date,\n" + "    tx_date,\n" + "    month_id\n" + "  )\n" + "select\n" + "  round(\n" + "    coalesce(\n" + "      substr(trim(t2.org_zone), 1, 1) + 590,\n" + "      t1.home_county / 100 + 590\n" + "    )\n" + "  ) as home_city,\n" + "  coalesce(t2.org_zone, t1.home_county * 100 + 99) as home_area_code,\n" + "  0 brand_id,\n" + "  5901000378,\n" + "  sum(t1.sum_fee) as kpi_value,\n" + "  ${_data_date},\n" + "  ${_data_date},\n" + "  ${_data_month}\n" + "from\n" + "  PUBLIC_SOFT.QRY_GRP_XXH_AMT_D_CSH t1\n" + "  left join PUBLIC_SOFT.FT_MID_GROUP_CUSTOMER_D_CSH t2 on t1.group_id = t2.group_id\n" + "  and to_char(t2.sum_date, 'yyyymmdd') between '20190101'\n" + "  and '20191231'\n" + "where\n" + "  to_char(t1.sum_date, 'yyyymmdd') between '20190101'\n" + "  and '20191231'\n" + "  and t1.product_id = 9999\n" + "group by\n" + "  coalesce(\n" + "    substr(trim(t2.org_zone), 1, 1) + 590,\n" + "    t1.home_county / 100 + 590\n" + "  ),\n" + "  coalesce(t2.org_zone, t1.home_county * 100 + 99),\n" + "  t2.grp_mgr_id;\n" + "--结果表插入年累计信息化收入数据\n" + "insert into\n" + "  PUBLIC_SOFT.KPI_GRP_INC_INFO_DAILY_CSH (\n" + "    home_city,\n" + "    home_area_code,\n" + "    brand_id,\n" + "    kpi_code,\n" + "    kpi_value,\n" + "    cur_date,\n" + "    tx_date,\n" + "    month_id\n" + "  )\n" + "select\n" + "  round(\n" + "    coalesce(\n" + "      substr(trim(t2.org_zone), 1, 1) + 590,\n" + "      t1.home_county / 100 + 590\n" + "    )\n" + "  ) as home_city,\n" + "  coalesce(t2.org_zone, t1.home_county * 100 + 99) as home_area_code,\n" + "  0 brand_id,\n" + "  5901000380,\n" + "  sum(t1.sum_fee) as kpi_value,\n" + "  ${_data_date},\n" + "  ${_data_date},\n" + "  ${_data_month}\n" + "from\n" + "  PUBLIC_SOFT.QRY_GRP_XXH_AMT_D_CSH t1\n" + "  left join PUBLIC_SOFT.FT_MID_GROUP_CUSTOMER_D_CSH t2 on t1.group_id = t2.group_id\n" + "  and to_char(t2.sum_date, 'yyyymmdd') between '20190101'\n" + "  and '20191231'\n" + "where\n" + "  to_char(t1.sum_date, 'yyyymmdd') between '20190101'\n" + "  and '20191231'\n" + "  and t1.product_id = 9999\n" + "group by\n" + "  coalesce(\n" + "    substr(trim(t2.org_zone), 1, 1) + 590,\n" + "    t1.home_county / 100 + 590\n" + "  ),\n" + "  coalesce(t2.org_zone, t1.home_county * 100 + 99),\n" + "  t2.grp_mgr_id\n" + "union all\n" + "select\n" + "  round(\n" + "    coalesce(\n" + "      substr(trim(t2.org_zone), 1, 1) + 590,\n" + "      t1.home_county / 100 + 590\n" + "    )\n" + "  ) as home_city,\n" + "  coalesce(t2.org_zone, t1.home_county * 100 + 99) as home_area_code,\n" + "  0 brand_id,\n" + "  5901000380,\n" + "  sum(t1.sum_fee) as kpi_value,\n" + "  ${_data_date},\n" + "  ${_data_date},\n" + "  ${_data_month}\n" + "from\n" + "  PUBLIC_SOFT.QRY_GRP_XXH_AMT_D_CSH t1\n" + "  left join PUBLIC_SOFT.FT_MID_GROUP_CUSTOMER_D_CSH t2 on t1.group_id = t2.group_id\n" + "  and to_char(t2.sum_date, 'yyyymmdd') between '20190101'\n" + "  and '20191231'\n" + "where\n" + "  to_char(t1.sum_date, 'yyyymmdd') between '20190101'\n" + "  and ${_data_date}\n" + "  and t1.product_id = 9999\n" + "group by\n" + "  coalesce(\n" + "    substr(trim(t2.org_zone), 1, 1) + 590,\n" + "    t1.home_county / 100 + 590\n" + "  ),\n" + "  coalesce(t2.org_zone, t1.home_county * 100 + 99),\n" + "  t2.grp_mgr_id;\n" + "--结果表插入管会收入数据\n" + "insert into\n" + "  PUBLIC_SOFT.KPI_GRP_INC_INFO_DAILY_CSH (\n" + "    home_city,\n" + "    home_area_code,\n" + "    brand_id,\n" + "    kpi_code,\n" + "    kpi_value,\n" + "    cur_date,\n" + "    tx_date,\n" + "    month_id\n" + "  )\n" + "select\n" + "  round(\n" + "    coalesce(\n" + "      substr(trim(t2.org_zone), 1, 1) + 590,\n" + "      t1.home_county / 100 + 590\n" + "    )\n" + "  ) as home_city,\n" + "  coalesce(t2.org_zone, t1.home_county * 100 + 99) as home_area_code,\n" + "  0 brand_id,\n" + "  5901000382,\n" + "  sum(t1.sum_fee) as kpi_value,\n" + "  ${_data_date},\n" + "  ${_data_date},\n" + "  ${_data_month}\n" + "from\n" + "  PUBLIC_SOFT.QRY_GRP_XXH_AMT_D_CSH t1\n" + "  left join PUBLIC_SOFT.FT_MID_GROUP_CUSTOMER_D_CSH t2 on t1.group_id = t2.group_id\n" + "  and to_char(t2.sum_date, 'yyyymmdd') between '20190101'\n" + "  and ${_data_date}\n" + "where\n" + "  to_char(t1.sum_date, 'yyyymmdd') between '20190101'\n" + "  and ${_data_date}\n" + "  and t1.product_id = 10001\n" + "group by\n" + "  coalesce(\n" + "    substr(trim(t2.org_zone), 1, 1) + 590,\n" + "    t1.home_county / 100 + 590\n" + "  ),\n" + "  coalesce(t2.org_zone, t1.home_county * 100 + 99),\n" + "  t2.grp_mgr_id;\n" + "insert into\n" + "  PUBLIC_SOFT.KPI_GRP_INC_INFO_DAILY_CSH (\n" + "    home_city,\n" + "    home_area_code,\n" + "    brand_id,\n" + "    kpi_code,\n" + "    kpi_value,\n" + "    cur_date,\n" + "    tx_date,\n" + "    month_id\n" + "  )\n" + "select\n" + "  590,\n" + "  100,\n" + "  1,\n" + "  ${job_id},\n" + "  ${seq},\n" + "  ${cyc_time},\n" + "  ${_data_day},\n" + "  ${data_mon}\n" + "from\n" + "  PUBLIC_SOFT.ta_dt_rr_contract_d_writer;*/";
        Pair pair = SqlInspect.inspect(sql, DbType.HIVE);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess((String) pair.getLeft());
        List<TableNameAndOpt> list = (List<TableNameAndOpt>)pair.getRight();
        System.out.println(list);
    }
}
