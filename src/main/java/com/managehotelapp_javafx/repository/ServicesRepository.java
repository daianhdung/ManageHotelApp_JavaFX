package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.ServiceEntity;
import com.managehotelapp_javafx.entity.UserStatusEntity;

import java.util.List;

public interface ServicesRepository extends GenericRepository<ServiceEntity> {
    List<ServiceEntity> getServicesList();
    ServiceEntity findServicesById(int idService);


    
}
