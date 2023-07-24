package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
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

}
