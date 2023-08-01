package com.managehotelapp_javafx.multithread;

import com.managehotelapp_javafx.dto.ServiceDTO;
import com.managehotelapp_javafx.services.ServicesService;
import com.managehotelapp_javafx.services.imp.ServicesServiceImp;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.List;

public class QueryStorage extends Service<GridPane> {

    private GridPane gridPane;
    ServicesService services = new ServicesServiceImp();

    @Override
    protected Task<GridPane> createTask() {
        return new Task<GridPane>() {
            @Override
            protected GridPane call() throws Exception {
                gridPane = new GridPane();
                gridPane.setGridLinesVisible(true);
                gridPane.setAlignment(Pos.CENTER);
                gridPane.setLayoutY(90);
                gridPane.setStyle("-fx-background-color: white");

                List<ServiceDTO> list = services.getServicesList();
                int maxRow = list.size();

                Node[][] contentArray = new Node[2][maxRow];

                int col = 0;
                int i = 0;
                for (int row = 0; row < maxRow; row++) {
                    var service = list.get(i);

                    Label label = new Label(service.getDescription());
                    label.setPadding(new Insets(40));
                    label.setFont(new Font("Arial", 25));
                    contentArray[col][row] = label;
                    gridPane.add(label, col, row);


                    int qtyConsumed = services.findServicesById(service.getId()).getQtyConsumed();
                    int inStock = service.getQuantity();
                    Label label2 = new Label(String.valueOf(inStock - qtyConsumed + "/" + inStock));
                    label2.setPadding(new Insets(40));
                    label2.setFont(new Font("Arial", 25));
                    contentArray[col + 1][row] = label2;

                    gridPane.add(label2, col + 1, row);
                    i++;
                }
                return gridPane;
            }
        };
    }
}
