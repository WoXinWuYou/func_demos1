package com.lmj.utils.timeutil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.time.DateUtils;

/**
 * @ProjectName: classifyserver
 * @Package: com.ldst.classifyserver.utils.timeutil
 * @Author: 程建云
 * @Date: 2019/5/22 8:26
 * @Version: 1.0
 */
public class TimeUtils {

    /**
     * 获取今年是哪一年
     *
     * @return
     */
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }
    public static Integer getNowYear(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    /**
     * 获取本月是哪一月
     *
     * @return
     */
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }
    public static Integer getNowMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    /**
     * 两个日期相减得到的天数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDiffDays(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff = (endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24);
        int days = new Long(diff).intValue();
        return days;
    }

    /**
     * 两个日期相减得到的毫秒数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long dateDiff(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return date2ms - date1ms;
    }


    /**
     * 获取两个日期中的最大日期
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date max(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {//beginDate日期大于endDate
            return beginDate;
        }
        return endDate;
    }


    /**
     * 获取两个日期中的最小日期
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date min(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }

    /**
     * 返回某个日期下几天的日期
     *
     * @param date
     * @param i
     * @return
     */
    public static Date getNextDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }

    /**
     * 获取某个日期前几个小时的日期
     * @param date
     * @param num
     * @return
     */
    public static Date getFrontHourDate(Date date,int num){
        Calendar call = Calendar.getInstance();
        call.setTime(date);
        call.add(Calendar.HOUR, -num);
        Date resultDate = call.getTime();
        return resultDate;
    }

    /**
     * 获取某个日期前几个小时的日期-----整点时间
     * @param date
     * @param num
     * @return
     */
    public static Date getFrontIntHourDate(Date date,int num){
        Calendar call = Calendar.getInstance();
        call.setTime(date);
        call.add(Calendar.HOUR, -num);
//        call.set(Calendar.MINUTE, 0);
//        call.set(Calendar.SECOND, 0);
        String resultDateStr = new SimpleDateFormat("yyyy-MM-dd HH:00:00").format(call.getTime());
        Date resultDate = toDate(resultDateStr,"yyyy-MM-dd HH:00:00");
        return resultDate;
    }

    /**
     * 、
     * 返回某个日期前几天的日期
     *
     * @param date
     * @param i
     * @return
     */
    public static Date getFrontDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
        return cal.getTime();
    }
    /**
     * 、
     * 返回某个日期前几月的日期
     *
     * @param date
     * @param i
     * @return
     */
    public static Date getFrontMonth(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - i);
        return cal.getTime();
    }

    /**
     * 获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
     *
     * @param beginYear
     * @param beginMonth
     * @param k
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List getTimeList(int beginYear, int beginMonth, int k) {
        List list = new ArrayList();
        Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
            list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }


    /**
     * 获取某年某月到某年某月按天的切片日期集合（间隔天数的集合）
     *
     * @param beginYear
     * @param beginMonth
     * @param endYear
     * @param endMonth
     * @param k
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List getTimeList(int beginYear, int beginMonth, int endYear, int endMonth, int k) {
        List list = new ArrayList();
        if (beginYear == endYear) {
            for (int j = beginMonth; j <= endMonth; j++) {
                list.add(getTimeList(beginYear, j, k));
            }
        } else {
            {
                for (int j = beginMonth; j < 12; j++) {
                    list.add(getTimeList(beginYear, j, k));
                }
                for (int i = beginYear + 1; i < endYear; i++) {
                    for (int j = 0; j < 12; j++) {
                        list.add(getTimeList(i, j, k));
                    }
                }
                for (int j = 0; j <= endMonth; j++) {
                    list.add(getTimeList(endYear, j, k));
                }
            }
        }
        return list;
    }


    //=================================时间格式转换==========================

    /**
     * 将时间按照传入的格式进行格式化
     *
     * @param date
     * @param formaterString
     * @return
     */
    public static String toString(Date date, String formaterString) {
        String time;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern(formaterString);
        time = formater.format(date);
        return time;
    }

    /**
     *  
     *  按照提供的格式将字符串转换成Date类型 
     *  @param dateStr 
     *  @param formaterString 
     *  @return 
     */
    public static Date toDate(String dateStr, String formaterString) {
        Date date = null;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern(formaterString);
        try {
            date = formater.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将long类型转化为Date
     *
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date LongToDare(long str) throws ParseException {
        return new Date(str * 1000);
    }
    //====================================其他常见日期操作方法======================

    /**
     * 判断当前日期是否在[startDate, endDate]区间
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return false;
        }
        long currentTime = new Date().getTime();
        if (currentTime >= startDate.getTime()
                && currentTime <= endDate.getTime()) {
            return true;
        }
        return false;
    }


    /**
     * 得到二个日期间的间隔天数
     *
     * @param secondString：后一个日期
     * @param firstString：前一个日期
     * @return
     */
    public static String getTwoDay(String secondString, String firstString) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date secondTime = myFormatter.parse(secondString);
            Date firstTime = myFormatter.parse(firstString);
            day = (secondTime.getTime() - firstTime.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }


    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     *
     * @param time：时间
     * @param minute：分钟（有正负之分）
     * @return
     */
    public static String getPreTime(Date time, String minute) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            long Time = (time.getTime() / 1000) + Integer.parseInt(minute) * 60;
            time.setTime(Time * 1000);
            mydate1 = format.format(time);
        } catch (Exception e) {
            return "";
        }
        return mydate1;
    }


    /**
     * 获取今天
     *
     * @return String
     */
    public static String getToday() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到一个时间延后或前移几天的时间
     *
     * @param nowdate：时间
     * @param delay：前移或后延的天数
     * @return
     */
    public static String getNextDay(String nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 判断是否闰年
     *
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) {
        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0) {
            return true;
        } else if ((year % 4) == 0) {
            if ((year % 100) == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    /**
     * 返回美国时间格式
     *
     * @param str
     * @return
     */
    public static String getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }


    /**
     * 判断二个时间是否在同一个周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     *
     * @return
     */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + "年第" + week + "周";
    }


    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     *
     * @param sdate：日期
     * @param num：星期几（星期天是一周的第一天）
     * @return
     */
    public static String getWeek(String sdate, String num) {
        // 再转换为时间
        Date dd = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if (num.equals("1")) // 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if (num.equals("2")) // 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if (num.equals("3")) // 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if (num.equals("4")) // 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if (num.equals("5")) // 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if (num.equals("6")) // 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if (num.equals("0")) // 返回星期日所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }


    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }


    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    public static String getWeekStr(String sdate) {
        String str = "";
        str = getWeek(sdate);
        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }


    /**
     * 两个时间之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }


    /**
     * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
     * 此函数返回该日历第一行星期日所在的日期
     *
     * @param sdate
     * @return
     */
    public static String getNowMonth(String sdate) {
        // 取该时间所在月的一号
        sdate = sdate.substring(0, 8) + "01";

        // 得到这个月的1号是星期几
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int u = c.get(Calendar.DAY_OF_WEEK);
        String newday = getNextDay(sdate, (1 - u) + "");
        return newday;
    }


    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写
     *
     * @param sformat
     * @return
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /**
     * 返回一个i位数的随机数
     *
     * @param i
     * @return
     */
    public static String getRandom(int i) {
        Random jjj = new Random();
        // int suiJiShu = jjj.nextInt(9);
        if (i == 0)
            return "";
        String jj = "";
        for (int k = 0; k < i; k++) {
            jj = jj + jjj.nextInt(9);
        }
        return jj;
    }

    /**
     * 获取当前时间的前一小时和前24小时的时间
     * @return
     */
    public static Map getHistoryTime(){
        Map map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar firstTime = Calendar.getInstance();
        firstTime.setTime(new Date());
        firstTime.set(Calendar.HOUR_OF_DAY,firstTime.get(Calendar.HOUR_OF_DAY)- 1);
        Calendar lastTime = Calendar.getInstance();
        lastTime.setTime(firstTime.getTime());
        lastTime.set(Calendar.HOUR_OF_DAY,lastTime.get(Calendar.HOUR_OF_DAY)- 24);
        String firstHistryTime = sdf.format(firstTime.getTime());
        String lastHistryTime = sdf.format(lastTime.getTime());
        map.put("firstHistoryTime",lastHistryTime);
        map.put("lastHistoryTime",firstHistryTime);
        return map;
    }

    /**
     * 获取当前时间前一天和前七天的时间
     * @return
     */
    public static Map getDay(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map map = new HashMap();
        Calendar firstTime = Calendar.getInstance();
        firstTime.add(Calendar.DATE, -1); //得到前一天
        String firstDate = df.format(firstTime.getTime());
        //获取前七天时间
        Calendar lastTime = Calendar.getInstance();
        lastTime.add(Calendar.DATE, -8); //得到前七天
        String lastDate = df.format(lastTime.getTime());
        map.put("firstDate",firstDate);
        map.put("lastDate",lastDate);
        return map;
    }

    /**
     * 获取当前时间前一天和前30天的时间
     * @return
     */
    public static Map get30Day(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map map = new HashMap();
        Calendar firstTime = Calendar.getInstance();
        firstTime.add(Calendar.DATE, -1); //得到前一天
        String firstDate = df.format(firstTime.getTime());
        //获取前七天时间
        Calendar lastTime = Calendar.getInstance();
        lastTime.add(Calendar.DATE, -30); //得到前七天
        String lastDate = df.format(lastTime.getTime());
        map.put("firstDate",lastDate);
        map.put("lastDate",firstDate);
        return map;
    }

    /**
     * 年周期时间处理
     * @return
     */
    public static Date dealYear(Date date,int i){
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.add(Calendar.YEAR,i);
        Date dateResult=time.getTime();
        return dateResult;
    }
    
    /**
     * 获取本年日期指定格式字符串list，从过去到当前排序
     * @param pattern
     * @return
     */
    public static List<String> getAllDayStrsCurYear(String pattern){
        List<String> dayStrs = new ArrayList<String>();
        Calendar now = Calendar.getInstance();
        int dayOfYearNum = now.get(Calendar.DAY_OF_YEAR);
        Calendar firstDayCurYear = Calendar.getInstance();
        firstDayCurYear.set(firstDayCurYear.get(Calendar.YEAR), 0, 1);
        dayStrs.add(TimeUtils.convertDate2String(firstDayCurYear.getTime(), pattern));
        for (int i=1; i<dayOfYearNum ; i++) {
            firstDayCurYear.add(Calendar.DAY_OF_YEAR, 1);
            dayStrs.add(TimeUtils.convertDate2String(firstDayCurYear.getTime(), pattern));
        }
        return dayStrs;
    }
    
    /**
     * 获取去年同期时间
     * @return
     */
    public static Map get365Day(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map map = new HashMap();
        Calendar firstTime = Calendar.getInstance();
        firstTime.add(Calendar.DATE, -1); //得到前一天
        String firstDate = df.format(firstTime.getTime());
        //获取前七天时间
        Calendar lastTime = Calendar.getInstance();
        lastTime.add(Calendar.DATE, -365); //得到前七天
        String lastDate = df.format(lastTime.getTime());
        map.put("firstDate",lastDate);
        map.put("lastDate",firstDate);
        return map;
    }
    public static void xxxmain(String[] args){
        /*
         * List map=getTimeList(2019,11,2019,12,30); System.out.println(map.get(0));
         * System.out.println("2020-02-28".substring(0,7));
         */
        /*List<String> strs = getAllDayStrsCurYear("yyyy-MM-dd");
        for (String string : strs) {
            System.out.println(string);
        }*/
        System.out.println(getNowYear());
    }

    /**
     * 获取指定时间 所在月份的最后一天
     * @param time
     * @return
     */
    public static String getLastDay(String time) {
        Date date = TimeUtils.toDate(time, "yyyy-MM-dd");
        Integer nowYear = TimeUtils.getNowYear(date);
        Integer nowMonth = TimeUtils.getNowMonth(date);
        //获取Calendar类的实例
        Calendar c = Calendar.getInstance();
        //设置年份
        c.set(Calendar.YEAR, nowYear);
        //设置月份，因为月份从0开始，所以用month - 1
        c.set(Calendar.MONTH, nowMonth - 1);
        //获取当前时间下，该月的最大日期的数字
        int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        //将获取的最大日期数设置为Calendar实例的日期数
        c.set(Calendar.DAY_OF_MONTH, lastDay);
        //获取当前月第一天c.set(Calendar.DAY_OF_MONTH, lastDay);
        String newTime = TimeUtils.toString(c.getTime(), "yyyy-MM-dd");
        return newTime;
    }
    public static String lastYear(String datetime){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(simpleDateFormat.parse(datetime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //表示你需要减少的年数
        int reduceYear = -1;
        c.add(Calendar.YEAR,reduceYear);
        Date y = c.getTime();
        String year = simpleDateFormat.format(y);
        return year;
    }
    
    public static Date parseFormatDate(Date date, String targetPattern) {
    	SimpleDateFormat sdf = new SimpleDateFormat(targetPattern);
    	try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) { // 不会发生异常
			e.printStackTrace();
			return null;
		}
	}
    
    public static Date parseTimeString2Date(String timeString) {
        if ((timeString == null) || (timeString.equals(""))) {
            return null;
        }
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = new Date(dateFormat.parse(timeString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static String convertDate2String(Date date, String pattern) {
        if (date == null)
            return null;
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static int getYear(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd");
        return Integer.parseInt(timeStr.substring(0, 4));
    }

    public static int getMonth(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd");
        return Integer.parseInt(timeStr.substring(5, 7));
    }

    public static int getDay(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd");
        return Integer.parseInt(timeStr.substring(8, 10));
    }

    public static  int getHour(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd");
        return Integer.parseInt(timeStr.substring(11, 13));
    }

    public static int getMinute(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd");
        return Integer.parseInt(timeStr.substring(14, 16));
    }

    public static  int getSecond(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd");
        return Integer.parseInt(timeStr.substring(17, 19));
    }

    /**
     * 获取日期中的小时数字
     * @param date
     * @return
     */
    public static Integer getDateHour(Date date){
        DateFormat dateformat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss"
        );
        String dateStr = dateformat.format(date);
        String hourStr = dateStr.substring(11,13);
        Integer hour = Integer.valueOf(hourStr);
        return hour;
    }
    
    public static List<Date> getMinuteRangeDateList(Date startDate, Date endDate){
    	List<Date> rangDates = new ArrayList<Date>();
    	if(startDate == null) {
    		startDate = new Date();
    	}
    	if(endDate == null) {
    		endDate = new Date();
    	}
    	String pattern = "yyyy-MM-dd HH:mm:00";
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	Calendar startCalendar = Calendar.getInstance();
    	Calendar endCalendar = Calendar.getInstance();
    	try {
    		startCalendar.setTime(sdf.parse(sdf.format(startDate)));
    		endCalendar.setTime(sdf.parse(sdf.format(endDate)));
    	} catch (ParseException e) { // 不会出错
    		e.printStackTrace();
    	}
    	while(startCalendar.before(endCalendar) || startCalendar.equals(endCalendar)) { // 小于等于
    		rangDates.add(startCalendar.getTime());
    		startCalendar.add(Calendar.MINUTE, 1);
    	}
    	
    	return rangDates;
    }
    public static List<Date> getHourRangeDateList(Date startDate, Date endDate){
    	List<Date> rangDates = new ArrayList<Date>();
    	if(startDate == null) {
    		startDate = new Date();
    	}
    	if(endDate == null) {
    		endDate = new Date();
    	}
    	String pattern = "yyyy-MM-dd HH:00:00";
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	Calendar startCalendar = Calendar.getInstance();
    	Calendar endCalendar = Calendar.getInstance();
    	try {
			startCalendar.setTime(sdf.parse(sdf.format(startDate)));
			endCalendar.setTime(sdf.parse(sdf.format(endDate)));
		} catch (ParseException e) { // 不会出错
			e.printStackTrace();
		}
    	while(startCalendar.before(endCalendar) || startCalendar.equals(endCalendar)) { // 小于等于
    		rangDates.add(startCalendar.getTime());
    		startCalendar.add(Calendar.HOUR, 1);
		}
    	
    	return rangDates;
    }
    public static List<Date> getDayRangeDateList(Date startDate, Date endDate){
    	List<Date> rangDates = new ArrayList<Date>();
    	if(startDate == null) {
    		startDate = new Date();
    	}
    	if(endDate == null) {
    		endDate = new Date();
    	}
    	String pattern = "yyyy-MM-dd 00:00:00";
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	Calendar startCalendar = Calendar.getInstance();
    	Calendar endCalendar = Calendar.getInstance();
    	try {
    		startCalendar.setTime(sdf.parse(sdf.format(startDate)));
    		endCalendar.setTime(sdf.parse(sdf.format(endDate)));
    	} catch (ParseException e) { // 不会出错
    		e.printStackTrace();
    	}
    	while(startCalendar.before(endCalendar) || startCalendar.equals(endCalendar)) { // 小于等于
    		rangDates.add(startCalendar.getTime());
    		startCalendar.add(Calendar.DATE, 1);
    	}
    	
    	return rangDates;
    }

    public static List<Date> findRangeMonthEndDayList(Date startMonitorDate, Date endMonitorDate) {
		// 获取月截止日期数组
		List<Date> endDates = new ArrayList<Date>();
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(DateUtils.truncate(startMonitorDate, Calendar.MONTH)); // 指定年月第一天
		int startYear = startCalendar.get(Calendar.YEAR);
		int startMonth = startCalendar.get(Calendar.MONTH);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(DateUtils.truncate(endMonitorDate, Calendar.DATE)); // 指定年月第一天
		int endYear = endCalendar.get(Calendar.YEAR);
		int endMonth = endCalendar.get(Calendar.MONTH); 
		
		for (; startYear <= endYear; startYear++,startMonth=0) {
			for(; (startYear < endYear && startMonth <= 11) || (startYear == endYear && startMonth <= endMonth) ; startMonth++) {
				if(startYear == endYear && startMonth == endMonth) {
					endDates.add(endCalendar.getTime());
				}else {
					startCalendar.add(Calendar.MONTH, 1); // 下个月
					Date monthEndDay = DateUtils.addDays(startCalendar.getTime(), -1); // 最后一天
					endDates.add(monthEndDay);
				}
			}
		}
		return endDates;
	}
	
	public static List<Date> findRangeYearEndDayList(Date startMonitorDate, Date endMonitorDate) {
		// 获取月截止日期数组
		List<Date> endDates = new ArrayList<Date>();
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(DateUtils.truncate(startMonitorDate, Calendar.MONTH)); // 指定年月第一天
		int startYear = startCalendar.get(Calendar.YEAR);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(DateUtils.truncate(endMonitorDate, Calendar.DATE)); // 指定年月第一天
		int endYear = endCalendar.get(Calendar.YEAR);
		
		for (; startYear <= endYear; startYear++) {
			if(startYear == endYear ) {
				endDates.add(endCalendar.getTime());
			}else {
				startCalendar.add(Calendar.YEAR, 1); // 下个月
				Date monthEndDay = DateUtils.addDays(startCalendar.getTime(), -1); // 最后一天
				endDates.add(monthEndDay);
			}
		}
		return endDates;
	}
}
