package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.BookingEntity;
import com.managehotelapp_javafx.repository.BookingRepository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRepositoryImp extends AbstractRepository<BookingEntity> implements BookingRepository {

    public boolean checkinBookingUpdate(BookingEntity bookingEntity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", bookingEntity.getId());
        parameters.put("status_booking_id", bookingEntity.getStatusBooking().getId());
        StringBuffer query = new StringBuffer("UPDATE BookingEntity SET "
                +", status_booking_id = :status_booking_id WHERE id = :id");
        return update(query.toString(),parameters);
    }

    @Override
    public List<BookingEntity> query(String hql, Map<String, Object> parameters) {
        return super.query(hql, parameters);
    }

    @Override
    public boolean insert(BookingEntity bookingEntity) {
        return super.insert(bookingEntity);
    }

    @Override
    public boolean update(BookingEntity bookingEntity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", bookingEntity.getUserEntity());
        parameters.put("customer_id", bookingEntity.getCustomer());
        parameters.put("booking_type", bookingEntity.getBookingType());
        parameters.put("estimate_date_in", bookingEntity.getEstimateDateIn());
        parameters.put("estimate_date_out", bookingEntity.getEstimateDateOut());
        parameters.put("actual_date_in", bookingEntity.getActualDateIn());
        parameters.put("actual_date_out", bookingEntity.getActualDateOut());
        parameters.put("num_adult", bookingEntity.getNumAdult());
        parameters.put("num_children", bookingEntity.getNumChildren());
        parameters.put("deposit", bookingEntity.getDeposit());
        parameters.put("special_request", bookingEntity.getSpecialRequest());
        parameters.put("status_booking_id", bookingEntity.getStatusBooking().getId());
        parameters.put("booking", bookingEntity.getBookingRoomEntities());
        parameters.put("created_at", bookingEntity.getBookingDate());
        StringBuffer query = new StringBuffer("UPDATE RoomEntity SET user_id = :user_id" +
                ", customer_id = :customer_id" +
                ", estimate_date_in = :estimate_date_in" +
                ", estimate_date_out = :estimate_date_out" +
                ", actual_date_in = :actual_date_in" +
                ", actual_date_out = :actual_date_out" +
                ", num_adult = :num_adult" +
                ", num_children = :num_children" +
                ", deposit = :deposit" +
                ", special_request = :special_request" +
                " WHERE id = :id");

        return update(query.toString(),parameters);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(String hql, Map<String, Object> parameters) {
        return super.delete(hql, parameters);
    }
  
    @Override
    public List<BookingEntity> findAllBooking() {
        return query("FROM BookingEntity", null);

    }

}
