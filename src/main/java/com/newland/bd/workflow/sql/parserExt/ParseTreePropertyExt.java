package com.newland.bd.workflow.sql.parserExt;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.Collection;


/**
 * Create by Howard on 2018/9/30
 */
public class ParseTreePropertyExt<V> extends ParseTreeProperty<V> {

    public void clear(){
        annotations.clear();
    }
    public Collection<V> getValues(){
        return annotations.values();
    }


}
