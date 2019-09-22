package com.itrelliscardealership.dao;

import com.itrelliscardealership.dao.model.Car;
import com.itrelliscardealership.web.util.SearchCriteria;
import com.itrelliscardealership.web.util.SearchOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.collection.IsIn.isIn;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarSpecificationTest {

    @Autowired
    private CarRepository carRepository;

    private Car carOne;
    private Car carTwo;
    private Car carThree;

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

        carThree = new Car();
        carThree.set_id("59d2698c86ab54cee8acdc7b");
        carThree.setMake("Mercedes");
        carThree.setColor("Gray");
        carThree.setYear(2013);
        carThree.setPrice(15669);
        carThree.setHasSunroof(false);
        carThree.setHasLowMiles(true);
        carThree.setHasPowerWindows(false);
        carThree.setHasNavigation(false);
        carThree.setHasHeatedSeats(false);
        carThree.setFourWheelDrive(false);
    }

    @Test
    public void testSearch_oneSpecification_shouldSucceed() {
        CarSpecification spec =
                new CarSpecification(new SearchCriteria("make", SearchOperation.EQUALITY, "toyota"));

        List<Car> results = carRepository.findAll(spec);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertThat(carOne, isIn(results));
        assertThat(carTwo, isIn(results));
    }

    @Test
    public void testSearch_twoSpecifications_shouldSucceed() {
        CarSpecification spec1 =
                new CarSpecification(new SearchCriteria("make", SearchOperation.EQUALITY, "toyota"));
        CarSpecification spec2 =
                new CarSpecification(new SearchCriteria("year", SearchOperation.EQUALITY, "2015"));

        List<Car> results = carRepository.findAll(Specification.where(spec1).and(spec2));

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertThat(carOne, isIn(results));
        assertThat(carTwo, isIn(results));
    }

    @Test
    public void testSearch_invalidSpecification_shouldSucceed() {
        CarSpecification spec =
                new CarSpecification(new SearchCriteria("make", SearchOperation.EQUALITY, "invalid"));

        List<Car> results = carRepository.findAll(spec);

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSearch_LessThanSpecification_shouldSucceed() {
        CarSpecification spec =
                new CarSpecification(new SearchCriteria("year", SearchOperation.LESS_THAN, "2014"));

        List<Car> results = carRepository.findAll(spec);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertFalse(results.contains(carOne));
        assertThat(carThree, isIn(results));
    }

    @Test
    public void testSearch_greaterThanSpecification_shouldSucceed() {
        CarSpecification spec =
                new CarSpecification(new SearchCriteria("year", SearchOperation.GREATER_THAN, "2015"));

        List<Car> results = carRepository.findAll(spec);

        assertNotNull(results);
        assertFalse(results.isEmpty());
    }

    @Test
    public void testSearch_nonExclusive__shouldSucceed() {
        CarSpecification spec1 =
                new CarSpecification(new SearchCriteria("make", SearchOperation.EQUALITY, "mercedes"));
        CarSpecification spec2 =
                new CarSpecification(new SearchCriteria("color", SearchOperation.EQUALITY, "white"));

        List<Car> results = carRepository.findAll(Specification.where(spec1).or(spec2));

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertThat(carThree, isIn(results));
        assertThat(carOne, isIn(results));
        assertFalse(results.contains(carTwo));
    }
}