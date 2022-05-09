package com.teama.bookingcar.repository;

import com.teama.bookingcar.entity.Booking;
import com.teama.bookingcar.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT e from Invoice e where e.createDate >= :from and e.createDate <= :to")
    List<Invoice> findByCreateDate(LocalDate from, LocalDate to);

    @Query("SELECT e from Invoice e where e.booking.id = :id")
    Invoice findByBookingId(Long id);
}
