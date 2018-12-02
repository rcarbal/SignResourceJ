package services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pojo.DiscrepencyParse;
import pojo.PurchaseOrder;
import utils.ProcessPODescrepencies;
import utils.PurchaseOrderExtractor;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class GetDiscrepancy extends Service<DiscrepencyParse> {

    @Override
    protected Task<DiscrepencyParse> createTask() {
        return new Task<>() {
            @Override
            protected DiscrepencyParse call() throws Exception {
                //standard call for program.
                ArrayList<PurchaseOrder> purchaseOrder = null;
                try {
                    purchaseOrder = PurchaseOrderExtractor.
                            getPurchaseOrder("C:/Users/rcarb/OneDrive/Documents/testExcel.xlsx");
                } catch (IOException | ParseException | InvalidFormatException e) {
                    e.printStackTrace();
                }

                return ProcessPODescrepencies.getDescrepencies(purchaseOrder);
            }
        };
    }
}
