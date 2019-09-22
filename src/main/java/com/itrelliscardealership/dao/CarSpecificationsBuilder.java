package com.itrelliscardealership.dao;

import com.itrelliscardealership.web.util.SearchCriteria;
import com.itrelliscardealership.dao.model.Car;
import com.itrelliscardealership.web.util.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public CarSpecificationsBuilder() {
        params = new ArrayList<>();
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

        List<Specification> specs = params.stream()
                .map(CarSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
