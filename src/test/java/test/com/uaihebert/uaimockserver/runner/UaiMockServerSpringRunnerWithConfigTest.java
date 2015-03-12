package test.com.uaihebert.uaimockserver.runner;

import com.uaihebert.uaimockserver.runner.UaiMockServerSpringRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertNotNull;

@RunWith(UaiMockServerSpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-test.xml")
@UaiRunnerMockServerConfiguration(configurationFile = "uaiMockServer.json")
public class UaiMockServerSpringRunnerWithConfigTest {

    @Autowired
    private ClassToInject classToInject;

    @Test
    public void isCreatingSpringContext() {
        assertNotNull("A instance should be used inject", classToInject);
    }
}