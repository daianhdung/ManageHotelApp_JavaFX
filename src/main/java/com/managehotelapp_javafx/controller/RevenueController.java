package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.dto.CustomerDTO;
import com.managehotelapp_javafx.dto.InvoiceDTO;
import com.managehotelapp_javafx.services.imp.RevenueServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class RevenueController implements Initializable {

    @FXML
    private Button btnNm;

    @FXML
    private Button btnNm1;

    @FXML
    private Button btnPm;

    @FXML
    private Button btnPm1;

    @FXML
    private LineChart<Number, Number> chartM;

    @FXML
    private LineChart<Number, Number> chartW;

    @FXML
    private LineChart<Number, Number> chartY;


    @FXML
    private DatePicker dtpF_Y;

    @FXML
    private DatePicker dtpM;




    @FXML
    private ListView<InvoiceDTO> listViewW;


    @FXML
    private ListView<InvoiceDTO> listviewM;

    @FXML
    private ListView<InvoiceDTO> listviewY;

    @FXML
    private Label lblDates;

    @FXML
    private  Label lblTotal;
    @FXML
    private  Label lblTotal1;
    @FXML
    private  Label lblTotal2;


    @FXML
    private NumberAxis axMonth;

    @FXML
    private NumberAxis axMonth2;

    @FXML
    private NumberAxis axMonth3;



    @FXML
    private NumberAxis axT;

    @FXML
    private NumberAxis axT2;

    @FXML
    private NumberAxis axT3;



    private LocalDate localDate ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dtpF_Y.setValue(LocalDate.now());
        dtpM.setValue(LocalDate.now());

        setWeekLineChart(LocalDate.now());
        setYearLineChart();
        setMonthLineChart();

        btnNm.setOnAction(actionEvent -> {
            LocalDate selectedDate = dtpM.getValue();
            if (selectedDate != null) {
                LocalDate newDate = selectedDate.plusMonths(1);
                dtpM.setValue(newDate);
            }
            chartM.getData().clear();
            listviewM.getItems().clear();
            setMonthLineChart();
        });

        btnPm.setOnAction(actionEvent -> {
            LocalDate selectedDate = dtpM.getValue();
            if (selectedDate != null) {
                LocalDate newDate = selectedDate.minusMonths(1);
                dtpM.setValue(newDate);
            }
            listviewM.getItems().clear();
            chartM.getData().clear();
            setMonthLineChart();
        });
        this.localDate = LocalDate.now();
        btnNm1.setOnAction(actionEvent -> {
            LocalDate newDate = localDate.plusWeeks(1);
            this.localDate = newDate;
            listViewW.getItems().clear();
            chartW.getData().clear();
            setWeekLineChart(newDate);
        });
        btnPm1.setOnAction(actionEvent -> {
            LocalDate newDate = localDate.minusWeeks(1);
            this.localDate = newDate;
            listViewW.getItems().clear();
            chartW.getData().clear();
            setWeekLineChart(newDate);
        });
        dtpM.setOnAction(actionEvent -> {
            listviewM.getItems().clear();
            chartM.getData().clear();
            setMonthLineChart();
        });
        dtpF_Y.setOnAction(actionEvent -> {
            chartY.getData().clear();
            listviewY.getItems().clear();
            setYearLineChart();
        });
    }

    private void setMonthLineChart()
    {
        RevenueServiceImp revenueServiceImp = new RevenueServiceImp();
        revenueServiceImp.setDate(dtpM.getValue());
        List<InvoiceDTO> monthList = revenueServiceImp.getMonthly();
        ObservableList<InvoiceDTO> observableMonthList = FXCollections.observableArrayList(monthList);
        listviewM.setItems(observableMonthList);
        listviewM.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(InvoiceDTO invoiceDTO, boolean empty) {
                super.updateItem(invoiceDTO, empty);
                setText(empty || invoiceDTO == null ? null : "ID: " + invoiceDTO.getId() + ", Total:"+ invoiceDTO.getPaymentAmount() +", Date: " + invoiceDTO.getCreatedAt());
            }
        });
        int lastDayOfMonth = YearMonth.of(revenueServiceImp.getDate().getYear(),revenueServiceImp.getDate().getMonth()).atEndOfMonth().getDayOfMonth();
        axMonth.setLowerBound(1);
        axMonth.setUpperBound(lastDayOfMonth);
        NumberAxis axisX = (NumberAxis)chartM.getXAxis();
        axisX.setTickUnit(1);
        axMonth.setLabel("Month");

        axT.setLabel("Total");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Revenue by month");
        Calendar calendar = Calendar.getInstance();
        int td = 0, mt = 0, i = 1, t =0;
        for (var item : monthList) {
            calendar.setTimeInMillis(item.getCreatedAt().getTime());
            int dateValue = calendar.get(Calendar.DAY_OF_MONTH);
            if(dateValue == td) {
                mt+=item.getPaymentAmount();
                t+=mt;
            }else
            {
                series.getData().add(new XYChart.Data<>(dateValue,mt));
                td = dateValue;
                mt = 0;
            }
        }
//        for(int k = 1;k <= lastDayOfMonth;k++)
//        {
//            for (var item : monthList) {
//                calendar.setTimeInMillis(item.getCreatedAt().getTime());
//                int dateValue = calendar.get(Calendar.DAY_OF_MONTH);
//                if(dateValue == k) {
//                    mt+=item.getPaymentAmount();
//                    t+=mt;
//                }else
//                {
//                    mt = 0;
//                    series.getData().add(new XYChart.Data<>(k,mt));
//                }
//            }
//        }
        chartM.getData().add(series);
        lblTotal.setText(String.valueOf(t));
    }
    private void setWeekLineChart(LocalDate localDate)
    {
        RevenueServiceImp revenueServiceImp = new RevenueServiceImp();
        revenueServiceImp.setDate(localDate);
        List<InvoiceDTO> weekList = revenueServiceImp.getWeekly();
        ObservableList<InvoiceDTO> observableWeekList = FXCollections.observableArrayList(weekList);
        listViewW.setItems(observableWeekList);
        listViewW.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(InvoiceDTO invoiceDTO, boolean empty) {
                super.updateItem(invoiceDTO, empty);
                setText(empty || invoiceDTO == null ? null : "ID: " + invoiceDTO.getId() + ", Total:"+ invoiceDTO.getPaymentAmount() +", Date: " + invoiceDTO.getCreatedAt());
            }
        });
        axMonth2.setLowerBound(1);
        axMonth2.setUpperBound(7);
        axMonth2.setLabel("Week");
        axT2.setLabel("Total");
        NumberAxis axisX = (NumberAxis)chartW.getXAxis();
        axisX.setTickUnit(1);

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        Calendar calendar = Calendar.getInstance();
        int _td = 0, mt = 0, i = 1, t =0;
        for (var item : weekList) {
            calendar.setTimeInMillis(item.getCreatedAt().getTime());
            int dateValue = calendar.get(Calendar.DAY_OF_WEEK);
            if(dateValue == _td) {
                mt+=item.getPaymentAmount();
                t+=mt;
            }else
            {
                series.getData().add(new XYChart.Data<>(dateValue,mt));
                _td = dateValue;
                mt = 0;
            }
        }

        chartW.getData().add(series);
        String fd = revenueServiceImp.getFromDate().toString();
        String td = revenueServiceImp.getToDate().toString();
        lblDates.setText(fd + " - " + td);
        lblTotal1.setText(String.valueOf(t));
    }

    private void setYearLineChart()
    {
        RevenueServiceImp revenueServiceImp = new RevenueServiceImp();
        revenueServiceImp.setDate(dtpF_Y.getValue());
        List<InvoiceDTO> yearList = revenueServiceImp.getYearly();
        ObservableList<InvoiceDTO> observableYearList = FXCollections.observableArrayList(yearList);

        listviewY.setItems(observableYearList);
        listviewY.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(InvoiceDTO invoiceDTO, boolean empty) {
                super.updateItem(invoiceDTO, empty);
                setText(empty || invoiceDTO == null ? null : "ID: " + invoiceDTO.getId() + ", Total:"+ invoiceDTO.getPaymentAmount() +", Date: " + invoiceDTO.getCreatedAt());
            }
        });

        axMonth3.setLabel("Year");
        axT3.setLabel("Total");

        int _td = 0, mt = 0, i = 0, t =0;
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        Calendar calendar = Calendar.getInstance();

        for (var item : yearList) {
            calendar.setTimeInMillis(item.getCreatedAt().getTime());
            int dateValue = calendar.get(Calendar.MONTH);
            i = item.getPaymentAmount();
            if(dateValue == _td) {
                mt+=item.getPaymentAmount();
                t+=mt;
            }else
            {
                series.getData().add(new XYChart.Data<>(dateValue,mt));
                _td = dateValue;
                mt = 0;
            }
        }

        chartY.getData().add(series);
        lblTotal2.setText(String.valueOf(t));
    }
}
