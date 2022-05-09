package com.teama.bookingcar.controller;

import com.teama.bookingcar.dto.CarUseInMonthDto;
import com.teama.bookingcar.dto.InvoiceDto;
import com.teama.bookingcar.dto.RevenueDto;
import com.teama.bookingcar.entity.Invoice;
import com.teama.bookingcar.service.IInvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/statistic")
public class StatisticController {

    @Autowired
    private IInvoiceService invoiceService;

    @GetMapping("/revenue")
    public ResponseEntity<?> get(@RequestParam(value = "from")
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                                 @RequestParam(value = "to")
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
                                 @RequestParam(value = "cid", required = false) Long customerId,
                                 @RequestParam(value = "did", required = false) Long driverId){

        List<InvoiceDto> invoices = invoiceService.getByCreateDate(fromDate, toDate);

        if(customerId != null && driverId != null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[cid] or [did] must null");
        }
        if(customerId != null){
            invoices = invoices.stream()
                    .filter(invoiceDto -> invoiceDto.getCustomer() != null)
                    .filter(invoiceDto -> invoiceDto.getCustomer().getId() == customerId)
                    .collect(Collectors.toList());
        }
        if(driverId != null){
            invoices = invoices.stream()
                    .filter(invoiceDto -> invoiceDto.getDriver() != null)
                    .filter(invoiceDto -> invoiceDto.getDriver().getId() == driverId)
                    .collect(Collectors.toList());
        }

        BigDecimal total = new BigDecimal(0);
        for(InvoiceDto invoiceDto: invoices){
            total = total.add(new BigDecimal(invoiceDto.getTotalCharge()));
        }
        RevenueDto revenueDto = new RevenueDto();
        revenueDto.setTotalRevenue(total);
        revenueDto.setInvoices(invoices);
        revenueDto.setAmountInvoice(invoices.size());
        return ResponseEntity.ok(revenueDto);
    }

    @GetMapping("/car-use-in-month")
    public ResponseEntity<?> getInMonth(@RequestParam("year") Integer year,
                                        @RequestParam("month") Integer month){
        List<InvoiceDto> list = invoiceService.getAll();

        List<InvoiceDto> carInMonth = list.stream()
                .filter(invoiceDto -> {
                      return invoiceDto != null
                              && invoiceDto.getDriver() != null
                              && invoiceDto.getDriver().getCar() != null
                             && invoiceDto.getCreateDate() != null
                             && invoiceDto.getCreateDate().getMonthValue() == month
                              && invoiceDto.getCreateDate().getYear() == year;
                }).collect(Collectors.toList());


        Map<String, Long> count = new HashMap<>();
        for(InvoiceDto invoiceDto: carInMonth){
            String licensePlate = invoiceDto.getDriver().getCar().getLicensePlate();
            if(licensePlate == null){
                continue;
            }
            if(!count.containsKey(licensePlate)){
                count.put(licensePlate, 1L);
            } else{
                count.put(licensePlate, count.get(licensePlate) + 1);
            }
        }
        return ResponseEntity.ok(count);

    }
}
