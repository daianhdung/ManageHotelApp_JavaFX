package com.managehotelapp_javafx.services;




import com.managehotelapp_javafx.dto.InvoiceStatusDTO;

import java.util.List;

public interface InvoiceStatusService {
    List<InvoiceStatusDTO> invStatusDTOList ();
    InvoiceStatusDTO findInvStatusById(int idInvStatus);
    InvoiceStatusDTO findInvStatusByTitle(String invStatus);

    boolean insertInvStatus (InvoiceStatusDTO invoiceStatusDTO);
    boolean deleteInvStatus (int idInvStatus);
    boolean updateInvStatus (InvoiceStatusDTO invoiceStatusDTO);

}
