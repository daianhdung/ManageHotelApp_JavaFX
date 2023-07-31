package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.CustomerEntity;
import com.managehotelapp_javafx.entity.FacilityEntity;
import com.managehotelapp_javafx.entity.RoomFacilityEntity;
import com.managehotelapp_javafx.repository.FacilityRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacilityRepositoryImp extends AbstractRepository<FacilityEntity> implements FacilityRepository {
    @Override
    public FacilityEntity findById(int id) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
        List<FacilityEntity> result = query("FROM FacilityEntity WHERE id = :id",parameter);
        return result.isEmpty() ? null : result.get(0);
    }
}
