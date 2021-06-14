package com.zhouxh.antlr4.tool.sql.bean.consanguinity;

/**
 * Create by Howard on 2018/10/20
 */
public enum ColumnType {
    NORMAL,         //普通的字段
    NULL,           //null字段
    STRING,         //字符串类型的字段
    NUMERIC,        //数字类型的字段
    SUB_QUERY,      //子查询类型的字段
    FUNCTION,       //函数类型的字段
    MERGE,          //union,minus等类型的字段
    BINARYOPT,      //双目运算符，比如+、-、*、/、%、||等
    UNARYOPT,       //单目运算符，比如，正负号
    CASE,           //case ... else ....end类型的字段
    WHEN_THEN,      //when.... then...类型的字段
    IN_LIST,        //xxx in (xxx,xxx,xxx)或not in (xxx,xxx,xxx)类型的字段
    BETWEEN,        //xxx between xxx and xxx类型的字段
    LIKE,           //xxx like (xxx)类型的字段
    IS_NULL         //xxx is null类型的字段
}
