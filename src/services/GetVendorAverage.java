package services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import pojo.PurchaseOrder;
import pojo.Vendor;
import pojo.VendorAverage;
import utils.PurchaseOrderExtractor;
import utils.PurchaseOrderUtils;
import utils.VendorParser;

import java.util.ArrayList;

public class GetVendorAverage extends Service<ArrayList<VendorAverage>> {
    @Override
    protected Task<ArrayList<VendorAverage>> createTask() {
        return new Task<>() {
            @Override
            protected ArrayList<VendorAverage> call() throws Exception {

                //Get all PO's in excel sheet
                ArrayList<PurchaseOrder> averagePo = null;
                averagePo = PurchaseOrderExtractor.getAveragePurchaseOrders();

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
                return vendorAverage;
            }
        };
    }
}
