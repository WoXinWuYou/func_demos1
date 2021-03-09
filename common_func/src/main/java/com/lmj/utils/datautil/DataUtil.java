package com.lmj.utils.datautil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataUtil {
	
	public static Double doubleFormat(String pattern, Double data) {
		if(data != null) {
			data = Double.parseDouble(new DecimalFormat(pattern).format(data));
		}
		return data;
	}
	
	/**
     * 求和
     * @param values 可传空
     * @return
     */
    public static Double sum(Double ... values) {
    	if(values == null || values.length == 0) {
    		return null;
    	}
    	Double sum = 0d;
    	int count = 0;
    	for (Double value : values) {
			if(value != null) {
				sum += value;
				count ++;
			}
		}
    	if(count == 0) {
    		return null;
    	}else {
    		return sum;
    	}
    }
    
    /**
     * 返回最大值，可能为null
     * @param A
     * @param B
     * @return
     */
    public static Double max(Double A, Double B) {
    	if(A == null) {
    		return B;
    	}else if(B == null) {
    		return A;
    	}else {
    		return Math.max(A, B);
    	}
    }
    
    /**
     * 求百分位数结果
     * @param values 源数据
     * @param percentiles 百分位数
     * @return
     */
    public static Double percentiles(Double[] values, int percentiles) {
    	return percentiles(DoubleArrTodoubleArr(values),percentiles);
    }
    
    /**
     * 求百分位数结果
     * @param values 源数据
     * @param percentiles 百分位数
     * @return
     */
    public static Double percentiles(double[] values, int percentiles) {
    	Double result = null;
    	if(values == null || values.length == 0) {
    		return result;
    	}
    	// 从小到大排序
    	Arrays.sort(values);
    	// 百分位数值
    	double k =  1 + (values.length - 1)*percentiles*1.0/100; // 百分位数序数
    	int s = (int) Math.floor(k); // 百分位数序数整数部分
		if(s >= values.length) { // 可能出现数组长度为1，数组越界
    		result = values[s-1];
    	}else {
    		result = values[s-1] + (values[s] - values[s-1]) * (k-s);
    	}
    	return result;
    }
    
    /**
     * 计算平均值
     * @param values 允许为空，允许有空元素
     * @return
     */
    public static Double avg(Double[] values) {
		return avg(DoubleArrTodoubleArr(values));
	}
    /**
     * 计算平均值
     * @param values 允许为空
     * @return
     */
    public static Double avg(double[] values) {
    	Double result = null;
    	if(values == null || values.length == 0) {
    		return result;
    	}
    	Double sum = 0d;
    	for (double value : values) {
			sum += value;
    	}
		result = sum/values.length;
    	return result;
    }
    
    public static Double intToDouble(Integer intValue) {
		return intValue == null? null : Double.valueOf(intValue.toString());
	}
    /**
     * double数组到Double数组转换
     * @param values 源数据
     * @param percentiles 百分位数
     * @return
     */
    public static Double[] doubleArrToDoubleArr(double[] values) {
    	if(values == null || values.length == 0) {
    		return null;
    	}
    	Double[] valueDoubles = new Double[values.length];
    	for (int i=0; i<values.length; i++) {
    		valueDoubles[i] = values[i];
		}
    	return valueDoubles;
    }
    
    /**
     * Double数组到double数组转换
     * @param values 允许为空，允许有空元素
     * @return
     */
    public static double[] DoubleArrTodoubleArr(Double[] values) {
    	if(values == null || values.length == 0) {
    		return null;
    	}
    	List<Double> valueList = new ArrayList<>();
		for (Double value : values) {
			if(value != null) {
				valueList.add(value);
			}
		}
		
		return valueList.stream().mapToDouble(Double::valueOf).toArray();
	}
}
