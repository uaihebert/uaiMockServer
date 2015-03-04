package test.com.uaihebert.uaimockserver.validation;

import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "repeatedUrlTest.json")
public class RepeatedURLDifferentHeaderTest {

    @Test
    public void isFindingUrlWithDifferentHeader01(){
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                .target(url)
                .request()
                .header("HEADER-01", "HEADER_VALUE_01")
                .get();

        assertEquals(201, response.getStatus());
    }

    @Test
    public void isFindingUrlWithDifferentHeader02(){
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                .target(url)
                .request()
                .header("HEADER-02", "HEADER_VALUE_02")
                .get();

        assertEquals(202, response.getStatus());
    }

    @Test
    public void isFindingUrlWithDifferentHeader03(){
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                .target(url)
                .request()
                .header("HEADER-03", "HEADER_VALUE_0AAA")
                .get();

        assertEquals(203, response.getStatus());
    }

    @Test
    public void isFindingUrlWithDifferentHeader04(){
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingHeader";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                .target(url)
                .request()
                .header("HEADER-03", "HEADER_VALUE_0BBB")
                .get();

        assertEquals(204, response.getStatus());
    }
}