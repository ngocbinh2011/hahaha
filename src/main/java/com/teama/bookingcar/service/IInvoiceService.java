package com.teama.bookingcar.service;

import com.teama.bookingcar.dto.BookingDto;
import com.teama.bookingcar.dto.InvoiceDto;

import java.time.LocalDate;
import java.util.List;

public interface IInvoiceService extends IServiceAdapter<InvoiceDto>{
    List<InvoiceDto> getByCreateDate(LocalDate from, LocalDate to);

    List<InvoiceDto> getAll();
}
