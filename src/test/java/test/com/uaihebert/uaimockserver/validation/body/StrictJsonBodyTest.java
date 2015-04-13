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
@UaiRunnerMockServerConfiguration(configurationFile = "bodyJsonStrictTest.json")
public class StrictJsonBodyTest {

    @Test
    public void isRejectingWhenNoBody() {
        final String url = "http://localhost:1234/uaiMockServer/jsonStrictBodyRequired";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().post(Entity.entity("", MediaType.TEXT_PLAIN_TYPE));

        assertEquals(500, response.getStatus());
    }

    @Test
    public void isRejectingWhenAllAttributesWithWrongValueInBody() {
        final String url = "http://localhost:1234/uaiMockServer/jsonStrictBodyRequired";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().post(Entity.entity("{id:12,age:334}", MediaType.TEXT_PLAIN_TYPE));

        assertEquals(500, response.getStatus());
    }

    @Test
    public void isRejectingWhenOnlyOneCorrectValueInBody() {
        final String url = "http://localhost:1234/uaiMockServer/jsonStrictBodyRequired";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().post(Entity.entity("{id:1,age:334}", MediaType.TEXT_PLAIN_TYPE));

        assertEquals(500, response.getStatus());
    }

    @Test
    public void isAcceptingTheCorrectValue() {
        final String url = "http://localhost:1234/uaiMockServer/jsonStrictBodyRequired";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().post(Entity.entity("{id:1,age:33}", MediaType.TEXT_PLAIN_TYPE));

        assertEquals(204, response.getStatus());
    }
}