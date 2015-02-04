package test.com.uaihebert.uaimockserver.validation;

import org.junit.Test;
import test.com.uaihebert.uaimockserver.TestAbstract;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class RequestContentTypeValidationTest extends TestAbstract {

    @Test
    public void isRaisingErrorIfContentTypeIsWrong() {
        final String url = "http://localhost:1234/uaiMockServer/requiredRequestContentType";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().get();

        assertEquals(500, response.getStatus());
    }

    @Test
    public void isNotBlockingWithRightContentType() {
        final String url = "http://localhost:1234/uaiMockServer/requiredRequestContentType";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                                    .target(url)
                                    .request()
                                    .header("Content-Type", "application/json")
                                    .get();

        assertEquals(204, response.getStatus());
    }
}