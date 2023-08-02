package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.InvoiceDTO;

import java.time.LocalDate;
import java.util.List;

public interface RevenueService {



    public List<InvoiceDTO> getToday();
    public List<InvoiceDTO>  getWeekly();
    public List<InvoiceDTO>  getMonthly();
    public List<InvoiceDTO>  getYearly();
    public List<InvoiceDTO>  getByDate();
}
