package pojo;

import java.util.ArrayList;

public class DiscrepencyParse {
    private int numberOfItemsWithDiscrepancies;
    private ArrayList<SRItem> discrepancyArray;
    private int totalPurchaseOrder;

    public int getNumberOfItemsWithDiscrepancies() {
        return numberOfItemsWithDiscrepancies;
    }

    public void addTotalItemsWithDescrepency(int numberItems) {
        this.numberOfItemsWithDiscrepancies = numberItems;
    }

    public void addArrayListOfDescrepencies(ArrayList<SRItem> srItemArrayList) {
        this.discrepancyArray = srItemArrayList;
    }

    public ArrayList<SRItem> getDiscrepancyArray() {
        return discrepancyArray;
    }

    public void addTotalPurchaseOrder(int size) {
        totalPurchaseOrder = size;
    }

    public int getTotalPurchaseOrder() {
        return totalPurchaseOrder;
    }
}
