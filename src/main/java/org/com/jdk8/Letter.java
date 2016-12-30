package org.com.jdk8;

public class Letter {

    public static String addHeader(String text) {
        return "From: " + text;
    }

    public static String addFooter(String text) {
        return "by: " + text;
    }

    public static String checkSpelling(String text) {
        return text.toUpperCase();
    }

}
