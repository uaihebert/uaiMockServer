package test.com.uaihebert.uaimockserver.gui;

import com.uaihebert.uaimockserver.model.HttpStatusCode;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
public class JavascriptGetTest {

    @Test
    public void isReturning200OnIndex() {
        final String url = AbstractTestServletTests.GUI_URL + "javascript?fileName=angular";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        // must read the entity or an NIOException will raise
        response.readEntity(String.class);

        assertEquals(HttpStatusCode.OK.code, response.getStatus());
    }

    @Test
    public void isReturningContentAsJavascriptType() {
        final String url = AbstractTestServletTests.GUI_URL + "javascript?fileName=angular";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        // must read the entity or an NIOException will raise
        response.readEntity(String.class);

        assertEquals("application/javascript", response.getMediaType().toString());
    }
}
