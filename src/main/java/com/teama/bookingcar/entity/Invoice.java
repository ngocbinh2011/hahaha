package com.teama.bookingcar.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Data
@Table(name = "invoice")
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(name = "total_charge")
    private Long totalCharge;

    @Column(name = "create_date")
    private LocalDate createDate;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;


}
