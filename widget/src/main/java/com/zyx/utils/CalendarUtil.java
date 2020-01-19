package com.zyx.utils;

import android.text.TextUtils;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 * Created by rxy on 2020/1/20.
 */
public class CalendarUtil {
    public static final String Y = "yyyy";
    public static final String YM = "yyyy-MM";
    public static final String MD = "MM-dd";
    public static final String YMD = "yyyy-MM-dd";
    public static final String YMD_HM = "yyyy-MM-dd HH:mm";
    public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String HM = "HH:mm";
    public static final String HMS = "HH:mm:ss";
    public static final String FORMAT_DEFAULT = "未知";

    private CalendarUtil() {
    }

    /**
     * 复制一个日期
     *
     * @param year
     * @param month
     * @param dayOfMonth
     * @return
     */
    public static Calendar clone(int year, int month, int dayOfMonth) {
        return new GregorianCalendar(year, month - 1, dayOfMonth);
    }

    /**
     * 复制一个日期
     *
     * @param calendar
     * @return
     */
    public static Calendar clone(Calendar calendar) {
        Calendar result = Calendar.getInstance(calendar.getTimeZone());
        result.setTimeInMillis(calendar.getTimeInMillis());
        return result;
    }

    /**
     * 复制一个日期
     *
     * @param date
     * @return
     */
    public static Calendar clone(Date date) {
        Calendar result = Calendar.getInstance();
        result.setTime(date);
        return result;
    }

    /**
     * 判断 - c1 在 c2 之后
     *
     * @param c1
     * @param c2
     * @return
     */
    public static boolean after(Object c1, Object c2) {
        long t1 = 0;
        long t2 = 0;

        if (c1 instanceof Date) {
            t1 = ((Date) c1).getTime();
        } else if (c1 instanceof Calendar) {
            t1 = ((Calendar) c1).getTimeInMillis();
        } else {
            return false;
        }

        if (c2 instanceof Date) {
            t2 = ((Date) c2).getTime();
        } else if (c1 instanceof Calendar) {
            t2 = ((Calendar) c2).getTimeInMillis();
        } else {
            return false;
        }

        return t1 > t2;
    }

    /**
     * 判断 - c1 在 c2 之前
     *
     * @param c1
     * @param c2
     * @return
     */
    public static boolean before(Object c1, Object c2) {
        long t1 = 0;
        long t2 = 0;

        if (c1 instanceof Date) {
            t1 = ((Date) c1).getTime();
        } else if (c1 instanceof Calendar) {
            t1 = ((Calendar) c1).getTimeInMillis();
        } else {
            return false;
        }

        if (c2 instanceof Date) {
            t2 = ((Date) c2).getTime();
        } else if (c1 instanceof Calendar) {
            t2 = ((Calendar) c2).getTimeInMillis();
        } else {
            return false;
        }

        return t1 < t2;
    }

    /**
     * 判断是不同一年
     *
     * @param calendar1
     * @param calendar2
     * @return
     */
    public static boolean isToYear(Calendar calendar1, Calendar calendar2) {
        if (calendar1 == null) return false;
        if (calendar2 == null) return false;
        return (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR));
    }

    /**
     * 判断是不是上一个月
     *
     * @param calendar1 当前月份
     * @param calendar2 被判定月份
     * @return
     */
    public static boolean isPrevMonth(Calendar calendar1, Calendar calendar2) {
        Calendar calendar = CalendarUtil.clone(calendar2);
        calendar.add(Calendar.MONTH, 1);

        return isToMonth(calendar1, calendar);
    }

    /**
     * 判断是不同一月
     *
     * @param calendar1
     * @param calendar2
     * @return
     */
    public static boolean isToMonth(Calendar calendar1, Calendar calendar2) {
        return (isToYear(calendar1, calendar2) && (calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)));
    }

    /**
     * 判断是不是下一个月
     *
     * @param calendar1 当前月份
     * @param calendar2 被判定月份
     * @return
     */
    public static boolean isNextMonth(Calendar calendar1, Calendar calendar2) {
        Calendar calendar = CalendarUtil.clone(calendar2);
        calendar.add(Calendar.MONTH, -1);

        return isToMonth(calendar1, calendar);
    }

    /**
     * 判断是昨天
     *
     * @param calendar1 当前日期
     * @param calendar2 被判定日期
     * @return
     */
    public static boolean isPrevDay(Calendar calendar1, Calendar calendar2) {
        Calendar calendar = CalendarUtil.clone(calendar2);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        return isToDay(calendar1, calendar);
    }

    /**
     * 判断是不同一日
     *
     * @param calendar1
     * @param calendar2
     * @return
     */
    public static boolean isToDay(Calendar calendar1, Calendar calendar2) {
        return (isToMonth(calendar1, calendar2) && (calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)));
    }

    /**
     * 判断是明天
     *
     * @param calendar1 当前日期
     * @param calendar2 被判定日期
     * @return
     */
    public static boolean isNextDay(Calendar calendar1, Calendar calendar2) {
        Calendar calendar = CalendarUtil.clone(calendar2);
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        return isToDay(calendar1, calendar);
    }

    /**
     * 判断是后天
     *
     * @param calendar1 当前日期
     * @param calendar2 被判定日期
     * @return
     */
    public static boolean isDayAfterTomorrow(Calendar calendar1, Calendar calendar2) {
        Calendar calendar = CalendarUtil.clone(calendar2);
        calendar.add(Calendar.DAY_OF_MONTH, -2);

        return isToDay(calendar1, calendar);
    }

    /**
     * 将时间格式化
     *
     * @param pattern 格式
     * @return
     */
    public static String format(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 将日期格式化
     *
     * @param calendar
     * @param pattern
     * @return
     */
    public static String format(Calendar calendar, String pattern) {
        return format(calendar.getTime(), pattern);
    }

    /**
     * 将时间格式化
     *
     * @param date    时间
     * @param pattern 格式
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null || TextUtils.isEmpty(pattern)) return null;
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将时间格式化
     *
     * @param s       时间
     * @param pattern 格式
     * @return
     */
    public static String format(String s, String pattern) {
        if (s == null || TextUtils.isEmpty(pattern)) return null;
        long lt = new Long(s);
        Date date = new Date(lt);
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将字符串 转为 Date
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(String date, String pattern) {
        if (TextUtils.isEmpty(date) || TextUtils.isEmpty(pattern)) return null;
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * yyyy-MM-dd 格式化为Date
     *
     * @param date
     * @return
     */
    public static Date parse(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        } else if (YMD_HMS.length() == date.length()) {
            return parse(date, YMD_HMS);
        } else {
            return parse(date, YMD);
        }
    }

    /**
     * 今天显示：HMS格式
     * 昨天显示：昨天
     * 其他显示：MD格式
     */
    public static void setShowText(TextView textView, String timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(TextUtils.isEmpty(timestamp) ? System.currentTimeMillis() : Long.valueOf(timestamp));
        if (CalendarUtil.isToDay(Calendar.getInstance(), calendar)) {
            TextViewUtil.setText(textView, "%s", CalendarUtil.format(timestamp, CalendarUtil.HMS));
        } else if (CalendarUtil.isPrevDay(Calendar.getInstance(), calendar)) {
            TextViewUtil.setText(textView, "昨天 %s", CalendarUtil.format(timestamp, CalendarUtil.HMS));
        } else {
            TextViewUtil.setText(textView, "%s", CalendarUtil.format(timestamp, CalendarUtil.MD));
        }
    }

    /**
     * 获取7天前时间搓
     */
    private long getLastWeekTime() {
        Calendar c = Calendar.getInstance();
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, -7);
        Date d = c.getTime();
        return d.getTime();
    }

    /**
     * 获取一月前时间搓
     */
    private long getLastMonthTime() {
        Calendar c = Calendar.getInstance();
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        return m.getTime();
    }

    /**
     * 获取三个月前时间搓
     */
    private long getLastMonth3Time() {
        Calendar c = Calendar.getInstance();
        //过去三个月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -3);
        Date m3 = c.getTime();
        return m3.getTime();
    }

    /**
     * 获取一年前时间搓
     */
    private long getLastYear3Time() {
        Calendar c = Calendar.getInstance();
        //过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        return y.getTime();
    }

}
