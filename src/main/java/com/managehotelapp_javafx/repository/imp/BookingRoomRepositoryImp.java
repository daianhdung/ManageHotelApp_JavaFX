package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.repository.BookingRoomRepository;

import java.util.ArrayList;
import java.util.List;

public class BookingRoomRepositoryImp extends AbstractRepository<BookingRoomEntity> implements BookingRoomRepository {


    @Override
    public List<BookingRoomEntity> findAll() {
        return query("FROM BookingRoomEntity", null);
    }
}
