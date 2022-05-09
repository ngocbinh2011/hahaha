package com.teama.bookingcar.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RevenueDto {
    private int amountInvoice;
    private BigDecimal totalRevenue;
    private List<InvoiceDto> invoices;
}
