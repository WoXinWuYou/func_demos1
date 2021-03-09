package com.lmj.constant;

public class Constant {
	public static final String INIT = "init";

	public static final String POLLUTION_NULL = "—";

	public static final Double SO2_HOUR_LIMIT_VALUE = 800.0;
	public static final Double SO2_24HOUR_LIMIT_VALUE = 2620.0;
	public static final Double NO2_HOUR_LIMIT_VALUE = 3840.0;
	public static final Double NO2_24HOUR_LIMIT_VALUE = 940.0;
	public static final Double PM25_24HOUR_LIMIT_VALUE = 500.0;
	public static final Double PM10_24HOUR_LIMIT_VALUE = 600.0;
	public static final Double CO_HOUR_LIMIT_VALUE = 150.0;
	public static final Double CO_24HOUR_LIMIT_VALUE = 60.0;
	public static final Double O3_HOUR_LIMIT_VALUE = 1200.0;
	public static final Double O38_LIMIT_VALUE = 800.0;

	/** 大气指标参数名定义 */
	public static final String SO2_TYPE="SO2";
	public static final String NO2_TYPE="NO2";
	public static final String O3_TYPE="O3";
	public static final String PM25_TYPE="PM25";
	public static final String PM10_TYPE="PM10";
	public static final String CO_TYPE="CO";
	
	/**
	 * 大气报警类型，1-离线;2-零值、负值;3-颗粒物倒挂;4-t突高;5-突低;6-恒值
	 */
	/** 离线 */
	public static final String AIR_OFFLINE_ALARM="1";
	/** 零负值*/
	public static final String AIR_INVALID_ALARM="2";
	/** 颗粒物倒挂 */
	public static final String AIR_INVERSE_ALARM="3";
	/** 突高 */
	public static final String AIR_OBVIOUS_HIGH_ALARM="4";
	/** 突低 */
	public static final String AIR_OBVIOUS_LOW_ALARM="5";
	/** 恒值 */
	public static final String AIR_CONSTANT_ALARM="6";
	
	/**
	 * 市级以下大气站点类型：1、市控，2、乡镇，3-园区
	 */
	/** 乡镇站 */
	public static final int AIR_COUNTY_POINT= 2;
	/** 园区站 */
	public static final int AIR_PARK_POINT= 3;
}
