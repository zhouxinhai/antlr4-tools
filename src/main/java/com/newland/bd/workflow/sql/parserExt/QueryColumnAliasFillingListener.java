package com.newland.bd.workflow.sql.parserExt;

import com.newland.bd.workflow.sql.bean.ColumnAndAliasName;
import com.newland.bd.workflow.sql.parser.EntireSqlBaseListener;
import com.newland.bd.workflow.sql.parser.EntireSqlParser;
import org.antlr.v4.runtime.TokenStream;

import java.util.*;

/**
 * Create by Howard on 2018/10/12
 */
public class QueryColumnAliasFillingListener extends EntireSqlBaseListener {
    private TokenStream tokenStream;


    QueryColumnAliasFilling queryColumnAliasFilling = new QueryColumnAliasFilling();

    public QueryColumnAliasFillingListener(EntireSqlParser parser){
        tokenStream = parser.getTokenStream();
    }

    public List<ColumnAndAliasName> getColumnAndAliasNameList() {
        return queryColumnAliasFilling.columnAndAliasNameList;
    }

    @Override
    public void enterSelectSingle(EntireSqlParser.SelectSingleContext ctx) {

        queryColumnAliasFilling.selectSingleStatck.push(ctx);
        if(queryColumnAliasFilling.selectSingleStatck.size() >1){//说明是嵌套在SelectSingle中的SelectSingle，无需填充别名
            return;
        }
        String columnName = tokenStream.getText(ctx.expression());
        ColumnAndAliasName columnAndAliasName = new ColumnAndAliasName(columnName);

        if(ctx.aliasName() != null){
            String chineseAnnotation =  ctx.aliasName().getText();
            //中文别名应该是用双引号包起来的中文，把双引号去掉
            columnAndAliasName.setChineseAnnotation(chineseAnnotation.replaceAll("\"",""));
        }

        queryColumnAliasFilling.tmpColumnAndAliasList.add(columnAndAliasName);
    }


    @Override
    public void exitSelectSingle(EntireSqlParser.SelectSingleContext ctx) {
        queryColumnAliasFilling.selectSingleStatck.pop();
    }


    @Override
    public void exitSingleStatement(EntireSqlParser.SingleStatementContext ctx) {
        queryColumnAliasFilling.aliasNameCache.clear();
        queryColumnAliasFilling.columnAndAliasNameList.clear();
        //对所有的columnName进行分析，决定是否需要进行别名填充
        queryColumnAliasFilling.listAliasFilling();
        queryColumnAliasFilling.tmpColumnAndAliasList.clear();
    }

    private class QueryColumnAliasFilling{
        private List<ColumnAndAliasName> tmpColumnAndAliasList = new ArrayList<>();
        private List<ColumnAndAliasName> columnAndAliasNameList = new ArrayList<>();
        private Map<String,Integer> aliasNameCache = new HashMap<>();
        private Stack<EntireSqlParser.SelectSingleContext> selectSingleStatck = new Stack<>();


        public void clear(){
            selectSingleStatck.clear();
            tmpColumnAndAliasList.clear();
            columnAndAliasNameList.clear();
            aliasNameCache.clear();
        }

        /**
         * 字段是否是字符串形式
         * @param columnAndAliasName
         * @return
         */
        private boolean stringAliasFilling(ColumnAndAliasName columnAndAliasName){
            if((columnAndAliasName.getAliasName().charAt(0) == '\''
                    && columnAndAliasName.getAliasName().charAt(columnAndAliasName.getAliasName().length()-1) == '\'')
                    ||( columnAndAliasName.getAliasName().charAt(0) == '"'
                    && columnAndAliasName.getAliasName().charAt(columnAndAliasName.getAliasName().length()-1) == '"')){

                columnAndAliasName.setAliasName("STR");
                columnAndAliasName.setFillingAlias(true);
                return true;
            }
            return false;
        }

        /**
         * 字段是否是子查询形式
         * @param columnAndAliasName
         * @return
         */
        private boolean subQueryFilling(ColumnAndAliasName columnAndAliasName){
            if(columnAndAliasName.getAliasName().charAt(0) == '('
                    && columnAndAliasName.getAliasName().charAt(columnAndAliasName.getAliasName().length()-1) == ')'){

                columnAndAliasName.setAliasName("SUBQUERY");
                columnAndAliasName.setFillingAlias(true);
                return true;
            }
            return false;
        }

        /**
         * 字段是否是函数形式
         * @param columnAndAliasName
         */
        private void functionAliasFilling(ColumnAndAliasName columnAndAliasName){
            //函数不是以"("开始，但是是以")"结束
            if(columnAndAliasName.getAliasName().charAt(0) != '('
                    && columnAndAliasName.getAliasName().charAt(columnAndAliasName.getAliasName().length()-1) == ')'){

                columnAndAliasName.setAliasName("FUNC");
                columnAndAliasName.setFillingAlias(true);
            }
        }

        /**
         * 去掉表字段前面的用户模式
         * @param columnAndAliasName
         */
        private void clearUsermodeOfColumn(ColumnAndAliasName columnAndAliasName){
            String[] split = columnAndAliasName.getAliasName().split("\\.");
            if(split.length> 1){
                columnAndAliasName.setAliasName(split[1]);
                columnAndAliasName.setFillingAlias(true);
            }
        }


        private void oneObjAliasFilling(ColumnAndAliasName columnAndAliasName){

            //字段是否是字符串形式
            if(!stringAliasFilling(columnAndAliasName)){
                //字段是否是子查询形式
                if(!subQueryFilling(columnAndAliasName)){
                    //字段是否是函数形式
                    functionAliasFilling(columnAndAliasName);
                }
            }

            //去掉表字段前面的用户模式
            clearUsermodeOfColumn(columnAndAliasName);

            Integer count = aliasNameCache.get(columnAndAliasName.getAliasName());
            if(count == null){
                count = 0;
            }
            aliasNameCache.remove(columnAndAliasName.getAliasName());
            count+=1;
            aliasNameCache.put(columnAndAliasName.getAliasName(),count);

            if(count !=1){//说明别名重名了，需要在别名下加后缀
                columnAndAliasName.setAliasName(columnAndAliasName.getAliasName()+"_"+count);
                columnAndAliasName.setFillingAlias(true);
            }

        }

        private void listAliasFilling(){
            for(ColumnAndAliasName columnAndAliasName:tmpColumnAndAliasList){
                oneObjAliasFilling(columnAndAliasName);
                columnAndAliasNameList.add(columnAndAliasName);
            }
        }
    }

}
