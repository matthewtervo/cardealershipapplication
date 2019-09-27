package com.cardealership.web.controller;

import com.cardealership.dao.CarRepository;
import com.cardealership.dao.model.Car;
import com.cardealership.web.util.RequestParamResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//todo - remove this workaround to allow frontend to make requests
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost"}, maxAge = 3600)
@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    RequestParamResolver paramResolver;

    @GetMapping(path = "/cars/all")
    public List<Car> getCarsAll() {
        List<Car> results = new ArrayList<>();
        carRepository.findAll().forEach(results::add);
        return results;
    }

    @GetMapping(path = "/cars")
    public List<Car> search(@RequestParam(value = "exclusive", defaultValue = "true") boolean exclusive,
                            @RequestParam(value = "search") String search) {
        Specification<Car> spec = paramResolver.resolveSpecification(exclusive, search);
        return carRepository.findAll(spec);
    }

    @GetMapping(path = "/cars/{id}")
    public List<Car> getCarById(@PathVariable(value = "id") String id) {
        List<Car> results = new ArrayList<>();
        if(!id.isEmpty()) {
            Optional<Car> car = carRepository.findById(id);
            if(car.isPresent()) {
                results.add(car.get());
            }
        }
        return results;
    }
}
