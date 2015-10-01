package com.dzhao.springmvc.model;

import com.dzhao.springmvc.codegen.annotation.GenerateMethod;
import com.dzhao.springmvc.codegen.annotation.GenerateRepo;
import com.dzhao.springmvc.codegen.annotation.GenerateRepository;
import com.dzhao.springmvc.codegen.annotation.GenerateRestController;
import com.dzhao.springmvc.model.generic.AbstractDomain;

import javax.persistence.*;

@Entity
@Table(name = "my_order_details")
@GenerateRepository(
        modelPackageName = "com.dzhao.springmvc.model.*",
        method = {
                @GenerateMethod(name = "order", type = "Order"),
                @GenerateMethod(name = "product", type = "Product")
        }
)
@GenerateRestController(
        modelPackageName = "com.dzhao.springmvc.model.*"
)
@GenerateRepo
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
