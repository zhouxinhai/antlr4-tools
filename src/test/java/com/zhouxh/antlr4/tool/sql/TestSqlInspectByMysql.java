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
public class TestSqlInspectByMysql {

    @Test
    void testTemporaryTable1() {
        String sql = " CREATE TEMPORARY TABLE tmp_table(\n" + " name VARCHAR(10) NOT NULL,\n" + " value1 INTEGER NOT NULL\n" + ")TYPE = HEAP";

        System.out.println(sql);

        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair);
    }

    @Test
    void testTemporaryTable2() {
        String sql = "CREATE temporary TABLE user_t (  \n" + "  id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '学号',  \n" + "  user_name varchar(40) NULL,  \n" + "  password varchar(255) NOT NULL,  \n" + "  age int(4) NOT NULL\n" + ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='学号表'; ";

        System.out.println(sql);

        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair);
    }

    @Test
    void testCommitAndRollback() {
        String sql = "COMMIT;" + "ROLLBACK";

        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("", TableOpt.COMMIT), new TableNameAndOpt("", TableOpt.ROLLBACK));
    }

    @Test
    void testCreateTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `runoob_tbl`(\n" + "`runoob_id` INT UNSIGNED AUTO_INCREMENT,\n" + " `runoob_title` VARCHAR(100) NOT NULL,\n" + " `runoob_author` VARCHAR(40) NOT NULL,\n" + " `submission_date` DATE,\n" + "  PRIMARY KEY ( `runoob_id` )\n" + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";

        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("`runoob_tbl`", TableOpt.CREATE_TABLE));
    }

    @Test
    void testWithRollup() {
        String sql = "SELECT name, SUM(singin) as singin_count FROM  employee_tbl GROUP BY name WITH ROLLUP;";

        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("employee_tbl", TableOpt.SELECT));
    }

    @Test
    void testRegexp() {
        String sql = "SELECT runoob_title FROM runoob_tbl WHERE runoob_title REGEXP '^st';";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("runoob_tbl", TableOpt.SELECT));

        sql = "SELECT NOT ('Monty!' REGEXP 'm%y%%' ) FROM apps";
        pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("apps", TableOpt.SELECT));
    }

    @Test
    void testRLIKE() {
        String sql = "SELECT 'Monty!' RLIKE 'm%y%%' FROM apps";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("apps", TableOpt.SELECT));
    }

    @Test
    void testDropColumn() {
        String sql = "ALTER TABLE cs  DROP name";

        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs", TableOpt.ALTER_DROP_COLUMN));
    }

    @Test
    void testChangeColumn() {
        String sql = "ALTER TABLE cs change i you BIGINT not null";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs", TableOpt.ALTER_MODIFY_COLUMN));

        sql = "ALTER TABLE cs  MODIFY qwe int NOT NULL DEFAULT 100;";
        pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs", TableOpt.ALTER_MODIFY_COLUMNTYPE));

        sql = "ALTER TABLE cs ALTER i SET DEFAULT 1000";
        pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs", TableOpt.ALTER_MODIFY_COLUMN));

        sql = "ALTER TABLE cs ALTER i DROP DEFAULT";
        pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs", TableOpt.ALTER_MODIFY_COLUMN));

        sql = "ALTER TABLE cs RENAME TO hh";
        pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair,
                                                       new TableNameAndOpt("cs", TableOpt.ALTER_RENAME_FROMTABLE),
                                                       new TableNameAndOpt("hh", TableOpt.ALTER_RENAME_TOTABLE));
    }

    @Test
    void testAddIndex(){
        String sql = "ALTER table cs ADD INDEX indexName(id);";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs", TableOpt.CREATE_INDEX));

        sql = "CREATE INDEX indexName ON cs(name(10))";
        pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs", TableOpt.CREATE_INDEX));

        sql = "CREATE UNIQUE INDEX indexName ON cs(name(10))";
        pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs", TableOpt.CREATE_INDEX));

        sql = "ALTER TABLE cs DROP PRIMARY KEY";
        pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs", TableOpt.DROP_INDEX));
    }

    @Test
    void testDropIndex(){
        String sql = "DROP INDEX indexName ON cs;";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs", TableOpt.DROP_INDEX));

        sql = "ALTER TABLE cs DROP INDEX indexName;";
        pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("cs", TableOpt.DROP_INDEX));
    }


    @Test
    void testBinary(){
        String sql = "SELECT c1 from runoob_tbl WHERE BINARY runoob_author='runoob.com';";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("runoob_tbl", TableOpt.SELECT));
    }

    @Test
    void testMysqlColumnCreateIndex(){
        String sql = "CREATE TABLE mytable(   \n" + "    ID INT NOT NULL,   \n" + "    username VARCHAR(16) NOT NULL,  \n" + "    INDEX indexName (username(16))   \n" + ");";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("mytable", TableOpt.CREATE_TABLE));
    }

    @Test
    void testPartiton(){
        String sql = " create table goods (\n" + " id int,\n" + " uname char(10)\n" + " )\n" + " partition by range(id) (\n" + " partition p1 values less than (10),               \n" + " partition p2 values less than (20),               \n" + " partition p3 values less than MAXVALUE       \n" + " )";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("goods", TableOpt.CREATE_TABLE));
    }

    @Test
    void testLeadingFrom(){
        String sql = "SELECT TRIM(LEADING 'x' FROM 'xxxbarxxx') FROM apps";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("apps", TableOpt.SELECT));
    }

    @Test
    void testArithmeticUnary(){
        String sql = "SELECT 29 | 15 ,1 ^ 1 , 4 >> 2,  1 << 2 FROM apps";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("apps", TableOpt.SELECT));
    }


    @Test
    void testDateFunction(){
        String sql = "SELECT DATE_ADD('2010-01-02', INTERVAL 31 DAY) FROM apps";
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("apps", TableOpt.SELECT));

        sql = "SELECT adddate('2010-01-02', INTERVAL 31 DAY) FROM apps";
        pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("apps", TableOpt.SELECT));

        sql = "SELECT DATE_SUB('2010-01-01 00:00:00', INTERVAL '1 1:1:1' DAY_SECOND) FROM apps;";
        pair = SqlInspect.inspect(sql, DbType.MYSQL);
        TestConsanguinityAnalyseCommonMethod.assertRes(pair, new TableNameAndOpt("apps", TableOpt.SELECT));
    }


}
