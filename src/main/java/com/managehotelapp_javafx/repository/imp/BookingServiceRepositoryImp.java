package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.BookingServiceEntity;
import com.managehotelapp_javafx.entity.UserStatusEntity;
import com.managehotelapp_javafx.repository.BookingServiceRepository;
import com.managehotelapp_javafx.repository.UserStatusRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingServiceRepositoryImp extends AbstractRepository<BookingServiceEntity> implements BookingServiceRepository {

    @Override
    public List<BookingServiceEntity> getBookingServiceList() {
        return query("FROM BookingServiceEntity ",null);
    }

    @Override
    public List<BookingServiceEntity> getBookingServiceListByBookingId(int id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("booking_room_id", id);
        List<BookingServiceEntity> result = query("FROM BookingServiceEntity WHERE booking_room_id = :booking_room_id",parameters);
        return result;
    }

    @Override
    public BookingServiceEntity findBookingServiceByIdBookingRoom(int id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        List<BookingServiceEntity> result = query("FROM BookingServiceEntity WHERE id = :id",parameters);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public BookingServiceEntity findBookingServiceByServiceId(String userStatus) {
        return null;
    }

    @Override
    public List<BookingServiceEntity> findBookingServiceByServiceId(int id) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("service_id", id);
        List<BookingServiceEntity> result = query("FROM BookingServiceEntity WHERE service_id = :service_id",parameters);
        return result.isEmpty() ? null : result;

    }

    @Override
    public boolean deleteBookingService(BookingServiceEntity bookingServiceEntity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("booking_room_id", bookingServiceEntity.getBookingRoomId());
        parameters.put("service_id", bookingServiceEntity.getServiceId());
        return delete("DELETE FROM BookingServiceEntity WHERE booking_room_id = :booking_room_id AND service_id = :service_id", parameters);
    }
}
