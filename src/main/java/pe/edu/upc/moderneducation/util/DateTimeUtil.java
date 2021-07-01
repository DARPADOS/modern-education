package pe.edu.upc.moderneducation.util;

import java.time.LocalDateTime;
import java.util.Date;

public class DateTimeUtil {
    
    public static Date getNow(){
        
        LocalDateTime now = LocalDateTime.now();
        return java.sql.Timestamp.valueOf(now);
    }

    public static Date addOneMonth(Date originalDate){
        LocalDateTime original = new java.sql.Timestamp(originalDate.getTime()).toLocalDateTime();
        original.plusMonths(1);
        return java.sql.Timestamp.valueOf(original);
    }
}
