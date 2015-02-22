package test.com.uaihebert.uaimockserver.gui;

import org.junit.Test;
import test.com.uaihebert.uaimockserver.TestAbstract;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class CssServletTest extends TestAbstract {

    @Test
    public void isReturning200OnIndex() {
        final String url = "http://localhost:1234/uai-mock-server-gui/css?fileName=bootstrap";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        // must read the entity or an NIOException will raise
        response.readEntity(String.class);

        assertEquals(200, response.getStatus());
    }

    @Test
    public void isReturningContentAsCssType() {
        final String url = "http://localhost:1234/uai-mock-server-gui/css?fileName=bootstrap";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        // must read the entity or an NIOException will raise
        response.readEntity(String.class);

        assertEquals("text/css", response.getMediaType().toString());
    }
}
