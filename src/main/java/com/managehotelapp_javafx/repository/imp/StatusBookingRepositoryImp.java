package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.entity.StatusBookingEntity;
import com.managehotelapp_javafx.repository.StatusBookingRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusBookingRepositoryImp extends AbstractRepository<StatusBookingEntity> implements StatusBookingRepository {

    @Override
    public List<StatusBookingEntity> findAll() {
        return query("FROM StatusBookingEntity", null);
    }

    @Override
    public StatusBookingEntity findByIdStatus(int id){
        return findByIdEntity(StatusBookingEntity.class, id);
    }

    @Override
    public StatusBookingEntity findByStatusByTitle(String name){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", name);
        List<StatusBookingEntity> result = query("FROM StatusBookingEntity WHERE title = :title", parameters);
        return result.isEmpty() ? null : result.get(0);
    }
}
