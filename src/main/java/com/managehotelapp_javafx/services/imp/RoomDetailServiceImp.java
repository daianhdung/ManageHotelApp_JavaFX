package com.managehotelapp_javafx.services.imp;
import com.managehotelapp_javafx.dto.*;
import com.managehotelapp_javafx.entity.*;
import com.managehotelapp_javafx.repository.BookingRepository;
import com.managehotelapp_javafx.repository.CustomerRepository;
import com.managehotelapp_javafx.repository.RoomRepository;
import com.managehotelapp_javafx.repository.imp.BookingRepositoryImp;
import com.managehotelapp_javafx.repository.imp.CustomerRepositoryImp;
import com.managehotelapp_javafx.repository.imp.RoomRepositoryImp;
import com.managehotelapp_javafx.services.RoomDetailService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class RoomDetailServiceImp implements RoomDetailService{
    RoomRepositoryImp roomRepository = new RoomRepositoryImp();
    BookingRepositoryImp bookingRepository = new BookingRepositoryImp() ;
    CustomerRepositoryImp customerRepository = new CustomerRepositoryImp();
    private CustomerEntity customer ;
    private List<RoomEntity> roomEntityList = new ArrayList<>();
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
    @Override
    public boolean checkIn(BookingDTO bookingDTO) {
        for (RoomEntity r : roomEntityList)
        {
            roomRepository.updateCheckInRoom(r);
        }
        if(customer==null) {
            customer = new CustomerEntity();
            customer.setFullName(bookingDTO.getCustomerName());
            customer.setIdentity(bookingDTO.getCustomerIDN());
            customer.setPhone(bookingDTO.getPhoneNumber());
            customerRepository.insertCustomer(customer);
            customer = getCustomerByIDN(bookingDTO.getCustomerIDN());
        }

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
            bookingRepository.insert(bookingEntity);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //customerRepository.updateCustomerStatus(customer);
        return false;
    }
}
