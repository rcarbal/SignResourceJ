package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ParseDates {
    public static int getBusinessDays(Date startDate, Date closeDate) {

        if (startDate == null || closeDate == null){
            return -1;
        }

        int bussinessDay =0;
        LocalDate date1 = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = closeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        while (date1.isBefore(date2)){
            DayOfWeek dw = date1.getDayOfWeek();
            if (dw != DayOfWeek.SATURDAY && dw != DayOfWeek.SUNDAY){
                bussinessDay++;
            }
            date1= date1.plusDays(1);
        }
        return bussinessDay +1;
    }
}
