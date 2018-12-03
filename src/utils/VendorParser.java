package utils;

import pojo.LineChartPoint;
import pojo.PurchaseOrder;
import pojo.Vendor;
import pojo.VendorAverage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class VendorParser {
    public static ArrayList<VendorAverage> getAverageOfVendors(ArrayList<Vendor> vendorsPurchaseOrder) {

        ArrayList<VendorAverage> vendorAverages = new ArrayList<>();

        for (Vendor vendor: vendorsPurchaseOrder) {
            VendorAverage vendorAverage = new VendorAverage();
            vendorAverage.setPoDays(new ArrayList<>());
           ArrayList<PurchaseOrder> vendorsPO = vendor.getPurchaseOrders();

           ArrayList<LineChartPoint> points = new ArrayList<>();

           //Get the PO from vendor
           for (PurchaseOrder po: vendorsPO){

               //get Days of week for PO
               Date startDate = po.getDateCreated();
               Date closeDate = po.getDateClosed();
               LineChartPoint point = new LineChartPoint();

               int poTotalBusinessDays = ParseDates.getBusinessDays(startDate, closeDate);
               if (poTotalBusinessDays >0){

                   point.setPoAverageDay(poTotalBusinessDays);
                   Calendar cal = Calendar.getInstance();
                   cal.setTime(closeDate);
                   int nthDay = cal.get(Calendar.DAY_OF_YEAR);
                   point.setPoReceivedInDay(nthDay);
                   points.add(point);

                   vendorAverage.getPoDays().add(poTotalBusinessDays);
               }
           }
           vendorAverage.setPoints(points);
           vendorAverage.setVendorVumber(vendor.getVendorNumber());
            vendorAverages.add(vendorAverage);
        }

        //Get average

        for (VendorAverage vendor: vendorAverages){
            ArrayList<Integer> allPoDays = vendor.getPoDays();
            int totalPos = allPoDays.size();
            int totalDaysOfAllPos = 0;
            for (int poDays : allPoDays){
                totalDaysOfAllPos = totalDaysOfAllPos +poDays;
            }

            //get average
            int average = totalDaysOfAllPos / totalPos;
            vendor.setAverageOfPOs(average);

        }

        return vendorAverages;
    }
}
