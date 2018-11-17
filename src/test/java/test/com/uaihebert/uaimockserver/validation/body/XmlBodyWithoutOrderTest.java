package test.com.uaihebert.uaimockserver.validation.body;

import com.uaihebert.uaimockserver.model.HttpStatusCode;
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
@UaiRunnerMockServerConfiguration(configurationFile = "bodyXmlValidationTest.json")
public class XmlBodyWithoutOrderTest {

    @Test
    public void isRejectingWhenNoBody() {
        final String url = "http://localhost:1234/uaiMockServer/xmlWithoutOrderTest";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().post(Entity.entity("", MediaType.TEXT_PLAIN_TYPE));

        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getStatus());
    }

    @Test
    public void isRejectingWhenAllAttributesWithWrongValueInBody() {
        final String url = "http://localhost:1234/uaiMockServer/xmlWithoutOrderTest";

        final Client client = ClientBuilder.newClient();

        final String json = "<person> <id>32</id> <age>44</age> </person>";
        final Entity<String> body = Entity.entity(json, MediaType.TEXT_PLAIN_TYPE);
        final Response response = client.target(url).request().post(body);

        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getStatus());
    }

    @Test
    public void isRejectingWhenOnlyOneCorrectValueInBody() {
        final String url = "http://localhost:1234/uaiMockServer/xmlWithoutOrderTest";

        final Client client = ClientBuilder.newClient();

        final String json = "<person> <id>1</id> <age>334</age> </person>";
        final Entity<String> body = Entity.entity(json, MediaType.TEXT_PLAIN_TYPE);
        final Response response = client.target(url).request().post(body);

        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getStatus());
    }

    @Test
    public void isAcceptingTheCorrectValueWithDifferentOrder() {
        final String url = "http://localhost:1234/uaiMockServer/xmlWithoutOrderTest";

        final Client client = ClientBuilder.newClient();

        final String json = "<person> <age>33</age> <id>1</id> </person>";
        final Entity<String> body = Entity.entity(json, MediaType.TEXT_PLAIN_TYPE);
        final Response response = client.target(url).request().post(body);

        assertEquals(HttpStatusCode.NO_CONTENT.code, response.getStatus());
    }

    @Test
    public void isAcceptingTheCorrectValue() {
        final String url = "http://localhost:1234/uaiMockServer/xmlWithoutOrderTest";

        final Client client = ClientBuilder.newClient();

        final String json = "<person> <id>1</id> <age>33</age> </person>";
        final Entity<String> body = Entity.entity(json, MediaType.TEXT_PLAIN_TYPE);
        final Response response = client.target(url).request().post(body);

        assertEquals(HttpStatusCode.NO_CONTENT.code, response.getStatus());
    }
}
