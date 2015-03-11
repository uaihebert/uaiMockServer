package com.uaihebert.uaimockserver.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collection;

public final class JsonUtil {
    private static final GsonBuilder GSON_BUILDER = new GsonBuilder()
                                                        .setPrettyPrinting()
                                                        .disableHtmlEscaping()
                                                        .registerTypeHierarchyAdapter(Collection.class, new GsonCollectionAdapter())
                                                        .setExclusionStrategies(new GsonAttributesToIgnore());

    private JsonUtil() {
    }

    public static <T> String toJsonWithNoEscaping(T originClass) {
        return GSON_BUILDER.create().toJson(originClass);
    }

    public static <T> T fromJson(String json, Class<T> targetClass) {
        return new Gson().fromJson(json, targetClass);
    }

    public static <T> String toJson(T originClass) {
        return new Gson().toJson(originClass);
    }
}