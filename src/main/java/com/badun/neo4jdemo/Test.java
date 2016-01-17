package com.badun.neo4jdemo;

/**
 * Created by Artsiom Badun.
 */
public class Test {
    private String stringVal;
    private Long longVal;

    public Test(String stringVal, Long longVal) {
        this.stringVal = stringVal;
        this.longVal = longVal;
    }

    public String getStringVal() {
        return stringVal;
    }

    public Long getLongVal() {
        return longVal;
    }
}
