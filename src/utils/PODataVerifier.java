package utils;

import pojo.PurchaseOrder;

import java.util.ArrayList;

public class PODataVerifier {
    public static int isNotClosed(ArrayList<PurchaseOrder> averagePo) {

        int notClosed = 0;
        //Check if all have created and received
        for (PurchaseOrder purchaseOrder : averagePo) {
            if (purchaseOrder.getDateClosed() == null) {
                notClosed++;
            }
        }
        return notClosed;
    }
}
