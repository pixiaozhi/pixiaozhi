package com.ryx.utils;

import android.view.KeyEvent;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EditText 的工具
 * Created by ryx on 2020/1/20.
 */
public class EditTextUtil {
    /**
     * 获取编辑框内的内容
     *
     * @param editText     编辑框
     * @param defaultValue 默认值
     * @param <T>          返回类型
     * @return
     */
    public static <T> T get(EditText editText, T defaultValue) {
        if (defaultValue instanceof Integer) {
            return (T) getInteger(editText, (Integer) defaultValue);
        } else if (defaultValue instanceof Float) {
            return (T) getFloat(editText, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return (T) getLong(editText, (Long) defaultValue);
        } else if (defaultValue instanceof Double) {
            return (T) getDouble(editText, (Double) defaultValue);
        } else if (defaultValue instanceof Short) {
            return (T) getShort(editText, (Short) defaultValue);
        } else if (defaultValue instanceof String) {
            return (T) getString(editText);
        } else {
            return defaultValue;
        }
    }

    /**
     * 获取编辑框内的内容
     *
     * @param editText 编辑框
     * @return 返回Integer类型
     */
    public static Integer getInteger(EditText editText) {
        return getInteger(editText, null);
    }

    /**
     * 获取编辑框内的内容
     *
     * @param editText     编辑框
     * @param defaultValue 默认值
     * @return 返回Integer类型
     */
    public static Integer getInteger(EditText editText, Integer defaultValue) {
        try {
            return Integer.valueOf(getString(editText));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 获取编辑框内的内容
     *
     * @param editText 编辑框
     * @return 返回Long类型
     */

    public static Long getLong(EditText editText) {
        return getLong(editText, null);
    }

    /**
     * 获取编辑框内的内容
     *
     * @param editText     编辑框
     * @param defaultValue 默认值
     * @return 返回Long类型
     */
    public static Long getLong(EditText editText, Long defaultValue) {
        try {
            return Long.valueOf(getString(editText));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 获取编辑框内的内容
     *
     * @param editText 编辑框
     * @return 返回Float类型
     */
    public static Float getFloat(EditText editText) {
        return getFloat(editText, null);
    }

    /**
     * 获取编辑框内的内容
     *
     * @param editText     编辑框
     * @param defaultValue 默认值
     * @return 返回Float类型
     */
    public static Float getFloat(EditText editText, Float defaultValue) {
        try {
            return Float.valueOf(getString(editText));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 获取编辑框内的内容
     *
     * @param editText 编辑框
     * @return 返回Double类型
     */
    public static Double getDouble(EditText editText) {
        return getDouble(editText, null);
    }

    /**
     * 获取编辑框内的内容
     *
     * @param editText     编辑框
     * @param defaultValue 默认值
     * @return 返回Double类型
     */

    public static Double getDouble(EditText editText, Double defaultValue) {
        try {
            return Double.valueOf(getString(editText));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 获取编辑框内的内容
     *
     * @param editText 编辑框
     * @return 返回Short类型
     */
    public static Short getShort(EditText editText) {
        return getShort(editText, null);
    }

    /**
     * 获取编辑框内的内容
     *
     * @param editText     编辑框
     * @param defaultValue 默认值
     * @return 返回Short类型
     */
    public static Short getShort(EditText editText, Short defaultValue) {
        try {
            return Short.valueOf(getString(editText));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 获取编辑框内的内容
     *
     * @param editText 编辑框
     * @return 返回String类型
     */
    public static String getString(EditText editText) {
        return editText.getText().toString();
    }


    /**
     * 使用正则式验证输入内容
     *
     * @param editText
     * @param regex
     * @return
     */
    public static boolean validate(EditText editText, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(getString(editText));
        return matcher.matches();
    }

    /**
     * 获取光标位置
     *
     * @param editText
     * @return
     */
    public static int getEditTextCursorIndex(EditText editText) {
        return editText.getSelectionStart();
    }

    /**
     * 向光标处插入文本
     *
     * @param editText
     * @param string
     */
    public static void insertText(EditText editText, String string, boolean b) {
        editText.getText().insert(getEditTextCursorIndex(editText), string);
    }

    /**
     * 向光标处插入文本
     *
     * @param editText
     * @param charSequence
     */
    public static void insertText(EditText editText, String charSequence) {
        int cursor = getEditTextCursorIndex(editText);

        editText.getText().insert(cursor, charSequence);
        editText.setText(editText.getText());
        editText.setSelection(cursor + charSequence.length());
    }

    /**
     * 删除光标处表情
     *
     * @param editText
     */
    public static void delTextEmoji(EditText editText) {
        KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0,
                0, KeyEvent.KEYCODE_ENDCALL);
        editText.dispatchKeyEvent(event);
    }

    /**
     * 光标左移
     *
     * @param editText
     * @param rollLeft
     */
    public static void setEditTextCusorRollLeft(EditText editText, int rollLeft) {
        editText.setSelection(getEditTextCursorIndex(editText) - rollLeft);
    }
}
