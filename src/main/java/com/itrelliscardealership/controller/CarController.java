package com.itrelliscardealership.controller;

import com.itrelliscardealership.dao.repository.CarRepository;
import com.itrelliscardealership.dao.model.Car;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping(path = "/allcars")
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add);
        return cars;
    }

    @GetMapping(path = "/carstest")
    public Iterable<Car> findAllByWebQuerydsl(
            @QuerydslPredicate(root = Car.class) Predicate predicate) {
        return carRepository.findAll(predicate);
    }

}
