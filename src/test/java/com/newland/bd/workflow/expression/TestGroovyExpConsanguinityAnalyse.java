package com.newland.bd.workflow.expression;

import com.newland.bd.workflow.expression.api.GroovyExpConsanguinityAnalyse;
import com.newland.bd.workflow.expression.bean.ExpDetialBean;
import com.newland.bd.workflow.sql.TestConsanguinityAnalyseCommonMethod;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Create by zhouxinhai on 2020/2/3
 */
public class TestGroovyExpConsanguinityAnalyse {

    private static void assertColumnName(List<String> columnNameList, String... expectColumnName) {
        Assert.assertEquals(columnNameList.size(), expectColumnName.length);
        for (int i = 0; i < columnNameList.size(); i++) {
            Assert.assertEquals(expectColumnName[i].toUpperCase(), columnNameList.get(i).toUpperCase());
        }
    }

    @Test
    void test1() {
        String exp = "$$[a]+1+func(t.b,2)";
        Pair<String, ExpDetialBean> StringBeanPair = GroovyExpConsanguinityAnalyse.consanguinityAnalyse(exp);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(StringBeanPair.getLeft());
        ExpDetialBean expDetialBean = StringBeanPair.getRight();
        assertColumnName(expDetialBean.getSrcColumns(), "a");
        Assert.assertEquals(expDetialBean.getConvertRule(), "a+1+func(t.b,2)");
    }

    @Test
    void test2() {
        String exp = "$$[a]+1+func($$[t.b],2)";
        Pair<String, ExpDetialBean> StringBeanPair = GroovyExpConsanguinityAnalyse.consanguinityAnalyse(exp);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(StringBeanPair.getLeft());
        ExpDetialBean expDetialBean = StringBeanPair.getRight();
        assertColumnName(expDetialBean.getSrcColumns(), "a", "t.b");
        Assert.assertEquals(expDetialBean.getConvertRule(), "a+1+func(t.b,2)");
    }

    @Test
    void test4() {
        String exp = "$$[a]+1+func($$[t.b],2) + '$$[c]'";
        Pair<String, ExpDetialBean> StringBeanPair = GroovyExpConsanguinityAnalyse.consanguinityAnalyse(exp);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(StringBeanPair.getLeft());
        ExpDetialBean expDetialBean = StringBeanPair.getRight();
        assertColumnName(expDetialBean.getSrcColumns(), "a", "t.b");
        Assert.assertEquals(expDetialBean.getConvertRule(), "a+1+func(t.b,2) + '$$[c]'");
    }

    @Test
    void test5() {
        String exp = "$$[a]+1+func($$[t.b],2) + \"$$[c]\"";
        Pair<String, ExpDetialBean> StringBeanPair = GroovyExpConsanguinityAnalyse.consanguinityAnalyse(exp);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(StringBeanPair.getLeft());
        ExpDetialBean expDetialBean = StringBeanPair.getRight();
        assertColumnName(expDetialBean.getSrcColumns(), "a", "t.b");
        Assert.assertEquals(expDetialBean.getConvertRule(), "a+1+func(t.b,2) + \"$$[c]\"");
    }

    @Test
    void test6() {
        String exp = "$$[a]+func1(func2($$[t.b],2))";
        Pair<String, ExpDetialBean> StringBeanPair = GroovyExpConsanguinityAnalyse.consanguinityAnalyse(exp);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(StringBeanPair.getLeft());
        ExpDetialBean expDetialBean = StringBeanPair.getRight();
        assertColumnName(expDetialBean.getSrcColumns(), "a", "t.b");
        Assert.assertEquals(expDetialBean.getConvertRule(), "a+func1(func2(t.b,2))");
    }

    @Test
    void test7() {
        String exp = "string.split($$[a], ',')[2]";
        Pair<String, ExpDetialBean> StringBeanPair = GroovyExpConsanguinityAnalyse.consanguinityAnalyse(exp);
        TestConsanguinityAnalyseCommonMethod.jsonResAssertSuccess(StringBeanPair.getLeft());
        ExpDetialBean expDetialBean = StringBeanPair.getRight();
        assertColumnName(expDetialBean.getSrcColumns(), "a");
        Assert.assertEquals(expDetialBean.getConvertRule(), "string.split(a, ',')[2]");
    }
}
