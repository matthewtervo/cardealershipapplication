package com.itrelliscardealership.web.controller;

import com.google.common.base.Joiner;
import com.itrelliscardealership.dao.CarSpecificationsBuilder;
import com.itrelliscardealership.dao.CarRepository;
import com.itrelliscardealership.dao.model.Car;
import com.itrelliscardealership.web.util.RequestParamResolver;
import com.itrelliscardealership.web.util.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    RequestParamResolver paramResolver;

    @GetMapping(path = "/allcars")
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add);
        return cars;
    }

    @GetMapping(path = "/cars")
    public List<Car> search(@RequestParam(value = "search") String search) {
        Specification<Car> spec = paramResolver.resolveSpecification(search);
        return carRepository.findAll(spec);
    }
}
