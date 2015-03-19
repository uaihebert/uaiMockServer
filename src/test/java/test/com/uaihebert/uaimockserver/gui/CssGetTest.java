package test.com.uaihebert.uaimockserver.gui;

import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
public class CssGetTest {

    @Test
    public void isReturning200OnIndex() {
        final String url = AbstractTestServletTests.GUI_URL + "css?fileName=bootstrap";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        // must read the entity or an NIOException will raise
        response.readEntity(String.class);

        assertEquals(200, response.getStatus());
    }

    @Test
    public void isReturningContentAsCssType() {
        final String url = AbstractTestServletTests.GUI_URL + "css?fileName=bootstrap";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        // must read the entity or an NIOException will raise
        response.readEntity(String.class);

        assertEquals("text/css", response.getMediaType().toString());
    }
}