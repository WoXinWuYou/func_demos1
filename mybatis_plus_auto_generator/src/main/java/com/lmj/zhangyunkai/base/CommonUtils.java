package com.lmj.zhangyunkai.base;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiasq
 * @version CommonUtils, v0.1 2017/11/11 11:51
 */
@Slf4j
public class CommonUtils {

	/**
	 * 提供对象属性null转""方法，目前只支持String的属性
	 * 
	 * @param obj
	 */
	public static Object convertNullToEmptyString(Object obj) {
		if (obj == null) {
            return obj;
        }
		// 获取对象属性
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			// 跳过静态属性
			String mod = Modifier.toString(field.getModifiers());
			if (mod.indexOf("static") != -1) {
                continue;
            }

			// 得到属性的类名
			String className = field.getType().getSimpleName();
			if ("String".equalsIgnoreCase(className)) {
				try {
					field.setAccessible(true); // 设置些属性是可以访问的
					Object val = field.get(obj);
					if (val == null) {
						field.set(obj, "");
					}
				} catch (IllegalAccessException e) {
					log.error(e.getMessage());
				}
			}
		}
		return obj;
	}

	/**
	 * 提供对象属性null转""方法，目前只支持String的属性
	 * 
	 * @param colls
	 * @return
	 */
	public static Collection convertNullToEmptyString(Collection colls) {
		for (Object coll : colls) {
			convertNullToEmptyString(coll);
		}
		return colls;
	}

	/**
	 * 如果为空就从collection中移除
	 * @auth framework
	 * @param
	 * @return
	 */
	public static void removeIfNullOrNullStr(Collection collection){
		collection.removeAll(Collections.singleton(null));
		collection.removeAll(Collections.singleton(""));
	}





	/**
	 * list数据循环copyProperties
	 * @param sources
	 * @param clazz
	 * @param <S>
	 * @param <T>
	 * @return
	 * @author zhangyk
	 * @date 2018年6月6日14:57:50
	 */
	public static <S, T> List<T> convertBeanList(List<S> sources, Class<T> clazz) {
		return sources.stream().map(source -> convertBean(source, clazz)).collect(Collectors.toList());
	}
	/**
	 * list数据循环copyProperties
	 * @param sources
	 * @param clazz
	 * @param <S>
	 * @param <T>
	 * @return
	 * @author zhangyk
	 * @date 2018年6月6日14:57:50
	 */
	public static <S, T> Collection<T> convertBeanCollection(Collection<S> sources, Class<T> clazz) {
		return sources.stream().map(source -> convertBean(source, clazz)).collect(Collectors.toList());
	}

	/**
	 * 简单属性copy
	 * @param s
	 * @param clazz
	 * @param <S>
	 * @param <T>
	 * @author zhangyk
	 * @date 2018年6月6日14:57:50
	 */
	public static <S, T> T convertBean(S s, Class<T> clazz) {
		if (s == null) {
			return null;
		}
		try {
			T t = clazz.newInstance();
			BeanUtils.copyProperties(s, t);
			return t;
		} catch (InstantiationException | IllegalAccessException e) {
			log.error("拷贝属性异常", e);
			throw new RuntimeException("拷贝属性异常");
		}
	}

	/**
	 *
	 * 格式化手机号
	 *
	 * @param phoneNum
	 * @return
	 */
	public static String convertMobilePhone(String phoneNum) {
		StringBuilder sb = new StringBuilder(11);
		StringBuilder temp = new StringBuilder(phoneNum.toString());

		while (temp.length() < 11) {
			return temp.toString();
		}

		char[] chars = temp.toString().toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (i == 3)
				sb.append(" ");
			else if (i == 7)
				sb.append(" ");
			sb.append(chars[i]);
		}
		return sb.toString();
	}
}
