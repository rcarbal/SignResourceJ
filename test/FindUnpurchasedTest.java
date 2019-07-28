import org.junit.jupiter.api.Test;
import parsePurchasables.utils.ParsePurchasebles;
import pojo.PurchaseOrder;
import pojo.SRItem;
import pojo.SalesOrder;
import utils.ExcelFileReader;
import utils.PurchaseOrderExtractor;
import utils.SalesOrderParse;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindUnpurchasedTest {

    @Test
    void getSalesOrders(){
        ArrayList<SalesOrder> salesOrderArrayList = null;
        try {
            salesOrderArrayList = SalesOrderParse.getAllSalesOrders();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        assertTrue(salesOrderArrayList.size() >0);
    }

    @Test
    void getPurchasingParts(){

        String fileLocation = "C:/Users/rcarb/OneDrive/Documents/signresource/material.xlsx";
        ArrayList<SRItem> purchasingItems = null;
        try {
            purchasingItems = ExcelFileReader.getPurchasingItems(fileLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(purchasingItems.size() > 0);
    }

    @Test
    void matchPOsToSO(){

        //Get Purchasables
        String fileLocation = "C:/Users/rcarb/OneDrive/Documents/signresource/material.xlsx";
        ArrayList<SRItem> purchasingItems = null;
        try {
            purchasingItems = ExcelFileReader.getPurchasingItems(fileLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(purchasingItems.size() > 0);

        //Get POS
        ArrayList<PurchaseOrder> purchaseOrders = null;
        try {
            purchaseOrders = PurchaseOrderExtractor.
                    getPurchaseOrderReport("C:/Users/rcarb/OneDrive/Documents/signresource/poreport.xlsx");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        assert purchaseOrders != null;
        assertTrue(purchaseOrders.size()>0);

        //Get SalesOrders
        ArrayList<SalesOrder> salesOrderArrayList = null;
        try {
            salesOrderArrayList = SalesOrderParse.getAllSalesOrders();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        assertTrue(salesOrderArrayList.size() >0);

        ParsePurchasebles.findPurchasablesInSalesOrders(salesOrderArrayList, purchasingItems, purchaseOrders );
    }
}
