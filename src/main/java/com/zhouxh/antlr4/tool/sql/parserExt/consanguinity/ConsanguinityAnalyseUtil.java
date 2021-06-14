package com.zhouxh.antlr4.tool.sql.parserExt.consanguinity;

import com.zhouxh.antlr4.tool.sql.bean.consanguinity.*;

import java.util.*;

/**
 * Create by Howard on 2018/10/20
 */
public class ConsanguinityAnalyseUtil {

    /**
     * 完整columnBean的信息，填充它的归属表等
     * @param columnBean
     * @param curQueryTableBeansList
     * @return
     */
    public static ColumnBean promotionColumnBean(ColumnBean columnBean ,List<TableBean> curQueryTableBeansList,List<TableBean> dstTableBeanList){
        if(columnBean.columnType.equals(ColumnType.SUB_QUERY)){
            return columnBean;
        }
        List<TableBean> tableBeansList = (curQueryTableBeansList==null?dstTableBeanList:curQueryTableBeansList);

        for(TableBean tableBean:tableBeansList){
            if(columnBean.belongToTableAlias.equalsIgnoreCase(tableBean.tableAlias) ||
                    columnBean.belongToTableAlias.equalsIgnoreCase(tableBean.tableName) ||//需要去匹配别名，或者表名，来确定表字段的归属
                    tableBeansList.size() == 1){//如果只有一张表，不需要去匹配别名，就能确定这个字段归属的表
                if(tableBean.tableType.equals(TableType.SUB_QUERY)){//tableBean是一个子查询
                    substitutionColumnBean(columnBean,tableBean.columnBeans);
                }else if(tableBean.tableType.equals(TableType.NORMAL)){//tableBean是一个标准表
                    columnBean.belongToTableName = tableBean.tableName;
                }
                break;
            }
        }
        return columnBean;
    }

    /**
     * 根据With As语句中的上下文，完整columnBean的信息，填充它的归属表等
     * @param columnBean
     * @param withAsTableBeansList
     * @return
     */
    public static ColumnBean promotionColumnBeanWithAs(ColumnBean columnBean ,List<TableBean> withAsTableBeansList){
        if(!columnBean.columnType.equals(ColumnType.NORMAL)){
            return columnBean;
        }

        for(TableBean tableBean:withAsTableBeansList){
            if(columnBean.belongToTableName.equalsIgnoreCase(tableBean.tableAlias) ||
                    columnBean.belongToTableName.equalsIgnoreCase(tableBean.tableName) //需要去匹配别名，或者表名，来确定表字段的归属
                    ){
                if(tableBean.tableType.equals(TableType.SUB_QUERY)){//tableBean是一个子查询
                    substitutionColumnBean(columnBean,tableBean.columnBeans);
                }else if(tableBean.tableType.equals(TableType.NORMAL)){//tableBean是一个标准表
                    columnBean.belongToTableName = tableBean.tableName;
                }
                break;
            }
        }
        return columnBean;
    }




    /**
     * 当一个table是一个子查询，把columnBean进行等价替换
     * @param columnBean
     * @param columnBeansOfTable
     * @return
     */
    private static void substitutionColumnBean(ColumnBean columnBean , List<ColumnBean>  columnBeansOfTable){

        for(ColumnBean subQueryColumnBeans:columnBeansOfTable){
            //把columnBean中的信息，替换成为符合要求的tableBean中的columnBean

            if(columnBean.columnType.equals(ColumnType.NORMAL)){
                if(columnBean.columnName.equalsIgnoreCase(subQueryColumnBeans.columnName) ||
                columnBean.columnName.equalsIgnoreCase(subQueryColumnBeans.columnAlias)){
                    columnBean.copy(subQueryColumnBeans);  //进行等价替换
                }
            }
        }
    }


    /**
     * 标准化DstTableDetailBean
     * 将源表名和源字段中的头尾的`符号去掉
     * @param dstTableDetailBeanList
     */
    public static void standardizeDstTableDetailBean(DstTableDetailBeanList dstTableDetailBeanList){

        for(DstTableDetailBean dstTableDetailBean:dstTableDetailBeanList){
            if(dstTableDetailBean.tablename != null){
                if(dstTableDetailBean.tablename.startsWith("`")){
                    dstTableDetailBean.tablename = dstTableDetailBean.tablename.substring(1, dstTableDetailBean.tablename.length());
                }
                if(dstTableDetailBean.tablename.endsWith("`")){
                    dstTableDetailBean.tablename = dstTableDetailBean.tablename.substring(0, dstTableDetailBean.tablename.length()-1);
                }
            }

            List<String> dstColumnNameList = dstTableDetailBean.dstColumnNameList;
            if(dstColumnNameList != null){

                for(int i = 0; i< dstColumnNameList.size(); i++){
                    String newColumnName = null;
                    if(dstColumnNameList.get(i).startsWith("`")){
                        newColumnName = dstColumnNameList.get(i).substring(1, dstColumnNameList.get(i).length());
                    }

                    if(newColumnName != null && newColumnName.endsWith("`")){
                        newColumnName = newColumnName.substring(0,newColumnName.length()-1);
                    }

                    if(newColumnName != null){
                        dstColumnNameList.set(i,newColumnName);
                    }


                }

            }
        }


    }


    /**
     * 把colunmBean还原为祖先形式的字符串
     * @param columnBean
     * @return
     */
    public static String toConsanguinityFormat(ColumnBean columnBean){
        String formateStr = columnBean.toConsanguinityFormat();
        formateStr = formateStr.replaceAll("( ){2,}"," "); //连续的两个以上的空格，替换成一个空格
        formateStr = formateStr.replaceAll("\\( ","(");  //把左空格括号，替换成一个左括号
        formateStr = formateStr.replaceAll(" \\)",")");  //把空格右括号，替换成一个右括号
        return formateStr;
    }


    /**
     * 返回columnBean关联的原子columnBean，会进行去重操作
     * @param columnBean
     * @return
     */
    public static List<ColumnBean> columnBeanToAtomColumnBeansByRemoveRepetition(ColumnBean columnBean){
        Set<ColumnBean> atomColumnBeanSet = new HashSet<>();
        List<ColumnBean> atomColumnBeanList = new ArrayList<>();
        columnBeanToAtomColumnBeans(columnBean,atomColumnBeanSet);
        for(ColumnBean atomColumnBean:atomColumnBeanSet){
            atomColumnBeanList.add(atomColumnBean);
        }
        return atomColumnBeanList;
    }

    /**
     * 返回columnBean关联的原子columnBean，不会进行去重操作
     * @param columnBean
     * @return
     */
    public static List<ColumnBean> columnBeanToAtomColumnBeansByNoRemoveRepetition(ColumnBean columnBean){

        List<ColumnBean> atomColumnBeanList = new ArrayList<>();
        columnBeanToAtomColumnBeans(columnBean,atomColumnBeanList);
        return atomColumnBeanList;
    }

    private static Collection<ColumnBean> columnBeanToAtomColumnBeans(ColumnBean columnBean, Collection<ColumnBean> atomColumnBeanCollection){
        if(columnBean.columnType.equals(ColumnType.NORMAL) ||
                columnBean.columnType.equals(ColumnType.STRING) ||
                columnBean.columnType.equals(ColumnType.NULL) ||
                columnBean.columnType.equals(ColumnType.NUMERIC)){
            atomColumnBeanCollection.add(columnBean);
        }else{
            if(columnBean.relationColumns!=null){
                //when..then只有then后面的内容需要血缘展现
                if(columnBean.columnType.equals(ColumnType.WHEN_THEN)){
                    columnBeanToAtomColumnBeans(columnBean.relationColumns.get(1),atomColumnBeanCollection);
                }else{
                    for(ColumnBean relationColumnBean: columnBean.relationColumns){
                        columnBeanToAtomColumnBeans(relationColumnBean,atomColumnBeanCollection);
                    }
                }
            }
            if(columnBean.caseWhenColumnBean!=null){
                caseWhenColumnBeanToAtomColumnBeanList(columnBean.caseWhenColumnBean,atomColumnBeanCollection);
            }
        }
        return atomColumnBeanCollection;
    }

    private static Collection<ColumnBean> caseWhenColumnBeanToAtomColumnBeanList(CaseWhenColumnBean caseWhenColumnBean, Collection<ColumnBean> atomColumnBeanCollection){
        if(caseWhenColumnBean.caseExpression!=null){
            //caseExpression不比作为为血缘张展现，所以注释掉
            //columnBeanToAtomColumnBeans(caseWhenColumnBean.caseExpression,atomColumnBeanSet);
        }
        if(caseWhenColumnBean.whenClauses!=null){
            for(ColumnBean relationColumnBean: caseWhenColumnBean.whenClauses){
                columnBeanToAtomColumnBeans(relationColumnBean,atomColumnBeanCollection);
            }
        }
        if(caseWhenColumnBean.elseExpression!=null){
            columnBeanToAtomColumnBeans(caseWhenColumnBean.elseExpression,atomColumnBeanCollection);
        }

        return atomColumnBeanCollection;
    }


    /**
     * 获得输入实体，也就是输入表的表名list
     * @param dstTableDetailBean
     * @return
     */
    public static List<String> getInputEntity(DstTableDetailBean dstTableDetailBean){
        Set<String> inputSet = new HashSet<>();
        List<String> inputList = new ArrayList<>();
        for(ColumnBean columnBean : dstTableDetailBean.sourceColumnBeanList){
            List<ColumnBean> atomColumnBeanList =  ConsanguinityAnalyseUtil.columnBeanToAtomColumnBeansByRemoveRepetition(columnBean);
            for(ColumnBean atomColumnBean:atomColumnBeanList){
                if(atomColumnBean.columnType.equals(ColumnType.NORMAL)
                        && !atomColumnBean.belongToTableName.equals("UNKNOWN")){
                    inputSet.add(atomColumnBean.belongToTableName);
                }
            }
        }

        for (String inputEntity:inputSet){
            inputList.add(inputEntity);
        }
        return inputList;
    }

    /**
     * 获得输出实体，也就是输出表的表名
     * @param dstTableDetailBean
     * @return
     */
    public static String getOutEntity(DstTableDetailBean dstTableDetailBean){
        return dstTableDetailBean.tablename;
    }

    /**
     * 在血缘关系字符串中，将旧的“表名.字段名”替换为新的“表名.字段名”
     * @param consanguinityRelation
     * @param oldTableColumnName
     * @param newTableColumnName
     * @return
     */
    public static String replaceConsanguinityRelation(String consanguinityRelation,String oldTableColumnName,String newTableColumnName){

        oldTableColumnName = replaceOldTableColumnName(oldTableColumnName);
        consanguinityRelation = consanguinityRelation.replaceAll("^"+oldTableColumnName+"$",newTableColumnName);
        consanguinityRelation = consanguinityRelation.replaceAll("([^\\w])"+oldTableColumnName+"([^\\w])","$1"+newTableColumnName+"$2");
        consanguinityRelation = consanguinityRelation.replaceAll("^"+oldTableColumnName+"([^\\w])",newTableColumnName+"$1");
        consanguinityRelation = consanguinityRelation.replaceAll("([^\\w])"+oldTableColumnName+"$","$1"+newTableColumnName);
        return consanguinityRelation;
    }

    /**
     * 如果字符串中出现正则表达式的关键字，要进行关键字的转义
     * @param oldTableColumnName
     * @return
     */
    private static String replaceOldTableColumnName(String oldTableColumnName){
        //“\”替换要放在第一行
        oldTableColumnName = oldTableColumnName.replace("\\","\\\\");

        oldTableColumnName = oldTableColumnName.replace("$","\\$");
        oldTableColumnName = oldTableColumnName.replace(".","\\.");
        oldTableColumnName = oldTableColumnName.replace("{","\\{");
        oldTableColumnName = oldTableColumnName.replace("}","\\}");
        oldTableColumnName = oldTableColumnName.replace("[","\\[");
        oldTableColumnName = oldTableColumnName.replace("]","\\]");


        return oldTableColumnName;
    }

}
