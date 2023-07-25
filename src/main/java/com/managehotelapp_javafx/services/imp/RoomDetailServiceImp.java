package com.managehotelapp_javafx.services.imp;
import com.managehotelapp_javafx.dto.*;
import com.managehotelapp_javafx.entity.*;
import com.managehotelapp_javafx.repository.imp.*;
import com.managehotelapp_javafx.services.RoomDetailService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RoomDetailServiceImp implements RoomDetailService{
    RoomRepositoryImp roomRepository = new RoomRepositoryImp();
    BookingRepositoryImp bookingRepository = new BookingRepositoryImp() ;
    CustomerRepositoryImp customerRepository = new CustomerRepositoryImp();
    BookingRoomRepositoryImp bookingRoomRepositoryImp = new BookingRoomRepositoryImp();
    private CustomerEntity customer ;
    private List<RoomEntity> roomEntityList = new ArrayList<>();
    private Set<BookingRoomEntity> bookingRoomEntities =  new HashSet<>();
    public CustomerEntity getCustomerByIDN(String idn)
    {
        return customer = customerRepository.getCustomerByPID(idn);
    }
//    public BookingEntity getBookingByCustomerId(CustomerEntity customerEntity)
//    {
//        return  bookingEntity = bookingRepository.getBookingByCustomer(customerEntity);
//    }
    public List<RoomEntity> getRoomListByNames(Set<String> names)
    {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        for (String name : names)
        {
            roomEntityList.add(roomRepository.getRoomByRoomName(name));
        }
        return this.roomEntityList = roomEntityList;
    }

    private BookingRoomEntity bookingRoomEntity = new BookingRoomEntity();
    private BookingEntity bookingEntity = new BookingEntity();

    public BookingDTO getRoomDetail(){
        RoomEntity roomEntity = roomEntityList.get(0);
        Optional<BookingRoomEntity> getBookingRoomEntity = bookingRoomRepositoryImp.findAll().stream()
                .filter(item -> Objects.equals(item.getRoom().getRoomNumber(), roomEntity.getRoomNumber())
        ).findFirst();
        getBookingRoomEntity.ifPresent(object -> bookingRoomEntity = object);
        var bookingEntity = bookingRepository.findAllBooking().stream().filter(f -> f.getId() == bookingRoomEntity.getBooking().getId()).findFirst();
        bookingEntity.ifPresent(object -> this.bookingEntity = object);
        var item =  this.bookingEntity;
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setPhoneNumber(item.getCustomer().getPhone());
        bookingDTO.setPhoneNumber(item.getCustomer().getEmail());
//        bookingDTO.setStatus(item.getStatusBooking().getTitle());
        bookingDTO.setStatus("Reserved");
        bookingDTO.setCustomerIDN(item.getCustomer().getIdentity());
        bookingDTO.setCustomerName(item.getCustomer().getFullName());
        bookingDTO.setCheckInDate(item.getEstimateDateIn().toString());
        bookingDTO.setCheckOutDate(item.getEstimateDateOut().toString());
        bookingDTO.setBookingDate(item.getBookingDate().toString());
        return  bookingDTO;
    }
    @Override
    public boolean checkIn(BookingDTO bookingDTO) {
        if(customer==null) {
            customer = new CustomerEntity();
            customer.setFullName(bookingDTO.getCustomerName());
            customer.setIdentity(bookingDTO.getCustomerIDN());
            customer.setPhone(bookingDTO.getPhoneNumber());
            customerRepository.insertCustomer(customer);
            customer = getCustomerByIDN(bookingDTO.getCustomerIDN());
        }
        StatusBookingRepositoryImp statusBookingRepository = new StatusBookingRepositoryImp();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BookingEntity bookingEntity =  new BookingEntity();
        try {
            Date date1 = dateFormat.parse(bookingDTO.getBookingDate()+ " 00:00:00");
            Date date2 = dateFormat.parse(bookingDTO.getCheckinDate()+ " 00:00:00");
            Date date3 = dateFormat.parse(bookingDTO.getCheckOutDate()+ " 00:00:00");
            bookingEntity.setBookingDate(new Timestamp(date1.getTime()));
            bookingEntity.setCustomer(customer);
            bookingEntity.setEstimateDateIn(new Timestamp(date2.getTime()));
            bookingEntity.setEstimateDateOut(new Timestamp(date3.getTime()));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for (RoomEntity r : roomEntityList)
        {
            BookingRoomEntity bookingRoomEntity = new BookingRoomEntity();
            bookingRoomEntity.setRoom(r);
            bookingRoomEntity.setBooking(bookingEntity);
            bookingRoomEntities.add(bookingRoomEntity);
            roomRepository.updateCheckInRoom(r);
        }
//        bookingEntity.setStatusBooking(statusBookingRepository.findById(1).get(0));
        bookingEntity.setBookingRoomEntities(bookingRoomEntities);
        bookingRepository.insert(bookingEntity);
        for (var r : roomEntityList)
        {
            BookingRoomEntity bookingRoomEntity = new BookingRoomEntity();
            bookingRoomEntity.setRoom(r);
            bookingRoomEntity.setBooking(bookingEntity);
            bookingRoomRepositoryImp.insert(bookingRoomEntity);
        }
        //customerRepository.updateCustomerStatus(customer);
        return false;
    }
}
