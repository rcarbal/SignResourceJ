package utils;

public class VendorUtil {

    public static String getVendorInIndex(int i) {
//
//        AAT USA 10457
//        DAKTronic  11505
//        Pwm 11140
//        Pricevision  9097
        String vendor = "";

        switch (i){
            case 10457:
                vendor ="AAT USA";
                break;
            case 11505:
                vendor = "DAKTRONICS";
                break;
            case 11140:
                vendor = "PWM";
                break;
            case 9097:
                vendor = "PRICEVSION";
        }
        return vendor;
    }
}
