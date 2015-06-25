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
@UaiRunnerMockServerConfiguration(configurationFile = "sameUrlWithDifferentBodyTest.json")
public class SameUrlDifferentBodyTest {

    @Test
    public void isAcceptingTheCorrectValue() {
        for (int i = 0; i < 4; i++) {
            request(33, 200);
            request(34, 204);
        }
    }

    private void request(final int age, final int expectedStatus) {
        final String url = "http://localhost:1234/uaiMockServer/sameUrlWithDifferentBody";

        final Client client = ClientBuilder.newClient();

        final Response response = client.target(url).request().post(Entity.entity("{id:1,age:" + age +"}", MediaType.TEXT_PLAIN_TYPE));

        assertEquals(expectedStatus, response.getStatus());
    }
}