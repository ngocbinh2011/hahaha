package com.teama.bookingcar.controller;

import com.teama.bookingcar.dto.CarDto;
import com.teama.bookingcar.dto.DriverDto;
import com.teama.bookingcar.service.ICarService;
import com.teama.bookingcar.service.impl.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    private ICarService carService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CarDto dto){
        try {
            log.info("Someone create car " + dto);
            CarDto result = carService.create(dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.info("Error when save car");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CarDto dto){
        try {
            CarDto result = carService.update(id, dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.info("Error when update car");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> get(Pageable pageable){
        return ResponseEntity.ok(carService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(carService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try {
            boolean result = carService.deleteById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
