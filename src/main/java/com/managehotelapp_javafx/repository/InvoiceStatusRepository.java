package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.CustomerTypeEntity;
import com.managehotelapp_javafx.entity.InvoiceStatusEntity;

import java.util.List;

public interface InvoiceStatusRepository extends GenericRepository<InvoiceStatusEntity> {
    List<InvoiceStatusEntity> getInvStatusList();
    InvoiceStatusEntity findInvStatusById(int idInvStatus);
    InvoiceStatusEntity findInvStatusByTitle(String invStatus);
    boolean insertInvStatus(InvoiceStatusEntity invoiceStatusEntity);
    boolean updateInvStatus(InvoiceStatusEntity invoiceStatusEntity);
    boolean deleteInvStatus(int idInvStatus);
}
