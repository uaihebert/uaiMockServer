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
@UaiRunnerMockServerConfiguration(configurationFile = "repeatedUrlWithQueryParamTest.json")
public class RepeatedURLDifferentQueryParamTest {

    @Test
    public void isFindingUrlWithDifferentQuery01(){
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingQuery?QUERY_01=QUERY_VALUE_01";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                .target(url)
                .request()
                .get();

        assertEquals(201, response.getStatus());
    }

    @Test
    public void isFindingUrlWithDifferentQuery02(){
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingQuery?QUERY_02=QUERY_VALUE_02";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                .target(url)
                .request()
                .get();

        assertEquals(202, response.getStatus());
    }

    @Test
    public void isFindingUrlWithDifferentQuery03(){
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingQuery?QUERY_03=QUERY_VALUE_0AAA";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                .target(url)
                .request()
                .get();

        assertEquals(203, response.getStatus());
    }

    @Test
    public void isFindingUrlWithDifferentQuery04(){
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingQuery?QUERY_03=QUERY_VALUE_0BBB";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                .target(url)
                .request()
                .get();

        assertEquals(204, response.getStatus());
    }

    @Test
    public void isThrowingExceptionIfNoneIsFound(){
        final String url = "http://localhost:1234/uaiMockServer/sameURLUsingQuery";

        final Client client = ClientBuilder.newClient();

        final Response response = client
                .target(url)
                .request()
                .get();

        assertEquals(500, response.getStatus());
    }
}