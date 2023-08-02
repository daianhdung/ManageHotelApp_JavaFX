package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.InvoiceDTO;
import com.managehotelapp_javafx.repository.InvoiceRepository;
import com.managehotelapp_javafx.repository.imp.InvoiceRepositoryImp;
import com.managehotelapp_javafx.services.RevenueService;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class RevenueServiceImp implements RevenueService {
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate date;

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

   InvoiceRepositoryImp invoiceRepositoryImp = new InvoiceRepositoryImp();

    @Override
    public List<InvoiceDTO> getToday() {
        LocalDate todayDate = LocalDate.now();
        LocalDateTime startOfDay = todayDate.atStartOfDay();
        Timestamp timestampToday = Timestamp.valueOf(startOfDay);
        return invoiceRepositoryImp.findByDay(timestampToday).stream()
                .map(item -> {
                    InvoiceDTO invoiceDTO = new InvoiceDTO();
                    invoiceDTO.setIdCustomer(item.getCustomer().getId());
                    invoiceDTO.setId(item.getId());
                    invoiceDTO.setCreatedAt(item.getCreatedAt());
                    invoiceDTO.setPaymentAmount(item.getPaymentAmount());
                    return invoiceDTO;
                }).toList();
    }

    @Override
    public List<InvoiceDTO> getWeekly() {
        getWeekRange();
        LocalDateTime frD = fromDate.atStartOfDay();
        LocalDateTime tD = toDate.atStartOfDay();
        Timestamp timestampfrD = Timestamp.valueOf(frD);
        Timestamp timestamptD = Timestamp.valueOf(tD);
        return invoiceRepositoryImp.findBetweenDates(timestampfrD,timestamptD).stream()
                .map(item -> {
                    InvoiceDTO invoiceDTO = new InvoiceDTO();
                    invoiceDTO.setIdCustomer(item.getCustomer().getId());
                    invoiceDTO.setId(item.getId());
                    invoiceDTO.setCreatedAt(item.getCreatedAt());
                    invoiceDTO.setPaymentAmount(item.getPaymentAmount());
                    return invoiceDTO;
                }).toList();
    }

    @Override
    public List<InvoiceDTO> getMonthly() {

        return invoiceRepositoryImp.findByMonth(date).stream()
                .map(item -> {
                    InvoiceDTO invoiceDTO = new InvoiceDTO();
                    invoiceDTO.setIdCustomer(item.getCustomer().getId());
                    invoiceDTO.setId(item.getId());
                    invoiceDTO.setCreatedAt(item.getCreatedAt());
                    invoiceDTO.setPaymentAmount(item.getPaymentAmount());
                    return invoiceDTO;
                }).toList();
    }

    @Override
    public List<InvoiceDTO> getYearly() {
        var thisYear = date.getYear();
        return invoiceRepositoryImp.getInvoiceList().stream()
                .filter(f -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(f.getCreatedAt().getTime());
                    int year = calendar.get(Calendar.YEAR);
                    return year == thisYear;
                }).map(item -> {
                    InvoiceDTO invoiceDTO = new InvoiceDTO();
                    invoiceDTO.setIdCustomer(item.getCustomer().getId());
                    invoiceDTO.setId(item.getId());
                    invoiceDTO.setCreatedAt(item.getCreatedAt());
                    invoiceDTO.setPaymentAmount(item.getPaymentAmount());
                    return invoiceDTO;
                }).toList();
    }

    @Override
    public List<InvoiceDTO> getByDate() {
        Timestamp timestampToday = Timestamp.valueOf(date.atStartOfDay());
        return invoiceRepositoryImp.findByDay(timestampToday).stream()
                .map(item -> {
                    InvoiceDTO invoiceDTO = new InvoiceDTO();
                    invoiceDTO.setIdCustomer(item.getCustomer().getId());
                    invoiceDTO.setId(item.getId());
                    invoiceDTO.setCreatedAt(item.getCreatedAt());
                    invoiceDTO.setPaymentAmount(item.getPaymentAmount());
                    return invoiceDTO;
                }).toList();
    }

    private void getWeekRange() {
        fromDate = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        toDate  = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
    }
}
