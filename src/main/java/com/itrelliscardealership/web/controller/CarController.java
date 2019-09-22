package com.itrelliscardealership.web.controller;

import com.itrelliscardealership.dao.CarRepository;
import com.itrelliscardealership.dao.model.Car;
import com.itrelliscardealership.web.util.RequestParamResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//todo - remove this workaround to allow frontend to make requests
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost"}, maxAge = 3600)
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
    public List<Car> search(@RequestParam(value = "exclusive", defaultValue = "true") boolean exclusive,
                            @RequestParam(value = "search") String search) {
        Specification<Car> spec = paramResolver.resolveSpecification(exclusive, search);
        return carRepository.findAll(spec);
    }
}
