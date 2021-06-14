package com.zhouxh.antlr4.tool.sql;


import com.zhouxh.antlr4.tool.sql.api.SqlInspect;
import com.zhouxh.antlr4.tool.sql.bean.DbType;
import com.zhouxh.antlr4.tool.sql.bean.TableNameAndOpt;
import com.zhouxh.antlr4.tool.sql.bean.TableOpt;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Create by Howard on 2019/2/19
 */
public class TestSqlInspectByGbase {


    @Test
    void testCreateTable1(){
        String sql = "CREATE TABLE dwd_o_cust_person_day (\n" + "MONTH_ID varchar(20) DEFAULT NULL COMMENT '月份',\n" + "DAY_ID varchar(40) DEFAULT NULL COMMENT '日期',\n" + "CUST_NO varchar(40) DEFAULT NULL COMMENT '客户标识'\n" + ")COMPRESS(1, 3) \n" + "ENGINE=EXPRESS \n" + "DISTRIBUTED BY('cust_no') \n" + "NOCOPIES \n" + "DEFAULT CHARSET=utf8 \n" + "COMMENT='个人客户资料表'";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
    }

    @Test
    void testCreateTable2(){
        String sql = "CREATE TEMPORARY TABLE t1 \n" + "(a int,b varchar(10)) DISTRIBUTED BY ('a')\n" + "NOCOPIES;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
    }

    @Test
    void testCreateTable3(){
        String sql = "CREATE TABLE t8 NOCOPIES AS SELECT c1 FROM t7;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("t8", TableOpt.CREATE_TABLE),
                new TableNameAndOpt("t7", TableOpt.SELECT));
    }

    @Test
    void testCreateTable4(){
        String sql = "CREATE TABLE t8 NOCOPIES REPLICATED DISTRIBUTED BY SELECT c1 FROM t7;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
    }


    @Test
    void testCreateTable5(){
        String sql = "CREATE TABLE my_accounts (\n" + "chk_id   SERIAL PRIMARY KEY,\n" + "acct1    MONEY CHECK (acct1 BETWEEN 0 AND 99999),\n" + "acct2    MONEY CHECK (acct2 BETWEEN 0 AND 99999));";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
    }

    @Test
    void testCreateTable6(){
        String[] sqls = {"CREATE TABLE t(nameid int, name varchar(50)) AUTOEXTEND ON NEXT 1M;",
                "CREATE TABLE t1 (a int DEFAULT NULL,b varchar(10) COMPRESS(3));",
                "CREATE TABLE t2 (a int COMPRESS(3),b varchar(10) COMPRESS(5));",
                "CREATE TABLE my_accounts (\n" + "chk_id   SERIAL PRIMARY KEY,\n" + "acct1    MONEY CHECK (acct1 BETWEEN 0 AND 99999),\n" + "acct2    MONEY CHECK (acct2 BETWEEN 0 AND 99999));"};
        for(String sql:sqls){
            Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
            TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
        }
    }

    @Test
    void testCreateTable7() {
        String[] sqls = {"CREATE TABLE test_112 (\n" + "    id int(11) NOT NULL,\n" + "    t date NOT NULL,\n" + "    PRIMARY KEY (id)\n" + ") \n"
                + "PARTITION BY RANGE (id)\n" + "(   PARTITION p0 VALUES LESS THAN (736907),\n" + "    PARTITION p1 VALUES LESS THAN (736938),\n" + "    PARTITION p2 VALUES LESS THAN maxvalue\n" + ");", "CREATE TABLE b (a decimal(12,5) DEFAULT NULL, KEY idx_a (a) USING HASH LOCAL)"};
        for (String sql : sqls) {
            Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
            TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
        }
    }


    @Test
    void testCreateTable8(){
        String sql = "CREATE TABLE lfdim_area_cell (\n" + "  TIME_STAMP timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP\n" + ") ENGINE=EXPRESS DEFAULT CHARSET=utf8";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lfdim_area_cell", TableOpt.CREATE_TABLE));
    }


    @Test
    void testAlter(){
        String[] sqls = {"ALTER TABLE t ADD COLUMN name1 varchar(20) AFTER f_1;",
                "ALTER TABLE t ADD COLUMN (name3 varchar(30),address varchar(40));",
                "ALTER TABLE t DROP COLUMN name3,DROP COLUMN name2;",
                "ALTER TABLE t RENAME ttt2",
                "ALTER TABLE t MODIFY b varchar(10) FIRST;",
                "ALTER TABLE t1 DROP NOCOPIES;",
                "ALTER TABLE lineitem SHRINK SPACE;",
                "ALTER TABLE t MODIFY b varchar(20) FIRST;",
                "ALTER TABLE t MODIFY b varchar(30) AFTER c;",
                "ALTER VIEW test.v_t(a, b) AS SELECT name,address FROM t;",
                "ALTER TABLE t AUTOEXTEND ON NEXT 2M;",
                "ALTER TABLE t AUTOEXTEND OFF;"};

        for(String sql:sqls){
            Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
            TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
        }
    }

    @Test
    void testIndex(){
        String[] sqls = {"CREATE INDEX idx2 on t1(b) USING HASH GLOBAL;",
                "CREATE INDEX idx3 on t1(b) key_block_size=16384 USING HASH GLOBAL;",
                "CREATE INDEX idx4 on t1(b) key_dc_size=50 USING HASH GLOBAL;",
                "ALTER TABLE t ADD INDEX h2(a) key_dc_size=1 key_block_size=4096 USING HASH;",
                "DROP INDEX pt ON t;"};

        for(String sql:sqls){
            Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
            TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
        }

        String sql = "CREATE INDEX idx2 on t1(b) USING HASH GLOBAL;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("t1", TableOpt.CREATE_INDEX));

        sql = "CREATE INDEX idx1 on t(name1) USING HASH LOCAL";
        pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("t", TableOpt.CREATE_INDEX));
    }

    @Test
    void testMerge(){
        String sql = "MERGE INTO t1 USING t2 ON t1.i=t2.i WHEN MATCHED THEN UPDATE SET\n" + "t1.vc=t2.vc,t1.vc=t2.vc WHEN NOT MATCHED THEN INSERT(t1.i,t2.vc) VALUES(t2.i,t2.vc),(t2.i,t2.vc);";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("t1", TableOpt.UPDATE),
                new TableNameAndOpt("t2", TableOpt.SELECT));

        sql = "MERGE INTO t1 t USING t2 ON t.i=t2.i WHEN MATCHED THEN UPDATE SET\n" + "t.vc=t2.vc WHEN NOT MATCHED THEN INSERT (t.i,t.vc) VALUES (t2.i,t2.vc);";
        pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("t1", TableOpt.UPDATE),
                new TableNameAndOpt("t2", TableOpt.SELECT));
    }

    @Test
    void testIntoOutfile(){
        String[] sqls = {"SELECT c1 FROM test.t  INTO OUTFILE 'hdp://hadoop@192.168.10.1:50070/export/test.tbl' OUTFILEMODE BY HDFS;",
        "select c1 from t  into outfile '1' fields terminated by ';'",
        "select c1 from aa into outfile '/home/davies/out.txt' fields escaped by '' terminated by '|' optionally double_enclosed by '\"'",
        "SELECT c1 FROM gs INTO OUTFILE '/home/gbase/temp/length_1.txt' FIELDS TERMINATED BY '' ENCLOSED BY '' ESCAPED BY '';",
        "select c1 from gt into outfile '/home/davies/a' null_value 'aaaa';",
        "SELECT c1 FROM gs INTO OUTFILE '/home/gbase/temp/gs_g.txt' OUTFILEMODE BY LOCAL;",
        "select c1 from aa into outfile '/home/davies/out.txt' fields escaped by '' terminated by '|' optionally double_enclosed by '\"' null_value 'gg\"gg';",
        "SELECT c1 FROM test.t INTO OUTFILE 'hdp://hadoop@192.168.10.1:50070/export/test.tbl' OUTFILEMODE BY HDFS",
        "SELECT c1 FROM gs INTO OUTFILE '/home/gbase/temp/gs_g.txt' WRITEMODE BY NORMAL;",
        "SELECT c1 FROM test INTO OUTFILE 'HDP://192.168.153.21:50070/export/test.txt?user=gbase' OUTFILEMODE BY HDFS FILECOUNT 3;",
        "SELECT c1 FROM test INTO OUTFILE '/home/gbase/temp/test.txt' FILESIZE 33554432;",
        "select c1 from test_3 into outfile '/home/gbase/test.txt' character set gbk;",
        "SELECT c1 FROM test INTO OUTFILE '/home/gbase/temp/test.tbl' fields terminated by '|' with head",
        };

        for(String sql:sqls){
            Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
            TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(pair.getLeft());
        }
    }

    @Test
    void testIntoOutfile1(){
        String sql = "SELECT c1 FROM test.t  INTO OUTFILE 'hdp://hadoop@192.168.10.1:50070/export/test.tbl' OUTFILEMODE BY HDFS";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("test.t", TableOpt.SELECT));

        sql = "rmt:SELECT  c1 FROM product INTO OUTFILE '/home/gbase/temp/product.txt' FIELDS TERMINATED BY ';' ESCAPED BY 'g'";
        pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("product", TableOpt.SELECT));
    }

    @Test
    void testLoadDataFile(){
        String sql = "Load data infile 'data.tbl' into table t fields terminated by '|' set c='2016-06-06 18:08:08',d='default',e=20.6;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("t", TableOpt.LOAD_DATA));

        sql = "LOAD DATA INFILE 'ftp://127.0.0.1/data/test.tbl.lzo' replace INTO TABLE test.t DATA_FORMAT 3;";
        pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("test.t", TableOpt.LOAD_DATA));
    }

    @Test
    void testInsert(){
        String sql = "insert into gbase_output_test(user_id,name,age)  SELECT d1,d2,d3 FROM test_gbase ;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("gbase_output_test", TableOpt.INSERT)
                                                      ,new TableNameAndOpt("test_gbase", TableOpt.SELECT));
    }

    @Test
    void testBooleanValue() {
        String sql = "SELECT 1 IS NOT UNKNOWN, 0 IS NOT UNKNOWN, NULL IS NOT UNKNOWN FROM temp_test";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("temp_test", TableOpt.SELECT));
    }

    @Test
    void testOperator(){
        String sql = "SELECT 1 <=> 1, NULL <=> NULL, 1 <=> NULL FROM temp_test";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("temp_test", TableOpt.SELECT));
    }

    @Test
    void testFunction() {
        String sql = "SELECT GREATEST('B',BINARY 'a','C') FROM temp_test";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("temp_test", TableOpt.SELECT));

        sql = "SELECT LEAST(BINARY 'B',BINARY 'a', 'C') FROM  temp_test;";
        pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("temp_test", TableOpt.SELECT));

        sql = "SELECT ! (1+1) FROM  temp_test";
        pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("temp_test", TableOpt.SELECT));
    }

    @Test
    void testArithmeticBinary(){
        String sql = "SELECT 1 XOR 1 FROM temp_test";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("temp_test", TableOpt.SELECT));

        sql = "select 5 mod 2 from lf_test_gbase";
        pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lf_test_gbase", TableOpt.SELECT));
    }

    @Test
    void testShow() {
        String sql = "show INDEX FROM t";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("", TableOpt.OPTIMIZE));
    }

    @Test
    void testApostrophe() {
        String sql = "select to_date('2002-02-02 06:30:00','yyyy-mm-dd HH24:mi:ss') aa;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair);
    }

    @Test
    void testLimit() {
        String sql = "select c1 from student limit 3,2;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GBASE);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("student", TableOpt.SELECT));
    }

}
