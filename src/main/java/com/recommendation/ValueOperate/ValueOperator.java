package com.recommendation.ValueOperate;

/**
 * User: likang
 * Date: 15/10/21
 * Time: 下午11:40
 */
public interface ValueOperator {
    void setValue(String type, Class targetClass, String getMethodName, Object source, Object result);
}
