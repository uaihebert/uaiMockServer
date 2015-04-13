package test.com.uaihebert.uaimockserver.validation.body;

import com.uaihebert.uaimockserver.validator.body.UaiJSONComparator;
import com.uaihebert.uaimockserver.validator.body.UaiJSONCompareWrapper;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.FieldComparisonFailure;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.JSONComparator;

import java.util.List;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UaiJSONComparatorTest {
    private static final JSONComparator STRICT_COMPARATOR = new UaiJSONComparator(JSONCompareMode.STRICT);

    @Test
    public void isNotFailingWhenThereIsNoValue() throws JSONException {
        final JSONCompareResult jsonCompareResult = UaiJSONCompareWrapper.compareJSON("{}", "{}", STRICT_COMPARATOR);
        assertFalse("should not fail", jsonCompareResult.failed());
    }

    @Test
    public void isListingWhenAttributeIsNotPresent() {
        final JSONCompareResult jsonCompareResult = UaiJSONCompareWrapper.compareJSON("{id:1}", "{}", STRICT_COMPARATOR);

        final List<FieldComparisonFailure> failureList = jsonCompareResult.getFieldFailures();

        assertFalse("should list a missing field as field failure", failureList.isEmpty());

        final FieldComparisonFailure failure = failureList.get(0);

        assertEquals("the id field should be listed", "id", failure.getField());
    }

    @Test
    public void isListingMoreThanOneNotPresentAttribute() {
        final JSONCompareResult jsonCompareResult = UaiJSONCompareWrapper.compareJSON("{id:1, age:1, aNumber:1}", "{name:\"JC\"}", STRICT_COMPARATOR);

        final List<FieldComparisonFailure> failureList = jsonCompareResult.getFieldFailures();

        assertTrue("all the missing fields should be present", failureList.size() == 3);
    }

    @Test
    public void isThrowingRuntimeWhenSomethingWrongsHappen() {
        try {
            final String badFormattedJSON = "";
            UaiJSONCompareWrapper.compareJSON("{id:1, age:1, aNumber:1}", badFormattedJSON, STRICT_COMPARATOR);
            fail("should have an error");
        } catch (final RuntimeException ex) {
            // its the expected error
        }
    }

    @Test
    public void isComparingWithSuccess() {
        final JSONCompareResult jsonCompareResult = UaiJSONCompareWrapper.compareJSON("{id:1}", "{id:1}", STRICT_COMPARATOR);

        final List<FieldComparisonFailure> failureList = jsonCompareResult.getFieldFailures();

        assertTrue("Should not have any error", failureList.isEmpty());
    }

    @Test
    public void isComparingWithLine() {
        final String jsonWithoutLines = "{id:1,age:33}";
        final String jsonWithLines = "" +
                "{" +
                "   id:1," +
                "   age:33" +
                "}";
        final JSONCompareResult jsonCompareResult = UaiJSONCompareWrapper.compareJSON(jsonWithoutLines, jsonWithLines, STRICT_COMPARATOR);

        final List<FieldComparisonFailure> failureList = jsonCompareResult.getFieldFailures();

        assertTrue("Should not have any error", failureList.isEmpty());
    }
}