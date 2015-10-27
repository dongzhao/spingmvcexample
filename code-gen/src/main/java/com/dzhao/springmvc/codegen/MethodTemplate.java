package com.dzhao.springmvc.codegen;

import com.dzhao.springmvc.codegen.enums.OperatorEnum;

/**
 * Created by dzhao on 1/10/2015.
 */
public class MethodTemplate {

    private String type;
    private String name;
    private String method;
    private String operator;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
