package com.managehotelapp_javafx.controller;
import com.managehotelapp_javafx.dto.RoomFacilitiesDTO;
import com.managehotelapp_javafx.services.imp.RoomDetailServiceImp;
import com.managehotelapp_javafx.services.imp.RoomServiceImp;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomDetailController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Label lblRoom;

    @FXML
    private Label lblRoomType;

    @FXML
    private Label lblRoomStatus;

    @FXML
    private TableColumn<RoomFacilitiesDTO,Integer> tblColNo;
    @FXML
    private TableColumn<RoomFacilitiesDTO,String> tblColCat;
    @FXML
    private TableColumn<RoomFacilitiesDTO,Integer> tblColQt;


    @FXML
    private TableView<RoomFacilitiesDTO> tblFcl;

    public  RoomController roomController ;
    private String roomName = null;
    public void setRoomName(String rooms)
    {
        this.roomName = rooms;
    };
    public String getRoomName()
    {
        return  this.roomName ;
    };
    private RoomDetailServiceImp service = new RoomDetailServiceImp();

    private void setTableItem()
    {
        if(roomName!=null) {
            service.getRoomByName(roomName);

            var list = service.getFacilities();
            ObservableList<RoomFacilitiesDTO> data = FXCollections.observableArrayList();

            for (var item :
                    list) {
                data.add(item);
            }
            //data.add(new RoomFacilitiesDTO(1,"AAA",3));

            tblColNo.setCellValueFactory(new PropertyValueFactory<RoomFacilitiesDTO, Integer>("id"));
            tblColCat.setCellValueFactory(new PropertyValueFactory<RoomFacilitiesDTO, String>("name"));
            tblColQt.setCellValueFactory(new PropertyValueFactory<RoomFacilitiesDTO, Integer>("quantity"));

            tblFcl.setItems(data);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Returning to ROOM..");
            alert.showAndWait();
            var primaryStage = (Stage) lblRoom.getScene().getWindow();
            try {
                primaryStage.setScene(new Scene(FXMLLoaderConstant.getRoomScene().load()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblRoom.setText(lblRoom.getText()+roomName);
        var roomService = new RoomServiceImp();
        var room = service.getRoomByName(roomName);
        lblRoomStatus.setText(room.getRoomStatus().getTitle());

        tblFcl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        btnBack.setOnAction(actionEvent -> {
            ;
            try {
                Stage pstg = (Stage)btnBack.getScene().getWindow();
                pstg.setScene(new Scene(FXMLLoaderConstant.getRoomScene().load()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        setTableItem();
    }
}
