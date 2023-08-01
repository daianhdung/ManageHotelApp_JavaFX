package com.managehotelapp_javafx.multithread;

import com.managehotelapp_javafx.dto.InvoiceDTO;
import com.managehotelapp_javafx.services.InvoiceService;
import com.managehotelapp_javafx.services.imp.InvoiceServiceImp;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class QueryRevenue extends Service<String> {

    InvoiceService invoiceService = new InvoiceServiceImp();
    @Override
    protected Task<String> createTask() {
        return new Task<>() {
            @Override
            protected String call() throws Exception {
                return invoiceService.getRevenueToday();
            }
        };
    }
}
