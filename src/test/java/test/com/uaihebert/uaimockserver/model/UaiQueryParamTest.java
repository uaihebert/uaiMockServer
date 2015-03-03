package test.com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.model.UaiQueryParam;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UaiQueryParamTest {
    @Test
    public void isReturningNonNullList() {
        assertNotNull(new UaiQueryParam("null", false, null).getValueList());
    }
}