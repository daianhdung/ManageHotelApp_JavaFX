package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.repository.CustomerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositoryImp extends AbstractRepository<CustomerEntity> implements CustomerRepository {
    @Override
    public List<CustomerEntity> getCustomerList() {
        return query("FROM CustomerEntity", null) ;
    }

    @Override
    public CustomerEntity findCustomerById(int idCus) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id" , idCus);
        List<CustomerEntity> result = query("FROM CustomerEntity WHERE id = :id", parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public CustomerEntity findCustomerByType(String cusType) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", cusType);
        List<CustomerEntity> result = query("FROM CustomerEntity WHERE title = :title", parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public boolean insertCustomer(CustomerEntity customerEntity) {
        return insert(customerEntity);
    }

    @Override
    public boolean updateCustomer(CustomerEntity customerEntity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", customerEntity.getId());
        parameters.put("type_id", customerEntity.getCustomerType().getId());
        parameters.put("full_name", customerEntity.getFullName());
        parameters.put("dob", customerEntity.getDob());
        parameters.put("email", customerEntity.getEmail());
        parameters.put("gender", customerEntity.getGender());
        parameters.put("phone", customerEntity.getPhone());
        parameters.put("identity", customerEntity.getIdentity());
        parameters.put("passport_no", customerEntity.getPassportNo());
        parameters.put("address", customerEntity.getAddress());
        parameters.put("city", customerEntity.getCity());
        parameters.put("country", customerEntity.getCountry());
        parameters.put("booking_count", customerEntity.getBookingCount());
//        parameters.put("created_at", customerEntity.getCreatedAt());

        StringBuffer query = new StringBuffer("UPDATE CustomerEntity SET address = :address" +
                ", booking_count = :booking_count" +
                ", city = :city" +
                ", country = :country" +
//                ", created_at = :created_at" +
                ", dob = :dob" +
                ", email = :email" +
                ", full_name = :full_name" +
                ", gender = :gender" +
                ", identity = :identity" +
                ", passport_no = :passport_no" +
                ", phone = :phone" +
                ", type_id = :type_id" +
                " WHERE id= :id");

        return update(query.toString(),parameters);
    }

    @Override
    public boolean deleteCustomer(int idCus) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", idCus);
        return delete("DELETE CustomerEntity WHERE id = :id",parameters);
    }
}
