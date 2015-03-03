package test.com.uaihebert.uaimockserver.runner;

import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * Will test for several requests in the runner and making sure that
 * it will not mix configurations with the RunnerWithConfig02Test.class
 */
@RunWith(UaiMockServerRunner.class)
@UaiServerConfiguration(configurationFile = "runnerWithConfigTest01.json")
public class RunnerWithConfig01Test {

    @Test
    public void isDefaultConfigurationInvoked01(){
        final String url = "http://localhost:1234/uaiMockServer/runnerWithConfig01";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        assertEquals(204, response.getStatus());
    }

    @Test
    public void isDefaultConfigurationInvoked02(){
        final String url = "http://localhost:1234/uaiMockServer/runnerWithConfig01";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        assertEquals(204, response.getStatus());
    }

    @Test
    public void isDefaultConfigurationInvoked03(){
        final String url = "http://localhost:1234/uaiMockServer/runnerWithConfig01";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        assertEquals(204, response.getStatus());
    }
}