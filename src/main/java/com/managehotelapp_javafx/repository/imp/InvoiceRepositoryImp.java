package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.InvoiceEntity;
import com.managehotelapp_javafx.entity.UserEntity;
import com.managehotelapp_javafx.repository.InvoiceRepository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceRepositoryImp extends AbstractRepository<InvoiceEntity> implements InvoiceRepository {
    @Override
    public List<InvoiceEntity> getInvoiceList() {
        return query("FROM InvoiceEntity" ,null);
    }

    @Override
    public InvoiceEntity findInvById(int idInv) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", idInv);
        List<InvoiceEntity> result = query("FROM InvoiceEntity WHERE id = :id", parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<InvoiceEntity> findByDay(Timestamp timestamp) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("day", timestamp);
        List<InvoiceEntity> result = query("FROM InvoiceEntity WHERE DATE(created_at) = :day", parameters);
        return result.isEmpty() ? null : result;
    }

    @Override
    public InvoiceEntity findInvByStatus(int invStatus) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("invoice_status_id", invStatus);
        List<InvoiceEntity> result = query("FROM InvoiceEntity WHERE invoice_status_id = :invoice_status_id", parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public int insertInvoice(InvoiceEntity invoiceEntity) {
        return saveAndReturnId(invoiceEntity);
    }

    @Override
    public boolean updateInvoice(InvoiceEntity invoiceEntity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", invoiceEntity.getId());
        parameters.put("customer_id", invoiceEntity.getCustomer().getId());
        parameters.put("checkout_late", invoiceEntity.isCheckoutLate());
        parameters.put("payment_amount", invoiceEntity.getPaymentAmount());
        parameters.put("payment_date", invoiceEntity.getPaymentDate());
        parameters.put("invoice_status_id", invoiceEntity.getInvoiceStatus().getId());
        parameters.put("created_at", invoiceEntity.getCreatedAt());


        StringBuffer query = new StringBuffer("UPDATE UserEntity SET customer_id = :customer_id" +
                ", checkout_late = :checkout_late" +
                ", payment_amount = :payment_amount" +
                ", payment_date = :payment_date" +
                ", invoice_status_id = :invoice_status_id" +
                ", created_at = :created_at" +
                " WHERE id = :id");

        return update(query.toString(),parameters);
    }

    @Override
    public boolean deleteInvoice(int idInv) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", idInv);

        return delete("DELETE FROM InvoiceEntity WHERE id = :id",parameters);
    }
}
