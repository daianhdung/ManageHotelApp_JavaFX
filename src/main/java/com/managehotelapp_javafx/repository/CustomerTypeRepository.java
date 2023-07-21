package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.entity.CustomerTypeEntity;

import java.util.List;

public interface CustomerTypeRepository extends GenericRepository<CustomerTypeEntity>{

    List<CustomerTypeEntity> getCustomerTypeList();
    CustomerTypeEntity findCustomerTypeById(int idCusType);
    CustomerTypeEntity findCustomerTypeByTitle(String cusType);
    boolean insertCustomerType(CustomerTypeEntity CustomerTypeEntity);
    boolean updateCustomerType(CustomerTypeEntity CustomerTypeEntity);
    boolean deleteCustomerType(int idCusType);





}
