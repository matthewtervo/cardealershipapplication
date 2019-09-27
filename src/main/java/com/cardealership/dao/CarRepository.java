package com.cardealership.dao;

import com.cardealership.dao.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarRepository extends JpaRepository<Car, String>, JpaSpecificationExecutor<Car> { }
