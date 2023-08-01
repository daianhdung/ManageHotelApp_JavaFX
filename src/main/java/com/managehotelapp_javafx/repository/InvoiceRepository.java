package com.managehotelapp_javafx.repository;


import com.managehotelapp_javafx.entity.InvoiceEntity;

import java.sql.Timestamp;
import java.util.List;

public interface InvoiceRepository extends GenericRepository<InvoiceEntity>{
    List<InvoiceEntity> getInvoiceList();
    InvoiceEntity findInvById(int idInv);

    List<InvoiceEntity> findByDay(Timestamp timestamp);
    InvoiceEntity findInvByStatus (int invStatus);
    int insertInvoice (InvoiceEntity invoiceEntity);
    boolean updateInvoice (InvoiceEntity invoiceEntity);
    boolean deleteInvoice(int idInv);
}
