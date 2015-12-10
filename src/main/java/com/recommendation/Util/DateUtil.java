package com.recommendation.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: likang
 * Date: 15/10/22
 * Time: 上午9:20
 */
public class DateUtil {
    private static SimpleDateFormat yMdHmDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String date2String(Date date){
        return yMdHmDateFormat.format(date);
    }

    public static Date getDays(Long days){
        return new Date(getHours(24l).getTime() * days);
    }

    public static Date getHours(Long hours){
        return new Date(getMinutes(60l).getTime() * hours);
    }

    public static Date getMinutes(Long minutes){
        return new Date(getSeconds(60l).getTime() * minutes);
    }

    public static Date getSeconds(Long seconds){
        return new Date(getMiilis(1000l).getTime() * seconds);
    }

    public static Date getMiilis(Long miliis){
        return new Date(miliis);
    }
}
