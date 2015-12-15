package com.courseExercise.carpooling.recommendation.ValueOperate;

import com.courseExercise.carpooling.recommendation.constant.ClassEnum;

import java.lang.reflect.Method;
import java.util.List;

/**
 * User: likang
 * Date: 15/10/21
 * Time: 下午11:45
 */
public class StringValueOperator implements ValueOperator {
    @Override
    public void setValue(String type, Class targetClass, String getMethodName, Object source, Object result) {
        try {
            if (type.equals(ClassEnum.STRING.getTargetClass())) {
                Method method = targetClass.getMethod(getMethodName);
                String value = (String) method.invoke(source);//调用getter方法获取属性值
                if (value != null) {
                    ((List) result).add(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
