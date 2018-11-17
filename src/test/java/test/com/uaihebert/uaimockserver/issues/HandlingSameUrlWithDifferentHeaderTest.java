package test.com.uaihebert.uaimockserver.issues;

import com.uaihebert.uaimockserver.model.HttpStatusCode;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "issueHandlingSameUrlWithDifferentHeaders.json")
public class HandlingSameUrlWithDifferentHeaderTest {

    @Test
    public void isReturningResponseCorrect() {

        final String firstRequestURL = "http://localhost:1234/uaiMockServer/headerAmountTest";

        final Client firstRequest = ClientBuilder.newClient();
        final Response firstResponse = firstRequest
                .target(firstRequestURL)
                .request()
                .get();

        // must be 500 because the headers were not sent
        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, firstResponse.getStatus());

        final String url = "http://localhost:1234/uaiMockServer/headerAmountTest";

        final Client secondClient = ClientBuilder.newClient();
        final Response secondResponse = secondClient
                .target(url)
                .request()
                .header("A_HEADER_03", "A_VALUE")
                .header("A_HEADER_04", "A_VALUE")
                .get();

        assertEquals(HttpStatusCode.CREATED.code, secondResponse.getStatus());
    }
}
