package com.itrelliscardealership.dao.repository;

import com.itrelliscardealership.dao.model.Car;
import com.itrelliscardealership.dao.model.QCar;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface CarRepository extends JpaRepository<Car, Integer>,
        QuerydslPredicateExecutor<Car>, QuerydslBinderCustomizer<QCar> {

    @Override
    default public void customize(QuerydslBindings bindings, QCar root) {
        bindings.excluding(root._id);
        bindings.bind(String.class).first(
                (StringPath path, String value) -> path.equalsIgnoreCase(value));
    }
}
