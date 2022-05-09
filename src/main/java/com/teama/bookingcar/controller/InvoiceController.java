package com.teama.bookingcar.controller;

import com.teama.bookingcar.dto.BookingDto;
import com.teama.bookingcar.dto.InvoiceDto;
import com.teama.bookingcar.service.IInvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    private IInvoiceService invoiceService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody InvoiceDto dto) {
        try {
            log.info("Someone create invoice " + dto);
            InvoiceDto result = invoiceService.create(dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error when save invoice");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody InvoiceDto dto) {
        try {
            InvoiceDto result = invoiceService.update(id, dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.info("Error when update invoice");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> get(Pageable pageable, @RequestParam(value = "from", required = false)
                                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                                 @RequestParam(value = "to", required = false)
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
        if(fromDate == null){
            return ResponseEntity.ok(invoiceService.getAll(pageable));
        } else{
            return ResponseEntity.ok(invoiceService.getByCreateDate(fromDate, toDate));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(invoiceService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            boolean result = invoiceService.deleteById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
