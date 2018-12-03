package utils;

import javafx.scene.chart.XYChart;
import pojo.LineChartInfo;
import pojo.LineChartPoint;
import pojo.LineChartProcessing;
import pojo.VendorAverage;

import java.util.ArrayList;

public class ProcessLineChartDate {
    public static LineChartProcessing getSeries(ArrayList<VendorAverage> vendorAverages) {
        //        series.getData().add(new XYChart.Data<>(0,1));
        if (vendorAverages == null){
            return null;
        }
        XYChart.Series rank ;

        LineChartProcessing processing = new LineChartProcessing();
        for (VendorAverage vendorAverage: vendorAverages){
            rank = new XYChart.Series();
            rank.setName(VendorUtil.getVendorInIndex(vendorAverage.getVendorVumber()));
            ArrayList<LineChartPoint> points = vendorAverage.getPoints();

            for (LineChartPoint point : points ){
                int xDay = -1;
                int yAverage = -1;

                xDay = point.getPoReceivedInDay();
                yAverage = point.getPoAverageDay();
                rank.getData().add(new XYChart.Data<>(xDay, yAverage));

            }
            int index = vendorAverages.indexOf(vendorAverage);
            switch (index){
                case 0:
                    LineChartInfo lineChartInfo = new LineChartInfo();
                    lineChartInfo.setSeries(rank);
                    lineChartInfo.setVendor(vendorAverages.indexOf(vendorAverage));
                    processing.setInfo1(lineChartInfo);

                    break;
                case 1:
                    LineChartInfo lineChartInfo2 = new LineChartInfo();
                    lineChartInfo2.setSeries(rank);
                    lineChartInfo2.setVendor(vendorAverages.indexOf(vendorAverage));
                    processing.setInfo2(lineChartInfo2);
                    break;
                case 2:
                    LineChartInfo lineChartInfo3 = new LineChartInfo();
                    lineChartInfo3.setSeries(rank);
                    lineChartInfo3.setVendor(vendorAverages.indexOf(vendorAverage));
                    processing.setInfo3(lineChartInfo3);
                    break;
                case 3:
                    LineChartInfo lineChartInfo4 = new LineChartInfo();
                    lineChartInfo4.setSeries(rank);
                    lineChartInfo4.setVendor(vendorAverages.indexOf(vendorAverage));
                    processing.setInfo4(lineChartInfo4);
            }
        }
        //Returns Series in object called LineProcessing
        return processing;
    }
}
