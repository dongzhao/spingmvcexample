package com.dzhao.springmvc.model;

import com.dzhao.springmvc.codegen.annotation.GenerateMethod;
import com.dzhao.springmvc.codegen.annotation.GenerateRepo;
import com.dzhao.springmvc.codegen.annotation.GenerateRepository;
import com.dzhao.springmvc.codegen.annotation.GenerateRestController;
import com.dzhao.springmvc.model.generic.AbstractDomain;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;


@Entity
@Table(name = "my_product")
@GenerateRepository(
        modelPackageName = "com.dzhao.springmvc.model.*",
        method = {
                @GenerateMethod(name = "name", type = "String"),
                @GenerateMethod(name = "price", type = "BigDecimal")
        }
)
@GenerateRestController(
        modelPackageName = "com.dzhao.springmvc.model.*",
        method = {
                @GenerateMethod(name = "name", type = "String"),
                @GenerateMethod(name = "price", type = "BigDecimal")
        }
)
@GenerateRepo
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Product extends AbstractDomain {
    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
