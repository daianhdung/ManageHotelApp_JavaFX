package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.dto.RoomTypeDTO;
import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.entity.RoomTypeEntity;
import com.managehotelapp_javafx.repository.RoomRepository;
import com.managehotelapp_javafx.repository.RoomTypeRepository;
import com.managehotelapp_javafx.repository.imp.RoomRepositoryImp;
import com.managehotelapp_javafx.repository.imp.RoomTypeRepositoryImp;
import com.managehotelapp_javafx.services.RoomService;
import com.managehotelapp_javafx.services.RoomTypeService;

import java.util.List;

public class RoomTypeServiceImp implements RoomTypeService {
    RoomTypeRepository roomTypeRepository = new RoomTypeRepositoryImp();

    @Override
    public List<RoomTypeDTO> getAllRoom() {
        return roomTypeRepository.findAll().stream().map(item -> {
            RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
            roomTypeDTO.setId(item.getId());
            roomTypeDTO.setPrice(item.getPrice());
            return roomTypeDTO;
        }).toList();
    }

    @Override
    public RoomTypeDTO getRoomById(int id) {
        RoomTypeEntity roomTypeEntity = roomTypeRepository.findById(id);
        RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
        roomTypeDTO.setId(roomTypeEntity.getId());
        roomTypeDTO.setPrice(roomTypeEntity.getPrice());
        roomTypeDTO.setDescription(roomTypeEntity.getDescription());


        return roomTypeDTO;
    }
}
