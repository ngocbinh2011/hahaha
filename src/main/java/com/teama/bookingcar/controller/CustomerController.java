package com.teama.bookingcar.controller;

import com.teama.bookingcar.dto.CarDto;
import com.teama.bookingcar.dto.CustomerDto;
import com.teama.bookingcar.service.ICarService;
import com.teama.bookingcar.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CustomerDto dto) {
        try {
            log.info("Someone create customer " + dto);
            CustomerDto result = customerService.create(dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.info("Error when save customer");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> delete(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "address", required = false) String address,
                                    @RequestParam(value = "phone", required = false) String phone) {
        Set<CustomerDto> result = customerService.searchCustomer(name, address, phone);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CustomerDto dto) {
        try {
            CustomerDto result = customerService.update(id, dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.info("Error when update customer");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> get(Pageable pageable) {
        return ResponseEntity.ok(customerService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            boolean result = customerService.deleteById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
