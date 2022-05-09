package com.teama.bookingcar.service.mapper.impl;

import com.teama.bookingcar.dto.BookingDto;
import com.teama.bookingcar.dto.CustomerDto;
import com.teama.bookingcar.dto.InvoiceDto;
import com.teama.bookingcar.entity.Booking;
import com.teama.bookingcar.entity.Customer;
import com.teama.bookingcar.entity.Invoice;
import com.teama.bookingcar.service.mapper.AbstractDtoMapperAdapter;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper extends AbstractDtoMapperAdapter<Invoice, InvoiceDto> {
    public InvoiceMapper() {
        super(Invoice.class, InvoiceDto.class);
    }
}
