package com.lmj.log;
/***************************************************** 
 * HISTORY 
 *
 * 2019/ Biz 创建文件 
 *
 *******************************************************/

import java.math.BigDecimal;

import org.slf4j.LoggerFactory;

/**
 * @type: AuditCustomerController
 * @company: 立东生态
 * @author: zxj
 * @Date: 2019年11月23日
 * @Description:
 */


public class LogUtils {

    public static void error(String msg) {
        LoggerFactory.getLogger(getClassName()).error(msg);
    }

    public static void error(String msg, Object... obj) {
        LoggerFactory.getLogger(getClassName()).error(msg, obj);
    }

    public static void warn(String msg) {
        LoggerFactory.getLogger(getClassName()).error(msg);
    }

    public static void warn(String msg, Object... obj) {
        LoggerFactory.getLogger(getClassName()).error(msg, obj);
    }

    public static void info(String msg) {
        LoggerFactory.getLogger(getClassName()).info(msg);
    }

    public static void info(String msg, Object... obj) {
        LoggerFactory.getLogger(getClassName()).info(msg, obj);
    }

    public static void debug(String msg) {
        LoggerFactory.getLogger(getClassName()).debug(msg);
    }

    public static void debug(String msg, Object... obj) {
        LoggerFactory.getLogger(getClassName()).debug(msg, obj);
    }

    /**
     * 以秒为单位的间隔时间
     * @param startTimeInMillis
     * @return
     */
    public static Double getIntervalTimeInSeconds(Long startTimeInMillis) {
    	Double intervalTimeInSeconds = (System.currentTimeMillis()-startTimeInMillis)/1000.0;
    	BigDecimal bigDecimal = new BigDecimal(intervalTimeInSeconds);
    	return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
    
    // 获取调用 error,info,debug静态类的类名
    private static String getClassName() {
        return new SecurityManager() {
            public String getClassName() {
                return getClassContext()[3].getName();
            }
        }.getClassName();
    }


}
