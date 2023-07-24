package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.repository.BookingRoomRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRoomRepositoryImp extends AbstractRepository<BookingRoomEntity> implements BookingRoomRepository {


    @Override
    public List<BookingRoomEntity> findAll() {
        return query("FROM BookingRoomEntity", null);
    }

    @Override
    public BookingRoomEntity findById(int id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        return query("FROM BookingRoomEntity WHERE id = :id", parameters).get(0);
    }

    @Override
    public List<BookingRoomEntity> findByStatusBookingIdIsIn(List<Integer> listId) {
        Map<String, Object> parameters = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listId.size(); i++) {
            parameters.put("id" + i, listId.get(i));
            sb.append(":id").append(i);
            if (i < listId.size() - 1) {
                sb.append(", ");
            }
        }
        return query("FROM BookingRoomEntity WHERE status_booking_id IN (" + sb + ")", parameters);
    }

    @Override
    public BookingRoomEntity findByIdInvoice(int idInv) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("invoice_id", idInv);
        return query("FROM BookingRoomEntity WHERE invoice_id = :invoice_id", parameters).get(0);
    }

    @Override
    public List<BookingRoomEntity> findRoomByIdBooking(int idBooking) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("booking_id", idBooking);
        return query("FROM BookingRoomEntity WHERE booking_id = :booking_id", parameters);
    }
}
