package test.com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "optionalHeaderTest.json")
public class OptionalHeaderTest {

    @Test
    public void testIsReturningCorrect() {
        final String noHeaderUrl = "http://localhost:1234/uaiMockServer/optionalHeaderTest";
        final Client noHeaderClient = ClientBuilder.newClient();

        final Response noHeaderResponse = noHeaderClient
                .target(noHeaderUrl)
                .request()
                .get();

        assertEquals(201, noHeaderResponse.getStatus());

        final String url = "http://localhost:1234/uaiMockServer/optionalHeaderTest";

        final Client client = ClientBuilder.newClient();
        final Response response = client
                .target(url)
                .request()
                .header("OPTIONAL_01", "01")
                .header("OPTIONAL_02", "02")
                .get();

        assertEquals(204, response.getStatus());
    }

    @Test
    public void testWorkingWithWildCard() {
        final String url = "http://localhost:1234/uaiMockServer/optionalHeaderTest";

        final Client client = ClientBuilder.newClient();
        final Response response = client
                .target(url)
                .request()
                .header("OPTIONAL_03", "ANY VALUE")
                .get();

        assertEquals(202, response.getStatus());
    }

    @Test
    public void isThrowingExceptionWhenOptionalHeaderValueIsNotPresent() {
        final String url = "http://localhost:1234/uaiMockServer/optionalHeaderTest";

        final Client client = ClientBuilder.newClient();
        final Response response = client
                .target(url)
                .request()
                .header("OPTIONAL_01", "01")
                .header("OPTIONAL_02", "INVALID")
                .get();

        assertEquals(500, response.getStatus());
    }
}