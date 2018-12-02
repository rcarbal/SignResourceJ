import org.junit.jupiter.api.Test;
import pojo.PurchaseOrder;
import pojo.Vendor;
import pojo.VendorAverage;
import utils.VendorParser;
import utils.PODataVerifier;
import utils.PurchaseOrderExtractor;
import utils.PurchaseOrderUtils;

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


        //Verify the amount not closed
        int numberNotClosed = PODataVerifier.isNotClosed(averagePo);
        assertTrue(numberNotClosed > 0);

        //Separate the vendors
        ArrayList<Integer> vendors = new ArrayList<>();

        int able = 10457;
        int daktronics = 11505;
        int pwm = 11140;
        int pricevision = 9097;

        vendors.add(able);
        vendors.add(daktronics);
        vendors.add(pwm);
        vendors.add(pricevision);

        ArrayList<Vendor> vendorsPurchaseOrder =
                PurchaseOrderUtils.getPurchaseOrdersByVendor(vendors, averagePo);

        //Get average of vendor in days
        ArrayList<VendorAverage> vendorAverage =
                VendorParser.getAverageOfVendors(vendorsPurchaseOrder);

        assertTrue(vendorAverage.size() > 0);
    }
}
