package com.uaihebert.uaimockserver.util;

public final class StringUtils {
    public static final String EMPTY = "";

    private StringUtils() {
    }

    public static boolean isBlank(final String text) {
        if (text == null) {
            return true;
        }

        if (text.trim().length() == 0) {
            return true;
        }

        return false;
    }

    public static boolean isNotBlank(final String text) {
        return !isBlank(text);
    }
}