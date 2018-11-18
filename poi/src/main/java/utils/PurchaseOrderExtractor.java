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

        Workbook workbook = WorkbookFactory.create(file);
        // Getting the Sheet at rowIndex zero
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
                            if (strNew1.equals(""))strNew1 = "0";
                            BigDecimal unitPrice = new BigDecimal(strNew1);
                            item.addUnitPrice(unitPrice);
                            break;
                        case 10:
                            //STD cost
                            String strNew = cellValue.replace(",", "");
                            if (strNew.equals(""))strNew1 = "0";
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
            if(rowIndex > 0){
                item.addPoNumber(currentPO);
                srItemArrayList.add(item);
                rowIndex++;
            }else {
                rowIndex++;
            }

        }
        po.addToPartNumberArray(srItemArrayList);
        poArray.add(po);
        return poArray;

    }
}
