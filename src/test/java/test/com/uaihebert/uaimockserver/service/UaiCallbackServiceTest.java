package test.com.uaihebert.uaimockserver.service;

import com.uaihebert.uaimockserver.constants.UaiHttpMethod;
import com.uaihebert.uaimockserver.log.backend.LogBuilder;
import com.uaihebert.uaimockserver.model.UaiCallback;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.service.UaiCallbackService;
import org.junit.Before;
import org.junit.Test;
import test.com.uaihebert.uaimockserver.factory.UnirestRequestFactoryTest;
import test.com.uaihebert.uaimockserver.util.WebServerSimulator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class UaiCallbackServiceTest {

    @Before
    public void before() {
        // wee need to instantiate the Log to be used in the service
        LogBuilder.createInstance(false, true);
    }

    @Test
    public void ifNoCallbackIsProvidedNoExceptionIsThrown() {
        UaiCallbackService.executeCallback(null);
    }

    @Test
    public void isExecutingBasicGet() throws IOException, InterruptedException {
        final WebServerSimulator webServer = new WebServerSimulator();
        webServer.waitForRequest();

        final UaiCallback callback = new UaiCallback();
        callback.setHttpMethod(UaiHttpMethod.GET);
        callback.setCompleteUrlToCall("http://localhost:" + webServer.getLocalPort() + "/");
        callback.setDelayInMilli(0);

        UaiCallbackService.executeCallback(callback);

        waitForAsyncThreads();

        assertTrue(webServer.hasReceivedRequest());
    }

    @Test
    public void isExecutingGetWithQueryParams() throws IOException, InterruptedException {
        final WebServerSimulator webServer = new WebServerSimulator();
        webServer.waitForRequest();

        final UaiCallback callback = new UaiCallback();
        callback.setHttpMethod(UaiHttpMethod.GET);
        callback.setCompleteUrlToCall("http://localhost:" + webServer.getLocalPort() + "/");
        callback.setDelayInMilli(0);

        final List<String> values = new ArrayList<String>();
        values.add("ValueA");
        values.add("ValueB");
        final UaiQueryParam queryParamA = new UaiQueryParam("QueryParamA", false, values);
        final UaiQueryParam queryParamB = new UaiQueryParam("QueryParamB", false, values);

        callback.getQueryParamList().add(queryParamA);
        callback.getQueryParamList().add(queryParamB);

        UaiCallbackService.executeCallback(callback);

        waitForAsyncThreads();

        assertTrue(webServer.hasReceivedRequest());
        assertTrue(webServer.receivedRequestContains(queryParamA.getName()));
        assertTrue(webServer.receivedRequestContains(queryParamB.getName()));
        assertTrue(webServer.receivedRequestContains(values.get(0)));
        assertTrue(webServer.receivedRequestContains(values.get(1)));
    }

    @Test
    public void isExecutingGetWithHeaders() throws IOException, InterruptedException {
        final WebServerSimulator webServer = new WebServerSimulator();
        webServer.waitForRequest();

        final UaiCallback callback = new UaiCallback();
        callback.setHttpMethod(UaiHttpMethod.GET);
        callback.setCompleteUrlToCall("http://localhost:" + webServer.getLocalPort() + "/");
        callback.setDelayInMilli(0);

        final List<String> values = new ArrayList<String>();
        values.add("ValueA");
        values.add("ValueB");
        final UaiHeader headerA = new UaiHeader("HeaderA", false, values);
        final UaiHeader headerB = new UaiHeader("HeaderB", false, values);

        callback.getHeaderList().add(headerA);
        callback.getHeaderList().add(headerB);

        UaiCallbackService.executeCallback(callback);

        waitForAsyncThreads();

        assertTrue(webServer.hasReceivedRequest());
        assertTrue(webServer.receivedRequestContains(headerA.getName()));
        assertTrue(webServer.receivedRequestContains(headerB.getName()));
        assertTrue(webServer.receivedRequestContains(values.get(0)));
        assertTrue(webServer.receivedRequestContains(values.get(1)));
    }

    @Test
    public void isNotThrowingExceptionUpInCaseOfError() throws InterruptedException {
        final UaiCallback callback = new UaiCallback();
        callback.setHttpMethod(UaiHttpMethod.GET);
        callback.setCompleteUrlToCall("http://localhost:3787/");
        callback.setDelayInMilli(0);

        UaiCallbackService.executeCallback(callback);

        waitForAsyncThreads();
    }

    @Test
    public void isSendingRequestWithBody() throws IOException, InterruptedException {
        for (final UaiHttpMethod method : UnirestRequestFactoryTest.BODY_ALLOWED) {
            final WebServerSimulator webServer = new WebServerSimulator();
            webServer.waitForRequest();

            final UaiCallback callback = new UaiCallback();
            callback.setDelayInMilli(0);
            callback.setCompleteUrlToCall("http://localhost:" + webServer.getLocalPort() + "/");
            callback.setHttpMethod(method);
            callback.setBodyToSend("{\"testBody\":1234}");

            UaiCallbackService.executeCallback(callback);

            waitForAsyncThreads();

            assertTrue(webServer.hasReceivedRequest());
            assertTrue(webServer.receivedRequestContains("{\"testBody\":1234}"));
        }
    }

    // we now have 2 async threads running.
    // 1) WebServerSimulator
    // 2) Callback execution
    // we need a sleep here to wait for those threads to finish the proces
    private void waitForAsyncThreads() throws InterruptedException {
        final int waitTimeInMillis = 600;
        Thread.sleep(waitTimeInMillis);
    }
}
