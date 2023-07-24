package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.repository.RoomRepository;
import com.managehotelapp_javafx.repository.imp.RoomRepositoryImp;
import com.managehotelapp_javafx.services.RoomService;

import java.util.List;
import java.util.Objects;
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
        return null;
    }

    public BookingDTO getBookingByRoomName(String roomName)
    {
      var bookingEntity =  bookingRepository.getBookingByRoomName(roomName);
        return bookingEntity.getDTO();
    }
}
