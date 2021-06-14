package com.zhouxh.antlr4.tool.expression.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhouxinhai on 2020/2/3
 */
@Data
public class ExpDetialBean {
    private List<String> srcColumns = new ArrayList<>();
    private String convertRule;
}
