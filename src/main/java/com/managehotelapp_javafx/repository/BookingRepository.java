package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.BookingEntity;

import java.util.List;
import java.util.Map;

public interface BookingRepository extends GenericRepository<BookingEntity> {

    List<BookingEntity> query(String hql, Map<String, Object> parameters);

    boolean insert(BookingEntity bookingEntity);


    boolean update(BookingEntity bookingEntity);

    boolean delete(int id);
}
