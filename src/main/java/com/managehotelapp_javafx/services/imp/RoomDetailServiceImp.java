package com.managehotelapp_javafx.services.imp;
import com.managehotelapp_javafx.dto.*;
import com.managehotelapp_javafx.entity.*;
import com.managehotelapp_javafx.repository.FacilityRepository;
import com.managehotelapp_javafx.repository.RoomFacilityRepository;
import com.managehotelapp_javafx.repository.imp.*;
import com.managehotelapp_javafx.services.RoomDetailService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RoomDetailServiceImp implements RoomDetailService{
    RoomRepositoryImp roomRepository = new RoomRepositoryImp();
    RoomFacilityRepository roomFacilityRepository = new RoomFacilityRepositoryImp();

    private List<RoomEntity> roomEntityList = new ArrayList<>();
    private RoomEntity roomEntity = null;



    @Override
    public List<RoomEntity> getRoomListByNames(Set<String> names)
    {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        for (String name : names)
        {
            roomEntityList.add(roomRepository.getRoomByRoomName(name));
        }
        return this.roomEntityList = roomEntityList;
    }

    public RoomEntity getRoomByName(String roomName)
    {
        return roomEntity = roomRepository.getRoomByRoomName(roomName);
    }

    public void get()
    {
        var g = roomFacilityRepository.findAll();
        return;
    }

    public List<RoomFacilitiesDTO>  getFacilities(){
        return roomFacilityRepository.findAll().stream()
                .filter(f -> f.getRoomId() == roomEntity.getId())
                .map(item -> {
                    var fr = new FacilityRepositoryImp().findById(item.getFacilityId());
                    RoomFacilitiesDTO roomFacilitiesDTO =  new RoomFacilitiesDTO();
                    roomFacilitiesDTO.setId(item.getFacilityId());
                    roomFacilitiesDTO.setQuantity(item.getNumber());
                    roomFacilitiesDTO.setName(fr.getName());
                    roomFacilitiesDTO.setDescription(fr.getDescription());
                    return roomFacilitiesDTO;
                }).toList();
    }

}
