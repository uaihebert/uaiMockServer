package com.uaihebert.uaimockserver.util;

public final class StringUtils {
    private StringUtils() {
    }

    public static boolean isBlank(final String text) {
        if (text == null) {
            return true;
        }

        return text.trim().length() == 0;

    }

    public static boolean isNotBlank(final String text) {
        return !isBlank(text);
    }
}