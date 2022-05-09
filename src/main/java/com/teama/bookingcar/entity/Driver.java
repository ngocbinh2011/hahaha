package com.teama.bookingcar.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "rating")
    private Integer rating;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "create_date")
    private LocalDate createDate;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.REMOVE)
    private Set<Invoice> invoices = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) && Objects.equals(licenseNumber, driver.licenseNumber) && Objects.equals(phoneNumber, driver.phoneNumber) && Objects.equals(rating, driver.rating) && Objects.equals(car, driver.car) && Objects.equals(createDate, driver.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licenseNumber, phoneNumber, rating, car, createDate);
    }
}
