package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.InvoiceStatusEntity;
import com.managehotelapp_javafx.repository.InvoiceStatusRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceStatusRepositoryImp extends AbstractRepository<InvoiceStatusEntity> implements InvoiceStatusRepository {
    @Override
    public List<InvoiceStatusEntity> getInvStatusList() {
        return query("FROM InvoiceStatusEntity ", null);
    }

    @Override
    public InvoiceStatusEntity findInvStatusById(int idInvStatus) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", idInvStatus);
        List<InvoiceStatusEntity> result = query("FROM InvoiceStatusEntity WHERE id = :id",parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public InvoiceStatusEntity findInvStatusByTitle(String invStatus) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", invStatus);
        List<InvoiceStatusEntity> result = query("FROM InvoiceStatusEntity WHERE title = :title",parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public boolean insertInvStatus(InvoiceStatusEntity invoiceStatusEntity) {
        return false;
    }

    @Override
    public boolean updateInvStatus(InvoiceStatusEntity invoiceStatusEntity) {
        return false;
    }

    @Override
    public boolean deleteInvStatus(int idInvStatus) {
        return false;
    }


}
