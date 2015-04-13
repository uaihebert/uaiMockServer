package com.uaihebert.uaimockserver.validator.body;

import com.uaihebert.uaimockserver.log.backend.Log;
import org.skyscreamer.jsonassert.FieldComparisonFailure;
import org.skyscreamer.jsonassert.JSONCompareResult;

import java.util.List;

public final class UaiJsonFieldFailureLogger {
    private UaiJsonFieldFailureLogger() {
    }

    public static void logFailure(final String errorText, final JSONCompareResult jsonResult) {
        final List<FieldComparisonFailure> failureList = jsonResult.getFieldFailures();

        for (FieldComparisonFailure failure : failureList) {
            Log.warnFormatted(errorText, failure.getField(), failure.getExpected(), failure.getActual());
        }
    }
}