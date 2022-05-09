package com.teama.bookingcar.repository;

import com.teama.bookingcar.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT e from Booking e where e.createDate >= :from and e.createDate <= :to")
    List<Booking> findByCreateDate(LocalDate from, LocalDate to);
}
