package test.com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.model.BackUpStrategy;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UaiMockServerConfigTest {

    @Test
    public void isReturningOneFileAsDefaultStrategy() {
        UaiMockServerConfig uaiMockServerConfig = new UaiMockServerConfig();
        assertEquals("must have the one file as the default", BackUpStrategy.ONLY_ONE_FILE, uaiMockServerConfig.getBackUpStrategy());
    }
}
