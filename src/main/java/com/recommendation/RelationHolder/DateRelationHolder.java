package com.recommendation.RelationHolder;

import com.recommendation.Util.ValueUtil;
import com.recommendation.ValueOperate.DateValueOperator;
import com.recommendation.ValueOperate.ValueOperator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * User: likang
 * Date: 15/10/22
 * Time: 上午12:07
 */
public class DateRelationHolder {

    protected static ValueOperator valueOperator;

    static {
        valueOperator = new DateValueOperator();
    }

    public static boolean hasDate(Object object, Date date) {

        List<Date> result = new ArrayList<>();
        ValueUtil.setValue(object, result, valueOperator);

        for (Date str : result) {
            if (str.equals(date)) {
                return true;
            }
        }

        return false;
    }

    public static boolean before(Object object, Date date, Date distance) {
        List<Date> result = new ArrayList<>();
        ValueUtil.setValue(object, result, valueOperator);

        Date target = new Date(date.getTime() - distance.getTime());
        for (Date item : result) {
            if (item.after(target)) {
                return true;
            }
        }

        return false;
    }

    public static boolean after(Object object, Date date, Date distance) {
        List<Date> result = new ArrayList<>();
        ValueUtil.setValue(object, result, valueOperator);

        Date target = new Date(date.getTime() + distance.getTime());
        for (Date item : result) {
            if (item.before(target)) {
                return true;
            }
        }

        return false;
    }

    public static boolean around(Object object, Date date, Date distance) {
        List<Date> result = new ArrayList<>();
        ValueUtil.setValue(object, result, valueOperator);

        Date before = new Date(date.getTime() - distance.getTime());
        Date after = new Date(date.getTime() + distance.getTime());
        for (Date item : result) {
            if (item.after(before) && item.before(after)) {
                return true;
            }
        }

        return false;
    }
}
