package com.uaihebert.uaimockserver.validator.body;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.DefaultComparator;
import org.skyscreamer.jsonassert.comparator.JSONComparator;

import java.util.Set;

import static org.skyscreamer.jsonassert.comparator.JSONCompareUtil.getKeys;
import static org.skyscreamer.jsonassert.comparator.JSONCompareUtil.qualify;

public class UaiJSONComparator extends DefaultComparator implements JSONComparator {

    public UaiJSONComparator(JSONCompareMode mode) {
        super(mode);
    }

    @Override
    protected void checkJsonObjectKeysExpectedInActual(String prefix, JSONObject expected, JSONObject actual, JSONCompareResult result) throws JSONException {
        final Set<String> expectedKeys = getKeys(expected);
        for (String key : expectedKeys) {
            final Object expectedValue = expected.get(key);
            if (actual.has(key)) {
                final Object actualValue = actual.get(key);
                compareValues(qualify(prefix, key), expectedValue, actualValue, result);
            } else {
                result.missing(prefix, key);
                result.fail(key, expectedValue, "we did not received the value");
            }
        }
    }

    @Override
    protected void checkJsonObjectKeysActualInExpected(String prefix, JSONObject expected, JSONObject actual, JSONCompareResult result) {
        final Set<String> actualKeys = getKeys(actual);

        for (String key : actualKeys) {
            if (!expected.has(key)) {
                result.unexpected(prefix, key);
                final Object actualValue = actual.opt(key);
                result.fail(key, actualValue, String.format("The [%s] is not mapped", key));
            }
        }
    }
}