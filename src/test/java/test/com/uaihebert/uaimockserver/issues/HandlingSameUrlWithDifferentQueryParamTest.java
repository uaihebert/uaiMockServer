package test.com.uaihebert.uaimockserver.issues;

import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "issueHandlingSameUrlWithDifferentQueryParam.json")
public class HandlingSameUrlWithDifferentQueryParamTest {
    // todo create a test like this for queryParam

    @Test
    public void isReturningResponseCorrect() {

        final String firstRequestURL = "http://localhost:1234/uaiMockServer/queryParamAmountTest";

        final Client firstRequest = ClientBuilder.newClient();
        final Response firstResponse = firstRequest
                .target(firstRequestURL)
                .request()
                .get();

        // must be 500 because the headers were not sent
        assertEquals(500, firstResponse.getStatus());

        final String url = "http://localhost:1234/uaiMockServer/queryParamAmountTest?param01=a&param02=b";

        final Client secondClient = ClientBuilder.newClient();
        final Response secondResponse = secondClient
                .target(url)
                .request()
                .get();

        assertEquals(201, secondResponse.getStatus());
    }
}
