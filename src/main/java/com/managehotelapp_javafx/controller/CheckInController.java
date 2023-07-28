package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.dto.CustomerDTO;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.services.RoomService;
import com.managehotelapp_javafx.services.imp.CheckInServiceImp;
import com.managehotelapp_javafx.services.imp.RoomDetailServiceImp;
import com.managehotelapp_javafx.services.imp.RoomServiceImp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInController implements Initializable {

    @FXML
    private Button btnCheckIn;

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
    private ComboBox cbxStatus;
    @FXML
    private ComboBox cbxRoomType;

    @FXML
    private ScrollPane scrlPnRoomList;

    private GridPane gridPane = new GridPane();

    private CheckInServiceImp service = new CheckInServiceImp();
    @FXML
    private void setBtnCheckIn()
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
    private List<CustomerDTO> customerDTOList = new ArrayList<>();
    private List<RoomDTO> roomDTOList = new ArrayList<>();
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
        btnCheckIn.setOnAction(event -> {
            if(validate())
                setBtnCheckIn();
        });
        ColumnConstraints col1 = new ColumnConstraints(94);
        ColumnConstraints col2 = new ColumnConstraints(94);
        ColumnConstraints col3 = new ColumnConstraints(94);
        gridPane.getColumnConstraints().addAll(col1, col2, col3);

        customerDTOList = service.getListCustomer();
        searchCustomer();
        searchRoomByType();
    }
    private String suggestion = "";

    private void searchCustomer() {
        // Create a context menu to display the suggestions.
        ContextMenu contextMenu = new ContextMenu();
        //        for (String suggestion : suggestions) {
        //            MenuItem item = new MenuItem(suggestion);
        //            item.setOnAction(event -> tfSearch.setText(suggestion));
        //            contextMenu.getItems().add(item);
        //        }

        tfNationalID.setOnKeyReleased(event -> {
            String text = tfNationalID.getText();
            CustomerDTO customer;
                if (text.isEmpty()) {
                    customer = null;
                    contextMenu.hide();
                } else {
                    contextMenu.getItems().clear();
                    customer = customerDTOList.stream()
                            .filter(f -> Objects.equals(f.getIdentity(), text)).toList().get(0);

                    if (event.getCode() == KeyCode.ENTER) {
                        suggestion = customer.getFullName();
                        MenuItem item = new MenuItem();
                        Label dummyForWidth = new Label(suggestion);
                        dummyForWidth.setPrefWidth(250);
                        item.setGraphic(dummyForWidth);
                        item.setOnAction(e -> {
                                    tfNationalID.setText(String.valueOf(customer.getId()));
                                }
                        );
                        if (!contextMenu.getItems().isEmpty()) {
                            contextMenu.show(tfNationalID, Side.BOTTOM, 500, 0);

                        } else {
                            contextMenu.hide();
                        }
                        if (event.getCode() == KeyCode.ENTER) {
                            if (customer != null) {
                                tfEmail.setText(customer.getEmail());
                                tfPhoneNumber.setText(customer.getPhone());
                                tfName.setText(customer.getFullName());
                            }
                        }
                    }
                }

        });
    }

    private void searchRoomByType()
    {
        var roomTypeList = service.getRoomType();
        roomDTOList = new RoomServiceImp().getAvailableRoom();
        cbxRoomType.getItems().add("All");
        cbxRoomType.setValue("All");
        for (var rt : roomTypeList) {
            cbxRoomType.getItems().add(rt);
        }

        for (var rr : roomDTOList)
        {
            setRoomItem(rr.getRoomNo());
        }
        cbxRoomType.setOnAction(event ->{
            gridPane.getChildren().clear();
            gridPane.getRowConstraints().clear();
            if(cbxRoomType.getValue()=="All")
            {
                for (var rr : roomDTOList)
                {
                    setRoomItem(rr.getRoomNo());
                }
            }else {
                var rooms = service.getRoomsByType(roomDTOList, cbxRoomType.getValue().toString());
                for (var r :
                        rooms) {
                    setRoomItem(r.getRoomNo());
                }
            }
        });
    }

    private String selectRoom(Pane pane)
    {

       if(pane.getStyle().contains("#9EE20C;"))
       {
           Label lb = (Label)pane.getChildren().get(0);
           roomsList.add(lb.getText());
           return "rgba(240, 60, 49, 0.63);";
       }
       else
       {
           Label lb = (Label)pane.getChildren().get(0);
           roomsList.remove(lb.getText());
           return "#9EE20C;";
       }
    }

    private void setRoomItem(String r) {
        Pane pane = new Pane();
        pane.setPrefHeight(40.0);
        pane.setPrefWidth(80.0);
        pane.setMaxHeight(40.0);
        pane.setMaxWidth(80.0);
        pane.setStyle("-fx-background-color: #9EE20C;");

        Label label = new Label(r);
        label.setLayoutX(20.0);
        label.setLayoutY(10.0);
        label.setFont(new Font(19.0));

        pane.getChildren().add(label);


        pane.setOnMouseClicked((MouseEvent event) -> {
            pane.setStyle("-fx-background-color:" + selectRoom(pane));
        });
        int gpsize =   gridPane.getChildren().size() == 0 ? 0 : gridPane.getChildren().size();
        var lastItem = gpsize >  0 ? gridPane.getChildren().get(gpsize-1) : null;

        int lastCol = lastItem == null ? 0 : GridPane.getColumnIndex(lastItem) == 0 ? 1 : 0;
        int lastRow = lastItem == null || lastCol==0 ? 0 : GridPane.getRowIndex(lastItem);
        gridPane.getRowConstraints().add(new RowConstraints(57));

        gridPane.add(pane,lastCol,lastCol==1&&lastRow==1?lastRow+1:lastRow);
        GridPane.setHalignment(pane, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(pane, javafx.geometry.VPos.CENTER);
        scrlPnRoomList.setContent(gridPane);
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
        btnCheckIn.setDisable(true);
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