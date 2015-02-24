package test.com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.model.UaiRoute;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UaiRouteTest {

    @Test
    public void isEqualsWorking() {
        final UaiRoute uaiRoute1 = new UaiRoute("111", null, null, null);
        final UaiRoute uaiRoute2 = new UaiRoute("111", null, null, null);

        assertTrue(uaiRoute1.equals(uaiRoute2));
    }

    @Test
    public void isNotEqualsWorking() {
        final UaiRoute uaiRoute1 = new UaiRoute("111", null, null, null);
        final UaiRoute uaiRoute2 = new UaiRoute("222", null, null, null);

        assertFalse(uaiRoute1.equals(uaiRoute2));
    }
}