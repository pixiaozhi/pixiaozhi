package com.ryx.utils;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * TextView 的 setText 工具
 * Created by ryx on 2020/1/20.
 */
public class TextViewUtil {
    /**
     * TextView 设置显示内容
     *
     * @param view
     * @param format  模板，参照java.lang.String.format(String format, Object... args)
     * @param objects 参数
     * @return
     */
    public static TextView setHtml(View view, String format, Object... objects) {
        if (format == null) throw new RuntimeException("Invalid:format is null!");
        return setHtml(view, String.format(format, objects));
    }

    /**
     * TextView 设置显示内容
     *
     * @param view
     * @param source 待显示内容
     * @return
     */
    public static TextView setHtml(View view, String source) {
        return setText(view, Html.fromHtml(source));
    }

    /**
     * TextView 设置显示内容
     *
     * @param view
     * @param res     模板资源，参照java.lang.String.format(String format, Object... args)
     * @param objects 参数
     */
    public static TextView setText(View view, int res, Object... objects) {
        return setText(view, view.getContext().getString(res), objects);
    }

    /**
     * TextView 设置显示内容
     *
     * @param view
     * @param format  模板，参照java.lang.String.format(String format, Object... args)
     * @param objects 参数
     */
    public static TextView setText(View view, String format, Object... objects) {
        if (format == null) throw new RuntimeException("Invalid:format is null!");
        return setText(view, String.format(format, objects));
    }

    /**
     * TextView 设置显示内容
     *
     * @param view
     * @param calendar 日期
     * @param res      模板资源,参照SimpleDateFormat的使用
     */
    public static TextView setText(View view, Calendar calendar, int res) {
        return setText(view, calendar.getTime(), res);
    }

    /**
     * TextView 设置显示内容
     *
     * @param view
     * @param date 时间
     * @param res  模板资源,参照SimpleDateFormat的使用
     */
    public static TextView setText(View view, Date date, int res) {
        return setText(view, date, view.getContext().getString(res));
    }

    /**
     * TextView 设置显示内容
     *
     * @param view
     * @param calendar 日期
     * @param pattern  模板,参照SimpleDateFormat的使用
     */
    public static TextView setText(View view, Calendar calendar, String pattern) {
        return setText(view, CalendarUtil.format(calendar, pattern));
    }

    /**
     * TextView 设置显示内容
     *
     * @param view
     * @param date    时间
     * @param pattern 模板,参照SimpleDateFormat的使用
     */
    public static TextView setText(View view, Date date, String pattern) {
        return setText(view, CalendarUtil.format(date, pattern));
    }

    /**
     * TextView 设置显示内容
     *
     * @param view
     * @param sequence 待显示内容
     */
    public static TextView setText(View view, CharSequence sequence) {
        if (!(view instanceof TextView)) {
            throw new RuntimeException("Invalid:view not is TextView extends class!");
        }
        ((TextView) view).setText(sequence);
        return ((TextView) view);
    }

    /**
     * TextView 设置显示内容
     *
     * @param view
     * @param res  待显示内容资源
     */
    public static TextView setText(View view, int res) {
        if (!(view instanceof TextView)) {
            throw new RuntimeException("Invalid:view not is TextView extends class!");
        }
        ((TextView) view).setText(res);
        return ((TextView) view);
    }
}
