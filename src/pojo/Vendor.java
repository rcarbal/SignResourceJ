package pojo;

import java.util.ArrayList;

public class Vendor {

    private ArrayList<PurchaseOrder> purchaseOrders;
    private int vendorNumber;


    public ArrayList<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(ArrayList<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public int getVendorNumber() {
        return vendorNumber;
    }

    public void setVendorNumber(int vendorNumber) {
        this.vendorNumber = vendorNumber;
    }
}
