package test.com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.model.UaiQueryParam;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;

public class UaiQueryParamTest {
    @Test
    public void isReturningNonNullListWithEmpty() {
        assertNotNull(new UaiQueryParam("null", Collections.<String>emptyList()).getValueList());
    }

    @Test
    public void isReturningNonNullListWithNull() {
        assertNotNull(new UaiQueryParam("null", null).getValueList());
    }
}