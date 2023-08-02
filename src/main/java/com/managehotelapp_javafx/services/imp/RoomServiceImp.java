package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.entity.RoomStatusEntity;
import com.managehotelapp_javafx.repository.RoomRepository;
import com.managehotelapp_javafx.repository.imp.BookingRepositoryImp;
import com.managehotelapp_javafx.repository.imp.RoomRepositoryImp;
import com.managehotelapp_javafx.repository.imp.RoomStatusRepositoryImp;
import com.managehotelapp_javafx.repository.imp.RoomTypeRepositoryImp;
import com.managehotelapp_javafx.services.RoomService;
import com.managehotelapp_javafx.utils.enumpackage.RoomStatus;

import java.util.*;
import java.util.stream.Collectors;

public class RoomServiceImp implements RoomService {
    RoomRepositoryImp roomRepository = new RoomRepositoryImp();
    BookingRepositoryImp bookingRepository = new BookingRepositoryImp();
    @Override
    public List<RoomDTO> getAllRoom() {
        return roomRepository.getRooms().stream().filter(item->item.getRoomStatus()!=null).map(item -> {
            RoomDTO roomDTO = new RoomDTO();
            if(item.getRoomStatus()!=null){
            roomDTO.setId(item.getId());
            roomDTO.setRoomNo(item.getRoomNumber());
            roomDTO.setStatus(item.getRoomStatus().getTitle());
            roomDTO.setType(item.getRoomType().getDescription());
            }
            return roomDTO;
        }).toList();
    }

    @Override
    public List<RoomDTO> getAvailableRoom() {
        return roomRepository.getAvailableRooms().stream().map(item -> {
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setId(item.getId());
                roomDTO.setRoomNo(item.getRoomNumber());
                roomDTO.setStatus(item.getRoomStatus().getTitle());
                roomDTO.setType(item.getRoomType().getDescription());
                return roomDTO;
        }).toList();
    }

    @Override
    public List<RoomDTO> getUnavailableRoom() {
        return roomRepository.getUnavailableRooms().stream().map(item -> {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setId(item.getId());
            roomDTO.setRoomNo(item.getRoomNumber());
            roomDTO.setStatus(item.getRoomStatus().getTitle());
            roomDTO.setType(item.getRoomType().getDescription());
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
        roomDTO.setType(roomEntity.getRoomType().getDescription());

        return roomDTO;
    }

    public List<RoomDTO> roomSearchService(String[] searchText)
    {
        List<RoomEntity> result = new ArrayList<>();
        try {
            result = new RoomRepositoryImp().findAll().stream()
                    .filter(f -> f.getRoomNumber().startsWith(searchText[0])).toList();
        }catch (Exception ignored){}

        if(result.size() == 0) {
            String type = searchText[0];
            String status = type.length() > 0 ? searchText[1] : searchText[0];

            if (type.length() > 0 && status.length() > 0)
                result = new RoomRepositoryImp().findAll().stream()
                        .filter(f -> f.getRoomStatus().getTitle().toLowerCase().equals(status.trim())
                                && f.getRoomType().getDescription().toLowerCase().startsWith(type.trim())).toList();
            else if (type.length() == 0) {
                result = new RoomRepositoryImp().findAll().stream()
                        .filter(f -> f.getRoomStatus().getTitle().toLowerCase().equals(status)).toList();
            }else if (status.length() == 0) {
                result = new RoomRepositoryImp().findAll().stream()
                        .filter(f -> f.getRoomType().getDescription().toLowerCase().startsWith(type)).toList();
            }
        }
        return result.stream().map(item->
        {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setId(item.getId());
            roomDTO.setRoomNo(item.getRoomNumber());
            roomDTO.setStatus(item.getRoomStatus().getTitle());
            roomDTO.setType(item.getRoomType().getDescription());
            return roomDTO;
        }).toList();
    }


}
