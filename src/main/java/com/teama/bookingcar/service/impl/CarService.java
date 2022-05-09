package com.teama.bookingcar.service.impl;

import com.teama.bookingcar.dto.CarDto;
import com.teama.bookingcar.entity.Car;
import com.teama.bookingcar.entity.Driver;
import com.teama.bookingcar.repository.CarRepository;
import com.teama.bookingcar.service.ICarService;
import com.teama.bookingcar.service.exception.InvalidDataException;
import com.teama.bookingcar.service.mapper.IConverterDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class CarService implements ICarService {

    private CarRepository carRepository;
    private IConverterDto<Car, CarDto> mapper;

    public CarService(CarRepository carRepository, IConverterDto<Car, CarDto> mapper) {
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    @Override
    public CarDto create(CarDto dto) throws Exception {
        if(dto.getLicensePlate() == null){
            throw new InvalidDataException("Require licensePlate");
        }
        Car entity = mapper.convertToEntity(dto);
        entity.setCreateDate(LocalDate.now());

        Car result = carRepository.save(entity);
        return mapper.convertToDto(result);
    }

    @Override
    public CarDto update(Long id, CarDto dto) throws Exception {
        if(!carRepository.existsById(id)){
            throw new InvalidDataException("car-id not found!");
        }
        if(dto.getLicensePlate() == null){
            throw new InvalidDataException("Require licensePlate");
        }
        Car entity = mapper.convertToEntity(dto);
        entity.setId(id);
        Car result = carRepository.save(entity);
        return mapper.convertToDto(result);
    }

    @Override
    public CarDto getById(Long id) {
        return mapper.convertToDto(
                carRepository.findById(id).orElse(null)
        );
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        if(!carRepository.existsById(id)){
            throw new InvalidDataException("Car-id not found!");
        }
        carRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<CarDto> getAll(Pageable pageable) {
        return mapper.convertToListDto(
                carRepository.findAll(pageable).getContent()
        );
    }
}
