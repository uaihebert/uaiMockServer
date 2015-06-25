package test.com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.model.UaiHeader;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;

public class UaiHeaderTest {

    @Test
    public void isReturningNonNullListWithEmpty() {
        assertNotNull(new UaiHeader("null", Collections.<String>emptyList()).getValueList());
    }

    @Test
    public void isReturningNonNullListWithNull() {
        assertNotNull(new UaiHeader("null", null).getValueList());
    }
}