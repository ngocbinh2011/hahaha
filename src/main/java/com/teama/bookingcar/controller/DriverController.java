package com.teama.bookingcar.controller;

import com.teama.bookingcar.dto.DriverDto;
import com.teama.bookingcar.service.IDriverService;
import com.teama.bookingcar.service.impl.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private IDriverService driverService;

    public DriverController(IDriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody DriverDto dto){
        try {
            log.info("Someone create driver " + dto);
            DriverDto result = driverService.create(dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.info("Error when save driver");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DriverDto dto){
        try {
            DriverDto result = driverService.update(id, dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.info("Error when update driver");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> get(Pageable pageable){
        return ResponseEntity.ok(driverService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(driverService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try {
            boolean result = driverService.deleteById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
