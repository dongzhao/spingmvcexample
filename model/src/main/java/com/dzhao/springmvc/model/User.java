package com.dzhao.springmvc.model;

import com.dzhao.springmvc.codegen.annotation.GenerateMethod;
import com.dzhao.springmvc.codegen.annotation.GenerateRepo;
import com.dzhao.springmvc.codegen.annotation.GenerateRepository;
import com.dzhao.springmvc.codegen.annotation.GenerateRestController;
import com.dzhao.springmvc.model.generic.AbstractDomain;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "my_user")
@GenerateRepository(
        modelPackageName = "com.dzhao.springmvc.model.*"
/*        method = {
                @GenerateMethod(name = "userName", type = "String"),
                @GenerateMethod(name = "email", type = "String")
        }*/
)
@GenerateRestController(
        modelPackageName = "com.dzhao.springmvc.model.*"
/*        method = {
                @GenerateMethod(name = "userName", type = "String"),
                @GenerateMethod(name = "email", type = "String")
        }*/
)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User extends AbstractDomain {

    @Column(name="USER_NAME", nullable = false)
    private String userName;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="EMAIL")
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
