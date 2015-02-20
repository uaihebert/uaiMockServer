package test.com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.util.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class StringUtilsTest {

    @Test
    public void isNotEmptyWorkingWhenEmpty() {
        assertFalse("return false when blank", StringUtils.isNotBlank(""));
    }
}