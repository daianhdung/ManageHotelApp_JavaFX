package com.managehotelapp_javafx.services.imp;

import com.managehotelapp_javafx.repository.StatusBookingRepository;
import com.managehotelapp_javafx.repository.imp.StatusBookingRepositoryImp;
import com.managehotelapp_javafx.services.StatusBookingService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StatusBookingServiceImp implements StatusBookingService {
    StatusBookingRepository statusBookingRepository = new StatusBookingRepositoryImp();
    @Override
    public ObservableList<String> getAllStatusBooking() {
        ObservableList<String> listStatus = FXCollections.observableArrayList();
        statusBookingRepository.findAll().forEach(item -> {
            listStatus.add(item.getTitle());
        });
        return listStatus;
    }
}
