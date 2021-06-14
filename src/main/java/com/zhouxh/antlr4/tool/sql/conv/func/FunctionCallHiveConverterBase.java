package com.zhouxh.antlr4.tool.sql.conv.func;

import com.zhouxh.antlr4.tool.sql.conv.HiveConverterBase;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * Create by Howard on 2018/9/30
 */
abstract public  class FunctionCallHiveConverterBase extends HiveConverterBase {

    /**
     * 对原始的函数进行转换
     * @param functionCtx   原始的函节点
     * @param tokenStreamRewriter  token重写器
     */
    abstract public void functionConvert(EntireSqlParser.FunctionCallContext functionCtx, TokenStreamRewriter tokenStreamRewriter);
}
