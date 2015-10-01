package com.dzhao.springmvc.model;

import com.dzhao.springmvc.codegen.annotation.GenerateRepoMethod;
import com.dzhao.springmvc.codegen.annotation.GenerateRepository;
import com.dzhao.springmvc.codegen.annotation.GenerateRestController;
import com.dzhao.springmvc.codegen.annotation.GenerateMethod;
import com.dzhao.springmvc.model.converter.BooleanToStringConverter;
import com.dzhao.springmvc.model.generic.AbstractDomain;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "my_customer")
@GenerateRepository(
        modelPackageName = "com.dzhao.springmvc.model.*",
        method = {
                @GenerateMethod(name = "firstName", type = "String"),
                @GenerateMethod(name = "lastName", type = "String"),
                @GenerateMethod(name = "gender", type = "Boolean")
        }
)
@GenerateRestController(
        modelPackageName = "com.dzhao.springmvc.model.*",
        method = {
                @GenerateMethod(name = "firstName", type = "String"),
                @GenerateMethod(name = "lastName", type = "String"),
                @GenerateMethod(name = "gender", type = "Boolean")
        }
)
public class Customer extends AbstractDomain {
    @Column(name="FIRST_NAME")
    private String firstName;
    @GenerateRepoMethod
    @Column(name="LAST_NAME")
    private String lastName;
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean gender;

    @ManyToMany
    @JoinTable(name="my_customer_order",
            joinColumns=
            @JoinColumn(name="CUST_ID", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="ORDER_ID", referencedColumnName="ID")
    )
    private List<Order> orders;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
