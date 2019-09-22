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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (_id == null ? 0 : _id.hashCode());
        hash = 31 * hash + (make == null ? 0 : make.hashCode());
        hash = 31 * hash + (color == null ? 0 : color.hashCode());
        hash = 31 * hash + (int) year;
        hash = 31 * hash + (int) price;
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Car car = (Car) obj;
        return _id.equals(car._id);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Car [_id=").append(_id).append("]")
                .append("[make=").append(make).append("]")
                .append("[color=").append(color).append("]")
                .append("[price").append(price).append("]")
                .append("[hasSunroof").append(hasSunroof).append("]")
                .append("[hasLowMiles").append(hasLowMiles).append("]")
                .append("[hasPowerWindows").append(hasPowerWindows).append("]")
                .append("[hasNavigation").append(hasNavigation).append("]")
                .append("[hasHeatedSeats").append(hasHeatedSeats).append("]")
                .append("[fourWheelDrive").append(isFourWheelDrive).append("]");
        return builder.toString();
    }

}
