package com.delta.worko2o.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 返回当前时间
     *
     * @return Date
     */
    public static Date getNowDate() {
        return new Date();
    }

    public static Date getNowBeforeDate(int beforeDays) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -beforeDays);
        try {
            Date endDate = dft.parse(dft.format(cal.getTime()));
            return endDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getBeforeDate(Date sourceDate, int beforeDays) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sourceDate);
        int day = cal.get(Calendar.DATE);
        cal.set(Calendar.DATE, day - beforeDays);
        try {
            Date endDate = dft.parse(dft.format(cal.getTime()));
            return endDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDate(String date, String format) {
        try {
            SimpleDateFormat dft = new SimpleDateFormat(format);
            return dft.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.DAY_OF_WEEK);
    }
}
