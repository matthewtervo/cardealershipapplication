package com.itrelliscardealership.controller;

import com.itrelliscardealership.repository.CarRepository;
import com.itrelliscardealership.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
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
}
