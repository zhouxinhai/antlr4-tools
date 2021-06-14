package com.zhouxh.antlr4.tool.sql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouxh.antlr4.tool.sql.api.Sql2Hsql;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * Create by Howard on 2018/9/28
 */
public class TestSql2Hsql {

    void jsonResAssertSuccess(String retJson){
        JSONObject jsonObject = JSON.parseObject(retJson);
        boolean res = jsonObject.getBoolean("success");
        Assert.assertEquals(res,true);
    }

    void jsonResAssertFail(String retJson){
        JSONObject jsonObject = JSON.parseObject(retJson);
        boolean res = jsonObject.getBoolean("success");
        Assert.assertEquals(res,false);
    }

    private static String readFileByLines(String fileName) {
            StringBuilder buf = new StringBuilder();
            File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//                System.out.println("以行为单位读取文件内容，一次读一整行：");
            String tempString;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                buf.append(tempString);
                buf.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

            return buf.toString();
        }

    /*    @Test
        void test1(){
            String filename = Objects.requireNonNull(TestSql2Hsql.class.getClassLoader().getResource("antlr/demo.sql")).getFile();
            ANTLRInputStream input = new ANTLRInputStream(readFileByLines(filename).toUpperCase());

            SqlBaseLexer lexer = new SqlBaseLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SqlBaseParser parser = new SqlBaseParser(tokens);

//            tokens.fill();
//            List<Token> tokenList = tokens.getTokens();
//            for (Token token:
//                 tokenList) {
//                System.out.println("token===="+token.toString());
//            }

            //parser.removeErrorListeners(); //移除ConsoleErrorListener
            ExtErrorListener errorListener = new ExtErrorListener();
            parser.addErrorListener(errorListener);

            ParseTree tree = parser.singleStatement();

            if(errorListener.getErrLexerMsg()!= null){
                System.err.println("解析错误，错误信息："+ errorListener.getErrLexerMsg().getCompleteErrMsg());
                return;
            }

            System.out.println("-----------------------------------------");
            System.out.println("树形结构输出："+tree.toStringTree(parser));
            System.out.println("-----------------------------------------");
            System.out.println("不带空格的输出："+tree.getText());
            System.out.println("-----------------------------------------");
            System.out.println("带空格的输出："+tokens.getText());
            TokenStreamRewriter  tokenStreamRewriter=  new TokenStreamRewriter(tokens);
            System.out.println("带空格的输出："+tokenStreamRewriter.getText());
            System.out.println("-----------------------------------------");


            //监听器遍历
            TranslateSqlListener listener = new TranslateSqlListener(parser,tokens);
            ParseTreeWalker walker =new  ParseTreeWalker();  //新建一个标准遍历器
            walker.walk(listener,tree);
            System.out.println("修改内容后的输出："+listener.tokenStreamRewriter.getText());
            System.out.println("-----------------------------------------");
        }*/

        @Test
        void testFile(){
            String filename = Objects.requireNonNull(TestSql2Hsql.class.getClassLoader().getResource("antlr/demo.sql")).getFile();
            String retJson = Sql2Hsql.convert(readFileByLines(filename));
            System.out.println(retJson);
            jsonResAssertSuccess(retJson);
        }

        @Test
        void testQuery(){
            //String sql = "select * from user_product a,user_info b where a.user_id = b.user_id ";
            //String sql = "select * from user_product inner join user_info on(user_product.user_id = user_info.user_id)";
            String sql = "select c1,c2 from user_product\n" +
                         "minus\n" +
                         "select c1,c2 from user_product_buckets";
            String retJson = Sql2Hsql.convert(sql);
            System.out.println(retJson);
            jsonResAssertFail(retJson);
        }

        @Test
        void testInsert(){
            String sql = "insert into user_product_buckets(user_id,product_id) values(\"001\",\"p001\")";
            String retJson = Sql2Hsql.convert(sql);
            System.out.println(retJson);
            jsonResAssertSuccess(retJson);
        }

        @Test
        void addColumnTest(){
            String sql  = "alter table user_product add column c1 varchar2(100)";
            String retJson = Sql2Hsql.convert(sql);
            System.out.println(retJson);
            jsonResAssertSuccess(retJson);
        }

        @Test
        void renameColumnTest1(){
            String sql  = "alter table user_product rename column c1 to c11 long";
            String retJson = Sql2Hsql.convert(sql);
            System.out.println(retJson);
            jsonResAssertSuccess(retJson);
        }

        @Test
        void renameColumnTest2(){
            String sql  = "alter table user_product rename column c1 to c11";
            String retJson = Sql2Hsql.convert(sql);
            System.out.println(retJson);
            jsonResAssertSuccess(retJson);
        }

        @Test
        void modifyColumnTest(){
            String sql  = "ALTER TABLE USER_PRODUCT MODIFY C1 NUMBER(13,1)";
            String retJson = Sql2Hsql.convert(sql);
            System.out.println(retJson);
            jsonResAssertSuccess(retJson);
        }

        @Test
        void createTableTest(){
            String sql  = "CREATE TABLE EMP\n" +
                    "(\n" +
                    "EMP_ID VARCHAR2(100),\n" +
                    "NAME NUMERIC(10,2),\n" +
                    "MANAGER_ID BLOB\n" +
                    ");";
            String retJson = Sql2Hsql.convert(sql);
            System.out.println(retJson);
            jsonResAssertSuccess(retJson);
        }

        @Test
        void sysdateTest(){
            String sql = "select sysdate from dual";
            String retJson = Sql2Hsql.convert(sql);
            System.out.println(retJson);
            jsonResAssertSuccess(retJson);
        }

        @Test
        void functionTest(){
            String sql = "select FUN_TEST( FUN_TEST(c1 ,1),2) from dual";
            String retJson = Sql2Hsql.convert(sql);
            System.out.println(retJson);
            jsonResAssertSuccess(retJson);
        }

        @Test
        void multiStatementTest(){
            String sql = "CREATE TABLE EMP\n" +
                    "(\n" +
                    "EMP_ID VARCHAR2(100),\n" +
                    "NAME NUMERIC(10,2),\n" +
                    "MANAGER_ID BLOB\n" +
                    ");\n" +
                    "select FUN_TEST( FUN_TEST(c1 ,1),2) from dual;\n" +
                    "select sysdate from dual";
            String retJson = Sql2Hsql.convert(sql);
            System.out.println(retJson);
            jsonResAssertSuccess(retJson);
        }

    @Test
    void testInsetSelect(){
        String sql = "INSERT INTO TABLE_NODE8891 SELECT \n" +
                "TEST_HIVE_IN.HIVE_IN_INT HIVE_IN_INT ,\n" +
                "TEST_HIVE_IN.HIVE_IN_STRING HIVE_IN_STRING ,\n" +
                "TEST_HIVE_IN.HIVE_IN_DOUBLE HIVE_IN_DOUBLE ,\n" +
                "TEST_HIVE_IN.HIVE_IN_FLOAT HIVE_IN_FLOAT ,\n" +
                "TEST_HIVE_IN.HIVE_IN_DECIMAL HIVE_IN_DECIMAL  \n" +
                "FROM TEST_HIVE_IN\n";
        String retJson = Sql2Hsql.convert(sql);
        System.out.println(retJson);
        jsonResAssertSuccess(retJson);

    }




}
