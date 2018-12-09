import org.junit.jupiter.api.Test;
import pojo.SRItem;
import pojo.SalesOrder;
import utils.ExcelFileReader;
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
        ArrayList<SRItem> purchasingItems =
                ExcelFileReader.getPurchasingItems(fileLocation);
    }

    @Test
    void matchPOsToSO(){

    }
}
