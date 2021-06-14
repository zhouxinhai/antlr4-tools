package com.newland.bd.workflow.sql.bean;

/**
 * Create by Howard on 2018/10/16
 */
public enum  AggregateFunctionName {
    AVG,
    COUNT,
    MAX,
    MIN,
    SUM,
    COUNT_BIG,
    GROUPING,
    BINARY_CHECKSUM,
    CHECKSUM_AGG,
    CHECKSUM,
    STDEV,
    STDEVP,
    VAR,
    VARP,
    avg,
    count,
    max,
    min,
    sum,
    count_big,
    grouping,
    binary_checksum,
    checksum_agg,
    checksum,
    stdev,
    stdevp,
    var,
    varp;

    public static boolean isAggregateFunctionName(String functionName){
        for(AggregateFunctionName value :AggregateFunctionName.values()){
            if(value.toString().equals(functionName)){
                return true;
            }
        }
        return false;
    }
}
