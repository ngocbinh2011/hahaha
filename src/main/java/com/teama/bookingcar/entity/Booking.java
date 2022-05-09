package com.teama.bookingcar.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_location")
    private String startLocation;

    @Column(name = "end_location")
    private String endLocation;

    @Column(name = "pick_up_time")
    private LocalDateTime pickUpTime;

    @Column(name = "drop_off_time")
    private LocalDateTime dropOffTime;

    @Column(name = "distance_trip")
    private Double distanceTrip;

    @Column(name = "create_date")
    private LocalDate createDate;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.REMOVE)
    private Invoice invoice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(startLocation, booking.startLocation) && Objects.equals(endLocation, booking.endLocation) && Objects.equals(pickUpTime, booking.pickUpTime) && Objects.equals(dropOffTime, booking.dropOffTime) && Objects.equals(distanceTrip, booking.distanceTrip) && Objects.equals(createDate, booking.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startLocation, endLocation, pickUpTime, dropOffTime, distanceTrip, createDate);
    }
}