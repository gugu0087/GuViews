package com.xyc.guguviews.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hasee on 2017/12/25.
 */

public class DateUtils {

    private static DateUtils instance = null;
    public static final String MINUTE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String DAY_FORMAT = "yyyy/MM/dd";
    public static final String DATE_FORMAT_YEAR2 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_DAY2 = "yyyy-MM-dd";
    private String mFormatStr = MINUTE_FORMAT;

    private DateUtils() {

    }

    public static DateUtils getInstance() {
        if (instance == null) {
            instance = new DateUtils();
        }
        return instance;
    }

    public String getFormatStr() {
        return mFormatStr;
    }

    /**
     * 设置时间格式
     *
     * @param formatStr
     */
    public void setFormatStr(String formatStr) {
        if (formatStr == null) {
            mFormatStr = "yyyy/MM/dd HH:mm:ss";
        }
        mFormatStr = formatStr;
    }

    /**
     * 时间戳转string时间格式
     *
     * @param time
     * @return
     */
    public String getLongToString(long time,String formatStr) {
        if (time == 0) {
            time = getSystemLongTime();
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr, Locale.CHINA);
        Date date = new Date(time);
        return format.format(date);
    }

    /**
     * string时间格式转date格式
     *
     * @param strTime
     * @return
     */
    public Date getStringToDate(String strTime,String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr, Locale.CHINA);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * date时间格式转long
     *
     * @param date
     * @return
     */
    public long getDateToLong(Date date) {
        if (date == null) {
            return getSystemLongTime();
        }
        return date.getTime();
    }

    /**
     * long时间格式转date
     *
     * @param time
     * @return
     */
    public Date getLongToDate(long time,String formatStr) {
        String longToString = getLongToString(time,formatStr);
        Date stringToDate = getStringToDate(longToString,formatStr);
        return stringToDate;
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public long getSystemLongTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取系统当前年份
     *
     * @return
     */
    public int getCurrentYear() {
        Calendar c = Calendar.getInstance();//
        int year = c.get(Calendar.YEAR); // 获取当前年份
        if (year < 2017) { // 做个系统时间异常保护
            return 2017;
        }
        return year;
    }

    /**
     * 获取系统当前月份
     *
     * @return
     */
    public int getCurrentMonth() {
        Calendar c = Calendar.getInstance();//
        int month = c.get(Calendar.MONTH) + 1; // 获取当前月份
        Log.d("xyc", "getCurrentMonth: month=" + month);
        if (month < 1 || month > 12) { // 做个系统时间异常保护
            return 1;
        }
        return month;
    }

    /**
     * 格式化月份成2位数
     *
     * @param month
     * @return
     */
    public String getMonthDouble(int month) {
        String months;
        if (month < 10) {
            months = "0" + month;
        } else {
            months = String.valueOf(month);
        }
        return months;
    }

    /**
     * 获取特定日期的时间戳
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public long getSpecificDateTime(int year, int month, int day) {
        String time = year + "/" + month + "/" + day;//获取制定日期时间戳
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA).parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (date1 == null) {
            return getSystemLongTime();
        }
        return date1.getTime();
    }

    /**
     * get与特定时间相差若干天数的时间
     *
     * @param currentTime
     * @param days
     * @return
     */
    public long getDiffSpecificDaysTime(long currentTime, int days) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime);
        Calendar afterDay = getAfterDay(cal, days);
        return afterDay.getTimeInMillis();
    }

    /**
     * 计算两个日期之间差的天数（httl模版用）
     *
     * @param ts1 时间戳1
     * @param ts2 时间戳2
     * @return
     */
    public int getCaculate2Days(long ts1, long ts2) {
        Date firstDate = getLongToDate(ts1,mFormatStr);
        Date secondDate = getLongToDate(ts2,mFormatStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDate);
        int dayNum1 = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(secondDate);
        int dayNum2 = calendar.get(Calendar.DAY_OF_YEAR);
        return Math.abs(dayNum1 - dayNum2);
    }

    /**
     * 获取特定日期的下一天
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public long getSpecificDayNextDay(int year, int month, int day) {
        //后一天
        Calendar cl = setCalendar(year, month, day);
        Calendar afterDay = getAfterDay(cl, 1);

        return afterDay.getTimeInMillis();
    }

    /**
     * 获取当前时间的后一天时间
     *
     * @param cl
     * @return
     */
    public Calendar getAfterDay(Calendar cl, int days) {
        int day = cl.get(Calendar.DATE);
        cl.set(Calendar.DATE, day + days);
        return cl;
    }

    /**
     * 当前时间的前一天
     *
     * @param currentTime
     * @return
     */
    public long gettSpecificTimeBeforeDay(long currentTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentTime);
        cal.add(Calendar.DATE, -1);
        return cal.getTimeInMillis();
    }

    /**
     * 设置前一天时间
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public Calendar setCalendar(int year, int month, int date) {
        Calendar cl = Calendar.getInstance();
        cl.set(year, month - 1, date);
        return cl;
    }

    /**
     * 相隔多少天
     *
     * @param date1
     * @param date2
     * @return
     */
    public float daysBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);

        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);

        long time2 = cal.getTimeInMillis();
        return ((float) time2 - (float) time1) / ((float) 1000 * (float) 3600);
    }

    /**
     * 相隔多少天
     *
     * @return
     */
    public float daysBetween(long time1, long time2) {
        return ((float) time2 - (float) time1) / ((float) 1000 * (float) 3600 * 24);
    }

    /**
     * 下一天时间
     * 获取
     *
     * @return
     */
    public long getNextDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return cal.getTimeInMillis();
    }

    /**
     * 获取昨天的时间
     *
     * @return
     */
    public long getYesTodayTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTimeInMillis();
    }

    /**
     * 获取前天的时间
     *
     * @return
     */
    public long getBeforeYesTodayTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2);
        return cal.getTimeInMillis();
    }

    /**
     * 通过年份和月份 得到当月的日子
     *
     * @param year
     * @param month
     * @return
     */
    public int getMonthDays(int year, int month) {
        month++;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

    /**
     * 返回当前月份1号位于周几
     *
     * @param year  年份
     * @param month 月份，传入系统获取的，不需要正常的
     * @return 日：1		一：2		二：3		三：4		四：5		五：6		六：7
     */
    public int getFirstDayWeek(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        Log.d("DateView", "DateView:First:" + calendar.getFirstDayOfWeek());
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据列明获取周
     *
     * @param column
     * @return
     */
    public String getWeekName(int column) {
        switch (column) {
            case 0:
                return "周日";
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
            default:
                return "";
        }
    }
}
