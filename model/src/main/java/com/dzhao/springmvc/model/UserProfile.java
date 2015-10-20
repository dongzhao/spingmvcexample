package com.dzhao.springmvc.model;

import com.dzhao.springmvc.codegen.annotation.GenerateMethod;
import com.dzhao.springmvc.codegen.annotation.GenerateRepo;
import com.dzhao.springmvc.codegen.annotation.GenerateRepository;
import com.dzhao.springmvc.codegen.annotation.GenerateRestController;
import com.dzhao.springmvc.model.converter.BooleanToStringConverter;
import com.dzhao.springmvc.model.generic.AbstractDomain;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dzhao on 2/09/2015.
 */
@Entity
@Table(name = "my_user_profile")
@GenerateRepository(
        modelPackageName = "com.dzhao.springmvc.model.*",
        method = {
                @GenerateMethod(name = "user", type = "User"),
                @GenerateMethod(name = "address", type = "String"),
                @GenerateMethod(name = "dateOfBirth", type = "Date"),
                @GenerateMethod(name = "gender", type = "boolean")
        }
)
@GenerateRestController(
        modelPackageName = "com.dzhao.springmvc.model.*",
        method = {
                @GenerateMethod(name = "user", type = "User"),
                @GenerateMethod(name = "address", type = "String"),
                @GenerateMethod(name = "dateOfBirth", type = "Date"),
                @GenerateMethod(name = "gender", type = "boolean")
        }
)
@GenerateRepo
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class UserProfile extends AbstractDomain {

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @Column(name = "living_address")
    private String address;
    @Column(name = "date_of_birth")
    //@JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    @Column
    @Convert(converter = BooleanToStringConverter.class)
    private boolean gender;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
