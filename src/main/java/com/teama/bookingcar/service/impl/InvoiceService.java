package com.teama.bookingcar.service.impl;

import com.teama.bookingcar.dto.BookingDto;
import com.teama.bookingcar.dto.InvoiceDto;
import com.teama.bookingcar.entity.Booking;
import com.teama.bookingcar.entity.Invoice;
import com.teama.bookingcar.repository.BookingRepository;
import com.teama.bookingcar.repository.CustomerRepostitory;
import com.teama.bookingcar.repository.DriverRepository;
import com.teama.bookingcar.repository.InvoiceRepository;
import com.teama.bookingcar.service.IInvoiceService;
import com.teama.bookingcar.service.exception.InvalidDataException;
import com.teama.bookingcar.service.mapper.IConverterDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {

    private InvoiceRepository invoiceRepository;
    private IConverterDto<Invoice, InvoiceDto> mapper;
    private CustomerRepostitory customerRepostitory;
    private DriverRepository driverRepository;
    private BookingRepository bookingRepository;

    public InvoiceService(InvoiceRepository invoiceRepository,
                          IConverterDto<Invoice, InvoiceDto> mapper,
                          CustomerRepostitory customerRepostitory,
                          DriverRepository driverRepository,
                          BookingRepository bookingRepository) {
        this.invoiceRepository = invoiceRepository;
        this.mapper = mapper;
        this.customerRepostitory = customerRepostitory;
        this.driverRepository = driverRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public InvoiceDto create(InvoiceDto dto) throws Exception {
        validate(dto);
        Invoice invoice = mapper.convertToEntity(dto);

        Booking booking = bookingRepository.findById(dto.getBooking().getId()).orElse(null);
        booking.setDropOffTime(LocalDateTime.now());
        booking = bookingRepository.save(booking);

        invoice.setBooking(booking);
        invoice.setCreateDate(LocalDate.now());
        InvoiceDto result = mapper.convertToDto(
                invoiceRepository.save(invoice)
        );
        return result;
    }

    @Override
    public InvoiceDto update(Long id, InvoiceDto dto) throws Exception {
        if(!invoiceRepository.existsById(id)){
            throw new InvalidDataException("invoice-id not found!");
        }
        validate(dto);

        Invoice invoice = mapper.convertToEntity(dto);
        invoice.setId(id);
        return mapper.convertToDto(
                invoiceRepository.save(invoice)
        );
    }

    @Override
    public InvoiceDto getById(Long id) {
        return mapper.convertToDto(
                invoiceRepository.findById(id).orElse(null)
        );
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        if(!invoiceRepository.existsById(id)){
            throw new InvalidDataException("invoice-id not found!");
        }
        invoiceRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<InvoiceDto> getAll(Pageable pageable) {
        return mapper.convertToListDto(
                invoiceRepository.findAll(pageable).getContent()
        );
    }

    private void validate(InvoiceDto dto) throws InvalidDataException {
        if(dto.getCustomer() == null || dto.getCustomer().getId() == null){
            throw new InvalidDataException("Require customer[id]");
        }
        if(dto.getDriver() == null || dto.getDriver().getId() == null){
            throw new InvalidDataException("Require driver[id]");
        }
        if(dto.getBooking() == null || dto.getBooking().getId() == null){
            throw new InvalidDataException("Require Booking[id]");
        }
        if(dto.getTotalCharge() == null || dto.getTotalCharge() < 0){
            throw new InvalidDataException("Invoice[total-charge] must greater than 0");
        }
        if(!customerRepostitory.existsById(dto.getCustomer().getId())){
            throw new InvalidDataException("customer[id] not found");
        }
        if(!driverRepository.existsById(dto.getDriver().getId())){
            throw new InvalidDataException("driver[id] not found");
        }
        if(!bookingRepository.existsById(dto.getBooking().getId())){
            throw new InvalidDataException("driver[id] not found");
        }
        if(invoiceRepository.findByBookingId(dto.getBooking().getId()) != null){
            throw new InvalidDataException("Booking[id] are using");
        }
    }

    @Override
    public List<InvoiceDto> getByCreateDate(LocalDate from, LocalDate to) {
        return mapper.convertToListDto(
                invoiceRepository.findByCreateDate(from, to)
        );
    }

    @Override
    public List<InvoiceDto> getAll() {
        return mapper.convertToListDto(invoiceRepository.findAll());
    }
}
