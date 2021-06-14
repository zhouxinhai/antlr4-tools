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
public class TestSqlInspectByGp {

    @Test
    void testGpTypeConvert(){
        String sql = "select to_char(125.8::real,'99909')  from cs1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs1", TableOpt.SELECT));
    }

    @Test
    void testDoubleColon(){
        String sql = "SELECT gp_segment_id, tableoid::regclass, COUNT(*)\n" +
                "  FROM tst_data_table\n" +
                " GROUP BY 1, 2 ORDER BY 1, 2;\n";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("tst_data_table", TableOpt.SELECT));

    }

    @Test
    void testLimitAndOffset(){
        String sql = "SELECT c1 FROM AGE_TYPE LIMIT 5 offset 1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("AGE_TYPE", TableOpt.SELECT));
    }

    @Test
    void testVacuumAnalyze(){
        String sql = "VACUUM ANALYZE t1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("t1", TableOpt.SELECT));
    }

    @Test
    void testTruncate(){
        String sql = "TRUNCATE t1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("t1", TableOpt.TRUNCATE));
    }


    @Test
    void testCreateTable1(){
        String sql = "create table t1(\n" +
                "            c1 number not null\n" +
                "    )\n" +
                "    LOCATION('file://host1:5432/data/expense/*.csv',\n" +
                "                     'file://host2:5432/data/expense/*.csv',\n" +
                "                     'file://host3:5432/data/expense/*.csv')\n" +
                "    FORMAT 'TEXT' (DELIMITER ',')\n" +
                "    TABLESPACE data_ts\n" +
                "    WITH (appendonly=TRUE, orientation=COLUMN)\n" +
                "    DISTRIBUTED BY (rec_id)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("t1", TableOpt.CREATE_TABLE));
    }

    @Test
    void testCreateTable2(){
        String sql = "CREATE TABLE my_table (rec_id INT, name TEXT)\n" +
                "DISTRIBUTED BY (rec_id)\n" +
                "PARTITION BY RANGE (rec_id)\n" +
                "( START (20) END (25) EVERY(1), DEFAULT PARTITION def )\n";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.CREATE_TABLE));
    }

    @Test
    void testCreateTable3(){
        String sql = "CREATE TABLE my_table\n" +
                "( rec_id INT,\n" +
                "  exec_date DATE,\n" +
                "  city VARCHAR(16) )\n" +
                "DISTRIBUTED BY ( rec_id )\n" +
                "PARTITION BY RANGE ( exec_date )\n" +
                "( PARTITION jan_2008 START (DATE '2008-01-01') inclusive,\n" +
                "  PARTITION feb_2008 START (DATE '2008-02-01') inclusive,\n" +
                "  PARTITION mar_2008 START (DATE '2008-03-01') inclusive,\n" +
                "  PARTITION apr_2008 START (DATE '2008-04-01') inclusive,\n" +
                "  PARTITION may_2008 START (DATE '2008-05-01') inclusive,\n" +
                "  PARTITION jun_2008 START (DATE '2008-06-01') inclusive,\n" +
                "  PARTITION jul_2008 START (DATE '2008-07-01') inclusive,\n" +
                "  PARTITION aug_2008 START (DATE '2008-08-01') inclusive,\n" +
                "  PARTITION sep_2008 START (DATE '2008-09-01') inclusive,\n" +
                "  PARTITION oct_2008 START (DATE '2008-10-01') inclusive,\n" +
                "  PARTITION nov_2008 START (DATE '2008-11-01') inclusive,\n" +
                "  PARTITION dec_2008 START (DATE '2008-12-01') inclusive END (DATE '2009-01-01') exclusive );\n";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.CREATE_TABLE));
    }

    @Test
    void testCreateTable4(){
        String sql = "CREATE TABLE my_table ( rec_id INT, exec_date DATE, city VARCHAR(16) )\n" +
                "WITH (appendonly=TRUE, orientation=COLUMN)\n" +
                "DISTRIBUTED BY (rec_id)\n" +
                "PARTITION BY LIST (city)\n" +
                "( PARTITION usa VALUES('usa'),\n" +
                "  PARTITION china VALUES('chaina'),\n" +
                "  DEFAULT PARTITION other )\n";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.CREATE_TABLE));
    }


    @Test
    void testCreateTable5(){
        String sql = "CREATE TABLE my_table\n" +
                "( rec_id INT,\n" +
                "  exec_date DATE,\n" +
                "  city VARCHAR(16) )\n" +
                "DISTRIBUTED BY (rec_id)\n" +
                "PARTITION BY RANGE (exec_date)\n" +
                "SUBPARTITION BY LIST (city)\n" +
                "SUBPARTITION TEMPLATE\n" +
                "( SUBPARTITION usa VALUES ('usa'),\n" +
                "  SUBPARTITION china VALUES ('china'),\n" +
                "  DEFAULT SUBPARTITION city_other )\n" +
                "( START (DATE '2013-01-01') END (DATE '2014-01-01') EVERY (INTERVAL '1 month'),\n" +
                "  DEFAULT PARTITION other )\n";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.CREATE_TABLE));
    }

    @Test
    void testCreateTable6(){
        String sql = "CREATE EXTERNAL TABLE ext_tst_data_table\n" +
                "( LIKE tst_data_table )\n" +
                "LOCATION\n" +
                "( 'gpfdist://10.1.0.181:8081/data/table_*.dat',\n" +
                "  'gpfdist://10.1.0.182:8081/data/table_*.dat' )\n" +
                "FORMAT 'text' ( delimiter '|' NULL '')\n" +
                "ENCODING 'gbk'\n" +
                "LOG ERRORS INTO err_ext_tst_data_table\n" +
                "SEGMENT REJECT LIMIT 100 PERCENT;\n";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("ext_tst_data_table", TableOpt.CREATE_TABLE));
    }

    @Test
    void testCreateTable7(){
        String sql = "CREATE TABLE my_table\n" +
                "( rec_id INT CONSTRAINT pk_table_id PRIMARY KEY USING INDEX TABLESPACE data_ts,\n" +
                "  name TEXT NOT NULL,\n" +
                "  age INT CONSTRAINT chk_table_age CHECK(age>=18),\n" +
                "  birth DATE NOT NULL,\n" +
                "  notes TEXT DEFAULT 'this is notes!')\n" +
                "TABLESPACE data_ts\n" +
                "DISTRIBUTED BY (rec_id)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.CREATE_TABLE));
    }

    @Test
    void testCreateTable8(){
        String sql = "CREATE TABLE my_table\n" +
                "( rec_id INT,\n" +
                "  exec_date DATE ENCODING(compresstype=quicklz),\n" +
                "  notes TEXT,\n" +
                "  COLUMN notes ENCODING(compresstype=zlib, compresslevel=9),\n" +
                "  DEFAULT COLUMN ENCODING(compresstype=quicklz, blocksize=8192) )\n" +
                "WITH (appendonly=TRUE, orientation=COLUMN)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.CREATE_TABLE));
    }

    @Test
    void testCreateTable9(){
        String sql = "CREATE TABLE my_table_bk (LIKE my_table) DISTRIBUTED BY (rec_id)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table_bk", TableOpt.CREATE_TABLE));
    }

    @Test
    void testCreateTable10(){
        String sql = "CREATE TABLE my_table AS SELECT c1, substr(val_desc, 1,3) FROM my_table_bk WHERE rec_id=1 DISTRIBUTED BY (rec_id)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.CREATE_TABLE),
                new TableNameAndOpt("my_table_bk", TableOpt.SELECT));
    }

    @Test
    void testAlterPartition1(){
        String sql = "ALTER TABLE my_table ADD PARTITION jan09 START (DATE '2009-01-01') END (DATE '2009-02-01')";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_SET_PARTITION ));
    }

    @Test
    void testAlterPartition2(){
        String sql = "ALTER TABLE my_table ADD DEFAULT PARTITION part_def";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_SET_PARTITION ));
    }

    @Test
    void testAlterPartition3(){
        String sql = "ALTER TABLE my_table RENAME PARTITION FOR ('2008-01-01') TO prt200801";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_SET_PARTITION ));
    }

    @Test
    void testAlterPartition4(){
        String sql = "ALTER TABLE my_table RENAME PARTITION FOR (rank(1)) TO prt20080101";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_SET_PARTITION ));
    }

    @Test
    void testAlterPartition5(){
        String sql = "ALTER TABLE my_table SPLIT PARTITION FOR ('2009-01-01') AT ('2009-01-16') INTO (PARTITION prt200901a, PARTITION prt200901b)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_SET_PARTITION ));
    }

    @Test
    void testAlterPartition6(){
        String sql = "ALTER TABLE my_table SPLIT DEFAULT PARTITION START (DATE '2009-02-01') END (DATE '2009-03-01') INTO (PARTITION prt200902, DEFAULT PARTITION )";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_SET_PARTITION ));
    }

    @Test
    void testAlterPartition7(){
        String sql = "ALTER TABLE my_table DROP PARTITION FOR ('2009-01-01')";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_SET_PARTITION ));

        sql = "ALTER TABLE my_table DROP DEFAULT PARTITION";
        pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_SET_PARTITION ));


        sql = "ALTER TABLE my_table TRUNCATE PARTITION FOR (rank(1))";
        pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_SET_PARTITION ));

        sql = "ALTER TABLE my_table EXCHANGE PARTITION FOR (rank(1)) WITH TABLE tmp_table";
        pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_SET_PARTITION ));
    }

    @Test
    void testAlterColumn(){
        String sql = "ALTER TABLE my_table ADD CONSTRAINT check_id CHECK(rec_id>=1)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_ADD_COLUMN ));
    }

    @Test
    void testAlterConstraint1(){
        String sql = "ALTER TABLE my_table ADD CONSTRAINT check_id CHECK(rec_id>=1)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_ADD_COLUMN ));

        sql = "ALTER TABLE my_table DROP CONSTRAINT check_id";
        pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.ALTER_MODIFY_COLUMN ));

        sql = "alter table css add constraint css_cs_id_fk foreign key (id) references cs (id)";
        pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("css", TableOpt.ALTER_MODIFY_COLUMN ));
    }

    @Test
    void testAlterIndex(){
        String sql = "CREATE INDEX idx_id_my_table ON TABLE my_table(data_id)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("my_table", TableOpt.CREATE_INDEX ));

        sql = "CREATE INDEX idx_id_tst_table ON tst_table (data_id, data_val)";
        pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("tst_table", TableOpt.CREATE_INDEX ));

        sql = "CREATE UNIQUE INDEX idx1_id_tst_table ON tst_table (data_id)";
        pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("tst_table", TableOpt.CREATE_INDEX ));

        sql = "CREATE INDEX idx2_id_tst_table ON tst_table USING BITMAP (data_id)";
        pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("tst_table", TableOpt.CREATE_INDEX ));

        sql = "CREATE INDEX idx_val_tst_table ON tst_table ( (lower(data_val)) )";
        pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("tst_table", TableOpt.CREATE_INDEX ));

        sql = "DROP INDEX idx1_id_tst_table";
        pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, null);

        sql = "REINDEX idx2_id_tst_table";
        pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, null);
    }

    @Test
    void testComment(){
        String sql = "comment on table css is '13123'";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("css", TableOpt.UPDATE ));

    }

    @Test
    void testTocharFunction(){
        String sql = "select to_char(interval '15h 2m 12s' ,'HH24:MI:SS') from cs1;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs1", TableOpt.SELECT ));
    }

    @Test
    void testAgeFunction(){
        String sql = "select age(timestamp '2001-04-10',timestamp '1957-06-13') from cs1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs1", TableOpt.SELECT ));
    }

    @Test
    void testDataPartFunction(){
        String sql = "select date_part('hour', timestamp '2001-02-16 20:38:40')from cs1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs1", TableOpt.SELECT ));
    }

    @Test
    void testIsfiniteFunction(){
        String sql = "select isfinite(interval '4 hours') from cs1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs1", TableOpt.SELECT ));
    }

    @Test
    void testJustifyDaysFunction(){
        String sql = "select justify_days(interval '30 days') from  cs1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs1", TableOpt.SELECT ));
    }

    @Test
    void testJustifyHoursFunction(){
        String sql = "select justify_hours(interval '24 hours') from  cs1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs1", TableOpt.SELECT ));
    }

    @Test
    void testJustifyIntervalFunction(){
        String sql = "select justify_interval(interval '1 mon -1 hour')  from  cs1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.GP);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs1", TableOpt.SELECT ));
    }

}
