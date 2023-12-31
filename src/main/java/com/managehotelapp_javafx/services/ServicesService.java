package com.managehotelapp_javafx.services;

import com.managehotelapp_javafx.dto.ServiceDTO;
import com.managehotelapp_javafx.dto.UserRoleDTO;
import com.managehotelapp_javafx.entity.ServiceEntity;
import com.managehotelapp_javafx.entity.UserStatusEntity;

import java.util.List;

public interface ServicesService {

    List<ServiceDTO> getServicesList();

    ServiceDTO findServicesById(int id);




}
