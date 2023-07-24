package com.managehotelapp_javafx.repository;


import com.managehotelapp_javafx.entity.InvoiceEntity;

import java.util.List;

public interface InvoiceRepository extends GenericRepository<InvoiceEntity>{
    List<InvoiceEntity> getInvoiceList();
    InvoiceEntity findInvById(int idInv);
    InvoiceEntity findInvByStatus (int invStatus);
    boolean insertInvoice (InvoiceEntity invoiceEntity);
    boolean updateInvoice (InvoiceEntity invoiceEntity);
    boolean deleteInvoice(int idInv);
}
