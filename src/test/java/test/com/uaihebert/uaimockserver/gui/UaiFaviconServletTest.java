package test.com.uaihebert.uaimockserver.gui;

import com.uaihebert.uaimockserver.model.HttpStatusCode;
import org.junit.Test;
import test.com.uaihebert.uaimockserver.TestAbstract;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class UaiFaviconServletTest extends TestAbstract {

    @Test
    public void isReturning200OnIndex() {
        final String url = "http://localhost:1234/favicon.ico";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        assertEquals(HttpStatusCode.OK.code, response.getStatus());
    }

    @Test
    public void isReturningContentAsPngType() {
        final String url = "http://localhost:1234/favicon.ico";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        assertEquals("image/png", response.getMediaType().toString());
    }
}
