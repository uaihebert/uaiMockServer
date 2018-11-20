package test.com.uaihebert.uaimockserver.service;

import com.uaihebert.uaimockserver.log.backend.LogBuilder;
import com.uaihebert.uaimockserver.model.UaiCallback;
import com.uaihebert.uaimockserver.service.UaiCallbackService;
import org.junit.Before;
import org.junit.Test;
import test.com.uaihebert.uaimockserver.util.WebServerSimulator;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class UaiCallbackServiceTest {

    @Before
    public void before() {
        // wee need to instantiate the Log to be used in the service
        LogBuilder.createInstance(false, true);
    }

    @Test
    public void ifNoCallbackIsProvidedNoExceptionIsThrown() {
        UaiCallbackService.executeCallBack(null);
    }

    @Test
    public void isExecutingBasicGet() throws IOException {
        final WebServerSimulator webServer = new WebServerSimulator();
        webServer.waitForRequest();

        final UaiCallback callback = new UaiCallback();
        callback.setCompleteUrlToCall("http://localhost:" + webServer.getLocalPort() + "/");

        UaiCallbackService.executeCallBack(callback);

        assertTrue(webServer.hasReceivedRequest());
    }

    @Test
    public void isNotThrowingExceptionUpInCaseOfError() {
        final UaiCallback callback = new UaiCallback();
        callback.setCompleteUrlToCall("http://localhost:3787/");

        UaiCallbackService.executeCallBack(callback);
    }
}
