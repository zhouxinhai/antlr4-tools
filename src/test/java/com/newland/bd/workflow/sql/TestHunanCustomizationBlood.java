package com.newland.bd.workflow.sql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newland.bd.workflow.sql.api.SqlConsanguinityAnalyse;
import com.newland.bd.workflow.sql.api.SqlInspect;
import com.newland.bd.workflow.sql.bean.DbType;
import com.newland.bd.workflow.sql.bean.TableNameAndOpt;
import com.newland.bd.workflow.sql.bean.consanguinity.SqlConsanguinityBean;
import com.newland.bd.workflow.sql.bean.consanguinity.SqlConsanguinityBeanList;
import com.newland.bd.workflow.sql.bean.consanguinity.TableConsanguinityBean;
import com.newland.bd.workflow.sql.bean.consanguinity.TableConsanguinityBeanList;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Create by Howard on 2019/3/14
 */
public class TestHunanCustomizationBlood {


    /**
     * 校验sql返回的报文
     * @param retJson
     * @return
     */
    private boolean isSqlValid(String retJson){
        JSONObject jsonObject = JSON.parseObject(retJson);
        boolean res = jsonObject.getBoolean("success");
        String output = jsonObject.getString("output");
        if(!res){
            System.err.println(output);
        }
        return res;
    }

    /**
     * 将sql中所有涉及到的表都打印出来
     * @param tableNameAndOptList
     */
    private void displayTableName(List<TableNameAndOpt> tableNameAndOptList){
        for(TableNameAndOpt tableNameandOpt : tableNameAndOptList){
            System.out.println("对表【"+tableNameandOpt.getTableName()+"】进行了【"+ tableNameandOpt.getTableOpt() +"】操作");
        }
    }

    /**
     * 展示血缘信息
     * @param sqlConsanguinityBeanList
     */
    private void displayConsanguinity(SqlConsanguinityBeanList sqlConsanguinityBeanList){
        for(SqlConsanguinityBean sqlConsanguinityBean:sqlConsanguinityBeanList){
            System.out.println("===============================================");
            System.out.println("目标表："+sqlConsanguinityBean.dstTablename);
            System.out.println("目标字段："+sqlConsanguinityBean.dstColumnName);
            System.out.println("源表："+sqlConsanguinityBean.srcTablename);
            System.out.println("源字段："+sqlConsanguinityBean.srcColumnName);
            System.out.println("字段转换规则："+sqlConsanguinityBean.convertRule);
        }
    }

    private void displayTableConsanguinity(TableConsanguinityBeanList tableConsanguinityBeans) {
        for (TableConsanguinityBean tableConsanguinityBean : tableConsanguinityBeans) {
            System.out.println("===============================================");
            System.out.println("目标表：" + tableConsanguinityBean.dstTablename);
            System.out.println("源表：" + tableConsanguinityBean.srcTablename);
        }
    }

    /**
     * 源和目标的字段一一映射
     */
    @Test
    void test1(){
        //定义一条sql语句
        String sql = "INSERT INTO TC_US_USER_DISC_GRPT_M\n" +
                "partition(STATIS_MONTH=${DATE_STRING})\n" +
                "(\n" +
                "city_code\n" +
                ",user_id\n" +
                ",busi_code\n" +
                ",start_date\n" +
                ",end_date\n" +
                ",order_stat\n" +
                ",brand\n" +
                ")\n" +
                "SELECT\n" +
                "a.city_code\n" +
                ",A.USER_ID\n" +
                ",A.disc_id\n" +
                ",A.start_time\n" +
                ",A.end_time\n" +
                ",B.user_stat\n" +
                ",B.report_brand\n" +
                "FROM TB_SU_USER_DISC_M A,\n" +
                "TC_UB_USER_GRPT_M B\n" +
                "WHERE A.USER_ID=B.USER_ID\n" +
                "AND A.STATIS_MONTH = ${DATE_STRING}\n" +
                "AND b.STATIS_MONTH = ${DATE_STRING}\n" +
                "AND a.end_time>=concat('${C_EYYYY_MM_DD}',' 23:59:59')\n" +
                "AND cast(replace(substr(a.start_time, 1, 7), '-', '') as int) <= ${DATE_STRING}";

        //调用接口1，“获取sql语句中涉及操作表”，注意要传入数据库的类型
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);

        //检测接口1中sql语句是否正确
        if(!isSqlValid(pair.getLeft())){
            System.err.println("调用接口1，sql语句解析失败");
            return;
        }

        //将sql中所有涉及到的表都打印出来，湖南侧自行对返回的表进行实体校验
        displayTableName(pair.getRight());

        //调用接口2，”获取sql语句的血缘结构“
        Pair<String, SqlConsanguinityBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyseExt(sql, null, false);

        //检测接口2中sql语句是否正确
        if(!isSqlValid(stringListPair.getLeft())){
            System.err.println("调用接口2，sql语句解析失败");
            return;
        }

        //展示血缘信息
        displayConsanguinity(stringListPair.getRight());
    }

    /**
     * 源字段清晰和目标字段不明确
     */
    @Test
    void test2(){
        //定义一条sql语句
        String sql = "INSERT INTO TC_US_USER_DISC_GRPT_M\n" +
                "SELECT\n" +
                "a.city_code\n" +
                ",A.USER_ID\n" +
                ",A.disc_id\n" +
                ",A.start_time\n" +
                ",A.end_time\n" +
                ",B.user_stat\n" +
                ",B.report_brand\n" +
                "FROM TB_SU_USER_DISC_M A,\n" +
                "TC_UB_USER_GRPT_M B\n" +
                "WHERE A.USER_ID=B.USER_ID\n" +
                "AND A.STATIS_MONTH = ${DATE_STRING}\n" +
                "AND b.STATIS_MONTH = ${DATE_STRING}\n" +
                "AND a.end_time>=concat('${C_EYYYY_MM_DD}',' 23:59:59')\n" +
                "AND cast(replace(substr(a.start_time, 1, 7), '-', '') as int) <= ${DATE_STRING}";

        //调用接口1，“获取sql语句中涉及操作表”，注意要传入数据库的类型
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE);

        //检测接口1中sql语句是否正确
        if(!isSqlValid(pair.getLeft())){
            System.err.println("调用接口1，sql语句解析失败");
            return;
        }

        //将sql中所有涉及到的表都打印出来，湖南侧自行对返回的表进行实体校验
        displayTableName(pair.getRight());

        //调用接口2，”获取sql语句的血缘结构“
        Pair<String, SqlConsanguinityBeanList> stringListPair = SqlConsanguinityAnalyse.consanguinityAnalyseExt(sql, null, true);

        //检测接口2中sql语句是否正确
        if(!isSqlValid(stringListPair.getLeft())){
            System.err.println("调用接口2，sql语句解析失败");
            return;
        }

        //展示血缘信息
        displayConsanguinity(stringListPair.getRight());
    }

    /**
     * 只需要得到目标表和源表的映射关系
     */
    @Test
    void test3(){
        //定义一条sql语句
        String sql =
                "insert into t1 \n" +
                " select * from (select * from t2),t3\n" +
                " union\n" +
                " select * from (select * from t4),\n" +
                " (\n" +
                "  select * from (select * from t5),t6\n" +
                "  union\n" +
                "  select * from (select * from t7)\n" +
                " )";

        //调用接口1，“获取sql语句中涉及操作表”，注意要传入数据库的类型
        Pair<String, List<TableNameAndOpt>> pair = SqlInspect.inspect(sql, DbType.HIVE, true);

        //检测接口1中sql语句是否正确
        if(!isSqlValid(pair.getLeft())){
            System.err.println("调用接口1，sql语句解析失败");
            return;
        }

        //将sql中所有涉及到的表都打印出来，湖南侧自行对返回的表进行实体校验
        displayTableName(pair.getRight());


        //调用接口2，”获取sql语句的表血缘结构“
        Pair<String, TableConsanguinityBeanList> stringListPair = SqlConsanguinityAnalyse.tableConsanguinityAnalyse(sql);

        //检测接口2中sql语句是否正确
        if(!isSqlValid(stringListPair.getLeft())){
            System.err.println("调用接口2，sql语句解析失败");
            return;
        }

        //展示血缘信息
        displayTableConsanguinity(stringListPair.getRight());

    }

}
