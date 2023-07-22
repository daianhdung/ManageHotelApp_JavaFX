package com.managehotelapp_javafx.repository;


import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface CustomerRepository extends GenericRepository<CustomerEntity>{

     List<CustomerEntity> getCustomers() ;

     CustomerEntity getCustomerById(int id);

    CustomerEntity getCustomerByName(String name);

     List<CustomerEntity> getCustomerList();
    CustomerEntity findCustomerById(int idCus);
    CustomerEntity findCustomerByType(String cusType);
    boolean insertCustomer(CustomerEntity customerEntity);
    boolean updateCustomer(CustomerEntity customerEntity);
    boolean deleteCustomer(int idCus);

}
