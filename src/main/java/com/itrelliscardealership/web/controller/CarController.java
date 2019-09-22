package com.itrelliscardealership.web.controller;

import com.itrelliscardealership.dao.CarSpecificationsBuilder;
import com.itrelliscardealership.dao.CarRepository;
import com.itrelliscardealership.dao.model.Car;
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

    @GetMapping(path = "/allcars")
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add);
        return cars;
    }

    @GetMapping(path = "/cars")
    public List<Car> search(@RequestParam(value = "search") String search) {
        CarSpecificationsBuilder builder = new CarSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<Car> spec = builder.build();
        return carRepository.findAll(spec);
    }
}
