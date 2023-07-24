package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.entity.ServiceEntity;
import com.managehotelapp_javafx.entity.UserStatusEntity;
import com.managehotelapp_javafx.repository.ServicesRepository;
import com.managehotelapp_javafx.repository.UserStatusRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicesRepositoryImp extends AbstractRepository<ServiceEntity> implements ServicesRepository {
    @Override
    public List<ServiceEntity> getServicesList() {
        return query("FROM ServiceEntity ",null);
    }

    @Override
    public ServiceEntity findServicesById(int idService) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", idService);
        List<ServiceEntity> result = query("FROM ServiceEntity WHERE id = :id",parameters);
        return result.isEmpty() ? null : result.get(0);
    }


}
