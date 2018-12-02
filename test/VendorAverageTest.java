import org.junit.jupiter.api.Test;
import pojo.PurchaseOrder;
import utils.PODataVerifier;
import utils.PurchaseOrderExtractor;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VendorAverageTest {

    @Test
    void readAverageExcelFile(){
        ArrayList<PurchaseOrder> averagePo = null;
        try {
            averagePo = PurchaseOrderExtractor.getAveragePurchaseOrders();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        assertTrue(averagePo.size() > 0);

        int numberNotClosed = PODataVerifier.isNotClosed(averagePo);

        int notClosed = 0;
        //Check if all have created and received
        for (PurchaseOrder purchaseOrder: averagePo){
            if (purchaseOrder.getDateClosed() == null){
                notClosed++;
            }
        }

        assertTrue(notClosed > 0);

        //Separate the vendors


    }
}
