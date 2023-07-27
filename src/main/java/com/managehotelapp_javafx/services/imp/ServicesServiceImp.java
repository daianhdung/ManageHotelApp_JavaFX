package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.ServiceDTO;
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
import java.util.stream.Collectors;

import static com.managehotelapp_javafx.mapper.ServiceMapper.toServiceDTO;

public class ServicesServiceImp implements ServicesService {
    ServicesRepository servicesRepository = new ServicesRepositoryImp();

    @Override
    public List<ServiceDTO> getServicesList() {
        return servicesRepository.getServicesList().stream().map(item -> {
            ServiceDTO serviceDTO = new ServiceDTO();
            return toServiceDTO(item, serviceDTO);
        }).collect(Collectors.toList());
    }

    @Override
    public ServiceEntity findServicesById(int id) {
        return servicesRepository.findServicesById(id);
    }


}
