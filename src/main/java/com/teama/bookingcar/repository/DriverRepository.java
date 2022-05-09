package com.teama.bookingcar.repository;

import com.teama.bookingcar.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query("SELECT e FROM Driver e WHERE e.car.id = :id")
    Driver findByCarId(Long id);
}
