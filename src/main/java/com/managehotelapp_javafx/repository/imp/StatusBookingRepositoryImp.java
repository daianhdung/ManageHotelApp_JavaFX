package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.StatusBookingEntity;
import com.managehotelapp_javafx.repository.StatusBookingRepository;

import java.util.List;

public class StatusBookingRepositoryImp extends AbstractRepository<StatusBookingEntity> implements StatusBookingRepository {


    @Override
    public List<StatusBookingEntity> findAll() {
        return query("FROM StatusBookingEntity", null);
    }
}
