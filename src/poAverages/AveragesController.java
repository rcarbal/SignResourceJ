package poAverages;

import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pojo.LineChartProcessing;
import pojo.VendorAverage;
import services.GetVendorAverage;
import utils.ProcessLineChartDate;
import utils.VendorUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class AveragesController implements Initializable {
    @FXML
    public Text randed_1;
    @FXML
    public Text randed_2;
    @FXML
    public Text randed_3;
    @FXML
    public Text randed_4;
    @FXML
    public Button parse_button;
    @FXML
    public Text rank1Days;
    @FXML
    public Text rank2Days;
    @FXML
    public Text rank3Days;
    @FXML
    public Text rank4Days;
    @FXML
    public Pane pane_for_lineGraph;
    @FXML
    public Button rank1;
    @FXML
    public Button rank2;
    @FXML
    public Button rank3;
    @FXML
    public Button rank4;

    private ArrayList<VendorAverage> vendorAverages;
    private LineChartProcessing data;

    LineChart<Number, Number> averageLineChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setClickListeners();
        loadLineChart();

    }

    private void setClickListeners() {
        parse_button.setOnMouseClicked(parser -> {
            parseAverageExcelFile();
        });

        rank1.setOnMouseClicked(r1 -> {
            showRank1();
        });

        rank2.setOnMouseClicked(r2 -> {
            showRank2();
        });

        rank3.setOnMouseClicked(r3 -> {
            showRank3();
        });

        rank4.setOnMouseClicked(r4 -> {
            showRank4();
        });
    }

    private void showRank4() {
        averageLineChart.getData().clear();
        averageLineChart.getData().add(data.getInfo4().getSeries());
    }

    private void showRank3() {
        averageLineChart.getData().clear();
        averageLineChart.getData().add(data.getInfo3().getSeries());
    }

    private void showRank2() {
        averageLineChart.getData().clear();
        averageLineChart.getData().add(data.getInfo2().getSeries());
    }

    private void showRank1() {
        averageLineChart.getData().clear();
        averageLineChart.getData().add(data.getInfo1().getSeries());
    }

    private void parseAverageExcelFile() {
        Service<ArrayList<VendorAverage>> vendorAverageService = new GetVendorAverage();
        vendorAverageService.setOnSucceeded(e -> {
            vendorAverages = vendorAverageService.getValue();
            Collections.sort(vendorAverages);
            seRankingLEDVendors();
        });
        vendorAverageService.restart();
    }

    private void seRankingLEDVendors() {
        if (vendorAverages == null) {
            System.out.println("VendorAverage is NULL");
        } else {
            loadLineChartData();
            randed_1.setText(VendorUtil.getVendorInIndex(vendorAverages.get(0).getVendorVumber()) + "  ");
            rank1Days.setText(String.valueOf(vendorAverages.get(0).getAverageOfPOs()) + " days "+
                    " Total POs: "+ String.valueOf(vendorAverages.get(0).getPoDays().size()));

            randed_2.setText(VendorUtil.getVendorInIndex(vendorAverages.get(1).getVendorVumber()) + "  ");
            rank2Days.setText(String.valueOf(vendorAverages.get(1).getAverageOfPOs()) + " days " +
                    " Total POs: "+ String.valueOf(vendorAverages.get(1).getPoDays().size()));

            randed_3.setText(VendorUtil.getVendorInIndex(vendorAverages.get(2).getVendorVumber()) + "  ");
            rank3Days.setText(String.valueOf(vendorAverages.get(2).getAverageOfPOs()) + " days " +
                    " Total POs: "+ String.valueOf(vendorAverages.get(2).getPoDays().size()));

            randed_4.setText(VendorUtil.getVendorInIndex(vendorAverages.get(3).getVendorVumber()) + "  ");
            rank4Days.setText(String.valueOf(vendorAverages.get(3).getAverageOfPOs()) + " days "+
                    " Total POs: "+ String.valueOf(vendorAverages.get(3).getPoDays().size()));
        }

    }


    private void loadLineChart() {
        pane_for_lineGraph.getChildren().clear();
        //x-axis
        NumberAxis xAxis = new NumberAxis(1, 365, 10);
        xAxis.setLabel("nth Day In Year");

        //y-axis
        NumberAxis yAxis = new NumberAxis(0, 31, 1);
        yAxis.setLabel("Average Days");

        averageLineChart = new LineChart<>(xAxis, yAxis);
        ;
        averageLineChart.setTitle("LED Vendors Average Days");

        averageLineChart.setMinWidth(950);
        averageLineChart.setMinHeight(650);
        pane_for_lineGraph.getChildren().add(averageLineChart);
    }

    private void loadLineChartData() {
        data = ProcessLineChartDate.getSeries(vendorAverages);

    }
}
