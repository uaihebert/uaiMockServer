package test.com.uaihebert.uaimockserver.service;

import com.uaihebert.uaimockserver.log.backend.LogBuilder;
import com.uaihebert.uaimockserver.model.UaiCallback;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.service.UaiCallbackService;
import org.junit.Before;
import org.junit.Test;
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
    public void isExecutingGetWithQueryParams() throws IOException {
        final WebServerSimulator webServer = new WebServerSimulator();
        webServer.waitForRequest();

        final UaiCallback callback = new UaiCallback();
        callback.setCompleteUrlToCall("http://localhost:" + webServer.getLocalPort() + "/");

        final List<String> values = new ArrayList<String>();
        values.add("ValueA");
        values.add("ValueB");
        final UaiQueryParam queryParamA = new UaiQueryParam("QueryParamA", false, values);
        final UaiQueryParam queryParamB = new UaiQueryParam("QueryParamB", false, values);

        callback.getQueryParamList().add(queryParamA);
        callback.getQueryParamList().add(queryParamB);

        UaiCallbackService.executeCallBack(callback);

        assertTrue(webServer.hasReceivedRequest());
        assertTrue(webServer.receivedRequestContains(queryParamA.getName()));
        assertTrue(webServer.receivedRequestContains(queryParamB.getName()));
        assertTrue(webServer.receivedRequestContains(values.get(0)));
        assertTrue(webServer.receivedRequestContains(values.get(1)));
    }

    @Test
    public void isExecutingGetWithHeaders() throws IOException {
        final WebServerSimulator webServer = new WebServerSimulator();
        webServer.waitForRequest();

        final UaiCallback callback = new UaiCallback();
        callback.setCompleteUrlToCall("http://localhost:" + webServer.getLocalPort() + "/");

        final List<String> values = new ArrayList<String>();
        values.add("ValueA");
        values.add("ValueB");
        final UaiHeader headerA = new UaiHeader("HeaderA", false, values);
        final UaiHeader headerB = new UaiHeader("HeaderB", false, values);

        callback.getHeaderList().add(headerA);
        callback.getHeaderList().add(headerB);

        UaiCallbackService.executeCallBack(callback);

        assertTrue(webServer.hasReceivedRequest());
        assertTrue(webServer.receivedRequestContains(headerA.getName()));
        assertTrue(webServer.receivedRequestContains(headerB.getName()));
        assertTrue(webServer.receivedRequestContains(values.get(0)));
        assertTrue(webServer.receivedRequestContains(values.get(1)));
    }

    @Test
    public void isNotThrowingExceptionUpInCaseOfError() {
        final UaiCallback callback = new UaiCallback();
        callback.setCompleteUrlToCall("http://localhost:3787/");

        UaiCallbackService.executeCallBack(callback);
    }
}
