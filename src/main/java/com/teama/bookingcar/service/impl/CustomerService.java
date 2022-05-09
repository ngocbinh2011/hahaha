package com.teama.bookingcar.service.impl;

import com.teama.bookingcar.dto.CustomerDto;
import com.teama.bookingcar.dto.DriverDto;
import com.teama.bookingcar.entity.Car;
import com.teama.bookingcar.entity.Customer;
import com.teama.bookingcar.entity.Driver;
import com.teama.bookingcar.repository.CustomerRepostitory;
import com.teama.bookingcar.repository.DriverRepository;
import com.teama.bookingcar.service.ICustomerService;
import com.teama.bookingcar.service.exception.InvalidDataException;
import com.teama.bookingcar.service.mapper.IConverterDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    private CustomerRepostitory customerRepostitory;
    private IConverterDto<Customer, CustomerDto> mapper;

    public CustomerService(CustomerRepostitory customerRepostitory, IConverterDto<Customer, CustomerDto> mapper) {
        this.customerRepostitory = customerRepostitory;
        this.mapper = mapper;
    }

    @Override
    public CustomerDto create(CustomerDto dto) throws Exception {
        if(dto.getName() == null){
            throw new InvalidDataException("Require name");
        }
        if(dto.getPhoneNumber() == null){
            throw new InvalidDataException("Require phoneNumber");
        }
        Customer entity = mapper.convertToEntity(dto);
        entity.setCreateDate(LocalDate.now());
        Customer result = customerRepostitory.save(entity);
        return mapper.convertToDto(result);
    }

    @Override
    public Set<CustomerDto> searchCustomer(String name, String address, String phoneNumber) {
        Set<Customer> result = new LinkedHashSet<>();
        if(name != null){
            result.addAll(customerRepostitory.findByName(name));
        }
        if(address != null){
            if (result.isEmpty()) {
                result.addAll(customerRepostitory.findByAddress(address));
            } else {
                Set<Customer> customers = customerRepostitory.findByAddress(address);
                final Set<Customer> filter = result;
                Set<Customer> afterFilter = customers.stream()
                        .filter(ticketDto -> filter.contains(ticketDto))
                        .collect(Collectors.toSet());
                result = afterFilter;
            }
        }
        if(phoneNumber != null){
            if (result.isEmpty()) {
                result.addAll(customerRepostitory.findByPhoneNumber(phoneNumber));
            } else {
                Set<Customer> customers = customerRepostitory.findByPhoneNumber(phoneNumber);
                final Set<Customer> filter = result;
                Set<Customer> afterFilter = customers.stream()
                        .filter(ticketDto -> filter.contains(ticketDto))
                        .collect(Collectors.toSet());
                result = afterFilter;
            }
        }
        return new LinkedHashSet<>(mapper.convertToListDto(result));
    }

    @Override
    public CustomerDto update(Long id, CustomerDto dto) throws Exception {
        if(!customerRepostitory.existsById(id)){
            throw new InvalidDataException("customer-id not found!");
        }
        if(dto.getName() == null){
            throw new InvalidDataException("Require name");
        }
        if(dto.getPhoneNumber() == null){
            throw new InvalidDataException("Require phoneNumber");
        }

        Customer entity = mapper.convertToEntity(dto);
        entity.setId(id);
        Customer result = customerRepostitory.save(entity);
        return mapper.convertToDto(result);
    }

    @Override
    public CustomerDto getById(Long id) {
        return mapper.convertToDto(
                customerRepostitory.findById(id).orElse(null)
        );
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        if(!customerRepostitory.existsById(id)){
            throw new InvalidDataException("customer-id not found!");
        }
        customerRepostitory.deleteById(id);
        return true;
    }

    @Override
    public Collection<CustomerDto> getAll(Pageable pageable) {
        return mapper.convertToListDto(
                customerRepostitory.findAll(pageable).getContent()
        );
    }
}
