package com.itrelliscardealership.repository;

import com.itrelliscardealership.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
