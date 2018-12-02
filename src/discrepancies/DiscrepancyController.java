package discrepancies;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pojo.DiscrepencyParse;
import services.GetDiscrepancy;

import java.net.URL;
import java.util.ResourceBundle;

public class DiscrepancyController implements Initializable {

    public Text purchase_order_text;
    public Text discrepancy_text;
    //remove later
    private DiscrepencyParse parse;


    private DiscrepencyModel model = new DiscrepencyModel();

    public Button ok_button;
    public Button parse_button;
    @FXML
    private PieChart pieChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                new PieChart.Data("NA", 0),
                new PieChart.Data("NA", 1),
                new PieChart.Data("NA", 0));

        pieChart.setData(pieChartData);
        clickForParseFile();

    }

    private void clickForParseFile() {
        parse_button.setOnMouseClicked(e -> {
//            model.getExcelDiscrepancyParse();

            //This is will be removed
            Service<DiscrepencyParse> service = new GetDiscrepancy();
            service.setOnSucceeded(i -> {
                parse = service.getValue();
                setPiechart();
            });
            service.restart();
        });
    }

    private void setPiechart () {
        int discrepancies = parse.getDiscrepancyArray().size();
        int totalItems = parse.getNumberOfItemsWithDiscrepancies();

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                new PieChart.Data("", totalItems),
                new PieChart.Data("Discrpancies", discrepancies));
        pieChart.setData(pieChartData);
        purchase_order_text.setText(String.valueOf(totalItems));
        discrepancy_text.setText(String.valueOf(discrepancies));
    }
}
