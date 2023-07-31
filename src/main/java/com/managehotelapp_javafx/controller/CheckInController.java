package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.HelloApplication;
import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.dto.CustomerDTO;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.dto.ServiceDTO;
import com.managehotelapp_javafx.services.imp.CheckInServiceImp;
import com.managehotelapp_javafx.services.imp.RoomServiceImp;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import com.managehotelapp_javafx.utils.session.SessionUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
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
    private GridPane gpServices;

    @FXML
    private ScrollPane sscrp;

    @FXML
    private ScrollPane scrlPnRoomList;

    @FXML
    private TextField tfChildren;

    @FXML
    private TextField tfAdult;

    @FXML
    private Button btnR;

    @FXML
    private Button btnBack;

    @FXML
    Label lblG;

    private GridPane gridPane = new GridPane();

    private CheckInServiceImp service = new CheckInServiceImp();

    private final BookingDTO bookingDTO = new BookingDTO();

    private List<ServiceDTO> selectedServices = new ArrayList<>();

    private String specialRequest = "";

    private ConfirmBoxController confirmBoxController;

    @FXML
    private void setBtnCheckIn() throws Exception {
        //bookingRoomEntity.setCreatedAt(new Timestamp(Instant.now().toEpochMilli()));
        long coDate = dtpkCheckOut.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long ciDate = dtpkCheckIn.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        bookingDTO.setUserId(SessionUser.getInstance().getId());
        bookingDTO.setCustomerIDN(tfNationalID.getText());
        bookingDTO.setCustomerName(tfName.getText());
        bookingDTO.setPhoneNumber(tfPhoneNumber.getText());
        bookingDTO.setBookingDate(dtpkBookingdate.getValue().toString());
        bookingDTO.setCheckInDate(dtpkCheckIn.getValue().toString());
        bookingDTO.setCheckOutDate(dtpkCheckOut.getValue().toString());
        bookingDTO.setStatus(cbxStatus.getSelectionModel().getSelectedItem().toString());
        bookingDTO.setCustomerRequest(specialRequest);
        bookingDTO.setAdultCount(Integer.parseInt(tfAdult.getText()));
        bookingDTO.setChildrenCount(Integer.parseInt(tfChildren.getText()));
        confirmBoxController = new ConfirmBoxController(roomsList, bookingDTO);

        service.setCustomerByIDN(tfNationalID.getText());
        service.setRoomListByNames(roomsList);
        service.setBookingServices(selectedServices);
        service.setBookingStatus(cbxStatus.getSelectionModel().getSelectedItem().toString());

        try {
            if (getConfirmBox())
                getResultBox(service.checkIn(bookingDTO), "");
        } catch (Exception e) {
            getResultBox(false, e.toString());
            throw new RuntimeException(e);
        }


    }

    public RoomController roomController;
    private Set<String> roomsList = new HashSet<>();

    public void setRooms(Set<String> rooms) {
        this.roomsList = rooms;
    }

    ;
    private List<CustomerDTO> customerDTOList = new ArrayList<>();
    private List<CustomerDTO> f_customerDTOList = new ArrayList<>();
    private List<RoomDTO> roomDTOList = new ArrayList<>();

    public Set<String> getRooms() {
        return this.roomsList;
    }

    ;

    private static final String PHONE_REGEX = "^\\+?(?:\\d\\s?){9,15}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    // Regular expression for a valid email format
    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringBuilder rooms = new StringBuilder();

        dtpkBookingdate.setValue(LocalDate.now());
        dtpkCheckIn.setValue(LocalDate.now());
        dtpkCheckIn.valueProperty().addListener((observable, oldValue, newValue) -> {
            dtpkCheckOut.setValue(newValue.plusDays(1));
        });
        dtpkBookingdate.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && dtpkCheckIn.getValue() != null && newValue.isAfter(dtpkCheckIn.getValue())) {
                showErrorMessage("Invalid Date Selection", "Booking date cannot be after Check-In date.");
            }
        });
        dtpkCheckIn.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && dtpkCheckOut.getValue() != null && newValue.isAfter(dtpkCheckOut.getValue())) {
                showErrorMessage("Invalid Date Selection", "Check-In date cannot be after Check-Out date.");
                dtpkCheckIn.setValue(LocalDate.now());
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
        for (var stt :
                service.getListOfCheckInBookingStatus()) {
            cbxStatus.getItems().add(stt.getTitle());
            cbxStatus.setValue(stt.getTitle());
        }


        btnCheckIn.setOnAction(event -> {
            if (validate()) {
                try {
                    setBtnCheckIn();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        RequestBoxController requestBoxController = new RequestBoxController();
        btnR.setOnAction(actionEvent -> {
            try {
                if (specialRequest.length() == 0) {
                    specialRequest = getRequestBox(requestBoxController);
                } else {
                    requestBoxController.setMessage(specialRequest);
                    getRequestBox(requestBoxController);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        GuestListController guestListController = new GuestListController();
        lblG.setOnMouseClicked(mouseEvent -> {
            try {
                if (f_customerDTOList.size() == 0)
                    f_customerDTOList = getFollowingCustomerDTOList(guestListController);
                else {
                    guestListController.setFollowerList(f_customerDTOList);
                    f_customerDTOList = getFollowingCustomerDTOList(guestListController);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btnBack.setOnAction(actionEvent -> {
            ;
            try {
                Stage pstg = (Stage) btnBack.getScene().getWindow();
                pstg.setScene(new Scene(FXMLLoaderConstant.getHomeScene().load()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ColumnConstraints col1 = new ColumnConstraints(94);
        ColumnConstraints col2 = new ColumnConstraints(94);
        ColumnConstraints col3 = new ColumnConstraints(94);
        gridPane.getColumnConstraints().addAll(col1, col2, col3);

        customerDTOList = service.getListCustomer();
        searchCustomer();
        searchRoomByType();
        getServices();


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
            contextMenu.getItems().clear();
            if (text.length() > 5) {
                try {
                    for (var c : customerDTOList) {
                        if (c.getIdentity().startsWith(text)) {
                            suggestion = c.getFullName();
                            MenuItem item = new MenuItem();
                            Label dummyForWidth = new Label(suggestion);
                            dummyForWidth.setPrefWidth(250);
                            item.setGraphic(dummyForWidth);

                            item.setOnAction(e -> {
                                        tfNationalID.setText(String.valueOf(c.getIdentity()));
                                        //if (event.getCode() == KeyCode.ENTER)
                                        {
                                            tfEmail.setText(c.getEmail());
                                            tfPhoneNumber.setText(c.getPhone());
                                            tfName.setText(c.getFullName());
                                        }
                                    }
                            );
                            contextMenu.getItems().add(item);
                        }
                    }
                    if (!contextMenu.getItems().isEmpty()) {
                        contextMenu.show(tfNationalID, Side.BOTTOM, 0, 0);
                    } else {
                        contextMenu.hide();
                    }
                } catch (Exception ignored) {
                }
            }
        });
    }

    private void searchRoomByType() {
        var roomTypeList = service.getRoomType();
        roomDTOList = new RoomServiceImp().getAvailableRoom();
        cbxRoomType.getItems().add("All");
        cbxRoomType.setValue("All");
        for (var rt : roomTypeList) {
            cbxRoomType.getItems().add(rt);
        }

        for (var rr : roomDTOList) {
            setRoomItem(rr.getRoomNo());
        }
        cbxRoomType.setOnAction(event -> {
            gridPane.getChildren().clear();
            gridPane.getRowConstraints().clear();
            if (cbxRoomType.getValue() == "All") {
                for (var rr : roomDTOList) {
                    setRoomItem(rr.getRoomNo());
                }
            } else {
                var rooms = service.getRoomsByType(roomDTOList, cbxRoomType.getValue().toString());
                for (var r :
                        rooms) {
                    setRoomItem(r.getRoomNo());
                }
            }
        });
    }

    private String selectRoom(Pane pane) {

        if (pane.getStyle().contains("#9EE20C;")) {
            Label lb = (Label) pane.getChildren().get(0);
            roomsList.add(lb.getText());
            return "rgba(240, 60, 49, 0.63);";
        } else {
            Label lb = (Label) pane.getChildren().get(0);
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
        int gpsize = gridPane.getChildren().size() == 0 ? 0 : gridPane.getChildren().size();
        var lastItem = gpsize > 0 ? gridPane.getChildren().get(gpsize - 1) : null;

        int lastCol = lastItem == null ? 0 : GridPane.getColumnIndex(lastItem);
        int lastRow = lastItem == null ? 0 : GridPane.getRowIndex(lastItem);

        int col = lastCol == 2 || lastItem == null ? 0 : lastCol + 1;
        int row = lastCol == 2 ? lastRow + 1 : lastRow;
        gridPane.getRowConstraints().add(new RowConstraints(57));
        gridPane.add(pane, col, row);
        GridPane.setHalignment(pane, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(pane, javafx.geometry.VPos.CENTER);
        scrlPnRoomList.setContent(gridPane);
    }

    private void setRoomDetail() {
        var rd = service.getRoomDetail();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var customer = service.setCustomerByIDN(rd.getCustomerIDN());
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

    private boolean validate() {
        String m = "";
        if (tfEmail.getText() == null) {
            tfEmail = new TextField("");
        }
        if (roomsList.size() > 0) {
            if (!tfNationalID.getText().isEmpty()) {
                if (!tfName.getText().isEmpty()) {
                    if (isValidPhoneNumber(tfPhoneNumber.getText())) {
                        if (isValidEmail(tfEmail.getText())) {
                            if (dtpkBookingdate.getValue() != null) {
                                if (dtpkCheckIn.getValue() != null) {
                                    if (dtpkCheckOut.getValue() != null) {
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
            } else {
                m += "Identification Number can not be empty \n";
            }
            m += "Please choose at least one room!";
        }
        showErrorMessage("ERROR", m);
        return false;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }

    // Method to validate the email format
    private boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return email.equals("") || matcher.matches();
    }

    private void showErrorMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean getConfirmBox() throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("booking/bk-confirm-box.fxml"));
        ConfirmBoxController controller = new ConfirmBoxController(roomsList, bookingDTO);
        loader.setController(controller);
        Scene scene = new Scene(loader.load(), Color.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        final Stage messageBoxStage = new Stage();
        messageBoxStage.initStyle(StageStyle.TRANSPARENT);
        messageBoxStage.initModality(Modality.APPLICATION_MODAL);
        messageBoxStage.setTitle("Confirm Booking");
        messageBoxStage.setScene(scene);
        messageBoxStage.showAndWait();

        return controller.result;
    }

    private boolean getResultBox(boolean result, String message) throws Exception {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("booking/bk-result-box.fxml"));
        ResultBoxController controller = new ResultBoxController(result, message);
        loader.setController(controller);
        Scene scene = new Scene(loader.load(), Color.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        final Stage messageBoxStage = new Stage();
        messageBoxStage.initStyle(StageStyle.TRANSPARENT);
        messageBoxStage.initModality(Modality.APPLICATION_MODAL);
        messageBoxStage.setTitle("Confirm Booking");
        messageBoxStage.setScene(scene);
        messageBoxStage.showAndWait();

        return controller.result;
    }

    private void getServices() {
        GridPane gridPane1 = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints(178);
        ColumnConstraints col2 = new ColumnConstraints(79);
        ColumnConstraints col3 = new ColumnConstraints(43);
        gridPane1.setPrefHeight(Region.USE_COMPUTED_SIZE);
        gridPane1.setPrefWidth(Region.USE_COMPUTED_SIZE);
        gridPane1.getColumnConstraints().addAll(col1, col2, col3);
        int i = 0;
        for (var s : service.getServices()) {
            RowConstraints rc = new RowConstraints(30);

            gridPane1.getRowConstraints().add(new RowConstraints(30));
            CheckBox chb = new CheckBox(s.getDescription());

            Label lbPrice = new Label(String.valueOf(s.getPrice()));
            Label lbQty = new Label(String.valueOf(s.getQuantity()));

            GridPane.setValignment(lbPrice, javafx.geometry.VPos.CENTER);
            GridPane.setValignment(lbQty, javafx.geometry.VPos.CENTER);

            chb.setOnAction(actionEvent -> {
                if (chb.isSelected()) {
                    selectedServices.add(s);
                } else {
                    selectedServices.remove(s);
                }
            });
            gridPane1.add(chb, 0, i);
            gridPane1.add(lbPrice, 1, i);
            gridPane1.add(lbQty, 2, i);
            i++;
        }
        sscrp.setContent(gridPane1);

    }

    private String getRequestBox(RequestBoxController controller) throws IOException {

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("booking/bk-request-box.fxml"));

        loader.setController(controller);
        Scene scene = new Scene(loader.load(), Color.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);

        final Stage messageBoxStage = new Stage();
        messageBoxStage.initStyle(StageStyle.TRANSPARENT);
        messageBoxStage.initModality(Modality.APPLICATION_MODAL);
        messageBoxStage.setTitle("Customer Request");
        messageBoxStage.setScene(scene);
        messageBoxStage.showAndWait();
        return controller.getMessage();
    }

    private List<CustomerDTO> getFollowingCustomerDTOList(GuestListController controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("booking/bk-followers-box.fxml"));

        loader.setController(controller);
        Scene scene = new Scene(loader.load(), Color.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        final Stage messageBoxStage = new Stage();
        messageBoxStage.initStyle(StageStyle.TRANSPARENT);
        messageBoxStage.initModality(Modality.APPLICATION_MODAL);
        messageBoxStage.setTitle("List of Guests");
        messageBoxStage.setScene(scene);
        messageBoxStage.showAndWait();

        return controller.getFollowerList();
    }

    protected static class ConfirmBoxController implements Initializable {
        @FXML
        private Button btnCancel;

        @FXML
        private Button btnConfirm;

        @FXML
        private Label lblBkDate;

        @FXML
        private Label lblBkRoom;

        @FXML
        private Label lblCkInDate;

        @FXML
        private Label lblCkOutDate;

        @FXML
        private Label lblCustomerName;

        @FXML
        private Label lblUserName;

        @FXML
        private Label lblcustomerId;

        @FXML
        private VBox _vb;

        @FXML
        private Pane _pane;

        @FXML
        private GridPane _gp;
        private boolean result = false;

        @FXML
        protected void btnConfirmClicked() {
            result = true;
            close();
        }

        @FXML
        protected void btnCancelClicked() {
            result = false;
            close();
        }

        protected Set<String> roomList = new HashSet<>();
        protected BookingDTO bookingDTO = new BookingDTO();
        CheckInController checkInController = new CheckInController();

        protected ConfirmBoxController(Set<String> roomList, BookingDTO bookingDTO) {
            this.roomList = roomList;
            this.bookingDTO = bookingDTO;
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            _vb.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            _pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            _gp.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            lblUserName.setText(SessionUser.getInstance().getFullName());
            lblCustomerName.setText(bookingDTO.getCustomerName());
            lblcustomerId.setText(bookingDTO.getCustomerIDN());
            lblBkDate.setText(bookingDTO.getBookingDate());
            lblCkInDate.setText(bookingDTO.getCheckinDate());
            lblCkOutDate.setText(bookingDTO.getCheckOutDate());
            lblBkRoom.setText(String.join(", ", roomList));
            Tooltip tooltip = new Tooltip(String.join(", ", roomList));
            tooltip.setAutoHide(false);
            tooltip.setWrapText(true);
            tooltip.setMaxWidth(198);
            tooltip.setShowDuration(new Duration(999999));
            tooltip.setStyle("-fx-font-size: 16px");
            //lblBkRoom.setTooltip(tooltip);
            lblBkRoom.setOnMouseEntered(e -> {
                if (isLabelOverflowing(lblBkRoom.getText())) {
                    lblBkRoom.setTooltip(tooltip);
                }
            });
            lblBkRoom.setOnMouseExited(e -> tooltip.hide());

            btnCancel.setOnAction(actionEvent -> {
                btnCancelClicked();
            });
            btnConfirm.setOnAction(actionEvent -> {
                btnConfirmClicked();
            });

        }

        private void close() {
            Stage stage = (Stage) btnConfirm.getScene().getWindow();
            stage.close();
        }

        private boolean isLabelOverflowing(String text) {
            return text.length() > 24;
        }
    }

    protected static class ResultBoxController implements Initializable {

        @FXML
        private Button btnOK;

        @FXML
        private Label lblResult;

        @FXML
        private Label lblMsg;

        @FXML
        Pane _pane;

        @FXML
        Pane _rpane;

        @FXML
        VBox _vb;

        public ResultBoxController() {

        }

        public boolean getResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        private boolean result;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        private String message;

        @FXML
        protected void btnOKClicked() {
            close();
        }

        public ResultBoxController(boolean result) {
            this.result = result;
        }

        public ResultBoxController(boolean result, String message) {
            this.result = result;
            this.message = message;
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            _vb.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            _pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            _rpane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

            if (!result) {
                _rpane.setStyle("-fx-background-color: rgba(240, 60, 49, 0.63);");
                lblResult.setText("ERROR!!");
                lblMsg.setText(message);
            } else {
                lblResult.setText("SUCCESS!!");
                lblMsg.setStyle("-fx-font-size: 20px");
                lblMsg.setAlignment(Pos.CENTER);
                lblMsg.setText("Everything are all set!");
            }

            btnOK.setOnAction(actionEvent -> close());
        }

        private void close() {
            Stage stage = (Stage) btnOK.getScene().getWindow();
            stage.close();
        }


    }

    protected static class RequestBoxController implements Initializable {

        @FXML
        private Button btnOK;

        @FXML
        TextArea txtAr;

        @FXML
        Pane _pane;

        @FXML
        Pane _rpane;

        @FXML
        VBox _vb;


        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        private String message;


        protected void btnOKClicked() {
            message = txtAr.getText();
            close();
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            _vb.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            _pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            _rpane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            btnOK.setOnAction(actionEvent -> close());
            btnOK.setOnAction(actionEvent -> btnOKClicked());
            txtAr.setText(message);
        }

        private void close() {
            Stage stage = (Stage) btnOK.getScene().getWindow();
            stage.close();
        }


    }

    protected static class GuestListController implements Initializable {
        @FXML
        private TextField tfFullname;

        @FXML
        private TextField tdIdentity;

        @FXML
        private TextField tfPhoneNo;

        @FXML
        private DatePicker tfDob;

        @FXML
        private ListView<CustomerDTO> listview0;

        @FXML
        private RadioButton gender1;

        @FXML
        private RadioButton gender2;

        @FXML
        private RadioButton gender3;

        @FXML
        private Button btnNew;

        @FXML
        private Button btnAdd;

        @FXML
        private Button btnRemove;

        @FXML
        private Button btnBack;

        public List<CustomerDTO> getFollowerList() {
            return followerList;
        }

        public void setFollowerList(List<CustomerDTO> followerList) {
            this.followerList = followerList;
        }

        private List<CustomerDTO> followerList = new ArrayList<>();

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            ObservableList<CustomerDTO> observableCustomerDTOList = FXCollections.observableArrayList(followerList);
            ToggleGroup genderToggleGroup = new ToggleGroup();
            gender1.setToggleGroup(genderToggleGroup);
            gender2.setToggleGroup(genderToggleGroup);
            gender3.setToggleGroup(genderToggleGroup);
            listview0.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(CustomerDTO customerDTO, boolean empty) {
                    super.updateItem(customerDTO, empty);
                    setText(empty || customerDTO == null ? null : "Name: " + customerDTO.getFullName() + ", ID: " + customerDTO.getIdentity());
                }
            });

            listview0.setItems(observableCustomerDTOList);
            listview0.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    tdIdentity.setText(newValue.getIdentity());
                    tfDob.setValue(newValue.getDob().toLocalDate());
                    tfFullname.setText(newValue.getFullName());
                    tfPhoneNo.setText(newValue.getPhone());
                    String gender = newValue.getGender();
                    if ("Male".equals(gender)) {
                        gender1.setSelected(true);
                    } else if ("Female".equals(gender)) {
                        gender2.setSelected(true);
                    } else {
                        gender3.setSelected(true);
                    }
                }
            });

            btnAdd.setOnAction(actionEvent -> add());
            btnNew.setOnAction(actionEvent -> {
                tfFullname.clear();
                tdIdentity.clear();
                tfPhoneNo.clear();
                tfDob.setValue(null);
            });
            btnRemove.setOnAction(actionEvent -> {
                var selectedItem = listview0.getSelectionModel().getSelectedItem();
                remove(selectedItem);
                followerList.remove(selectedItem);
            });
            btnBack.setOnAction(actionEvent -> {
                Stage stage = (Stage) btnBack.getScene().getWindow();
                stage.close();
            });
        }

        private void add() {
            String fullname = tfFullname.getText();
            String identity = tdIdentity.getText();
            String phoneNo = tfPhoneNo.getText();
            LocalDate dob = tfDob.getValue();
            String gender = gender1.isSelected() ? "Male" : (gender2.isSelected() ? "Female" : "Other");

            CustomerDTO customerDTO = new CustomerDTO(fullname, identity, phoneNo, dob, gender);

            followerList.add(customerDTO);

            listview0.setItems(FXCollections.observableArrayList(followerList));

            tfFullname.clear();
            tdIdentity.clear();
            tfPhoneNo.clear();
            tfDob.setValue(null);
        }

        private void remove(CustomerDTO customerDTO) {
            followerList.remove(customerDTO);
            listview0.setItems(FXCollections.observableArrayList(followerList));
            tfFullname.clear();
            tdIdentity.clear();
            tfPhoneNo.clear();
            tfDob.setValue(null);
        }


    }
}