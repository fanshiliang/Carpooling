package com.courseExercise.carpooling.recommendation.Util;

import com.courseExercise.carpooling.recommendation.ValueOperate.ValueOperator;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * User: likang
 * Date: 15/10/21
 * Time: 下午10:08
 */
public abstract class ValueUtil {

    public static void setValue(Object source, Object result, ValueOperator valueOperator) {
        Class targetClass = source.getClass();
        //获取类的属性
        Field[] fields = targetClass.getDeclaredFields();
        //遍历属性
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            String getMethodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);

            String type = fields[i].getGenericType().toString();
            valueOperator.setValue(type, targetClass, getMethodName, source, result);
        }
    }
}
