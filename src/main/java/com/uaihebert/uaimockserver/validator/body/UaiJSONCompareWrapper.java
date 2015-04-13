package com.uaihebert.uaimockserver.validator.body;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.JSONComparator;

public class UaiJSONCompareWrapper {
    private UaiJSONCompareWrapper() {
    }

    public static JSONCompareResult compareJSON(String expectedStr, String actualStr, JSONComparator comparator){
        try {
            return JSONCompare.compareJSON(expectedStr, actualStr, comparator);
        } catch (JSONException ex) {
            throw new IllegalStateException(ex);
        }
    }
}