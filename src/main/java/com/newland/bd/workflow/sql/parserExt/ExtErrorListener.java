package com.newland.bd.workflow.sql.parserExt;

import com.newland.bd.workflow.sql.bean.ErrLexerMsg;
import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自定义解析错误的监听器
 * Create by Howard on 2018/9/28
 */
public class ExtErrorListener extends BaseErrorListener {

    private List<ErrLexerMsg> errLexerMsgList;



    public ErrLexerMsg getErrLexerMsg() {
        if(errLexerMsgList == null ){
            return null;
        }
        return errLexerMsgList.get(0);
    }

    public void reset(){
        errLexerMsgList = null;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {


        ErrLexerMsg errLexerMsg = new ErrLexerMsg();

        List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
        Collections.reverse(stack);

        /*sb.append("rule stack: ").append(stack).append("\n");
        sb.append("line ").append(line).append(":").append(charPositionInLine).append(" at ").append(offendingSymbol)
                .append(msg).append("\n");*/

        //简单错误信息
        StringBuilder sb = new StringBuilder();
        sb.append("第").append(line).append("行，第").append(charPositionInLine+1).append("列出现语法错误");
        errLexerMsg.setSimpleErrMsg(sb.toString());

        //详细错误信息
        sb.append("，详细错误信息：").append(msg);
        errLexerMsg.setCompleteErrMsg(sb.toString());

        //错误语法所在的行号，行号从0开始计数
        errLexerMsg.setErrLine(line-1);
        //错误语法所在token的起始列号，列号从0开始计数
        errLexerMsg.setErrStartColumn(charPositionInLine);
        //错误语法所在token的结束列号，列号从0开始计数
        errLexerMsg.setErrStopColumn(  ((Token)offendingSymbol).getStopIndex()-((Token)offendingSymbol).getStartIndex()   +   charPositionInLine   );


        if(errLexerMsgList == null){
            errLexerMsgList = new ArrayList<ErrLexerMsg>();
        }
        errLexerMsgList.add(errLexerMsg);
    }
}
