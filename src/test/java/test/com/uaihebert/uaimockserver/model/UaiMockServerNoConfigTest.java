package test.com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.server.UaiMockServer;
import org.junit.Test;

public class UaiMockServerNoConfigTest {
    @Test
    public void isNotRaisingErrorIfRouteListIsNotPresent() {
        final UaiMockServer uaiMockServer = UaiMockServer.start("configWithoutFileMappList.config");
        uaiMockServer.shutdown();
    }
}