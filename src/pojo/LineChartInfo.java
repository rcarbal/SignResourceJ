package pojo;

import javafx.scene.chart.XYChart;

public class LineChartInfo {
    private int vendor;
    private  XYChart.Series series;

    public int getVendor() {
        return vendor;
    }

    public void setVendor(int vendor) {
        this.vendor = vendor;
    }

    public XYChart.Series getSeries() {
        return series;
    }

    public void setSeries(XYChart.Series series) {
        this.series = series;
    }
}
