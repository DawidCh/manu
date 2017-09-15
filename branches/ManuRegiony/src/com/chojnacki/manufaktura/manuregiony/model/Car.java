package com.chojnacki.manufaktura.manuregiony.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;
import java.util.Locale;

/**
 * Class which represents single car from file.
 */
public class Car {
    private long position;
    private String make;
    private String model;
    private Date entryDate;
    private Plate plate;
    private Locale origin;

    public Car(long position, String make, String model, Date entryDate, Plate plate, Locale origin) {
        this.position = position;
        this.make = make;
        this.model = model;
        this.entryDate = entryDate;
        this.plate = plate;
        this.origin = origin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public Locale getOrigin() {
        return origin;
    }

    public void setOrigin(Locale origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return new EqualsBuilder()
                .append(plate, car.plate)
                .append(entryDate, car.entryDate)
                .isEquals();
    }

    @Override
    public String toString() {
        return "Car{" +
                "position=" + position +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", entryDate=" + entryDate +
                ", plate=" + plate.toString() +
                ", origin=" + origin +
                '}';
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(plate)
                .append(entryDate)
                .toHashCode();
    }
}
