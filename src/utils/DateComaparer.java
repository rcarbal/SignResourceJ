package utils;

import java.util.Date;

public class DateComaparer {
    public static int compareDates(Date createdDate, Date srDateCreated) {
        if (createdDate == null){
            return -1;
        }

        if (srDateCreated == null){
            return -1;
        }
        int days = createdDate.compareTo(srDateCreated);
        return days;
    }
}
