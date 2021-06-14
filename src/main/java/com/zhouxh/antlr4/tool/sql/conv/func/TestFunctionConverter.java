package com.zhouxh.antlr4.tool.sql.conv.func;

import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

/**
 * Create by Howard on 2018/9/30
 */
public class TestFunctionConverter extends FunctionCallHiveConverterBase {
    /**
     * 修改函数名字,把函数名中的改成FUNCTION,并把他的两个输入参数进行调换
     * @param functionCtx   原始的函节点
     * @param tokenStreamRewriter  token重写器
     */
    @Override
    public void functionConvert(EntireSqlParser.FunctionCallContext functionCtx, TokenStreamRewriter tokenStreamRewriter) {
        List<ParseTree> children = functionCtx.children;
        ParserRuleContext para1 = null;
        ParserRuleContext para2 = null;
        for (ParseTree child :
                children) {
            //System.out.println(child.getText()+" "+child.getClass());
            child.getText();
            if (child instanceof  EntireSqlParser.ExpressionContext){
                if(para1 == null){
                    para1 = (ParserRuleContext)child;
                }else if (para2 == null){
                    para2 = (ParserRuleContext)child;
                }
            }
        }
        if(functionCtx.qualifiedName()!= null){
            tokenStreamRewriter.replace(functionCtx.getStart(),"FUNCTION");
        }

        String str2 = getText(para2);
        String str1 = getText(para1);

        if(para1!= null && para2 != null){
            tokenStreamRewriter.replace(para1.getStart(),para1.getStop(),getText(para2));
            tokenStreamRewriter.replace(para2.getStart(),para2.getStop(),getText(para1));
        }

//        System.out.println("重写后的内容是："+
//                tokenStreamRewriter.getText(Interval.of(functionCtx.getStart().getTokenIndex(), functionCtx.getStop().getTokenIndex())));

        setConvertedProperty(functionCtx,tokenStreamRewriter);
    }
}
