package com.newland.bd.workflow.sql.conv.type;

import com.newland.bd.workflow.sql.parser.EntireSqlParser;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * Create by Howard on 2018/9/30
 */
public class HiveTypeConverter extends TypeHiveConverterBase {
    @Override
    public void typeConvert(EntireSqlParser.TypeContext typeContext, TokenStreamRewriter tokenStreamRewriter) {
        if (typeContext.baseType() != null) {

            String dstBaseType = baseTypeConvert(typeContext.getText());
            if (dstBaseType != null) {
                tokenStreamRewriter.replace(typeContext.getStart(), typeContext.getStop(), dstBaseType);
            }

            setConvertedProperty(typeContext, tokenStreamRewriter);
        }
    }
}
