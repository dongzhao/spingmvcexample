package com.dzhao.springmvc.repositories.specification;

import com.dzhao.springmvc.codegen.domain.SearchCriteria;
import com.dzhao.springmvc.model.*;
import com.dzhao.springmvc.model.Order;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dzhao on 20/10/2015.
 */
public class CustomerSpecification implements Specification<Customer>{

    private final List<SearchCriteria> criterias;

    public CustomerSpecification(List<SearchCriteria> criterias){
        this.criterias = criterias;
    }

    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        criteriaQuery.distinct(true);
        List<Predicate> filters = new ArrayList<Predicate>();
        Join<Customer, Order> customerOrderJoin = root.join("orders", JoinType.LEFT);
/*        for (SearchCriteria criteria : criterias){
            Predicate filter = criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            filters.add(filter);
        }*/
        Predicate filter = criteriaBuilder.equal(root.get("firstName"), "Tom");
        filters.add(filter);
        filter = criteriaBuilder.between(customerOrderJoin.<Date>get("orderDate"), new Date(), new Date());
        filters.add(filter);
        return criteriaBuilder.and((Predicate[])filters.toArray());
    }
}
