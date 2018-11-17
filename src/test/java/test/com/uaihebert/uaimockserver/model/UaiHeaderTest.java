package test.com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.model.UaiHeader;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UaiHeaderTest {
    @Test
    public void isReturningNonNullList() {
        assertNotNull(new UaiHeader("null", false, null).getValueList());
    }
}
