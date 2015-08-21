package test.com.uaihebert.uaimockserver.runner;

import com.uaihebert.uaimockserver.runner.UaiMockServerSpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertNotNull;

@RunWith(UaiMockServerSpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-test.xml")
public class UaiMockServerSpringRunnerTest {

    @Autowired
    private ClassToInject classToInject;

    @Test
    public void isCreatingSpringContext() {
        assertNotNull("A instance should be used inject", classToInject);
    }

    @Test
    public void shouldNotRaiseErrorIfIsStartedAgain() throws InitializationError {
        final UaiMockServerSpringRunner runner = new UaiMockServerSpringRunner(UaiMockServerSpringRunnerTest.class);

        runner.run(new RunNotifier());
    }
}