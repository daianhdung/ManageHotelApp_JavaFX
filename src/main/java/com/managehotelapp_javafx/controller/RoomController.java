package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.HelloApplication;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.services.RoomService;
import com.managehotelapp_javafx.services.imp.RoomServiceImp;
import com.managehotelapp_javafx.utils.enumpackage.RoomStatus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class RoomController implements Initializable {
    RoomService roomService = new RoomServiceImp();

    @FXML
    private Label countAllRoom, countRoomAvailable;
    @FXML
    private GridPane gridTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<RoomDTO> roomDTOList = roomService.getAllRoom();
        countRoomAvailable.setText(String.valueOf(roomDTOList.stream()
                .filter(item -> item.getStatus().equals(RoomStatus.Available.toString()))
                .count()
        ));
        countAllRoom.setText(String.valueOf(roomDTOList.size()));

        int size = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                Button button = createButtonRoomAvailable(roomDTOList.get(size).getStatus());
                gridTable.add(button, j, i);
                Label label1 = createLabel(roomDTOList.get(size).getRoomNo());
                Label label2 = createLabel(roomDTOList.get(size).getType());
                GridPane.setMargin(label1, new Insets(0, 0, 110, 0));
                GridPane.setMargin(label2, new Insets(110, 0, 0, 0));
                gridTable.add(label1, j, i);
                gridTable.add(label2, j, i);
                if(size == roomDTOList.size() - 1){
                    break;
                }else{
                    size++;
                }
            }
            if(size == roomDTOList.size() - 1){
                break;
            }
        }
    }

    private Label createLabel(String text){
        Label label = new Label(text);
        return label;
    }


    private Button createButtonRoomAvailable(String roomStatus) {
        Button button = new Button();
        button.setPrefHeight(50);
        button.setPrefWidth(87);
       if( roomStatus.equals(RoomStatus.Available.toString())){
            button.setStyle("-fx-background-color: green;");
            button.setGraphic(createButtonContent("green"));
        }else{
           button.setStyle("-fx-background-color: red;");
           button.setGraphic(createButtonContent("red"));
       }
        return button;
    }


    private ImageView createButtonContent(String color) {
        try{
            ImageView imageView = new ImageView(getClass().getResource("/asset/image/bedroom_sleeping_4602.png").toExternalForm());
            imageView.setFitHeight(71);
            imageView.setFitWidth(59);
            imageView.setStyle("-fx-background-color:" + color + "; -fx-border-color: white;");
            return imageView;
        }catch (Exception e){
            System.out.println("Error loading image");
            e.printStackTrace();
            return null;
        }
    }
}
