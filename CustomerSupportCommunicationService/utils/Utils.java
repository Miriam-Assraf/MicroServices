package acs.utils;

import acs.logic.utils.TimeEnum;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

    public static String upperCaseToCamelCase(String upperCase){
        String words[] = upperCase.split("_");
        String camelCase = "";
        for (String word: words) {
            camelCase += word.charAt(0) + word.substring(1).toLowerCase();
        }
        camelCase = Character.toLowerCase(camelCase.charAt(0)) + camelCase.substring(1);
        return camelCase;
    }

    public static Date getFromDate(String timeEnum){
        LocalDateTime localDateTime = LocalDateTime.now();
        if(timeEnum.equals(TimeEnum.LAST_DAY.toString())){
            localDateTime = localDateTime.minusDays(1);
        }
        else if(timeEnum.equals(TimeEnum.LAST_WEEK.toString())){
            localDateTime = localDateTime.minusDays(7);
        }
        else if(timeEnum.equals(TimeEnum.LAST_MONTH.toString())){
            localDateTime = localDateTime.minusDays(30);
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

    }

}
