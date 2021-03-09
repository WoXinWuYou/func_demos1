package com.lmj.converter;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import com.lmj.utils.ExceptionUtils;

public class CourseDateConverter implements Converter<String, Date>  {
	private static String[] parsePatterns = {
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
	
	@Override
	public Date convert(String value) {
		if (value == null){
			return null;
		}
		try {
			return DateUtils.parseDate(value.toString(), parsePatterns);
		} catch (ParseException e) {
			// LogUtils.error("时间字符串：{}，格式化异常，异常信息：{}",value,ExceptionUtils.getStackTraceAsString(e));
			throw new RuntimeException("时间字符串："+ value +"，格式化异常，异常信息："+ ExceptionUtils.getStackTraceAsString(e));
		}
	}
}
