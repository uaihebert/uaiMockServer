package com.uaihebert.uaimockserver.validator.body;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.JSONComparator;

public final class UaiJSONCompareWrapper {
    private UaiJSONCompareWrapper() {
    }

    public static JSONCompareResult compareJSON(final String expectedStr,
                                                final String actualStr,
                                                final JSONComparator comparator) {
        try {
            return JSONCompare.compareJSON(expectedStr, actualStr, comparator);
        } catch (JSONException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
