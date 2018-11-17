package test.com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.util.GsonCollectionAdapter;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertNull;

public class GsonCollectionAdapterTest {

    @Test
    public void whenNullDoNotRaiseError() {
        assertNull(new GsonCollectionAdapter().serialize(null, null, null));
    }

    @Test
    public void whenEmptyDoNotRaiseError() {
        assertNull(new GsonCollectionAdapter().serialize(Collections.emptyList(), null, null));
    }
}
