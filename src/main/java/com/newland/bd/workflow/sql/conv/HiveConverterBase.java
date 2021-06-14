package com.newland.bd.workflow.sql.conv;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import com.newland.bd.workflow.sql.parserExt.ParseTreePropertyExt;

/**
 * Create by Howard on 2018/9/30
 */
public abstract class HiveConverterBase {

    //存放节点和转换后字符串的对应关系
    private static ParseTreePropertyExt<String> convertedProperty = new ParseTreePropertyExt<>();

    /**
     * 清除convertedFunctionProperty缓冲区
     */
    public static  synchronized void clear(){
        convertedProperty.clear();
    }

    protected void setConvertedProperty(ParserRuleContext ctx, TokenStreamRewriter tokenStreamRewriter){
        convertedProperty.put(ctx,tokenStreamRewriter.getText(Interval.of(ctx.getStart().getTokenIndex(), ctx.getStop().getTokenIndex())));
    }


    protected String getText(ParserRuleContext ctx){
        if(ctx.getChildCount() == 0){
            return "";
        }else {
            StringBuilder builder = new StringBuilder();

            String s = convertedProperty.get(ctx);
            if(s != null) {//ctx已经经过转换，用转换后的内容替换原始的内容
                builder.append(s);
            }else{
                for(int i = 0; i < ctx.getChildCount(); ++i){
                    if(ctx.getChild(i) instanceof TerminalNodeImpl){//是叶子节点
                        builder.append(ctx.getChild(i).getText());
                    }else{//不是叶子节点
                        builder.append(getText( (ParserRuleContext)ctx.getChild(i) ));
                    }
                }
            }

            return builder.toString();
        }
    }
}
