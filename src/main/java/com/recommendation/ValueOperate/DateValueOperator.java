package com.recommendation.ValueOperate;

import com.recommendation.constant.ClassEnum;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * User: likang
 * Date: 15/10/21
 * Time: 下午11:49
 */
public class DateValueOperator implements ValueOperator {
    @Override
    public void setValue(String type, Class targetClass, String getMethodName, Object source, Object result) {
        try {
            if (type.equals(ClassEnum.DATE.getTargetClass())) {
                Method method = targetClass.getMethod(getMethodName);
                Date value = (Date) method.invoke(source);//调用getter方法获取属性值
                if (value != null) {
                    ((List) result).add(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
