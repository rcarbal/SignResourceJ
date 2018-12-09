import org.junit.jupiter.api.Test;
import pojo.SalesOrder;
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
}
