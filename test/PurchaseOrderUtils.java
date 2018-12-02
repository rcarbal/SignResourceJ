import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;
import pojo.DiscrepencyParse;
import pojo.PurchaseOrder;
import utils.ProcessPODescrepencies;
import utils.PurchaseOrderExtractor;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseOrderUtils {

    @Test
    void extractPurchaseOrdersToArray(){

        ArrayList<PurchaseOrder> purchaseOrder = null;
        try {
            purchaseOrder = PurchaseOrderExtractor.
                    getPurchaseOrder("C:/Users/rcarb/OneDrive/Documents/testExcel.xlsx");
        } catch (IOException | ParseException | InvalidFormatException e) {
            e.printStackTrace();
        }
        assert purchaseOrder != null;
        assertTrue(purchaseOrder.size()>0);

    }

    @Test
    void comparePreceDiscrepencies(){

        //standard call for program.
        ArrayList<PurchaseOrder> purchaseOrder = null;
        try {
            purchaseOrder = PurchaseOrderExtractor.
                    getPurchaseOrder("C:/Users/rcarb/OneDrive/Documents/testExcel.xlsx");
        } catch (IOException | ParseException | InvalidFormatException e) {
            e.printStackTrace();
        }

        DiscrepencyParse descrepencies = ProcessPODescrepencies.getDescrepencies(purchaseOrder);
        assertTrue(descrepencies.getDiscrepancyArray().size() >0);
    }

}
