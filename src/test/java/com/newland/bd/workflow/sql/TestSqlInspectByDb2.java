package com.newland.bd.workflow.sql;

import com.newland.bd.workflow.sql.api.SqlInspect;
import com.newland.bd.workflow.sql.bean.DbType;
import com.newland.bd.workflow.sql.bean.TableNameAndOpt;
import com.newland.bd.workflow.sql.bean.TableOpt;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Create by Howard on 2019/2/19
 */
public class TestSqlInspectByDb2 {

    @Test
    void testTruncate() {
        String sql = "truncate table lf_test_dim1 immediate";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lf_test_dim1", TableOpt.TRUNCATE));
    }

    @Test
    void testAlter() {
        String sql = "alter table  LF_DIM_CITY_AREA11 drop primary key";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("LF_DIM_CITY_AREA11", TableOpt.DROP_INDEX));

        sql = "alter table  LF_DIM_CITY_AREA11 drop primary key";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("LF_DIM_CITY_AREA11", TableOpt.DROP_INDEX));

        sql = "ALTER TABLE lf_DIM_CITY_AREA11 ALTER TYPE_CHAR SET NOT NULL;";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("LF_DIM_CITY_AREA11", TableOpt.ALTER));

        //删除表记录
        sql = "alter table lf_DIM_CITY_AREA11 activate not logged initially WITH empty table";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lf_DIM_CITY_AREA11", TableOpt.ALTER));

        //删除表字段
        sql = "ALTER TABLE LF_DIM_CITY_AREA21 DROP TYPE_char";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("LF_DIM_CITY_AREA21", TableOpt.ALTER_DROP_COLUMN));

        sql = "ALTER TABLE LF_DIM_CITY_AREA11 ADD CONSTRAINT Lf_fk FOREIGN KEY (TYPE_DIM) REFERENCES LF_DIM_CITY_AREA13 (TYPE_DIM)";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("LF_DIM_CITY_AREA11", TableOpt.ALTER));

        sql = "ALTER TABLE LF_DIM_CITY_AREA11 DROP CONSTRAINT Lf_fk";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("LF_DIM_CITY_AREA11", TableOpt.ALTER));

        sql = "ALTER TABLE LF_DIM_CITY_AREA110 ADD CONSTRAINT LF_DIM_CITY_AREA110_TYPE_INT_pk UNIQUE (TYPE_DIM)";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("LF_DIM_CITY_AREA110", TableOpt.ALTER));

        sql = "ALTER TABLE LF_DIM_CITY_AREA110 ALTER TYPE_VARCHAR SET DATA TYPE varchar(20)";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("LF_DIM_CITY_AREA110", TableOpt.ALTER));

        sql = "ALTER TABLE LF_DIM_CITY_AREA110 ALTER COLUMN TYPE_INT SET DEFAULT 333";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("LF_DIM_CITY_AREA110", TableOpt.ALTER));

        sql = "ALTER TABLE lf_test_hunan ALTER COLUMN type_int SET NOT NULL";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lf_test_hunan", TableOpt.ALTER));
    }

    @Test
    void testComment(){
        String sql = "COMMENT ON TABLE LF_DIM_CITY_AREA11 IS 'fff'";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, null);

        sql = "COMMENT ON TABLE LF_DIM_CITY_AREA11 IS 'fff'";
        pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, null);
    }

    @Test
    void testCall() {
        String sql = "CALL SYSPROC.ADMIN_CMD('REORG TABLE LF_DIM_CITY_AREA11')";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("SYSPROC.ADMIN_CMD", TableOpt.CALL));
    }

    @Test
    void testCreateTable() {
        String sql = "CREATE TABLE lf_DIM_CITY_AREA (\n" + "  type_int int,\n" + "  type_char char(10),\n" + "  type_varchar varchar(10),\n" + "  type_dim " +
                "int not null,\n" + "  type_time time,\n" + "  type_timestamp timestamp,\n" + "  type_clob clob,\n" + "  type_smallint smallint,\n" + "  " +
                "type_bigint bigint,\n" + "  type_double double,\n" + "  type_float float,\n" + "  type_integer integer,\n" + "  type_real real,\n" + "  " +
                "type_date date,\n" + "  type_decimal decimal\n" + ") partition by RANGE(type_dim) (\n" + "  STARTING '591'  ENDING '599'\n" + ");";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lf_DIM_CITY_AREA", TableOpt.CREATE_TABLE));
    }

    @Test
    void testCreateTableAsSelect() {
        String sql = "create table lf_DIM_CITY_AREA33 as (\t\n" + "  select\n" + "    type_int,\n" + "    type_varchar\n" + "  from\n" + "    " +
                "lf_DIM_CITY_AREA11\n" + "  where\n" + "    type_int in(3, 11, 55)\n" + ") data initially deferred refresh deferred";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lf_DIM_CITY_AREA33", TableOpt.CREATE_TABLE), new TableNameAndOpt(
                "lf_DIM_CITY_AREA11", TableOpt.SELECT));
    }

    @Test
    void testCreateTableLike() {
        String sql = "create table tab_2 like tab_1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("tab_2", TableOpt.CREATE_TABLE));
    }

    @Test
    void testRefreshTableLike() {
        String sql = "refresh table lf_DIM_CITY_AREA33";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lf_DIM_CITY_AREA33", TableOpt.UPDATE));
    }

    @Test
    void testCreateIndex() {
        String sql = "CREATE INDEX LF_DIM_CITY_AREA110_TYPE_INT_index ON lf_DIM_CITY_AREA110 (TYPE_INT DESC)";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("lf_DIM_CITY_AREA110", TableOpt.CREATE_INDEX));
    }

    @Test
    void testCreateView() {
        String sql = "create view V_zjt_tables1(type_int,type_clob) as select type_int,type_clob from lf_test_dim1; ";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("V_zjt_tables1", TableOpt.CREATE_VIEW), new TableNameAndOpt("lf_test_dim1",
                                                                                                                                             TableOpt.SELECT));
    }

    @Test
    void testCreateSeq() {
        String sql = "CREATE SEQUENCE LF_DIM_CITY_AREA110_TYPE_FLOAT_SEQ INCREMENT BY 1 MINVALUE 1 NO MAXVALUE START WITH 1";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.DB2);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, null);
    }
}
