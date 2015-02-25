package test.com.uaihebert.uaimockserver.gui;

import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(UaiMockServerRunner.class)
@UaiServerConfiguration(configurationFile = "routePostTest.config")
public class UaiRouteServletPostTest {

    @Test
    public void isCreatingRecord() {
        // todo create test
    }
}