package pojo;

import java.util.ArrayList;

public class VendorAverage implements Comparable<VendorAverage> {

    private int vendorVumber;
    private ArrayList<Integer> poDays;
    private int vendorAverage;
    private ArrayList<LineChartPoint> points;

    public ArrayList<LineChartPoint> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<LineChartPoint> points) {
        this.points = points;
    }



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

    @Override
    public int compareTo(VendorAverage o) {
        int va = this.vendorAverage;
        int va2 = o.vendorAverage;
        return Integer.compare(va, va2);
    }
}
