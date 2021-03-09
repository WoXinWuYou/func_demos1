package com.lmj.utils.datautil;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * @ProjectName: classifyserver
 * @Package: com.ldst.classifyserver.utils.datautil
 * @ClassName: TokenUtil
 * @Author: 孙培柱
 * @Description: ${description}
 * @Date: 2019-05-16 16:34
 * @Version: 1.0
 */
public class TokenUtil {

    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("token") != null ? (String) request.getHeader("token") : "";
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token") != null ? (String) request.getParameter("token") : "";
        }
        return token;
    }

    public static String createToken() {
        return GUIDUtil.createGUID();
    }

    public static String getAuthtoken(HttpServletRequest request) {
        String token = request.getHeader("Authentication-Token") != null ? (String) request.getHeader("Authentication-Token") : "";
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("Authentication-Token") != null ? (String) request.getParameter("Authentication-Token") : "";
        }
        return token;
    }
}
