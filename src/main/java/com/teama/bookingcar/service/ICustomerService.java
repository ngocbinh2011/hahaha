package com.teama.bookingcar.service;

import com.teama.bookingcar.dto.CustomerDto;
import com.teama.bookingcar.entity.Customer;

import java.util.Set;

public interface ICustomerService extends IServiceAdapter<CustomerDto>{
    Set<CustomerDto> searchCustomer(String name, String address, String phoneNumber);
}
