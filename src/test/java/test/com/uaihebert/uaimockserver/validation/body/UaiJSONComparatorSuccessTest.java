package test.com.uaihebert.uaimockserver.validation.body;

import com.uaihebert.uaimockserver.validator.body.UaiJSONComparator;
import com.uaihebert.uaimockserver.validator.body.UaiJSONCompareWrapper;
import org.junit.Test;
import org.skyscreamer.jsonassert.FieldComparisonFailure;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.JSONComparator;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class UaiJSONComparatorSuccessTest {
    private static final JSONComparator STRICT_COMPARATOR = new UaiJSONComparator(JSONCompareMode.STRICT);
    private static final JSONComparator LENIENT_COMPARATOR = new UaiJSONComparator(JSONCompareMode.LENIENT);

    @Test
    public void isComparingWithSuccess() {
        final JSONCompareResult jsonCompareResult = UaiJSONCompareWrapper.compareJSON(
            "{id:1}",
            "{id:1}",
            STRICT_COMPARATOR
        );

        final List<FieldComparisonFailure> failureList = jsonCompareResult.getFieldFailures();

        assertTrue("Should not have any error", failureList.isEmpty());
    }

    @Test
    public void isIgnoringWhenNotRequired() {
        final JSONCompareResult jsonCompareResult = UaiJSONCompareWrapper.compareJSON(
            "{id:1}",
            "{id:1, age:33}",
            LENIENT_COMPARATOR
        );

        final List<FieldComparisonFailure> failureList = jsonCompareResult.getFieldFailures();

        assertTrue("Should not have any error", failureList.isEmpty());
    }
}
