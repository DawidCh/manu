package com.chojnacki.manufaktura.manuregiony.model;

/**
 * Class which represents single car plate
 */
public class Plate {
    private String region;
    private String number;

    public Plate(String number) {
        String[] countryAndNumber = number.split(" ");
        this.region = countryAndNumber[0].toUpperCase();
        this.number = countryAndNumber[1].toUpperCase();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Plate plate = (Plate) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(region, plate.region)
                .append(number, plate.number)
                .isEquals();
    }

    @Override
    public String toString() {
        return "Plate{" +
                "region=" + region +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(region)
                .append(number)
                .toHashCode();

    }
}
