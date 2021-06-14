package com.zhouxh.antlr4.tool.sql.bean;

/**
 * Create by Howard on 2018/10/10
 */
public class SqlHandleResult {
    private Boolean success;
    private String output;
    private ErrLexerMsg errLexerMsg = new ErrLexerMsg();

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public ErrLexerMsg getErrLexerMsg() {
        return errLexerMsg;
    }

    public void setErrLexerMsg(ErrLexerMsg errLexerMsg) {
        this.errLexerMsg = errLexerMsg;
    }

}
