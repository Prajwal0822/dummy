package com.gvnc.sample.web.util;

import java.lang.reflect.Method;

public class StringUtil {

    public static boolean isEmpty(String string) {
        return string == null || string.trim().equalsIgnoreCase("");
    }

    public static boolean isEmpty(StringBuilder stringBuilder) {
        return stringBuilder == null || stringBuilder.toString().trim().equalsIgnoreCase("");
    }

    public static String escapeEmptyString(String string) {
        if (isEmpty(string)) {
            return null;
        }
        return string;
    }

    public static String logFormat(Object object, String ... parameters) {

        StringBuffer buf = new StringBuffer();
        buf.append("[");
        for(String parameter:parameters) {
            buf.append(" ").append(parameter).append(":");
            try {
                String methodName = "get" + parameter.substring(0,1).toUpperCase() + parameter.substring(1);
                Method method = object.getClass().getMethod(methodName);
                Object value = method.invoke(object);
                buf.append(value).append(" ,");
            } catch (Exception e) {}
        }
        buf.deleteCharAt(buf.length()-1);
        buf.append("]");

        return buf.toString();
    }
}
