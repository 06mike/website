package org.com.demo.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

public class Demo {

    public static void main(String[] args) {
        Person person = new Person();
        System.err.println(person.getAdd());
    }

    public static <T> void invokeSetter(T target, String fieldName, Object args)
            throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        if (StringUtils.isNotBlank(fieldName)) {
            String methodName = "set" + firstCharUpperCase(fieldName);
            Class<?> clazz = target.getClass();
            Field field = clazz.getDeclaredField(fieldName);//获取字段
            Method method = clazz.getMethod(methodName, field.getType());
            method.invoke(target, args);
        }
    }

    /**
     * 
     * @MethodName: firstCharUpperCase
     * @Description: 将首字母大写
     * @param str 需要转换的字符串
     * @return 转换后的字符串
     */
    public static String firstCharUpperCase(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (str.length() > 0) {
            char c = stringBuilder.charAt(0);
            stringBuilder.setCharAt(0, Character.toUpperCase(c));
        }
        return stringBuilder.toString();
    }

    /**
     * 1、通过反射执行方法invoke 需要方法名称，和方法的参数列表
     */

}
