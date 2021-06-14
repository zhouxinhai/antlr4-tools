package com.zhouxh.antlr4.tool.sql.parserExt;

import com.zhouxh.antlr4.tool.sql.parser.EntireSqlBaseListener;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import lombok.Data;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Create by zhouxinhai on 2019/10/31
 */
@Data
public class QuerySplitListener extends EntireSqlBaseListener {
    private TokenStream tokenStream;
    private Stack<EntireSqlParser.QueryNoWithContext> queryNoWithContextStack = new Stack<>();
    private Stack<EntireSqlParser.QuerySpecificationContext> querySpecificationContextStack = new Stack<>();
    private Stack<EntireSqlParser.SelectSingleContext> selectSingleContextStack = new Stack<>();
    private Stack<EntireSqlParser.SortItemContext> sortItemContextStack = new Stack<>();
    private Stack<EntireSqlParser.DereferenceContext> dereferenceContextStack = new Stack<>();

    private SqlStruct sqlStruct = new SqlStruct();

    public QuerySplitListener(EntireSqlParser parser) {
        tokenStream = parser.getTokenStream();
    }

    @Override
    public void enterSortItem(EntireSqlParser.SortItemContext ctx) {
        sortItemContextStack.push(ctx);
    }

    @Override
    public void exitSortItem(EntireSqlParser.SortItemContext ctx) {
        sortItemContextStack.pop();
    }

    @Override
    public void enterColumnReference(EntireSqlParser.ColumnReferenceContext ctx) {
        if (sortItemContextStack.size() < 1) {
            return;
        }
        if (dereferenceContextStack.size() > 0) {
            return;
        }

        sqlStruct.getSortItems().add(ctx.getText());
    }

    @Override
    public void enterDereference(EntireSqlParser.DereferenceContext ctx) {
        if (sortItemContextStack.size() < 1) {
            return;
        }
        dereferenceContextStack.push(ctx);
        sqlStruct.getSortItems().add(ctx.getText());
    }

    @Override
    public void exitDereference(EntireSqlParser.DereferenceContext ctx) {
        if (sortItemContextStack.size() < 1) {
            return;
        }
        dereferenceContextStack.pop();
    }

    //前开后闭区间
    private String getExpressionOfOpenedClosedInterval(Token start, Token stop) {
        Interval interval = Interval.of(start.getTokenIndex() + 1, stop.getTokenIndex());
        return tokenStream.getText(interval);
    }

    //前闭后开区间
    private String getExpressionOfClosedOpenedInterval(int start, int stop) {
        Interval interval = Interval.of(start, stop);
        return tokenStream.getText(interval);
    }

    @Override
    public void enterQueryNoWith(EntireSqlParser.QueryNoWithContext ctx) {
        queryNoWithContextStack.push(ctx);
    }

    @Override
    public void exitQueryNoWith(EntireSqlParser.QueryNoWithContext ctx) {

        if (queryNoWithContextStack.size() > 1) {
            queryNoWithContextStack.pop();
            return;
        }

        String tailExp = getExpressionOfOpenedClosedInterval(ctx.queryTerm().stop, ctx.stop);
        if (sqlStruct.getTailExpression() != null) {
            tailExp = sqlStruct.getTailExpression() + tailExp;
        }
        sqlStruct.setTailExpression(tailExp);
        queryNoWithContextStack.pop();
    }

    @Override
    public void enterQuerySpecification(EntireSqlParser.QuerySpecificationContext ctx) {
        querySpecificationContextStack.push(ctx);
        if (querySpecificationContextStack.size() > 1) {
            return;
        }

        List<EntireSqlParser.SelectItemContext> selectItemContexts = ctx.selectItem();
        EntireSqlParser.SelectItemContext firstSelectItemContext = selectItemContexts.get(0);

        String sqlHead = getExpressionOfClosedOpenedInterval(ctx.SELECT().getSymbol().getTokenIndex(),
                                                             firstSelectItemContext.start.getTokenIndex() - 1);
        int selectPos = sqlHead.toUpperCase().indexOf("SELECT");
        sqlHead = sqlHead.substring(selectPos + "SELECT".length());
        sqlStruct.setHeadExpression(sqlHead);

        StringBuilder tableName = new StringBuilder();
        if (ctx.relation() != null || !ctx.relation().isEmpty()) {
            for (int i = 0; i < ctx.relation().size(); i++) {
                if (i != 0) {
                    tableName.append(",");
                }

                tableName.append(tokenStream.getText(ctx.relation().get(i)));
            }
            sqlStruct.setTableName(tableName.toString());
        }

        if (sqlStruct.getTableName() == null) {
            EntireSqlParser.SelectItemContext lastSelectItemContext = selectItemContexts.get(selectItemContexts.size() - 1);
            sqlStruct.setTailExpression(getExpressionOfOpenedClosedInterval(lastSelectItemContext.stop, ctx.stop));
        } else {
            sqlStruct.setTailExpression(getExpressionOfOpenedClosedInterval(ctx.relation().get(ctx.relation().size() - 1).stop, ctx.stop));
        }
    }

    @Override
    public void exitQuerySpecification(EntireSqlParser.QuerySpecificationContext ctx) {
        querySpecificationContextStack.pop();
    }

    @Override
    public void enterSelectSingle(EntireSqlParser.SelectSingleContext ctx) {
        selectSingleContextStack.push(ctx);
        if (selectSingleContextStack.size() > 1) {
            return;
        }

        if (querySpecificationContextStack.size() > 1) {
            return;
        }

        ColumnStruct columnStruct = new ColumnStruct();
        columnStruct.setFullColumnName(tokenStream.getText(ctx));
        columnStruct.setColumnName(tokenStream.getText(ctx.expression()));
        if (ctx.aliasName() != null) {
            columnStruct.setAlias(tokenStream.getText(ctx.aliasName()));
        }

        sqlStruct.getSelectItemList().add(columnStruct);
    }

    @Override
    public void exitSelectSingle(EntireSqlParser.SelectSingleContext ctx) {
        selectSingleContextStack.pop();
    }

    @Data
    public class SqlStruct {
        private String headExpression;
        private List<ColumnStruct> selectItemList = new ArrayList<>();
        String tableName;
        private String tailExpression;
        private List<String> sortItems = new ArrayList<>();
    }

    @Data
    public class ColumnStruct {
        private String columnName = "";
        private String alias = "";
        private String fullColumnName = "";
    }
}
