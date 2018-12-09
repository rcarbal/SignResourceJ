package pojo;

import java.math.BigDecimal;
import java.util.Date;

public class SRItem implements Comparable<SRItem> {

    private String itemNumber;
    private String itemDescription;
    private String itemLineStatus;
    private Date itemDueDate;
    private int vendorNumcer;
    private String vendorName;
    private BigDecimal unitPrice;
    private BigDecimal stdPrice;
    private Date dateEntered;
    private Date dateReceived;
    private int poNumber;
    private int orderedQuantity;

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }


    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getStdPrice() {
        return stdPrice;
    }


    public void addItemNumber(String cellValue) {
        itemNumber = cellValue;
    }

    public void addItemDescription(String cellValue) {
        itemDescription = cellValue;
    }

    public void addLineStatus(String cellValue) {
        itemLineStatus = cellValue;
    }

    public void addItemDueDate(Date date) {
        itemDueDate = date;
    }

    public void addItemVendor(int vendorNumber) {
        this.vendorNumcer = vendorNumber;
    }

    public int getVendorNumcer() {
        return vendorNumcer;
    }

    public void addVendorName(String cellValue) {
        vendorName = cellValue;
    }

    public void addUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void addStdPrice(BigDecimal stdPrice) {
        this.stdPrice = stdPrice;
    }

    public void addItemDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public void addPoNumber(int poNumber) {
        this.poNumber = poNumber;
    }

    public int getPoNumber() {
        return poNumber;
    }


    public Date getDateEntered() {
        return dateEntered;
    }

    public void addItemOrderQuantity(int itemQuantity) {
        this.orderedQuantity = itemQuantity;

    }

    @Override
    public int compareTo(SRItem o) {
        int po1 = this.poNumber;
        int po2 = o.getPoNumber();
        return Integer.compare(po1, po2);

    }
}
