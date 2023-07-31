package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.ServiceDTO;
import com.managehotelapp_javafx.dto.UserRoleDTO;
import com.managehotelapp_javafx.entity.ServiceEntity;
import com.managehotelapp_javafx.entity.UserStatusEntity;
import com.managehotelapp_javafx.repository.BookingServiceRepository;
import com.managehotelapp_javafx.repository.ServicesRepository;
import com.managehotelapp_javafx.repository.UserStatusRepository;
import com.managehotelapp_javafx.repository.imp.ServicesRepositoryImp;
import com.managehotelapp_javafx.repository.imp.UserStatusRepositoryImp;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.BookingService;
import com.managehotelapp_javafx.services.ServicesService;
import com.managehotelapp_javafx.services.UserStatusService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.managehotelapp_javafx.mapper.ServiceMapper.toServiceDTO;

public class ServicesServiceImp implements ServicesService {
    ServicesRepository servicesRepository = new ServicesRepositoryImp();
    BookingService bookingService = new BookingServiceImp();


    @Override
    public List<ServiceDTO> getServicesList() {
        return servicesRepository.getServicesList().stream().map(item -> {
            ServiceDTO serviceDTO = new ServiceDTO(item.getId(),item.getQuantity(),item.getPrice(), item.getDescription());
            return toServiceDTO(item, serviceDTO);
        }).collect(Collectors.toList());
    }

    @Override
    public ServiceDTO findServicesById(int id) {
        ServiceEntity serviceEntity = servicesRepository.findServicesById(id);


        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setPrice(serviceEntity.getPrice());
        serviceDTO.setDescription(serviceEntity.getDescription());
        serviceDTO.setQtyConsumed(bookingService.findBookingServicesBySerId(id).getQtyConsumed());




        return serviceDTO;
    }


}
