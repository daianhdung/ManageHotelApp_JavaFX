package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.services.imp.RoomDetailServiceImp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomDetailController implements Initializable {

    @FXML
    private Button btnCheckin;

    @FXML
    private DatePicker dtpkBookingdate;

    @FXML
    private DatePicker dtpkCheckIn;

    @FXML
    private DatePicker dtpkCheckOut;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfNationalID;

    @FXML
    private TextField tfPhoneNumber;

    @FXML
    private TextField tfRooms;

    @FXML
    private ComboBox cbxStatus;

    private RoomDetailServiceImp service = new RoomDetailServiceImp();
    @FXML
    private void setBtnCheckin()
    {
        //bookingRoomEntity.setCreatedAt(new Timestamp(Instant.now().toEpochMilli()));
        long coDate = dtpkCheckOut.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long ciDate = dtpkCheckIn.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCustomerIDN(tfNationalID.getText());
        bookingDTO.setCustomerName(tfName.getText());
        bookingDTO.setPhoneNumber(tfPhoneNumber.getText());
        bookingDTO.setBookingDate(dtpkBookingdate.getValue().toString());
        bookingDTO.setCheckInDate(dtpkCheckIn.getValue().toString());
        bookingDTO.setCheckOutDate(dtpkCheckOut.getValue().toString());
        bookingDTO.setStatus(cbxStatus.getSelectionModel().getSelectedItem().toString());

        service.getCustomerByIDN(tfNationalID.getText());
        service.getRoomListByNames(roomsList);

        if(cbxStatus.getSelectionModel().getSelectedItem()=="Checked In")
        {
            service.checkIn(bookingDTO);
        }
    }
    public  RoomController roomController ;
    private Set<String> roomsList = new HashSet<>();
    public void setRooms(Set<String> rooms)
    {
        this.roomsList = rooms;
    };

    public Set<String> getRooms()
    {
        return  this.roomsList ;
    };

    private static final String PHONE_REGEX = "^\\+?(?:\\d\\s?){9,15}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    // Regular expression for a valid email format
    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringBuilder rooms = new StringBuilder();
        dtpkCheckIn.setValue(LocalDate.now());
        dtpkCheckIn.valueProperty().addListener((observable, oldValue, newValue) -> {
            dtpkCheckOut.setValue(newValue.plusDays(1));
        });
        dtpkCheckIn.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && dtpkCheckOut.getValue() != null && newValue.isAfter(dtpkCheckOut.getValue())) {
                showErrorMessage("Invalid Date Selection", "Check-In date cannot be after Check-Out date.");
            }
        });
        dtpkCheckOut.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && dtpkCheckIn.getValue() != null && newValue.isBefore(dtpkCheckIn.getValue())) {
                showErrorMessage("Invalid Date Selection", "Check-Out date cannot be before Check-In date.");
            }
        });
        tfPhoneNumber.setTextFormatter(new TextFormatter<Integer>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));
        cbxStatus.getItems().addAll("Checked In","Reserve");
        cbxStatus.setValue("Checked In");
        roomsList = roomController.getSeletedRooms();
        btnCheckin.setOnAction(event -> {
            if(validate())
                setBtnCheckin();
        });
        for (String r :roomsList)
        {
            rooms.append(r).append(", ");
        }
        tfRooms.setText(rooms.toString());
        if(service.getRoomListByNames(roomsList).size()==1 && service.getRoomListByNames(roomsList).get(0).getRoomStatus().getId()==1)
        {
            setRoomDetail();
        }
    }

    private  void setRoomDetail()
    {
        var rd = service.getRoomDetail();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var customer = service.getCustomerByIDN(rd.getCustomerIDN());
        tfNationalID.setText(rd.getCustomerIDN());
        tfName.setText(rd.getCustomerName());
        tfPhoneNumber.setText(rd.getPhoneNumber());
        tfEmail.setText(customer.getEmail());
        dtpkCheckOut.setValue(LocalDate.parse(rd.getCheckOutDate().split(" ")[0], formatter));
        dtpkCheckIn.setValue(LocalDate.parse(rd.getCheckinDate().split(" ")[0], formatter));
        dtpkBookingdate.setValue(LocalDate.parse(rd.getBookingDate().split(" ")[0], formatter));
        cbxStatus.setValue(rd.getStatus());
        btnCheckin.setDisable(true);
    }
    private boolean validate()
    {
        String m = "";
        if(!tfNationalID.getText().isEmpty()) {
            if (!tfName.getText().isEmpty()) {
                if (isValidPhoneNumber(tfPhoneNumber.getText())) {
                    if (tfEmail.getText().isEmpty() || isValidEmail(tfEmail.getText())) {
                        if (dtpkBookingdate.getValue()!=null) {
                            if (dtpkCheckIn.getValue()!=null) {
                                if (dtpkCheckOut.getValue()!=null) {
                                    return true;
                                } else {
                                    m += "Check-out date can not be empty \n";
                                }
                            } else {
                                m += "Check-in date can not be empty \n";
                            }
                        } else {
                            m += "Booking date can not be empty \n";
                        }
                    } else {
                        m += "Invalid email \n";
                    }
                } else {
                    m += "Invalid phone number \n";
                }
            } else {
                m += "Name can not be empty \n";
            }
        }else {
            m += "Identification Number can not be empty \n";
        }
        showErrorMessage("ERROR",m);
        return  false;
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }

    // Method to validate the email format
    private boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    private void showErrorMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}