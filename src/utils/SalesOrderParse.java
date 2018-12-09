package utils;

import org.apache.poi.ss.usermodel.*;
import pojo.SRItem;
import pojo.SalesOrder;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class SalesOrderParse {
    public static ArrayList<SalesOrder> getAllSalesOrders() throws IOException, ParseException {
        ArrayList<SalesOrder> salesOrders = new ArrayList<>();
        File file = new File("C:/Users/rcarb/OneDrive/Documents/signresource/salesorders.xlsx");


        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Row> rowIterator = sheet.rowIterator();





        for (Row row: sheet) {
            SalesOrder salesOrder = new SalesOrder();

            SRItem item = new SRItem();

            for (Cell cell: row) {
                if (cell.getRowIndex() == 0){
                    continue;
                }
                else {
                    int columnIndex = cell.getColumnIndex();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    switch (columnIndex){
                        case 0:
                            //Due date
                            if (cellValue == null)break;;
                            SimpleDateFormat objSDF = new SimpleDateFormat("MM/dd/yy");
                            Date salesOrderDate = objSDF.parse(cellValue);
                            salesOrder.addDueDate(salesOrderDate);
                            break;
                        case 1:
                            //SO Created Date
                            if (cellValue==null)break;
                            SimpleDateFormat objSDF2 = new SimpleDateFormat("MM/dd/yy");
                            Date salesCreatedDate = objSDF2.parse(cellValue);
                            salesOrder.addCreatedDate(salesCreatedDate);
                            break;
                        case 5:
                            //SO Number
                            if (cellValue==null)break;
                            int soNumber = Integer.parseInt(cellValue);
                            salesOrder.addSONumber(soNumber);
                            break;
                        case 9:
                            //Part Number
                            if (cellValue==null)break;
                            item.addItemNumber(cellValue);
                            break;
                        case 13:
                            //Item Quantity
                            if (cellValue==null)break;
                            int itemQuantity = Integer.parseInt(cellValue);
                            item.addItemOrderQuantity(itemQuantity);
                            break;
                        case 26:

                            //Check if there is already a sales order in the @salesOrder
                            if (salesOrders.size() > 0){

                                boolean found = false;

                                for (SalesOrder salesOrder1: salesOrders){
                                    if (salesOrder1.getSalesOrderNumber() == salesOrder.getSalesOrderNumber()){

                                        //add the item to the current SalesOrder in the arralylist
                                        salesOrder1.getSRItemArray().add(item);
                                        System.out.println(String.valueOf(salesOrder1.getSalesOrderNumber()));
                                        found = true;
                                        break;
                                    }

                                }
                                if (!found){
                                        //if current row so is not found, add item to sales order, and sales order to
                                        // arraylist.
                                        salesOrder.setSRItemArray(new ArrayList<>());
                                        salesOrder.getSRItemArray().add(item);
                                        salesOrders.add(salesOrder);
                                        System.out.println(String.valueOf(salesOrder.getSalesOrderNumber()));

                                }
                            }
                            else {
                                //if salesOrder array > 0 then add the sales order to the array.
                                salesOrder.setSRItemArray(new ArrayList<>());
                                salesOrder.getSRItemArray().add(item);
                                salesOrders.add(salesOrder);

                            }
                    }

                }



            }
        }


        return salesOrders;
    }
}
