package com.uaihebert.uaimockserver.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.Arrays;
import java.util.List;

public class GsonAttributesToIgnore implements ExclusionStrategy {

    private static final List<String> ALWAYS_TO_SKIP_ATTRIBUTES = Arrays.asList("usingWildCard", "uaiFile", "id");

    public boolean shouldSkipClass(Class<?> arg0) {
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return ALWAYS_TO_SKIP_ATTRIBUTES.contains(f.getName());
    }
}