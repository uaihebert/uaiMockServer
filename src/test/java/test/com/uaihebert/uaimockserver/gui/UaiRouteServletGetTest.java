package test.com.uaihebert.uaimockserver.gui;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.dto.response.IndexResponseDTO;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.server.UaiMockServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UaiRouteServletGetTest {
    private static UaiMockServer uaiMockServer;

    @BeforeClass
    public static void before() {
        final URL resource = UaiMockServerConfig.class.getResource("/configForListAllRoutes.json");

        uaiMockServer = UaiMockServer.start(resource.getFile());
    }

    @AfterClass
    public static void after() {
        uaiMockServer.shutdown();
    }

    @Test
    public void isReturning200OnIndex() {
        final String url = AbstractTestServletTests.GUI_URL +  "uaiRoute";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        // must read the entity or an NIOException will raise
        response.readEntity(String.class);

        assertEquals(200, response.getStatus());
    }

    @Test
    public void isReturningContentAsJsonType() {
        final String url = AbstractTestServletTests.GUI_URL +  "uaiRoute";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        // must read the entity or an NIOException will raise
        response.readEntity(String.class);

        assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());
    }

    @Test
    public void isReturningABody() {
        final String url = AbstractTestServletTests.GUI_URL +  "uaiRoute";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        final String bodyAsString = response.readEntity(String.class);

        final IndexResponseDTO indexResponseDTO = new Gson().fromJson(bodyAsString, IndexResponseDTO.class);

        assertNotNull("must have an body", indexResponseDTO);
    }

    @Test
    public void validatingIfAllRouteAttributesAreReturned() {
        final String url = AbstractTestServletTests.GUI_URL +  "uaiRoute";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        final String bodyAsString = response.readEntity(String.class);

        final IndexResponseDTO indexResponseDTO = new Gson().fromJson(bodyAsString, IndexResponseDTO.class);

        UaiRouteDTOTestValidator.hasAllAttributesPopulated(indexResponseDTO);
    }
}