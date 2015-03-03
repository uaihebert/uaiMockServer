package com.uaihebert.uaimockserver.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRoute;

public class GsonAttributesToIgnore implements ExclusionStrategy {

    public boolean shouldSkipClass(Class<?> arg0) {
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return (f.getDeclaringClass() == UaiMockServerConfig.class && f.getName().equals("uaiFile")) ||
                (f.getDeclaringClass() == UaiRoute.class && f.getName().equals("uaiFile"));
    }
}