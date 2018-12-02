package discrepancies;

import javafx.concurrent.Service;
import pojo.DiscrepencyParse;
import services.GetDiscrepancy;

public class DiscrepencyModel {

    private DiscrepencyParse data;



    public void getExcelDiscrepancyParse() {

        Service<DiscrepencyParse> service = new GetDiscrepancy();
        service.setOnSucceeded(e->{
            data = service.getValue();
        });
        service.restart();
    }
}
