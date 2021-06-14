package com.newland.bd.workflow.sql.bean;

/**
 * Create by Howard on 2018/10/12
 */
public class ColumnAndAliasName {
    private String columnName = "";   //字段名
    private String aliasName  =   "";  //字段名对应的别名
    private boolean isFillingAlias = false;  //是否为字段名，强制加了一个别名
    private String chineseAnnotation = "";    //字段中文的注解，必须是双引号包起来的

    public ColumnAndAliasName(String columnName){
        this.columnName = columnName;
        this.aliasName = columnName;
        this.isFillingAlias = false;
    }

    public String getChineseAnnotation() {
        return chineseAnnotation;
    }

    public void setChineseAnnotation(String chineseAnnotation) {
        this.chineseAnnotation = chineseAnnotation;
    }

    public String getColumnName() {
        return columnName;
    }

    private void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public boolean isFillingAlias() {
        return isFillingAlias;
    }

    public void setFillingAlias(boolean fillingAlias) {
        isFillingAlias = fillingAlias;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ColumnAndAliasName{");
        sb.append("columnName='").append(columnName).append('\'');
        sb.append(", aliasName='").append(aliasName).append('\'');
        sb.append(", isFillingAlias=").append(isFillingAlias);
        sb.append(", chineseAnnotation='").append(chineseAnnotation).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
