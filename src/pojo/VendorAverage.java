package pojo;

import java.util.ArrayList;

public class VendorAverage {

    private int vendorVumber;
    private ArrayList<Integer> poDays;
    private int vendorAverage;

    public int getVendorVumber() {
        return vendorVumber;
    }

    public void setVendorVumber(int vendorVumber) {
        this.vendorVumber = vendorVumber;
    }

    public ArrayList<Integer> getPoDays() {
        return poDays;
    }

    public void setPoDays(ArrayList<Integer> poDays) {
        this.poDays = poDays;
    }

    public void setAverageOfPOs(int average) {
        this.vendorAverage = average;
    }

    public int getAverageOfPOs() {
        return vendorAverage;
    }

}
