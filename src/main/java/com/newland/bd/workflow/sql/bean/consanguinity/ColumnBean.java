package com.newland.bd.workflow.sql.bean.consanguinity;

import java.util.List;
import java.util.Objects;

/**
 * Create by Howard on 2018/10/19
 */
public class ColumnBean {
    public String columnName = "";    //字段名字(要求大写)
    public String columnAlias = "";   //字段别名(要求大写)
    public String belongToTableName = "UNKNOWN"; //该字段归属于表的名字(要求大写)，当relatonColumns不是null的时候，belongToTableName为"<NONE>"
    public String belongToTableAlias = ""; //该字段归属于表的别名的名字(要求大写),当relatonColumns不是null的时候，belongToTableAlias为"<NONE>"
    public String convertRule = "" ; //转换规则，比如函数名字，union，minus等等
    public ColumnType columnType = ColumnType.NORMAL; //是否是子查询返回的ColumnBean，比如：select c1,(select c2 from table2) from table1 ，这里的(select c2 from table2)作为一个ColumnBean的时候，isSubQuery要填true
    public List<ColumnBean> relationColumns; //一个ColumnBean是由多个ColumnBean转换而来，比如：一个字段来自于fun(c1,c2)，或者一张表的一个字段，来自于TA表A1字段和TB表B1字段的union
    public CaseWhenColumnBean caseWhenColumnBean;   //当columnType为CASE_WHEN的时候，这个字段非空

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColumnBean that = (ColumnBean) o;

        if(this.columnType.equals(ColumnType.NORMAL)
                ||this.columnType.equals(ColumnType.STRING)
                || this.columnType.equals(ColumnType.NUMERIC)
                || this.columnType.equals(ColumnType.NULL)){
            return Objects.equals(columnName.toUpperCase(), that.columnName.toUpperCase()) &&
                    Objects.equals(belongToTableName.toUpperCase(), that.belongToTableName.toUpperCase()) &&
                    columnType == that.columnType ;
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(columnName, columnAlias, belongToTableName, belongToTableAlias, convertRule, columnType, relationColumns, caseWhenColumnBean);
    }

    public ColumnBean copy(ColumnBean other){
        this.columnName = other.columnName;
        this.columnAlias = other.columnAlias;
        this.belongToTableName = other.belongToTableName;
        this.belongToTableAlias = other.belongToTableAlias;
        this.convertRule = other.convertRule;
        this.relationColumns = other.relationColumns;
        this.columnType = other.columnType;
        this.caseWhenColumnBean = other.caseWhenColumnBean;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ColumnBean{");
        sb.append("columnName='").append(columnName).append('\'');
        sb.append(", columnAlias='").append(columnAlias).append('\'');
        sb.append(", belongToTableName='").append(belongToTableName).append('\'');
        sb.append(", belongToTableAlias='").append(belongToTableAlias).append('\'');
        sb.append(", convertRule='").append(convertRule).append('\'');
        sb.append(", columnType=").append(columnType);
        sb.append(", relationColumns=").append(relationColumns);
        sb.append(", caseWhenColumnBean=").append(caseWhenColumnBean);
        sb.append('}');
        return sb.toString();
    }

    public String toConsanguinityFormat(){
        if(this.columnType.equals(ColumnType.NORMAL)){
            return this.belongToTableName + "." + this.columnName;
        }

        if(this.columnType.equals(ColumnType.STRING)
                || this.columnType.equals(ColumnType.NUMERIC)
                || this.columnType.equals(ColumnType.NULL)){
            return this.columnName;
        }

        if(this.columnType.equals(ColumnType.FUNCTION)){
            String funcParas = "";

            for(int i = 0; i< this.relationColumns.size();i++){
                funcParas += this.relationColumns.get(i).toConsanguinityFormat();
                if(i != this.relationColumns.size() -1 ){
                    funcParas +=" , ";
                }
            }
            return this.convertRule + "( " + funcParas + " )";
        }

        if(this.columnType.equals(ColumnType.MERGE)){
            String funcParas = "";

            for(int i = 0; i< this.relationColumns.size();i++){
                funcParas += this.relationColumns.get(i).toConsanguinityFormat();
                if(i != this.relationColumns.size() -1 ){
                    funcParas +=" , ";
                }
            }
            return this.convertRule + "{" + funcParas + "}";
        }

        if(this.columnType.equals(ColumnType.BINARYOPT)){
            return "(" + this.relationColumns.get(0).toConsanguinityFormat()
                    + " "+ this.convertRule + " "
                    + this.relationColumns.get(1).toConsanguinityFormat() + ")";
        }

        if(this.columnType.equals(ColumnType.UNARYOPT)){
            return " " + this.convertRule
                    + this.relationColumns.get(0).toConsanguinityFormat();
        }

        if(this.columnType.equals(ColumnType.WHEN_THEN)){
            return "WHEN " +  this.relationColumns.get(0).toConsanguinityFormat()
                    + " THEN " + this.relationColumns.get(1).toConsanguinityFormat();
        }

        if(this.columnType.equals(ColumnType.CASE)){

            return caseWhenColumnBean.toConsanguinityFormat();
        }

        if(this.columnType.equals(ColumnType.IN_LIST)){
            String inList = "";
            for(int i = 1; i< this.relationColumns.size();i++){
                inList += this.relationColumns.get(i).toConsanguinityFormat();
                if(i != this.relationColumns.size() -1 ){
                    inList +=" , ";
                }
            }
            return " " + this.relationColumns.get(0).toConsanguinityFormat() + " " + this.convertRule + "(" + inList + ") ";
        }

        if(this.columnType.equals(ColumnType.BETWEEN)){
            return " " + this.relationColumns.get(0).toConsanguinityFormat()
                    + " " + this.convertRule + " "
                    + this.relationColumns.get(1).toConsanguinityFormat()
                    + " AND "
                    + this.relationColumns.get(2).toConsanguinityFormat();
        }

        if(this.columnType.equals(ColumnType.LIKE)){
            return " " + this.relationColumns.get(0).toConsanguinityFormat()
                    + " " + this.convertRule + " "
                    + this.relationColumns.get(1).toConsanguinityFormat();
        }

        if(this.columnType.equals(ColumnType.IS_NULL)){
            return " " + this.relationColumns.get(0).toConsanguinityFormat()
                    + " " + this.convertRule;
        }

        return null;
    }
}
