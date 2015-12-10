package com.recommendation.Test;

import com.recommendation.RelationHolder.DateRelationHolder;
import com.recommendation.RelationHolder.StringRelationHolder;
import com.recommendation.Util.DateUtil;

import java.util.Date;

/**
 * User: likang
 * Date: 15/10/22
 * Time: 上午12:18
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.test();
    }

    public void test() {
        TestData testData = new TestData();
        testData.setName("test");
        testData.setTime(new Date());
        String value = "e";

        System.out.println(StringRelationHolder.hasString(testData, value));

        //寻找此刻前后1分钟以内的所有对象属性的值，只要符合一个就返回true
        System.out.println(DateRelationHolder.around(testData, new Date(), DateUtil.getMinutes(1l)));
    }
}
