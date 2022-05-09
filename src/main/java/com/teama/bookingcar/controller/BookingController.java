package com.teama.bookingcar.controller;

import com.teama.bookingcar.dto.BookingDto;
import com.teama.bookingcar.service.IBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private IBookingService bookingService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody BookingDto dto){
        try {
            log.info("Someone create booking " + dto);
            BookingDto result = bookingService.create(dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.info("Error when save booking");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody BookingDto dto){
        try {
            BookingDto result = bookingService.update(id, dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.info("Error when update car");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("")
    public ResponseEntity<?> get(Pageable pageable,
                                 @RequestParam(value = "from", required = false)
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                                 @RequestParam(value = "to", required = false)
                                     @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate){
        if(fromDate == null){
            return ResponseEntity.ok(bookingService.getAll(pageable));
        } else{
            return ResponseEntity.ok(bookingService.getByCreateDate(fromDate, toDate));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookingService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try {
            boolean result = bookingService.deleteById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
