package com.dzhao.springmvc.model;

import com.dzhao.springmvc.codegen.annotation.GenerateMethod;
import com.dzhao.springmvc.codegen.annotation.GenerateRepo;
import com.dzhao.springmvc.codegen.annotation.GenerateRepository;
import com.dzhao.springmvc.codegen.annotation.GenerateRestController;
import com.dzhao.springmvc.codegen.enums.OperatorEnum;
import com.dzhao.springmvc.model.generic.AbstractDomain;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "my_order")
@GenerateRepository(
        modelPackageName = "com.dzhao.springmvc.model.*"
/*        method = {
                @GenerateMethod(name = "orderDate", type = "Date", opertor = OperatorEnum.AFTER)
        }*/
)
@GenerateRestController(
        modelPackageName = "com.dzhao.springmvc.model.*"
/*        method = {
                @GenerateMethod(name = "orderDate", type = "Date", opertor = OperatorEnum.AFTER)
        }*/
)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Order extends AbstractDomain {
    @Column(name="ORDER_DATE")
    private Date orderDate;

    @ManyToMany(mappedBy="orders")
    private List<Customer> customers;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
