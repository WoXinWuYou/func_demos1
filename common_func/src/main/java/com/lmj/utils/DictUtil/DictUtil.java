package com.lmj.utils.DictUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.lmj.entity.Dict;
import com.lmj.mapper.JsonMapper;

@Component
@ConditionalOnClass(RedisTemplate.class)
public class DictUtil {

    private static final String DICT_CACHE_KEY = "dictMap";
    private static final String DEFAULT_DICT_VALUE = "";
	private static final String DEFAULT_DICT_LABEL = "";
	
    @Autowired
    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    private void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate){
        DictUtil.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 根据类型获取指定类型的所有字典信息
     * @param type
     * @return
     */
    public static List<Dict> getDictList(String type){
        Map<String, List<Dict>> dictsMap = init();
        List<Dict> dictList = dictsMap.get(type);
        if (dictList.size()==0){
            dictList = new ArrayList<>();
        }else {
            Collections.sort(dictList, new Comparator<Dict>() {
                @Override
                public int compare(Dict o1, Dict o2) {
                    if (o1!=null && o2!=null && o1.getSort()!=null && o2.getSort()!=null){
                        return o1.getSort()-o2.getSort();
                    }
                    return 0;
                }
            }); // 升序排列
        }
        return dictList;
    }

    public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
    
    public static String getDictLabel(String value, String type){
		return getDictLabel(value,type,DEFAULT_DICT_LABEL);
	}
	
	/**
	 * @param values 逗号分隔
	 * @param type
	 * @param defaultValue
	 * @return 逗号分隔
	 */
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type){
		return getDictLabels(values,type,DEFAULT_DICT_LABEL);
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static String getDictValue(String label, String type){
		return getDictValue(label,type,DEFAULT_DICT_VALUE);
	}

    public static Map<String, List<Dict>> init() {
        Map<String, List<Dict>> dictsMsp = new HashMap<String, List<Dict>>();
        String dictAllCacheStr = "";
        dictAllCacheStr = stringRedisTemplate.opsForValue().get(DICT_CACHE_KEY);
        if (StringUtils.isNotBlank(dictAllCacheStr)) {
            JsonMapper jsonMapper = new JsonMapper();

            try {
                dictsMsp = (Map<String, List<Dict>>) jsonMapper.readValue(dictAllCacheStr,  new TypeReference<Map<String,List<Dict>>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dictsMsp;
    }


}
