package test.com.uaihebert.uaimockserver.validation;

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
@UaiRunnerMockServerConfiguration(configurationFile = "repeatedUrlWithHeaderTest.json")
public class RepeatedURLDifferentHeaderTest {

    @Test
    public void isFindingUrlWithDifferentHeader01() {
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client
            .target(url)
            .request()
            .header("HEADER-01", "HEADER_VALUE_01")
            .get();

        assertEquals(HttpStatusCode.CREATED.code, response.getStatus());
    }

    @Test
    public void isFindingUrlWithDifferentHeader02() {
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client
            .target(url)
            .request()
            .header("HEADER-02", "HEADER_VALUE_02")
            .get();

        assertEquals(HttpStatusCode.ACCEPTED.code, response.getStatus());
    }

    @Test
    public void isFindingUrlWithDifferentHeader03() {
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client
            .target(url)
            .request()
            .header("HEADER-03", "HEADER_VALUE_0AAA")
            .get();

        assertEquals(HttpStatusCode.NON_AUTHORITATIVE.code, response.getStatus());
    }

    @Test
    public void isFindingUrlWithDifferentHeader04() {
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client
            .target(url)
            .request()
            .header("HEADER-03", "HEADER_VALUE_0BBB")
            .get();

        assertEquals(HttpStatusCode.NO_CONTENT.code, response.getStatus());
    }

    @Test
    public void isThrowingExceptionIfNoneIsFound() {
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client
            .target(url)
            .request()
            .get();

        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getStatus());
    }
}
