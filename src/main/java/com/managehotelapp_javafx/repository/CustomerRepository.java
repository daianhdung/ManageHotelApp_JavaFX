package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepository extends GenericRepository<CustomerEntity>{

    List<CustomerEntity> getCustomerList();
    CustomerEntity findCustomerById(int idCus);
    CustomerEntity findCustomerByType(String cusType);
    boolean insertCustomer(CustomerEntity customerEntity);
    boolean updateCustomer(CustomerEntity customerEntity);
    boolean deleteCustomer(int idCus);





}
