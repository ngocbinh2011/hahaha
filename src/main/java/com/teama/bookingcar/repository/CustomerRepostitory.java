package com.teama.bookingcar.repository;

import com.teama.bookingcar.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepostitory extends JpaRepository<Customer, Long> {
    Set<Customer> findByName(String name);

    Set<Customer> findByAddress(String address);

    Set<Customer> findByPhoneNumber(String phoneNumber);
}
