package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.StatusBookingEntity;
import com.managehotelapp_javafx.repository.StatusBookingRepository;

import java.util.List;

public class StatusBookingRepositoryImp extends AbstractRepository<StatusBookingEntity> implements StatusBookingRepository {


    public List<StatusBookingEntity> findById(int id) {
        return query("FROM StatusBookingEntity WHERE id = "+id, null);
    }

    @Override
    public List<StatusBookingEntity> findAll() {
        return query("FROM StatusBookingEntity", null);
    }
}
