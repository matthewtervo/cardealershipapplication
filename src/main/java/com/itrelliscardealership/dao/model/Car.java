package com.itrelliscardealership.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length=40)
    private String _id;

    @Column(length=40)
    private String make;

    @Column(length=40)
    private String color;

    private int year;
    private int price;
    private boolean hasSunroof;
    private boolean isFourWheelDrive;
    private boolean hasLowMiles;
    private boolean hasPowerWindows;
    private boolean hasNavigation;
    private boolean hasHeatedSeats;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isHasSunroof() {
        return hasSunroof;
    }

    public void setHasSunroof(boolean hasSunroof) {
        this.hasSunroof = hasSunroof;
    }

    public boolean isFourWheelDrive() {
        return isFourWheelDrive;
    }

    public void setFourWheelDrive(boolean fourWheelDrive) {
        isFourWheelDrive = fourWheelDrive;
    }

    public boolean isHasLowMiles() {
        return hasLowMiles;
    }

    public void setHasLowMiles(boolean hasLowMiles) {
        this.hasLowMiles = hasLowMiles;
    }

    public boolean isHasPowerWindows() {
        return hasPowerWindows;
    }

    public void setHasPowerWindows(boolean hasPowerWindows) {
        this.hasPowerWindows = hasPowerWindows;
    }

    public boolean isHasNavigation() {
        return hasNavigation;
    }

    public void setHasNavigation(boolean hasNavigation) {
        this.hasNavigation = hasNavigation;
    }

    public boolean isHasHeatedSeats() {
        return hasHeatedSeats;
    }

    public void setHasHeatedSeats(boolean hasHeatedSeats) {
        this.hasHeatedSeats = hasHeatedSeats;
    }
}
