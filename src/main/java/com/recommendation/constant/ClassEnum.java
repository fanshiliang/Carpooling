package com.recommendation.constant;

/**
 * User: likang
 * Date: 15/10/21
 * Time: 下午10:39
 */
public enum ClassEnum {
    STRING(0, "class java.lang.String"),
    DATE(2, "class java.util.Date");
    private Integer id;
    private String targetClass;

    ClassEnum(Integer id, String targetClass) {
        this.id = id;
        this.targetClass = targetClass;
    }

    public Integer getId() {
        return id;
    }

    public String getTargetClass() {
        return targetClass;
    }
}
