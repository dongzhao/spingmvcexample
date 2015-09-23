package com.dzhao.springmvc.model;

import com.dzhao.springmvc.codegen.annotation.GenerateRepository;
import com.dzhao.springmvc.codegen.annotation.GenerateRestController;
import com.dzhao.springmvc.model.generic.AbstractDomain;

import javax.persistence.*;

@Entity
@Table(name = "my_order_details")
@GenerateRepository(
        modelPackageName = "com.dzhao.springmvc.model.*")
@GenerateRestController(
        modelPackageName = "com.dzhao.springmvc.model.*",
        urlPath = "/rest/api/"
)
public class OrderDetails extends AbstractDomain {
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PRODUCT_ID", nullable=false, updatable=false)
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

   public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
