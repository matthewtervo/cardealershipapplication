package com.itrelliscardealership.dao;

import com.itrelliscardealership.web.util.SearchCriteria;
import com.itrelliscardealership.dao.model.Car;
import com.itrelliscardealership.web.util.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CarSpecificationsBuilder {

    private final List<SearchCriteria> params;
    private final boolean exclusive;

    public CarSpecificationsBuilder(boolean exclusive) {
        this.exclusive = exclusive;
        this.params = new ArrayList<>();
    }

    public CarSpecificationsBuilder with(
            String key, String operation, Object value) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        params.add(new SearchCriteria(key, op, value));
        return this;
    }

    public Specification<Car> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification result = new CarSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            if(exclusive) {
                result = Specification.where(result).and(new CarSpecification(params.get(i)));
            } else {
                result = Specification.where(result).or(new CarSpecification(params.get(i)));
            }
        }
        return result;
    }
}
