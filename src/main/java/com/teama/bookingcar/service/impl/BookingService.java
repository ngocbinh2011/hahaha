package com.teama.bookingcar.service.impl;

import com.teama.bookingcar.dto.BookingDto;
import com.teama.bookingcar.dto.CarDto;
import com.teama.bookingcar.entity.Booking;
import com.teama.bookingcar.entity.Car;
import com.teama.bookingcar.repository.BookingRepository;
import com.teama.bookingcar.repository.CarRepository;
import com.teama.bookingcar.service.IBookingService;
import com.teama.bookingcar.service.exception.InvalidDataException;
import com.teama.bookingcar.service.mapper.IConverterDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Service
public class BookingService implements IBookingService {

    private BookingRepository bookingRepository;
    private IConverterDto<Booking, BookingDto> mapper;

    public BookingService(BookingRepository bookingRepository, IConverterDto<Booking, BookingDto> mapper) {
        this.bookingRepository = bookingRepository;
        this.mapper = mapper;
    }

    @Override
    public BookingDto create(BookingDto dto) throws Exception {
        if(dto.getStartLocation() == null || dto.getEndLocation() == null){
            throw new InvalidDataException("Require startLocation and endLocation");
        }
        if(dto.getPickUpTime() == null){
            throw new InvalidDataException("Require pickup Time");
        }
        if(dto.getDistanceTrip() == null){
            throw new InvalidDataException("Require distance trip");
        }
        Booking entity = mapper.convertToEntity(dto);
        entity.setCreateDate(LocalDate.now());

        Booking result = bookingRepository.save(entity);
        return mapper.convertToDto(result);
    }

    @Override
    public BookingDto update(Long id, BookingDto dto) throws Exception {
        if(!bookingRepository.existsById(id)){
            throw new InvalidDataException("booking-id not found!");
        }
        if(dto.getStartLocation() == null || dto.getEndLocation() == null){
            throw new InvalidDataException("Require startLocation and endLocation");
        }
        if(dto.getPickUpTime() == null){
            throw new InvalidDataException("Require pickup Time");
        }
        if(dto.getDistanceTrip() == null){
            throw new InvalidDataException("Require distance trip");
        }
        Booking entity = mapper.convertToEntity(dto);
        entity.setCreateDate(LocalDate.now());
        entity.setId(id);

        Booking result = bookingRepository.save(entity);
        return mapper.convertToDto(result);
    }

    @Override
    public BookingDto getById(Long id) {
        return mapper.convertToDto(
                bookingRepository.findById(id).orElse(null)
        );
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        if(!bookingRepository.existsById(id)){
            throw new InvalidDataException("booking-id not found!");
        }
        bookingRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<BookingDto> getAll(Pageable pageable) {
        return mapper.convertToListDto(
                bookingRepository.findAll(pageable).getContent()
        );
    }

    @Override
    public List<BookingDto> getByCreateDate(LocalDate fromDate, LocalDate toDate) {
        return mapper.convertToListDto(
                bookingRepository.findByCreateDate(fromDate, toDate)
        );
    }
}
