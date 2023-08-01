package com.managehotelapp_javafx.repository;

import com.managehotelapp_javafx.entity.FacilityEntity;

public interface FacilityRepository extends GenericRepository<FacilityEntity> {
    FacilityEntity findById(int id);
}
