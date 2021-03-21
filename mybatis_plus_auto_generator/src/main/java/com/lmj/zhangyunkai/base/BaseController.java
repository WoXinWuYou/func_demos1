package com.lmj.zhangyunkai.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lmj.zhangyunkai.sys.entity.TSysUser;

import lombok.extern.slf4j.Slf4j;

/**
 * web层通用数据处理
 *
 * @author framework
 */
@Slf4j
public class BaseController {
	protected TSysUser getCurrentUser() {
        Object currUser = getRequestAttribute("currUser");
        JSONObject userJson = JSONObject.parseObject(JSON.toJSONString(currUser));
        TSysUser user = JSON.toJavaObject(userJson, TSysUser.class);
        return user;
    }


    private Object getRequestAttribute(String key) {
        Object value = null;
        try {
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            value = request.getAttribute(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
