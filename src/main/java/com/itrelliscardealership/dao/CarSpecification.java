package com.itrelliscardealership.dao;

import com.itrelliscardealership.web.util.SearchCriteria;
import com.itrelliscardealership.dao.model.Car;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

public class CarSpecification implements Specification<Car> {

    private SearchCriteria criteria;

    public CarSpecification(final SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Object value = criteria.getValue();
        if (root.get(criteria.getKey()).getJavaType() == Boolean.class) {
            value = Boolean.valueOf((String)value);
        }
        if (root.get(criteria.getKey()).getJavaType() == Integer.class) {
            value = Integer.valueOf((String)value);
        }

        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), value);
            case GREATER_THAN:
                return builder.greaterThan(root.get(
                    criteria.getKey()), value.toString());
            case LESS_THAN:
                return builder.lessThan(root.get(
                    criteria.getKey()), value.toString());
            default:
                return null;
        }
    }
}
