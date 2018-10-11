package com.urler.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AppUtils {

    public static boolean isUrlValid(String url) {
        if (url == null) {
            return false;
        }

        Pattern p = Pattern.compile("^(http://|https://)?([a-zA-Z0-9]+.)*?[a-zA-Z0-9]+[\\.](/)?([^\\.]+)$");
        Matcher m;
        m = p.matcher(url);
        return m.matches();
    }

    public static boolean isDateValid(String date) {
        try {
            String[] parts = date.split("-");
            Integer year = Integer.parseInt(parts[0]);
            Integer month = Integer.parseInt(parts[1]);
            Integer day = Integer.parseInt(parts[2]);
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            cal.getTime();
            
        } catch (Throwable e) {
            return false;
        }
        return true;
    }

    public static Date getBeginningOfDate(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        return cal.getTime();
    }

    public static boolean isProtocolSpecified(String url) {
        Pattern p = Pattern.compile("^(http:\\/\\/|https:\\/\\/).+");
        Matcher m;
        m = p.matcher(url);
        return m.matches();
    }

    public static Date getDateFromString(String dateAsString) {
        String[] parts = dateAsString.split("-");
        Integer year = Integer.parseInt(parts[0]);
        Integer month = Integer.parseInt(parts[1]);
        Integer day = Integer.parseInt(parts[2]);
        
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return getBeginningOfDate(cal.getTime());
    }
    
}
