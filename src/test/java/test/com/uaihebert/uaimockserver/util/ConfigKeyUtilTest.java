package test.com.uaihebert.uaimockserver.util;

import com.typesafe.config.Config;
import com.uaihebert.uaimockserver.factory.TypeSafeConfigFactory;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.util.ConfigKeyUtil;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertFalse;

public class ConfigKeyUtilTest {

    @Test
    public void isReturningBooleanDefaultInsteadNull() {
        final URL resource = UaiMockServerConfig.class.getResource("/performanceTest.config");
        final Config config = TypeSafeConfigFactory.loadConfiguration(new File(resource.getFile()));

        final boolean doNotExist = ConfigKeyUtil.getBooleanSilently("doNotExist", config);
        assertFalse("making sure that if the config is not found, false is returned", doNotExist);
    }
}