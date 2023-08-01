package com.managehotelapp_javafx.services;



import com.managehotelapp_javafx.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {

    List<InvoiceDTO> getInvoiceDTOList ();

    InvoiceDTO findInvoiceById(int idInv);

    int insertInvoiceDTO(InvoiceDTO user);

    boolean deleteInvoiceDTO(int idInvDTO);
    boolean updateInvoiceDTO(InvoiceDTO invoiceDTO);

    String getRevenueToday();

}
