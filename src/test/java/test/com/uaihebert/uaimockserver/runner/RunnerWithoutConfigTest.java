package test.com.uaihebert.uaimockserver.runner;

import com.uaihebert.uaimockserver.model.HttpStatusCode;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
public class RunnerWithoutConfigTest {

    @Test
    public void isDefaultConfigurationInvoked() {
        final String url = "http://localhost:1234/uaiMockServer/";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        assertEquals(HttpStatusCode.OK.code, response.getStatus());
    }

    @Test
    public void isDefaultConfigurationInvoked2() {
        final String url = "http://localhost:1234/uaiMockServer/";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        assertEquals(HttpStatusCode.OK.code, response.getStatus());
    }
}
