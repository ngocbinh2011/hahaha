package com.teama.bookingcar.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "convertible")
    private String convertible;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "licensePlate")
    private String licensePlate;

    @Column(name = "create_date")
    private LocalDate createDate;

    @OneToOne(mappedBy = "car", cascade = CascadeType.REMOVE)
    private Driver driver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(make, car.make) && Objects.equals(model, car.model) && Objects.equals(color, car.color) && Objects.equals(convertible, car.convertible) && Objects.equals(rating, car.rating) && Objects.equals(licensePlate, car.licensePlate) && Objects.equals(createDate, car.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, color, convertible, rating, licensePlate, createDate);
    }
}
