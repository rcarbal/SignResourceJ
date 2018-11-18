package pojo;

import java.util.ArrayList;

public class DescrepencyParse {
    private int numberOfItems;
    private ArrayList<SRItem> array;

    public void addTotalItemsWithDescrepency(int numberItems) {
        this.numberOfItems = numberItems;
    }

    public void addArrayListOfDescrepencies(ArrayList<SRItem> srItemArrayList) {
        this.array = srItemArrayList;
    }

    public ArrayList<SRItem> getArray() {
        return array;
    }
}
