package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.HelloApplication;
import com.managehotelapp_javafx.dto.BookingDTO;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.entity.RoomEntity;
import com.managehotelapp_javafx.repository.imp.RoomRepositoryImp;
import com.managehotelapp_javafx.services.RoomService;
import com.managehotelapp_javafx.services.imp.RoomServiceImp;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class RoomController implements Initializable {

    @FXML
    private Button btnCheckin;

    @FXML
    private Button btnSearch;

    @FXML
    private CheckBox cbSelected;

    @FXML
    private Label lblAvRooms;

    @FXML
    private Label lblRoomName;

    @FXML
    private Label lblRoomStatus;

    @FXML
    private Label lblRoomType;

    @FXML
    private TextField tfSearch;

    @FXML
    private ScrollPane scrlpnRoomsList;

    private GridPane gridPane2 = new GridPane();

    public Set<String> getSeletedRooms() {
        return seletedRooms;
    }

    public void setSeletedRooms(Set<String> seletedRooms) {
        this.seletedRooms = seletedRooms;
    }

    private Set<String> seletedRooms = new HashSet<>();
    RoomService roomService = new RoomServiceImp();
    private Stage primaryStage;
    private FXMLLoader fxmlLoader;
    private List<RoomDTO> availableRooms = roomService.getAvailableRoom();
    private List<RoomDTO> unavailableRooms = roomService.getUnavailableRoom();
    private List<RoomDTO> roomDTOList = roomService.getAllRoom();
    RoomDetailController roomDetailController = new RoomDetailController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblAvRooms.setText(String.valueOf(availableRooms.size()) + "/" + String.valueOf(roomDTOList.size()));

        roomDetailController.roomController = this;
        getRoomItem(roomDTOList);


        scrlpnRoomsList.setContent(gridPane2);
        search();
    }

    private final String[] suggestions = {"Available Rooms", "Unavailable Rooms"};
    private boolean textFieldClicked = false;

    private void search() {
        // Create a context menu to display the suggestions.
        ContextMenu contextMenu = new ContextMenu();
        //        for (String suggestion : suggestions) {
        //            MenuItem item = new MenuItem(suggestion);
        //            item.setOnAction(event -> tfSearch.setText(suggestion));
        //            contextMenu.getItems().add(item);
        //        }
        tfSearch.setOnMouseClicked(event -> {
            textFieldClicked = true;
            contextMenu.getItems().clear();
            for (String suggestion : suggestions) {
                MenuItem item = new MenuItem();
                Label dummyForWidth = new Label("Filter: " + suggestion);
                dummyForWidth.setPrefWidth(500);
                item.setGraphic(dummyForWidth);
                item.setOnAction(e -> {
                            tfSearch.setText("Filter: " + suggestion);
                            gridPane2.getChildren().clear();
                            if (suggestion == "Available Rooms") {
                                getRoomItem(availableRooms);
                            } else {
                                getRoomItem(unavailableRooms);
                            }
                        }
                );
                contextMenu.getItems().add(item);
            }
            if (!contextMenu.getItems().isEmpty()) {
                contextMenu.show(tfSearch, Side.BOTTOM, 0, 0);
            } else {
                contextMenu.hide();
            }
        });

        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (tfSearch.getText().isEmpty()) {
                gridPane2.getChildren().clear();
                getRoomItem(roomDTOList);
            }
        });

        tfSearch.setOnKeyReleased(event -> {
            String text = tfSearch.getText();
            if (textFieldClicked && event.getCode() == KeyCode.BACK_SPACE) {
                tfSearch.clear();
                textFieldClicked = false;
            }
            if (text.isEmpty()) {
                gridPane2.getChildren().clear();
                getRoomItem(roomDTOList);
                contextMenu.hide();
            } else {
                contextMenu.getItems().clear();
                for (String suggestion : suggestions) {
                    if (suggestion.toLowerCase().startsWith(text.toLowerCase())) {
                        MenuItem item = new MenuItem(suggestion);
                        item.setOnAction(e -> tfSearch.setText(suggestion));
                        contextMenu.getItems().add(item);
                    }
                }
                if (!contextMenu.getItems().isEmpty()) {
                    contextMenu.show(tfSearch, Side.BOTTOM, 500, 0);
                } else {
                    contextMenu.hide();
                }
            }
        });
    }

    public void setBtnCheckin(Event event) throws IOException {

        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getRoomDetailScene();
        roomDetailController.setRooms(seletedRooms);
        fxmlLoader.setController(roomDetailController);
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();
    }

    private void getRoomItem(List<RoomDTO> rooms) {
        int n = rooms.size();
        int maxCol = 4;
        int rows = Math.round((float) n / maxCol);
        int i = 0;
        for (int row = 0; row < rows; row++) {
            for(int col = 0; col < maxCol ;col++) {
                GridPane gridPane = new GridPane();
                gridPane.setPrefHeight(195.0);
                gridPane.setPrefWidth(200.0);
                // Create ColumnConstraints
                ColumnConstraints column1 = new ColumnConstraints();
                column1.setHalignment(javafx.geometry.HPos.CENTER);
                column1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
                column1.setMaxWidth(153.0);
                column1.setMinWidth(10.0);
                column1.setPrefWidth(153.0);

                ColumnConstraints column2 = new ColumnConstraints();
                column2.setHalignment(javafx.geometry.HPos.CENTER);
                column2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
                column2.setMaxWidth(94.0);
                column2.setMinWidth(10.0);
                column2.setPrefWidth(47.0);

                // Add ColumnConstraints to GridPane
                gridPane.getColumnConstraints().addAll(column1, column2);

                // Create RowConstraints
                RowConstraints row1 = new RowConstraints();
                row1.setMaxHeight(38.0);
                row1.setMinHeight(10.0);
                row1.setPrefHeight(27.0);
                row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

                RowConstraints row2 = new RowConstraints();
                row2.setMaxHeight(113.0);
                row2.setMinHeight(10.0);
                row2.setPrefHeight(113.0);
                row2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

                RowConstraints row3 = new RowConstraints();
                row3.setMaxHeight(77.0);
                row3.setMinHeight(0.0);
                row3.setPrefHeight(17.0);
                row3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

                RowConstraints row4 = new RowConstraints();
                row4.setMaxHeight(64.0);
                row4.setMinHeight(10.0);
                row4.setPrefHeight(17.0);
                row4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
                // Add RowConstraints to GridPane
                gridPane.getRowConstraints().addAll(row1, row2, row3, row4);

                var r = rooms.get(i);
                String RoomName = r.getRoomNo();
                String RoomType = r.getType();
                String RoomStatus = r.getStatus();
                // Create Labels
                Label lblRoomName = new Label();
                Label lblRoomType = new Label();
                Label lblRoomStatus = new Label();

                // Set fx:id for Labels
                lblRoomName.setId("lblRoomName_" + RoomName);
                lblRoomType.setId("lblRoomType_" + RoomName);
                lblRoomStatus.setId("lblRoomStatus_" + RoomName);
                // Set fx:id for Labels
                lblRoomName.setText(RoomName);
                lblRoomType.setText(RoomType);
                lblRoomStatus.setText(RoomStatus);
                String rsStyle = Objects.equals(RoomStatus, "Available") ? "-fx-text-fill: green;" : "-fx-text-fill: red;";
                lblRoomStatus.setStyle(rsStyle);

                // Add Labels to GridPane
                gridPane.add(lblRoomName, 0, 0);
                gridPane.add(lblRoomType, 0, 2);
                gridPane.add(lblRoomStatus, 0, 3);

                // Create CheckBox
                CheckBox cbSelected = new CheckBox();
                cbSelected.setId("cbSelected");
                cbSelected.setMnemonicParsing(false);
                cbSelected.setDisable(!Objects.equals(RoomStatus, "Available"));
                cbSelected.setOnAction(event -> {                   
                        var selectedItem = (Node) event.getSource();
                        Label lr = (Label) selectedItem.getScene().lookup("#lblRoomName_" + RoomName);
                    if (Objects.equals(RoomStatus, "Available")) {
                        if (cbSelected.isSelected()) {
                            seletedRooms.add(lr.getText());
                        } else {
                            seletedRooms.removeIf(s -> s.equals(lr.getText()));
                        }
                    }
                });

                // Add CheckBox to GridPane
                gridPane.add(cbSelected, 1, 0);

                // Create ImageView
                ImageView imageView = new ImageView();
                imageView.setFitHeight(103.0);
                imageView.setFitWidth(123.0);
                imageView.setPickOnBounds(true);
                imageView.setPreserveRatio(true);

                // Set image for ImageView
                imageView.setImage(new Image(getClass().getResourceAsStream("/asset/image/bed.png")));
                // Add ImageView to GridPane
                gridPane.add(imageView, 0, 1);
                gridPane.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2) {
                        seletedRooms = new HashSet<>();
                        var selectedItem = (Node) event.getSource();
                        Label lr = (Label) selectedItem.lookup("#lblRoomName_" + RoomName);

                        seletedRooms.add(lr.getText());
                        roomDetailController.setRooms(seletedRooms);
                        fxmlLoader = FXMLLoaderConstant.getRoomDetailScene();
                        fxmlLoader.setController(roomDetailController);
                        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        try {
                            primaryStage.setScene(new Scene(fxmlLoader.load()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
                gridPane2.add(gridPane, col, row);
                i++;
                if(i==n){
                    return;
                }
            }
        }
    }
}
