package pojo;

import java.util.ArrayList;
import java.util.Date;

public class PurchaseOrder implements Comparable<PurchaseOrder>{

    private int purchaseOrderNumber = -1;
    private ArrayList<SRItem> itemArray;
    private Date dateCreated;
    private Date dateClosed;
    private int vendor;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public void addPurchaseOrderNumber(int poNumber) {
        this.purchaseOrderNumber = poNumber;
    }

    public void addToPartNumberArray(ArrayList<SRItem> srItemArrayList) {
        itemArray =  srItemArrayList;
    }

    public int getPoNumber() {
        return purchaseOrderNumber;
    }

    public int getVendor() {
        return vendor;
    }

    public void setVendor(int vendor) {
        this.vendor = vendor;
    }

    @Override
    public int compareTo(PurchaseOrder o) {
        int po1 = this.purchaseOrderNumber;
        int po2 = o.purchaseOrderNumber;
        return Integer.compare(po1, po2);
    }

    public ArrayList<SRItem> getItemArray() {
        return itemArray;
    }
}
