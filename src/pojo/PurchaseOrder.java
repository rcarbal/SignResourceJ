package pojo;

import java.util.ArrayList;

public class PurchaseOrder implements Comparable<PurchaseOrder>{

    private int purchaseOrderNumber = -1;
    private ArrayList<SRItem> itemArray;

    public void addPurchaseOrderNumber(int poNumber) {
        this.purchaseOrderNumber = poNumber;
    }

    public void addToPartNumberArray(ArrayList<SRItem> srItemArrayList) {
        itemArray =  srItemArrayList;
    }

    public int getPoNumber() {
        return purchaseOrderNumber;
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
