package utils;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import pojo.PurchaseOrder;
import pojo.SRItem;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class PurchaseOrderExtractor {
    public static ArrayList<PurchaseOrder> getPurchaseOrder(String s) throws IOException, ParseException, InvalidFormatException {

        ArrayList<PurchaseOrder> poArray = new ArrayList<>();

        File file = new File("C:/Users/rcarb/OneDrive/Documents/testExcel.xlsx");

//         WorkbookFactory.create(file);
        // Getting the Sheet at rowIndex zero

        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Row> rowIterator = sheet.rowIterator();

        int currentPO = -2;
        PurchaseOrder po = new PurchaseOrder();
        int rowIndex = 0;
        ArrayList<SRItem> srItemArrayList = new ArrayList<>();

        for (Row row : sheet) {
            //When the row changes a new item should be added
            SRItem item = new SRItem();


            if (rowIndex == 0) {
                //
            } else {
                int columnIndex = 0;

                for (Cell cell : row) {

                    String cellValue = dataFormatter.formatCellValue(cell);

                    switch (columnIndex) {
                        case 0:
                            int parsingPo = Integer.valueOf(cellValue);
                            po.addPurchaseOrderNumber(currentPO);
                            if (currentPO == -2) {
                                currentPO = parsingPo;
                            }
                            if (currentPO != parsingPo) {

                                // save PO to array.
                                po.addToPartNumberArray(srItemArrayList);
                                poArray.add(po);
                                po = new PurchaseOrder();
                                srItemArrayList = new ArrayList<>();
                                currentPO = parsingPo;
                            }
                            break;
                        case 1:
                            item.addItemNumber(cellValue);
                            break;
                        case 2:
                            //2 is for SO due date
                            break;
                        case 3:
                            //SR item description
                            item.addItemDescription(cellValue);
                            break;
                        case 4:
                            //Item line status
                            item.addLineStatus(cellValue);
                            break;
                        case 5:
                            //Item due date
                            SimpleDateFormat objSDF = new SimpleDateFormat("MM/dd/yy");
                            Date date = objSDF.parse(cellValue);
                            item.addItemDueDate(date);
                            break;
                        case 6:
                            //vendor number
                            int vendorNumber = Integer.valueOf(cellValue);
                            item.addItemVendor(vendorNumber);
                            break;
                        case 7:
                            //vendor name
                            item.addVendorName(cellValue);
                            break;
                        case 8:
                            //SO DT ENT
                            break;
                        case 9:
                            //unit price
                            String strNew1 = cellValue.replace(",", "");
                            if (strNew1.equals("")) strNew1 = "0";
                            BigDecimal unitPrice = new BigDecimal(strNew1);
                            item.addUnitPrice(unitPrice);
                            break;
                        case 10:
                            //STD cost
                            String strNew = cellValue.replace(",", "");
                            if (strNew.equals("")) strNew1 = "0";
                            BigDecimal stdPrice = new BigDecimal(strNew);
                            item.addStdPrice(stdPrice);
                            break;
                        case 11:
                            //Prod code
                            break;
                        case 12:
                            //date entered
                            SimpleDateFormat objSDFDtEnt = new SimpleDateFormat("MM/dd/yy");
                            Date date2 = objSDFDtEnt.parse(cellValue);
                            item.addItemDateEntered(date2);
                            break;
                        case 13:
                            //dateReceived
                            String a = "";
                            break;
                    }
                    columnIndex++;
                }
            }
            if (rowIndex > 0) {
                item.addPoNumber(currentPO);
                srItemArrayList.add(item);
                rowIndex++;
            } else {
                rowIndex++;
            }

        }
        po.addToPartNumberArray(srItemArrayList);
        poArray.add(po);
        return poArray;

    }

    public static ArrayList<PurchaseOrder> getAveragePurchaseOrders() throws IOException, ParseException {

        ArrayList<PurchaseOrder> poArray = new ArrayList<>();

        File file = new File("C:/Users/rcarb/OneDrive/Documents/pop.xlsx");

//        WorkbookFactory.create(file);
        // Getting the Sheet at rowIndex zero

        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Row> rowIterator = sheet.rowIterator();

        ArrayList<SRItem> srItemArrayList = new ArrayList<>();

        for (Row row : sheet) {

            SRItem item = new SRItem();

            for (Cell cell : row) {

                String cellValue = dataFormatter.formatCellValue(cell);
                int cellColumnIndex = cell.getColumnIndex();
                int cellRowIndex = cell.getRowIndex();

                if (cellRowIndex == 0) {
                    String a ="";
                } else {

                    switch (cellColumnIndex) {
                        case 0:

                            if (!isInteger(cellValue)){
                                break;
                            }
                            int parsingPo = Integer.valueOf(cellValue);
                            item.addPoNumber(parsingPo);
                            break;
                        case 25:

                            //Date entered
                            SimpleDateFormat objSDF = new SimpleDateFormat("MM/dd/yy");
                            Date dateEntered = objSDF.parse(cellValue);
                            item.addItemDateEntered(dateEntered);
                            break;
                        case 26:
                            //Last Dare received
                            SimpleDateFormat objSDFDtEnt = new SimpleDateFormat("MM/dd/yy");
                            Date lastDateReceived = objSDFDtEnt.parse(cellValue);
                            item.setDateReceived(lastDateReceived);
                            break;
                        case 27:
                            //vendor number
                            int vendorNumber = Integer.valueOf(cellValue);
                            item.addItemVendor(vendorNumber);
                            break;
                    }
                    //add the SR to new PO.

                }
            }
            int poIndex = getPurchaseOrderIndexFromArray(item.getPoNumber(), poArray);
            if (item.getPoNumber() == 0){
                //Hold
            }

            //If PO does not exist then add to new PO and add to arrayof SR objects
            else if (poIndex == -1){
                srItemArrayList.add(item);

                PurchaseOrder purchaseOrder = new PurchaseOrder();
                purchaseOrder.addPurchaseOrderNumber(item.getPoNumber());
                purchaseOrder.addToPartNumberArray(srItemArrayList);

                //set the created date
                purchaseOrder.setDateCreated(item.getDateEntered());

                //Set the canceled date
                Date dateClosed = item.getDateReceived();
                purchaseOrder.setDateClosed(dateClosed);
                purchaseOrder.setVendor(item.getVendorNumcer());

                poArray.add(purchaseOrder);
                srItemArrayList = new ArrayList<>();
            }
            else if (poIndex> -1){
                //Replace the current PO with a new PO instance
                PurchaseOrder purchaseOrder = poArray.get(poIndex);
                purchaseOrder.getItemArray().add(item);

                //Set created date
                if (purchaseOrder.getDateCreated() != null){
                    //Compare Dates
                    Date createdDate = purchaseOrder.getDateCreated();
                    Date srDateCreated = item.getDateEntered();
                    int numberOfDays = DateComaparer.compareDates(createdDate, srDateCreated);
                }
                else if (purchaseOrder.getDateCreated() == null){
                    Date srDateCreated = item.getDateEntered();
                    purchaseOrder.setDateCreated(srDateCreated);
                }

                //Set date received
                if (purchaseOrder.getDateClosed() == null){
                    purchaseOrder.setDateCreated(item.getDateReceived());
                }
                else if (purchaseOrder.getDateClosed() !=null){
                    Date purchaseOrderClosedDate = purchaseOrder.getDateClosed();
                    Date srItemClosedDate = item.getDateReceived();

                    //Compare dates
                    int days = DateComaparer.compareDates(purchaseOrderClosedDate, srItemClosedDate);
                    if (days >0){
                        purchaseOrder.setDateClosed(srItemClosedDate);
                    }
                }

                purchaseOrder.setVendor(item.getVendorNumcer());
                poArray.set(poIndex, purchaseOrder);
                srItemArrayList = new ArrayList<>();
            }
        }
        return poArray;
    }

    private static int getPurchaseOrderIndexFromArray(int poNumber, ArrayList<PurchaseOrder> poArray) {

        if (poArray == null|| poArray.size() < 1 ){
            return -1;
        }
        //Find po in
        for (PurchaseOrder po: poArray ) {
            if (po.getPoNumber() == poNumber){
                return poArray.indexOf(po);
            }
        }
        return -1;
    }

    public static boolean isInteger( String input ) {

        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
}
