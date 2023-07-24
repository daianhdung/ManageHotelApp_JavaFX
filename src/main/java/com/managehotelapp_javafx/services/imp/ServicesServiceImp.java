package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.UserRoleDTO;
import com.managehotelapp_javafx.entity.ServiceEntity;
import com.managehotelapp_javafx.entity.UserStatusEntity;
import com.managehotelapp_javafx.repository.ServicesRepository;
import com.managehotelapp_javafx.repository.UserStatusRepository;
import com.managehotelapp_javafx.repository.imp.ServicesRepositoryImp;
import com.managehotelapp_javafx.repository.imp.UserStatusRepositoryImp;
import com.managehotelapp_javafx.services.ServicesService;
import com.managehotelapp_javafx.services.UserStatusService;

import java.util.ArrayList;
import java.util.List;

public class ServicesServiceImp implements ServicesService {
    ServicesRepository servicesRepository = new ServicesRepositoryImp();

    @Override
    public List<ServiceEntity> getServicesList() {
        return servicesRepository.getServicesList();
    }

    @Override
    public ServiceEntity findServicesById(int id) {
        return servicesRepository.findServicesById(id);
    }


}
