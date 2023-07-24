package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.repository.BookingRoomRepository;
import com.managehotelapp_javafx.repository.imp.BookingRoomRepositoryImp;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.RoomService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.managehotelapp_javafx.mapper.BookingRoomMapper.toBookingRoomDTO;
import static com.managehotelapp_javafx.utils.constant.StatusConstant.*;

public class BookingRoomServiceImp implements BookingRoomService {

    BookingRoomRepository bookingRoomRepository = new BookingRoomRepositoryImp();
    RoomService roomService = new RoomServiceImp();

    @Override
    public List<BookingRoomDTO> getAllBookingsRoom() {
        List<BookingRoomDTO> bookingRoomDTOList = new ArrayList<>();
        bookingRoomRepository.findAll().forEach(item -> {
            bookingRoomDTOList.add(toBookingRoomDTO(item));
        });
        return bookingRoomDTOList;
    }

    @Override
    public BookingRoomDTO getBookingRoomById(int id) {
        BookingRoomEntity bookingRoom = bookingRoomRepository.findById(id);
        BookingRoomDTO bookingRoomDTO = new BookingRoomDTO();
        bookingRoomDTO.setRoomNo(bookingRoom.getRoom().getRoomNumber());
        bookingRoomDTO.setCustomerName(bookingRoom.getBooking().getCustomer().getFullName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateFormat.format(bookingRoom.getBooking().getBookingDate());
        bookingRoomDTO.setBookingDate(formattedDateTime);

        if (bookingRoom.getBooking().getActualDateIn() != null) {
            formattedDateTime = dateFormat.format(bookingRoom.getBooking().getActualDateIn());
            bookingRoomDTO.setCheckinDate(formattedDateTime);
        }

        if (bookingRoom.getBooking().getActualDateOut() != null) {
            formattedDateTime = dateFormat.format(bookingRoom.getBooking().getActualDateOut());
            bookingRoomDTO.setCheckoutDate(formattedDateTime);
        }
        bookingRoomDTO.setStatus(bookingRoom.getStatusBooking().getTitle());
        bookingRoomDTO.setNumAdult(bookingRoom.getBooking().getNumAdult());
        bookingRoomDTO.setNumChildren(bookingRoom.getBooking().getNumChildren());
        bookingRoomDTO.setEmail(bookingRoom.getBooking().getCustomer().getEmail());
        bookingRoomDTO.setPhoneNumber(bookingRoom.getBooking().getCustomer().getPhone());
        bookingRoomDTO.setSpecialRequest(bookingRoom.getBooking().getSpecialRequest());

        return bookingRoomDTO;
    }

    @Override
    public List<BookingRoomDTO> getCurrentBookingRoom() {
        List<BookingRoomDTO> bookingRoomDTOList = new ArrayList<>();
        Integer[] listInteger = {BOOKING_RESERVED, BOOKING_CHECKIN};
        bookingRoomRepository.findByStatusBookingIdIsIn(Arrays.asList(listInteger)).forEach(item -> {
            bookingRoomDTOList.add(toBookingRoomDTO(item));
        });
        return bookingRoomDTOList;
    }

    @Override
    public List<BookingRoomDTO> getBookingRoomHistory() {
        List<BookingRoomDTO> bookingRoomDTOList = new ArrayList<>();
        Integer[] StatusIdList = {BOOKING_CHECKOUT, BOOKING_CANCELLED, BOOKING_NO_SHOW};
        bookingRoomRepository.findByStatusBookingIdIsIn(Arrays.asList(StatusIdList)).forEach(item -> {
            bookingRoomDTOList.add(toBookingRoomDTO(item));
        });
        return bookingRoomDTOList;
    }

    public BookingRoomEntity getBookingRoomByIdInvoice(int idInv) {

        return bookingRoomRepository.findByIdInvoice(idInv);
    }


    @Override
    public List<RoomDTO> getRoomByIdBooking(int idBooking) {
        List<RoomDTO> roomDTOList = new ArrayList<>();
       List<BookingRoomEntity> bookingRoomEntityList=  bookingRoomRepository.findRoomByIdBooking(idBooking);
        bookingRoomEntityList.forEach(bookingRoomEntity -> {
            RoomDTO roomDTO = roomService.getRoomById(bookingRoomEntity.getRoom().getId());

            roomDTOList.add(roomDTO);
        });

        return roomDTOList;
    }
}
