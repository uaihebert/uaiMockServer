package test.com.uaihebert.uaimockserver.validation.body;

import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "bodyRegexValidationTest.json")
public class ByRegexTest {

    @Test
    public void isRejectingWhenNoBody() {
        final String url = "http://localhost:1234/uaiMockServer/regexBodyRequired";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().post(Entity.entity("", MediaType.TEXT_PLAIN_TYPE));

        assertEquals(500, response.getStatus());
    }

    @Test
    public void isRejectingWhenDoesntMatchAnyPatterns() {
        final String url = "http://localhost:1234/uaiMockServer/regexNotMatchAny";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().post(Entity.entity("{value: simple-text}", MediaType.TEXT_PLAIN_TYPE));

        assertEquals(500, response.getStatus());
    }

    @Test
    public void isRejectingWhenDoesntMatchOnePattern() {
        final String url = "http://localhost:1234/uaiMockServer/regexNotMatchOne";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().post(Entity.entity("{value: .regex, name: uaihebert}", MediaType.TEXT_PLAIN_TYPE));

        assertEquals(500, response.getStatus());
    }

    @Test
    public void isAcceptingWhenMatchingAllPatterns() {
        final String url = "http://localhost:1234/uaiMockServer/regexMatchAll";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().post(Entity.entity("{value: .regex, name: uaihebert, id: 1234}", MediaType.TEXT_PLAIN_TYPE));

        assertEquals(204, response.getStatus());
    }
}
