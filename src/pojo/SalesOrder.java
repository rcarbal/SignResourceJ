package pojo;

import java.util.ArrayList;
import java.util.Date;

public class SalesOrder {
    private Date dueDate;
    private Date createdDate;
    private int soNumber;
    private ArrayList<SRItem> itemsToOrder;


    public void addDueDate(Date salesOrderDate) {
        this.dueDate = salesOrderDate;
    }

    public void addCreatedDate(Date salesCreatedDate) {
        this.createdDate = salesCreatedDate;
    }

    public void addSONumber(int soNumber) {
        this.soNumber = soNumber;
    }

    public int getSalesOrderNumber() {
        return soNumber;
    }

    public ArrayList<SRItem> getSRItemArray() {
        return itemsToOrder;
    }

    public void setSRItemArray(ArrayList<SRItem> itemArray) {
        if (this.itemsToOrder != null)this.itemsToOrder.clear();
        this.itemsToOrder = itemArray;
    }
}
