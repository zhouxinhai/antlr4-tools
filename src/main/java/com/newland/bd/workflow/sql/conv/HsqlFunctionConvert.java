package com.newland.bd.workflow.sql.conv;

import com.newland.bd.workflow.sql.parser.EntireSqlParser;
import org.antlr.v4.runtime.TokenStreamRewriter;
import com.newland.bd.workflow.sql.conv.func.FunctionCallHiveConverterBase;
import com.newland.bd.workflow.sql.conv.func.TestFunctionConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Create by Howard on 2018/9/30
 */
public class HsqlFunctionConvert {

    //函数名和转换器的对应关系
    private  static Map<String, FunctionCallHiveConverterBase> fucntionNameAndConverter = new HashMap<String, FunctionCallHiveConverterBase>();
    static{
        fucntionNameAndConverter.put("FUN_TEST\\(.*\\)",new TestFunctionConverter());
        fucntionNameAndConverter.put("fun_test\\(.*\\)",new TestFunctionConverter());
    }


    /**
     *
     * @param functionCtx   原始的函节点
     * @param tokenStreamRewriter  token重写器
     */
    public static void functionConvert(EntireSqlParser.FunctionCallContext functionCtx, TokenStreamRewriter tokenStreamRewriter){

        Set<Map.Entry<String, FunctionCallHiveConverterBase>> entries = fucntionNameAndConverter.entrySet();
        for(Map.Entry<String, FunctionCallHiveConverterBase> entry:entries){
            boolean matches = functionCtx.getText().matches(entry.getKey());
            if(matches){
                entry.getValue().functionConvert(functionCtx,tokenStreamRewriter);
                break;
            }
        }

    }
}
