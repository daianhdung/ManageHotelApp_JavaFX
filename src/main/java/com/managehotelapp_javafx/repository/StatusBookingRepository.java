package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.StatusBookingEntity;

import java.util.List;

public interface StatusBookingRepository extends GenericRepository<StatusBookingEntity>{

    List<StatusBookingEntity> findAll();
    StatusBookingEntity findByIdStatus(int id);

    StatusBookingEntity findByStatusByTitle(String name);
}
