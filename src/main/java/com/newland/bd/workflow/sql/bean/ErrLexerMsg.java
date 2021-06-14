package com.newland.bd.workflow.sql.bean;

/**
 * Create by Howard on 2018/10/10
 */
public class ErrLexerMsg {

    private String simpleErrMsg = "";   //简化后的错误信息
    private String completeErrMsg = "";   //完整的错误信息
    private Integer  errLine = -1; //错误语法所在的行号，行号从0开始计数
    private Integer  errStartColumn = -1; //错误语法所在token的起始列号，列号从0开始计数
    private Integer  errStopColumn = -1; //错误语法所在token的结束列号，列号从0开始计数


    public String getSimpleErrMsg() {
        return simpleErrMsg;
    }

    public void setSimpleErrMsg(String simpleErrMsg) {
        this.simpleErrMsg = simpleErrMsg;
    }

    public String getCompleteErrMsg() {
        return completeErrMsg;
    }

    public void setCompleteErrMsg(String completeErrMsg) {
        this.completeErrMsg = completeErrMsg;
    }

    public Integer getErrLine() {
        return errLine;
    }

    public void setErrLine(Integer errLine) {
        this.errLine = errLine;
    }

    public Integer getErrStartColumn() {
        return errStartColumn;
    }

    public void setErrStartColumn(Integer errStartColumn) {
        this.errStartColumn = errStartColumn;
    }

    public Integer getErrStopColumn() {
        return errStopColumn;
    }

    public void setErrStopColumn(Integer errStopColumn) {
        this.errStopColumn = errStopColumn;
    }
}
