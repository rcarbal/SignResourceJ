package utils;

import pojo.DescrepencyParse;
import pojo.PurchaseOrder;
import pojo.SRItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class ProcessPODescrepencies {
    public static DescrepencyParse getDescrepencies(ArrayList<PurchaseOrder> purchaseOrderArrayList) {
        DescrepencyParse descrepencyParse = new DescrepencyParse();
        ArrayList<SRItem> srItemArrayList = new ArrayList<>();
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
                        srItemArrayList.add(item);
                    }
                }
            }
        }

        descrepencyParse.addTotalItemsWithDescrepency(numberItems);
        Collections.sort(srItemArrayList);
        descrepencyParse.addArrayListOfDescrepencies(srItemArrayList);

        return descrepencyParse;
    }
}
