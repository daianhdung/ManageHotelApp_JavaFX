package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.CustomerTypeEntity;
import com.managehotelapp_javafx.repository.CustomerTypeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerTypeRepositoryImp extends AbstractRepository<CustomerTypeEntity> implements CustomerTypeRepository {
    @Override
    public List<CustomerTypeEntity> getCustomerTypeList() {
        return query("FROM CustomerTypeEntity", null);
    }

    @Override
    public CustomerTypeEntity findCustomerTypeById(int idCusType) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", idCusType);
        List<CustomerTypeEntity> result = query("FROM CustomerTypeEntity WHERE id = :id",parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public CustomerTypeEntity findCustomerTypeByTitle(String cusType) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", cusType);
        List<CustomerTypeEntity> result = query("FROM CustomerTypeEntity WHERE title = :title",parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public boolean insertCustomerType(CustomerTypeEntity CustomerTypeEntity) {
        return false;
    }

    @Override
    public boolean updateCustomerType(CustomerTypeEntity CustomerTypeEntity) {
        return false;
    }

    @Override
    public boolean deleteCustomerType(int idCusType) {
        return false;
    }
}
