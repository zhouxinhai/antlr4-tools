package com.newland.bd.workflow.sql.parserExt.inspect;

import com.newland.bd.workflow.sql.parser.EntireSqlParser;
import com.newland.bd.workflow.sql.parserExt.HsqlRecognitionException;
import org.antlr.v4.runtime.TokenStream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 校验字段用到的表别名是否真实存在
 * Create by Howard on 2018/10/16
 */
public class ColumnAndTableAliasInspect {

    private class ColumnAndTableAlias {
        public  EntireSqlParser.DereferenceContext ctx;
        public String TableAliasName = "";
    }
    private TokenStream tokenStream;
    private Set<String>  tableAliasNameSet = new HashSet<>();   //所有的表用到的别名
    private List<ColumnAndTableAlias> columnCtxAndTableAliasesList =  new ArrayList<>();  //所有字段用到的表别名

    public ColumnAndTableAliasInspect(TokenStream tokenStream){
        this.tokenStream = tokenStream;
    }


    public void clear(){
        tableAliasNameSet.clear();
        columnCtxAndTableAliasesList.clear();
    }

    /**
     * 把所有表别名都收集起来
     */
    public void storeTableAliasNameSet(EntireSqlParser.AliasedRelationContext ctx){
        if(ctx.relationPrimary()!=null){
            String tableName = ctx.relationPrimary().getText();
            tableAliasNameSet.add(tableName);
        }

        if(ctx.identifier()!=null){
            String tableAliasName = ctx.identifier().getText();
            tableAliasNameSet.add(tableAliasName);
        }
    }

    /**
     * 把所有字段的引用到的表别名都收集起来
     */
    public void storeColumnCtxAndTableAliasesList(EntireSqlParser.DereferenceContext ctx){
        if(ctx.primaryExpression() != null){
            String tableAliasName  = ctx.primaryExpression().getText();
            ColumnAndTableAlias columnCtxAndTableAlias =  new ColumnAndTableAlias();
            columnCtxAndTableAlias.ctx = ctx;
            columnCtxAndTableAlias.TableAliasName =tableAliasName;
            columnCtxAndTableAliasesList.add(columnCtxAndTableAlias);
        }
    }

    /**
     * 校验字段用到的别名是否都是定义过的表别名
     */
    public void columnAndTableAliasInspect(){
        for(ColumnAndTableAlias columnCtxAndTableAlias:columnCtxAndTableAliasesList){
            if(!tableAliasNameSet.contains(columnCtxAndTableAlias.TableAliasName)){
                //字段引用的表别名为定义过
                throw new HsqlRecognitionException(columnCtxAndTableAlias.ctx.primaryExpression().start,
                        "无法识别"+tokenStream.getText(columnCtxAndTableAlias.ctx)+"中的表别名:"+
                                tokenStream.getText(columnCtxAndTableAlias.ctx.primaryExpression()));
            }
        }
        clear();
    }

}
