package com.dzhao.springmvc.codegen.template;

/**
 * Created by dzhao on 26/11/2015.
 */
public class FieldTestValueTemplate extends FieldInfoTemplate{

    private String testValueIn;
    private String testValueOut;

    public String getTestValueIn() {
        return testValueIn;
    }

    public void setTestValueIn(String testValueIn) {
        this.testValueIn = testValueIn;
    }

    public String getTestValueOut() {
        return testValueOut;
    }

    public void setTestValueOut(String testValueOut) {
        this.testValueOut = testValueOut;
    }
}
