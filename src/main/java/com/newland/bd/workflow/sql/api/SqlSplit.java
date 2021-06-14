package com.newland.bd.workflow.sql.api;

import com.google.common.collect.ImmutableSet;
import com.newland.bd.workflow.sql.parserExt.StatementSplitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Howard on 2018/10/9
 */
public class SqlSplit {

    /**
     * 对sql语句块，按照封号进行拆分
     *
     * @param sql               sql语句块
     * @param returnEmptyString true:要返回空字符串，false:不需要返回空字符串
     * @return
     */
    public static List<String> getStatementList(String sql, boolean returnEmptyString) {
        return getStatementList(sql, ";", returnEmptyString);
    }

    /**
     * 对sql语句块，按照封号进行拆分(不返回空字符串)
     *
     * @param sql sql语句块
     * @return
     */
    public static List<String> getStatementList(String sql) {
        return getStatementList(sql, false);
    }

    /**
     * 对sql语句块，按照delimiter进行拆分
     *
     * @param sql               sql语句块
     * @param delimiter         分隔符
     * @param returnEmptyString true:要返回空字符串，false:不需要返回空字符串
     * @return
     */
    public static List<String> getStatementList(String sql, String delimiter, boolean returnEmptyString) {
        StatementSplitter splitter = new StatementSplitter(sql, ImmutableSet.of(delimiter));

        List<String> statementList = new ArrayList<>();
        List<StatementSplitter.Statement> completeStatements = splitter.getCompleteStatements();
        for (StatementSplitter.Statement statement : completeStatements) {
            filterEmpty(statementList, statement.toString(), returnEmptyString);
        }
        String splitterPartialStatement = splitter.getPartialStatement();
        filterEmpty(statementList, splitterPartialStatement, returnEmptyString);
        return statementList;
    }

    private static void filterEmpty(List<String> statementList, String curSql, boolean returnEmptyString) {
        if (returnEmptyString) {//可以返回空的sql语句
            statementList.add(curSql);
        } else {//不可以返回空的sql语句
            if (curSql != null && curSql.length() != 0) {
                String[] subSqls = curSql.split("\n");
                String lastSql = "";

                for (String subSql : subSqls) {
                    subSql = subSql.trim();
                    if (subSql.length() == 0) {
                        continue;
                    }
                    if (!subSql.startsWith("--")) {
                        lastSql += subSql;
                        lastSql += "\n";
                    }
                }
                if (lastSql.length() != 0) {
                    lastSql = lastSql.substring(0, lastSql.length() - 1);
                }
                if (!isAnotation(lastSql)) {
                    statementList.add(lastSql);
                }
            }
        }
    }

    //用非贪婪模式去陪/**/
    private static final String ANOTATION_REGEX = "/\\*(.*?)\\*/";

    private static boolean isAnotation(String sql) {
        String res = sql.replaceAll(ANOTATION_REGEX, "");
        return res.trim().length() == 0;
    }
}
