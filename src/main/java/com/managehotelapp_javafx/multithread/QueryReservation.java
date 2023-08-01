package com.managehotelapp_javafx.multithread;

import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.imp.BookingRoomServiceImp;
import com.managehotelapp_javafx.utils.enumpackage.BookingStatus;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class QueryReservation extends Service<String> {
    BookingRoomService bookingRoomService = new BookingRoomServiceImp();
    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                int checkInQty = (int) bookingRoomService.getCurrentBookingRoom()
                        .stream().filter(bookingRoomDTO -> bookingRoomDTO.getStatus().toUpperCase().equals(BookingStatus.CHECKED_IN.toString()))
                        .count();
                return String.valueOf(checkInQty);
            }
        };
    }

}
