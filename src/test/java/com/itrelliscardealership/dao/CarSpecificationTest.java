package com.itrelliscardealership.dao;

import com.itrelliscardealership.dao.model.Car;
import com.itrelliscardealership.web.util.SearchCriteria;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.collection.IsIn.isIn;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarSpecificationTest {

    @Autowired
    private CarRepository carRepository;

    private Car carOne;
    private Car carTwo;

    @Before
    public void init() {
        carOne = new Car();
        carOne.set_id("59d2698c340f2728382c0a73");
        carOne.setMake("Toyota");
        carOne.setColor("White");
        carOne.setYear(2015);
        carOne.setPrice(15895);
        carOne.setHasSunroof(true);
        carOne.setHasLowMiles(true);
        carOne.setHasPowerWindows(true);
        carOne.setHasNavigation(false);
        carOne.setHasHeatedSeats(true);
        carOne.setFourWheelDrive(false);

        carTwo = new Car();
        carTwo.set_id("59d2698cd6a3b8f0dd994c9d");
        carTwo.setMake("Toyota");
        carTwo.setColor("Black");
        carTwo.setYear(2015);
        carTwo.setPrice(13170);
        carTwo.setHasSunroof(true);
        carTwo.setHasLowMiles(true);
        carTwo.setHasPowerWindows(true);
        carTwo.setHasNavigation(false);
        carTwo.setHasHeatedSeats(false);
        carTwo.setFourWheelDrive(false);
    }

    @Test
    public void testSearch_oneSpecification_shouldSucceed() {
        CarSpecification spec =
                new CarSpecification(new SearchCriteria("make", ":", "toyota"));

        List<Car> results = carRepository.findAll(spec);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertThat(carOne, isIn(results));
        assertThat(carTwo, isIn(results));
    }

    @Test
    public void testSearch_twoSpecifications_shouldSucceed() {
        CarSpecification spec1 =
                new CarSpecification(new SearchCriteria("make", ":", "toyota"));
        CarSpecification spec2 =
                new CarSpecification(new SearchCriteria("year", ":", "2015"));

        List<Car> results = carRepository.findAll(Specification.where(spec1).and(spec2));

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertThat(carOne, isIn(results));
        assertThat(carTwo, isIn(results));
    }

    @Test
    public void testSearch_invalidSpecification_shouldSucceed() {
        CarSpecification spec =
                new CarSpecification(new SearchCriteria("make", ":", "invalid"));

        List<Car> results = carRepository.findAll(spec);

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}