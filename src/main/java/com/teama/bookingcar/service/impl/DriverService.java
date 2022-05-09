package com.teama.bookingcar.service.impl;

import com.teama.bookingcar.dto.DriverDto;
import com.teama.bookingcar.entity.Car;
import com.teama.bookingcar.entity.Driver;
import com.teama.bookingcar.repository.DriverRepository;
import com.teama.bookingcar.service.IDriverService;
import com.teama.bookingcar.service.exception.InvalidDataException;
import com.teama.bookingcar.service.mapper.IConverterDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class DriverService implements IDriverService {

    private DriverRepository driverRepository;
    private IConverterDto<Driver, DriverDto> mapper;

    public DriverService(DriverRepository driverRepository, IConverterDto<Driver, DriverDto> mapper) {
        this.driverRepository = driverRepository;
        this.mapper = mapper;
    }

    @Override
    public DriverDto create(DriverDto dto) throws Exception {
        if(dto.getLicenseNumber() == null){
            throw new InvalidDataException("Require licenseNumber");
        }
        if(dto.getCar() != null && dto.getCar().getId() != null){
            if(driverRepository.findByCarId(dto.getCar().getId()) != null){
                throw new InvalidDataException("Car are using!");
            }
        }
        Driver driver = mapper.convertToEntity(dto);
        driver.setCreateDate(LocalDate.now());

        Driver result = driverRepository.save(driver);
        return mapper.convertToDto(result);
    }

    @Override
    public DriverDto update(Long id, DriverDto dto) throws Exception {
        if(!driverRepository.existsById(id)){
            throw new InvalidDataException("Driver-id not found!");
        }
        if(dto.getLicenseNumber() == null){
            throw new InvalidDataException("Require licenseNumber");
        }
        Driver driver = driverRepository.findById(id).orElse(null);
        driver.setLicenseNumber(dto.getLicenseNumber());
        if(dto.getPhoneNumber() != null){
            driver.setPhoneNumber(dto.getPhoneNumber());
        }
        if(dto.getRating() != null){
            driver.setRating(dto.getRating());
        }
        if(dto.getCar() != null && dto.getCar().getId() != null){
            if(driverRepository.findByCarId(dto.getCar().getId()) != null){
                throw new InvalidDataException("Car are using!");
            }
            Car car = new Car();
            car.setId(dto.getCar().getId());
            driver.setCar(car);
        }
        driver.setId(id);
        Driver result = driverRepository.save(driver);
        return mapper.convertToDto(result);
    }

    @Override
    public DriverDto getById(Long id) {
        return mapper.convertToDto(
                driverRepository.findById(id).orElse(null)
        );
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        if(!driverRepository.existsById(id)){
            throw new InvalidDataException("Driver-id not found!");
        }
        driverRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<DriverDto> getAll(Pageable pageable) {
        return mapper.convertToListDto(
                driverRepository.findAll(pageable).getContent()
        );
    }
}
