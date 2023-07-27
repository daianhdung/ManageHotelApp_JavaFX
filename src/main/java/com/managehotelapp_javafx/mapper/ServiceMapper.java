package com.managehotelapp_javafx.mapper;

import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.dto.ServiceDTO;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.entity.ServiceEntity;

import java.text.SimpleDateFormat;

public class ServiceMapper {

    public static ServiceDTO toServiceDTO(ServiceEntity item, ServiceDTO serviceDTO){
        serviceDTO.setId(item.getId());
        serviceDTO.setPrice(item.getPrice());
        serviceDTO.setDescription(item.getDescription());
        serviceDTO.setQuantity(item.getQuantity());

        return serviceDTO;
    }
}
