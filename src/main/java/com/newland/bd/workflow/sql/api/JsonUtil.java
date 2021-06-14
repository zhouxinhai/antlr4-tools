package com.newland.bd.workflow.sql.api;

import com.alibaba.fastjson.JSON;
import com.newland.bd.workflow.sql.bean.ErrLexerMsg;
import com.newland.bd.workflow.sql.bean.SqlHandleResult;

/**
 * Create by Howard on 2018/9/30
 */
public class JsonUtil {
    public static String removeIllegalCharacterForJson(String json) {

        return json.replace("\r", "\\r").
                replace("\n", "\\n").
                replace("\"", "\\\"");

//        return json.replace("\"", "\\\"");
    }

    public static String responseJson(boolean isSuccess, String output, ErrLexerMsg errOutput){

        SqlHandleResult sqlHandleResult = new SqlHandleResult();
        sqlHandleResult.setSuccess(isSuccess);
        sqlHandleResult.setOutput(output);
        if(errOutput != null){
            sqlHandleResult.setErrLexerMsg(errOutput);
        }
        return JSON.toJSON(sqlHandleResult).toString();

    }

    public static SqlHandleResult convertToSqlHandleResult(String json){
        return JSON.parseObject(json, SqlHandleResult.class);
    }


}
