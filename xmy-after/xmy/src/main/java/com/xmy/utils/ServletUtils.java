package com.xmy.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Servlet工具类
 */
public class ServletUtils {
    /**
     * 获取当前请求
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取请求参数
     *
     * @param name 参数名
     * @return 参数值
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取请求参数（整数）
     *
     * @param name 参数名
     * @param defaultValue 默认值
     * @return 参数值
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        String value = getParameter(name);
        if (value != null && !value.isEmpty()) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 获取请求参数（布尔值）
     *
     * @param name 参数名
     * @param defaultValue 默认值
     * @return 参数值
     */
    public static Boolean getParameterToBoolean(String name, Boolean defaultValue) {
        String value = getParameter(name);
        if (value != null && !value.isEmpty()) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }
}