package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.entity.UserEntity;
import com.managehotelapp_javafx.repository.CustomerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CustomerRepositoryImp extends AbstractRepository<CustomerEntity> implements CustomerRepository {

    public CustomerEntity getCustomerByPID(String id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("identity", id);
        List<CustomerEntity> result = query("FROM CustomerEntity WHERE identity = :identity", parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    public boolean updateCustomerStatus(CustomerEntity customerEntity)
    {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", customerEntity.getId());
        parameters.put("customer", customerEntity.getBookingEntities());

        StringBuffer query = new StringBuffer("UPDATE CustomerEntity SET " +
                ", customer = :customer" +
                " WHERE id = :id");

        return super.update(query.toString(),parameters);
    }

    @Override
    public List<CustomerEntity> getCustomers() {
        return query("FROM CustomerEntity", null);
    }

    @Override
    public CustomerEntity getCustomerById(int id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        List<CustomerEntity> result = query("FROM CustomerEntity WHERE id = :id", parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public CustomerEntity getCustomerByName(String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        List<CustomerEntity> result = query("FROM CustomerEntity WHERE full_name = :name", parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public boolean update(CustomerEntity customerEntity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", customerEntity.getId());
        parameters.put("name", customerEntity.getAddress());
        parameters.put("created_at", customerEntity.getCreatedAt());
        parameters.put("identity", customerEntity.getIdentity());
        parameters.put("booking_count", customerEntity.getBookingCount());
        parameters.put("identity", customerEntity.getIdentity());
        parameters.put("passport_no", customerEntity.getPassportNo());
        parameters.put("phone", customerEntity.getPhone());
        parameters.put("email", customerEntity.getEmail());
        parameters.put("type_id", customerEntity.getCustomerType().getId());


        StringBuffer query = new StringBuffer("UPDATE UserEntity SET " +
                ", created_at = :created_at" +
                ", email = :email" +
                ", fullname = :fullname" +
                ", booking_count = :booking_count" +
                ", gender = :gender" +
                ", passport_no = :passport_no" +
                ", phone = :phone" +
                ", email = :email" +
                ", type_id = :type_id" +
                " WHERE id = :id");

        return update(query.toString(),parameters);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
    public boolean updateBooKing(CustomerEntity customerEntity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", customerEntity.getId());
        parameters.put("name", customerEntity.getAddress());
        parameters.put("created_at", customerEntity.getCreatedAt());
        parameters.put("identity", customerEntity.getIdentity());
        parameters.put("booking_count", customerEntity.getBookingCount());
        parameters.put("identity", customerEntity.getIdentity());
        parameters.put("passport_no", customerEntity.getPassportNo());
        parameters.put("phone", customerEntity.getPhone());
        parameters.put("email", customerEntity.getEmail());
        parameters.put("type_id", customerEntity.getCustomerType().getId());


        StringBuffer query = new StringBuffer("UPDATE UserEntity SET " +
                ", created_at = :created_at" +
                ", email = :email" +
                ", fullname = :fullname" +
                ", booking_count = :booking_count" +
                ", gender = :gender" +
                ", passport_no = :passport_no" +
                ", phone = :phone" +
                ", email = :email" +
                ", type_id = :type_id" +
                " WHERE id = :id");

        return update(query.toString(),parameters);
    }

}
