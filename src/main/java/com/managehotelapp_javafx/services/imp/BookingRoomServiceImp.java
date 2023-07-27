package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.entity.BookingEntity;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.repository.BookingRepository;
import com.managehotelapp_javafx.repository.BookingRoomRepository;
import com.managehotelapp_javafx.repository.CustomerRepository;
import com.managehotelapp_javafx.repository.StatusBookingRepository;
import com.managehotelapp_javafx.repository.imp.BookingRepositoryImp;
import com.managehotelapp_javafx.repository.imp.BookingRoomRepositoryImp;
import com.managehotelapp_javafx.repository.imp.CustomerRepositoryImp;
import com.managehotelapp_javafx.repository.imp.StatusBookingRepositoryImp;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.RoomService;
import com.managehotelapp_javafx.utils.enumpackage.BookingStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.managehotelapp_javafx.mapper.BookingRoomMapper.toBookingRoomDTO;
import static com.managehotelapp_javafx.utils.constant.StatusConstant.*;

public class BookingRoomServiceImp implements BookingRoomService {

    BookingRoomRepository bookingRoomRepository = new BookingRoomRepositoryImp();
    StatusBookingRepository statusBookingRepository = new StatusBookingRepositoryImp();
    CustomerRepository customerRepository = new CustomerRepositoryImp();

    BookingRepository bookingRepository = new BookingRepositoryImp();
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
        bookingRoomDTO.setId(bookingRoom.getId());
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
        bookingRoomDTO.setIdentity(bookingRoom.getBooking().getCustomer().getIdentity());
        bookingRoomDTO.setBookingAgent(bookingRoom.getBooking().getUserEntity().getFullName());
        bookingRoomDTO.setTotalExpenses(bookingRoom.getPayment());
        bookingRoomDTO.setDeposit(bookingRoom.getBooking().getDeposit());

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

    @Override
    public boolean updateBookingRoom(BookingRoomDTO bookingRoomDTO) {
        try{
            BookingRoomEntity bookingRoomEntity = bookingRoomRepository.findById(bookingRoomDTO.getId());
            if(bookingRoomDTO.getStatus().toUpperCase().equals(BookingStatus.CHECKED_IN.toString())){
                bookingRoomEntity.setStatusBooking(statusBookingRepository.findByIdStatus(BOOKING_CHECKIN));
            }
            //Update customer info in booking Detail
            CustomerEntity customerEntity = customerRepository.findCustomerById(bookingRoomEntity.getBooking().getCustomer().getId());
            customerEntity.setFullName(bookingRoomDTO.getCustomerName());
            customerEntity.setIdentity(bookingRoomDTO.getIdentity());
            customerEntity.setPhone(bookingRoomDTO.getPhoneNumber());
            customerEntity.setEmail(bookingRoomDTO.getEmail());
            customerRepository.update(customerEntity);
            //Update booking info in booking Detail
            BookingEntity bookingEntity = bookingRepository.findBookingById(bookingRoomEntity.getBooking().getId());
            bookingEntity.setNumAdult(bookingRoomDTO.getNumAdult());
            bookingEntity.setNumChildren(bookingRoomDTO.getNumChildren());
            bookingEntity.setDeposit(bookingRoomDTO.getDeposit());
            bookingEntity.setSpecialRequest(bookingRoomDTO.getSpecialRequest());
            bookingRepository.update(bookingEntity);

            bookingRoomRepository.update(bookingRoomEntity);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
