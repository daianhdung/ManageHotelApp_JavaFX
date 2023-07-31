package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.services.RoomService;
import com.managehotelapp_javafx.services.imp.RoomServiceImp;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    CheckInController checkInController = new CheckInController();
    RoomDetailController roomDetailController = new RoomDetailController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblAvRooms.setText(String.valueOf(availableRooms.size()) + "/" + String.valueOf(roomDTOList.size()));

        checkInController.roomController = this;
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
        fxmlLoader = FXMLLoaderConstant.getCheckInScene();
        fxmlLoader.setController(checkInController);
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
                var r = rooms.get(i);
                String roomName = r.getRoomNo();
                String roomType = r.getType();
                String roomStatus = r.getStatus();
                GridPane root = new GridPane();
                root.setPrefHeight(131.0);
                root.setPrefWidth(222.0);
                Insets rootMargin = new Insets(20);
                GridPane.setMargin(root, rootMargin);
                root.setId(roomName);
                Glow glow = new Glow(0.5);
                root.setOnMouseEntered(event -> root.setEffect(glow));
                root.setOnMouseExited(event -> root.setEffect(null));

                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
                columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
                columnConstraints.setMinWidth(10.0);
                columnConstraints.setPrefWidth(100.0);
                root.getColumnConstraints().add(columnConstraints);

                RowConstraints rowConstraints1 = new RowConstraints();
                rowConstraints1.setMaxHeight(93.33332824707031);
                rowConstraints1.setMinHeight(10.0);
                rowConstraints1.setPrefHeight(90.0);
                rowConstraints1.setValignment(javafx.geometry.VPos.CENTER);
                rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
                root.getRowConstraints().add(rowConstraints1);

                RowConstraints rowConstraints2 = new RowConstraints();
                rowConstraints2.setMaxHeight(54.33331298828125);
                rowConstraints2.setMinHeight(10.0);
                rowConstraints2.setPrefHeight(41.0);
                rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
                root.getRowConstraints().add(rowConstraints2);

                GridPane gridPane = new GridPane();
                gridPane.setId("_"+roomName);
                gridPane.setAlignment(javafx.geometry.Pos.CENTER);
                gridPane.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
                gridPane.setStyle("-fx-background-color: " + (roomStatus.equals("Available") ? "#9EE20C;" : "#FF0000;"));
                GridPane.setHalignment(gridPane, javafx.geometry.HPos.LEFT);

                ColumnConstraints innerColumnConstraints = new ColumnConstraints();
                innerColumnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
                innerColumnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
                innerColumnConstraints.setMinWidth(10.0);
                gridPane.getColumnConstraints().add(innerColumnConstraints);

                RowConstraints innerRowConstraints = new RowConstraints();
                innerRowConstraints.setMinHeight(10.0);
                innerRowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
                gridPane.getRowConstraints().add(innerRowConstraints);

                Label innerLabel = new Label(roomName);
                innerLabel.setAlignment(javafx.geometry.Pos.TOP_LEFT);
                innerLabel.setTextFill(Color.WHITE);
                innerLabel.setFont(new Font(55.0));
                gridPane.getChildren().add(innerLabel);

                Label outerLabel = new Label(roomType);
                outerLabel.setPrefHeight(35.0);
                outerLabel.setPrefWidth(217.0);
                outerLabel.setTextFill(Color.WHITE);
                outerLabel.setFont(new Font(20.0));
                GridPane.setRowIndex(outerLabel, 1);
                root.setOnMouseClicked(event -> {
                    var selectedItem = (Node) event.getSource();
                    GridPane gp = (GridPane) selectedItem.lookup("#_" + roomName);
                    if (event.getClickCount() == 2) {
                        roomDetailController.setRoomName(roomName);
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
                root.getChildren().addAll(gridPane, outerLabel);
                gridPane2.add(root, col, row);
                i++;
                if(i==n){
                    return;
                }
            }
        }
    }
}
