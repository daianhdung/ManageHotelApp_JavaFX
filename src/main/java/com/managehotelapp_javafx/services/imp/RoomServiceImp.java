package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.repository.RoomRepository;
import com.managehotelapp_javafx.repository.imp.RoomRepositoryImp;
import com.managehotelapp_javafx.services.RoomService;

import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImp implements RoomService {
    RoomRepository roomRepository = new RoomRepositoryImp();

    @Override
    public List<RoomDTO> getAllRoom() {
        return roomRepository.findAll().stream().map(item -> {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setId(item.getId());
            roomDTO.setRoomNo(item.getRoomNumber());
            roomDTO.setStatus(item.getRoomStatus().getTitle());
            roomDTO.setType(item.getRoomType().getId()+"");
            return roomDTO;
        }).toList();
    }

    @Override
    public RoomDTO getRoomById(int id) {
        RoomEntity roomEntity = roomRepository.findById(id);
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(roomEntity.getId());
        roomDTO.setRoomNo(roomEntity.getRoomNumber());
        roomDTO.setStatus(roomEntity.getRoomStatus().getTitle());
        roomDTO.setType(roomEntity.getRoomType().getId()+"");

        return roomDTO;
    }



}