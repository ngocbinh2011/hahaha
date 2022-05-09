package com.teama.bookingcar.service.mapper.impl;

import com.teama.bookingcar.dto.CarDto;
import com.teama.bookingcar.entity.Car;
import com.teama.bookingcar.service.mapper.AbstractDtoMapperAdapter;
import org.springframework.stereotype.Component;

@Component
public class CarMapper extends AbstractDtoMapperAdapter<Car, CarDto> {
    public CarMapper() {
        super(Car.class, CarDto.class);
    }
}
