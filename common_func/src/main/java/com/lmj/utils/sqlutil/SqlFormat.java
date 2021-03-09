package com.lmj.utils.sqlutil;

public class SqlFormat {
    /**
     * 时间格式化（查询条件）
     */
    private static final String dayFormat="%Y-%m-%d";
    private static final String monthFormat="%Y-%m";
    private static final String yearFormat="%Y";

    /**
     * 时间格式化（分组）
     */
    private static final String hourGroupFormat="%H";
    private static final String dayGroupFormat="%d";
    private static final String monthGroupFormat="%m";
    private static final String yearGroupFormat="%Y";
    public static String getDayFormat() {
        return dayFormat;
    }

    public static String getMonthFormat() {
        return monthFormat;
    }

    public static String getYearFormat() {
        return yearFormat;
    }

    public static String getHourGroupFormat() {
        return hourGroupFormat;
    }

    public static String getDayGroupFormat() {
        return dayGroupFormat;
    }

    public static String getMonthGroupFormat() {
        return monthGroupFormat;
    }

    public static String getYearGroupFormat() {
        return yearGroupFormat;
    }
}
