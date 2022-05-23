package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OtherUtils {
    public static int makeMoney(){
        return (int) ((Math.random() * (50000 - 150)) + 150);
    }

    public static Date getDateFromString(String date, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateFormat.format(dateFormat.parse(date)));
    }
}
