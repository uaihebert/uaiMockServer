package test.com.uaihebert.uaimockserver.gui;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.dto.response.IndexResponseDTO;
import com.uaihebert.uaimockserver.model.HttpStatusCode;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "configForListAllRoutes.json")
public class UaiRouteServletGetTest {

    @Test
    public void isReturning200OnIndex() {
        final String url = AbstractTestServletTests.GUI_URL +  "uaiRoute";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        // must read the entity or an NIOException will raise
        response.readEntity(String.class);

        assertEquals(HttpStatusCode.OK.code, response.getStatus());
    }

    @Test
    public void isReturningContentAsJsonType() {
        final String url = AbstractTestServletTests.GUI_URL +  "uaiRoute";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        // must read the entity or an NIOException will raise
        response.readEntity(String.class);

        assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());
    }

    @Test
    public void isReturningABody() {
        final String url = AbstractTestServletTests.GUI_URL +  "uaiRoute";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        final String bodyAsString = response.readEntity(String.class);

        final IndexResponseDTO indexResponseDTO = new Gson().fromJson(bodyAsString, IndexResponseDTO.class);

        assertNotNull("must have an body", indexResponseDTO);
    }

    @Test
    public void validatingIfAllRouteAttributesAreReturned() {
        final String url = AbstractTestServletTests.GUI_URL +  "uaiRoute";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        final String bodyAsString = response.readEntity(String.class);

        final IndexResponseDTO indexResponseDTO = new Gson().fromJson(bodyAsString, IndexResponseDTO.class);

        UaiRouteDTOTestValidator.hasAllAttributesPopulated(indexResponseDTO);
    }
}
