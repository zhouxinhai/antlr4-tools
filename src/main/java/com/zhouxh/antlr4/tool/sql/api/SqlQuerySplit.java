package com.zhouxh.antlr4.tool.sql.api;

import com.zhouxh.antlr4.tool.sql.bean.SqlHandleResult;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlLexer;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import com.zhouxh.antlr4.tool.sql.parserExt.ExtErrorListener;
import com.zhouxh.antlr4.tool.sql.parserExt.HsqlRecognitionException;
import com.zhouxh.antlr4.tool.sql.parserExt.QuerySplitListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Create by Howard on 2018/10/12
 */
public class SqlQuerySplit {

    public static Pair<String, QuerySplitListener.SqlStruct> querySplit(String querySql) {

        //将部分的sql，拼接成完整的sql，便于语法解析
        try {
            ANTLRInputStream input = new ANTLRInputStream(querySql);
            EntireSqlLexer lexer = new EntireSqlLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            EntireSqlParser parser = new EntireSqlParser(tokens);
            ExtErrorListener errorListener = new ExtErrorListener();
            parser.addErrorListener(errorListener);
            parser.setAllTypeTrue();

            ParseTree tree = parser.queryStatement();
            if (errorListener.getErrLexerMsg() != null) {
                return Pair.of(JsonUtil.responseJson(false, errorListener.getErrLexerMsg().getCompleteErrMsg(), errorListener.getErrLexerMsg()),
                               null);
            }

            //监听器遍历
            QuerySplitListener listener = new QuerySplitListener(parser);
            ParseTreeWalker walker = new ParseTreeWalker();  //新建一个标准遍历器
            walker.walk(listener, tree);

            if (errorListener.getErrLexerMsg() != null) {
                return Pair.of(JsonUtil.responseJson(false, errorListener.getErrLexerMsg().getCompleteErrMsg(), errorListener.getErrLexerMsg()),
                               null);
            }

            return Pair.of(JsonUtil.responseJson(true, "", null), listener.getSqlStruct());
        } catch (HsqlRecognitionException e) {
            return Pair.of(JsonUtil.responseJson(false, e.getMessage(), e.getErrLexerMsg()), null);
        }
    }

    /**
     * 根据selectItem的顺序，对select 类型的sql语句进行重组
     *
     * @param querySql
     * @param selectItemOrder
     * @return left: SqlHandleResult对应的json报文   rigth：重组后的select 类型的sql语句
     */
    public static Pair<Boolean, String> regroupQuerySql(String querySql, int[] selectItemOrder) {
        Pair<String, QuerySplitListener.SqlStruct> pair = querySplit(querySql);
        SqlHandleResult sqlHandleResult = JsonUtil.convertToSqlHandleResult(pair.getLeft());

        if (!sqlHandleResult.getSuccess()) {
            return Pair.of(false, sqlHandleResult.getOutput());
        }

        QuerySplitListener.SqlStruct sqlStruct = pair.getRight();

        StringBuilder sb = new StringBuilder();
        if(sqlStruct.getHeadExpression().startsWith(" ")){
            sb.append("select");
        }else{
            sb.append("select ");
        }

        sb.append(sqlStruct.getHeadExpression());

        if (selectItemOrder == null || selectItemOrder.length == 0) {
            for (QuerySplitListener.ColumnStruct selectItem : sqlStruct.getSelectItemList()) {
                sb.append(selectItem.getFullColumnName());
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        } else {
            for (int index : selectItemOrder) {
                if (index >= sqlStruct.getSelectItemList().size()) {
                    return Pair.of(false,
                                   String.format("对查询sql的selectItem进行顺序重组中，规则下标[%d]超出或达到了selectItem的上限[%d]",
                                                 index,
                                                 sqlStruct.getSelectItemList().size()));
                }
                sb.append(sqlStruct.getSelectItemList().get(index).getFullColumnName());
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append(" from ");
        sb.append(sqlStruct.getTableName());
        sb.append(sqlStruct.getTailExpression());
        return Pair.of(true, sb.toString());
    }
}
