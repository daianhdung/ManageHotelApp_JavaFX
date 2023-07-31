package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.dto.BookingServiceDTO;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.entity.BookingServiceEntity;
import com.managehotelapp_javafx.repository.BookingRoomRepository;
import com.managehotelapp_javafx.repository.BookingServiceRepository;
import com.managehotelapp_javafx.repository.imp.BookingRoomRepositoryImp;
import com.managehotelapp_javafx.repository.imp.BookingServiceRepositoryImp;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.BookingServicesService;
import com.managehotelapp_javafx.utils.constant.DateFormatConstant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.managehotelapp_javafx.mapper.BookingServiceMapper.toBookingServiceEntity;

public class BookingServicesServiceImp implements BookingServicesService {
    BookingServiceRepository bookingServiceRepository = new BookingServiceRepositoryImp();
    BookingRoomRepository bookingRoomRepository = new BookingRoomRepositoryImp();

    @Override
    public boolean insertBookingService(List<BookingServiceDTO> bookingServiceDTOList, List<BookingServiceDTO> bookingServiceDTOListDelete, int orderFee) {
        try {
            deleteBookingService(bookingServiceDTOListDelete);
            bookingServiceDTOList.forEach(item -> {
                BookingServiceEntity bookingServiceEntity = new BookingServiceEntity();
                bookingServiceRepository.save(toBookingServiceEntity(bookingServiceEntity, item));
            });
            BookingRoomEntity bookingRoomEntity = bookingRoomRepository.findById(bookingServiceDTOList.get(0).getBookingRoomId());
            bookingRoomEntity.setPayment(orderFee);
            bookingRoomRepository.save(bookingRoomEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BookingServiceDTO> findBooingServicesByBookingRoomId(int id) {
        List<BookingServiceDTO> list = new ArrayList<>();

        bookingServiceRepository.getBookingServiceListByBookingId(id).forEach(bookingServiceEntity -> {
            BookingServiceDTO bookingServiceDTO = new BookingServiceDTO();
            bookingServiceDTO.setServiceId(bookingServiceEntity.getServiceId());
            bookingServiceDTO.setQuantity(bookingServiceEntity.getQuantity());

            bookingServiceDTO.setServiceName(bookingServiceEntity.getService().getDescription());
            bookingServiceDTO.setPrice(bookingServiceEntity.getService().getPrice());
            bookingServiceDTO.setTotal(bookingServiceEntity.getTotal());

            String formattedDateTime =
                    new SimpleDateFormat(DateFormatConstant.DATETIME_FORMAT_PATTERN)
                    .format(bookingServiceEntity.getCreatedAt());
            bookingServiceDTO.setOrderDate(formattedDateTime);
            bookingServiceDTO.setServiceId(bookingServiceEntity.getServiceId());
            bookingServiceDTO.setBookingRoomId(bookingServiceEntity.getBookingRoomId());
            bookingServiceDTO.setRoomNo(bookingServiceEntity.getBookingRoom().getRoom().getRoomNumber());

            list.add(bookingServiceDTO);
        });

        return list;
    }

    @Override
    public boolean deleteBookingService(List<BookingServiceDTO> bookingService) {
        try{
            bookingService.forEach(item -> {
                BookingServiceEntity bookingServiceEntity = new BookingServiceEntity();
                bookingServiceEntity.setBookingRoomId(item.getBookingRoomId());
                bookingServiceEntity.setServiceId(item.getServiceId());
                bookingServiceRepository.deleteBookingService(bookingServiceEntity);
            });
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
