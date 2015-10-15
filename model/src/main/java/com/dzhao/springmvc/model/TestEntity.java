package com.dzhao.springmvc.model;

import com.dzhao.springmvc.codegen.annotation.MyClass;
import com.dzhao.springmvc.codegen.annotation.MyField;

import java.util.Date;

/**
 * Created by dzhao on 15/10/2015.
 */
@MyClass(name = "_testentity")
public class TestEntity {
    @MyField(name = "_id")
    private String id;
    @MyField(name = "_name")
    private String name;
    @MyField(name = "_birthday")
    private Date birthDay;

    private Boolean gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
