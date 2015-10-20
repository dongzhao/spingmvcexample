package com.dzhao.springmvc.codegen.domain;

/**
 * Created by dzhao on 20/10/2015.
 */
public class SearchCriteria {
    private String key;
    private Object value;
    private String operator;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
