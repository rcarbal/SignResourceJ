package utils;

import pojo.DiscrepencyParse;
import pojo.PurchaseOrder;
import pojo.SRItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class ProcessPODescrepencies {

    public static DiscrepencyParse getDescrepencies(ArrayList<PurchaseOrder> purchaseOrderArrayList) {
        DiscrepencyParse descrepencyParse = new DiscrepencyParse();
        descrepencyParse.addTotalPurchaseOrder(purchaseOrderArrayList.size());
        ArrayList<SRItem> srItemDescrepancyArrayList = new ArrayList<>();
        int numberItems = 0;

        if (purchaseOrderArrayList != null && purchaseOrderArrayList.size() > 0) {


            for (PurchaseOrder po : purchaseOrderArrayList) {
                //get values form PO to comapre
                ArrayList<SRItem> items = po.getItemArray();

                for (SRItem item : items){
                    numberItems++;

                    BigDecimal unitPrice = item.getUnitPrice();
                    BigDecimal stdPrice = item.getStdPrice();

                    if (!unitPrice.equals(stdPrice)){
                        srItemDescrepancyArrayList.add(item);
                    }
                }
            }
        }


        descrepencyParse.addTotalItemsWithDescrepency(numberItems);
        Collections.sort(srItemDescrepancyArrayList);
        descrepencyParse.addArrayListOfDescrepencies(srItemDescrepancyArrayList);

        return descrepencyParse;
    }
}
