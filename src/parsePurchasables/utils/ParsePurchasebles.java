package parsePurchasables.utils;

import pojo.PurchaseOrder;
import pojo.SRItem;
import pojo.SalesOrder;

import java.util.ArrayList;

public class ParsePurchasebles {


    public static void findPurchasablesInSalesOrders(ArrayList<SalesOrder> salesOrderArrayList,
                                                     ArrayList<SRItem> purchasingItems,
                                                     ArrayList<PurchaseOrder> purchaseOrders) {

        //loop through all the sales orders.
        for (SalesOrder so : salesOrderArrayList) {

            //Get The Sales order numbers
            ArrayList<SRItem> soItems = so.getSRItemArray();

            //loop through the so items.
            for (SRItem item : soItems) {

                //THis is the item we will search the POs
                String itemParseing = item.getItemNumberString();

                boolean itemFound = false;
                //Loop through all the purchasing products and try to match the parding item
                for (SRItem purchasingItem : purchasingItems) {
                    //Get the Purchasing items to compare
                    String itemIsPurchasing = purchasingItem.getItemNumberString();

                    //If the there is a match between the purchasing product and the parsing item
                    if (purchasingItem.getItemNumberString().equals(itemParseing)) {


                        //Parse all the POs and find a parsing item that has SO item
                        for (PurchaseOrder po : purchaseOrders) {

                            ArrayList<SRItem> poItems = po.getItemArray();

                            for (SRItem poItem : poItems) {

                                String poItemSO = poItem.getItemNumberString();
                                int soNumber = so.getSalesOrderNumber();

                                if (poItem.getItemNumberString().equals(itemParseing)) {

                                    if (poItem.getItemSalesorderNumber() == so.getSalesOrderNumber()) {

                                        //System.out.println("PO found for SO item " + so.getSalesOrderNumber());
                                        itemFound = true;
                                        break;
                                    }
                                }
                            }
                            if (itemFound) break;
                            //System.out.println("PO not found for SO " + so.getSalesOrderNumber() + " item " +itemParseing);

                        }


                    }


                }
                if (itemFound) break;
                System.out.println("PO not found for SO " + so.getSalesOrderNumber() + " item " +itemParseing);
            }

        }
    }
}
