package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface CustomerRepository extends GenericRepository<CustomerEntity>{

     List<CustomerEntity> getCustomers() ;

     CustomerEntity getCustomerById(int id);

    CustomerEntity getCustomerByName(String name);

     boolean insert(CustomerEntity customerEntity);

     boolean update(CustomerEntity customerEntity) ;

     boolean delete(int id) ;
}
