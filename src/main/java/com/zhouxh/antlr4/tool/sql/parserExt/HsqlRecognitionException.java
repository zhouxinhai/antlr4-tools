package com.zhouxh.antlr4.tool.sql.parserExt;

import com.zhouxh.antlr4.tool.sql.bean.ErrLexerMsg;
import org.antlr.v4.runtime.Token;

/**
 * Create by Howard on 2018/10/2
 */
public class HsqlRecognitionException extends RuntimeException {
    private ErrLexerMsg errLexerMsg;

    public HsqlRecognitionException(String errMsg){
        super(errMsg);
    }

    public HsqlRecognitionException(Token token, String errMsg){
        super(errMsg);

        StringBuilder sb = new StringBuilder();
        sb.append("第").append(token.getLine()).append("行，第").append(token.getCharPositionInLine()+1).append("列出现语法错误：").append(errMsg);

        errLexerMsg = new ErrLexerMsg();
        errLexerMsg.setCompleteErrMsg(sb.toString());
        errLexerMsg.setSimpleErrMsg(sb.toString());

        //错误语法所在的行号，行号从0开始计数
        errLexerMsg.setErrLine(token.getLine()-1);
        //错误语法所在token的起始列号，列号从0开始计数
        errLexerMsg.setErrStartColumn(token.getCharPositionInLine());
        //错误语法所在token的结束列号，列号从0开始计数
        errLexerMsg.setErrStopColumn(  ((Token)token).getStopIndex()-((Token)token).getStartIndex()   +   token.getCharPositionInLine()   );
    }

    public ErrLexerMsg getErrLexerMsg() {
        return errLexerMsg;
    }
}

