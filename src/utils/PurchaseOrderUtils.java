package utils;

import pojo.PurchaseOrder;
import pojo.Vendor;

import java.util.ArrayList;

public class PurchaseOrderUtils {


    public static ArrayList<Vendor> getPurchaseOrdersByVendor(ArrayList<Integer> listOfVendors, ArrayList<PurchaseOrder> arrayOfParsedPOs) {

        ArrayList<Vendor> arrayOfArrays = new ArrayList<>();

        //Cereate the array for the vendor
        for (Integer vendorNumber :listOfVendors) {
            switch (vendorNumber){
                case 10457:
                    Vendor aatUSA = new Vendor();
                    aatUSA.setVendorNumber(vendorNumber);
                    aatUSA.setPurchaseOrders(new ArrayList<>());
                    arrayOfArrays.add(aatUSA);
                    break;
                case 11505:
                    Vendor daktronics = new Vendor();
                    daktronics.setVendorNumber(vendorNumber);
                    daktronics.setPurchaseOrders(new ArrayList<>());
                    arrayOfArrays.add(daktronics);
                    break;

                case 11140:
                    Vendor pwm = new Vendor();
                    pwm.setVendorNumber(vendorNumber);
                    pwm.setPurchaseOrders(new ArrayList<>());
                    arrayOfArrays.add(pwm);
                    break;

                case 9097:
                    Vendor pricevision = new Vendor();
                    pricevision.setVendorNumber(vendorNumber);
                    pricevision.setPurchaseOrders(new ArrayList<>());
                    arrayOfArrays.add(pricevision);
            }
        }

        //Parse all Purchase orders and add to proper vendor
        for (PurchaseOrder po: arrayOfParsedPOs) {
            //need to get vendor
            int vendorInt = po.getVendor();

            int vendorIndex = -1;
            //find vendor
            for (Vendor vendor : arrayOfArrays) {
                int vendorNumber = vendor.getVendorNumber();
                if (vendorNumber == vendorInt){
                    vendorIndex = arrayOfArrays.indexOf(vendor);
                }
            }


            //Add po to vendors arraylist if PO is part of list
            if (vendorIndex != -1){
                arrayOfArrays.get(vendorIndex).getPurchaseOrders().add(po);
            }
        }
        return arrayOfArrays;

    }
}
