package test.com.uaihebert.uaimockserver.gui;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.dto.model.UaiFileDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRequestDTO;
import com.uaihebert.uaimockserver.dto.model.UaiResponseDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.dto.response.IndexResponseDTO;
import com.uaihebert.uaimockserver.model.BodyValidationType;
import com.uaihebert.uaimockserver.model.HttpStatusCode;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "routePostTest.json")
public class UaiRouteServletPostTest {
    public long getTotalOfRoutesFound() {
        final IndexResponseDTO toUpdateIndex = listAll();

        if (toUpdateIndex.getRouteList() != null) {
            return toUpdateIndex.getRouteList().size();
        }

        return 0;
    }

    private IndexResponseDTO listAll() {
        final String url = AbstractTestServletTests.GUI_URL + "uaiRoute";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        final String bodyAsString = response.readEntity(String.class);

        return new Gson().fromJson(bodyAsString, IndexResponseDTO.class);
    }

    @Test
    public void isCreating() {
        final long totalBeforePost = getTotalOfRoutesFound();

        final String entity = createEntity();

        final String url = AbstractTestServletTests.GUI_URL + "uaiRoute";

        final Client client = ClientBuilder.newClient();
        final Response post = client.target(url).request().post(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(HttpStatusCode.NO_CONTENT.code, post.getStatus());

        final long totalAfterPost = getTotalOfRoutesFound();
        assertEquals("making sure that only one was added", totalBeforePost + 1, totalAfterPost);
    }

    private String createEntity() {
        final URL resource = UaiMockServerConfig.class.getResource("/routePostTest.json");

        final UaiRouteDTO uaiRouteDTO = new UaiRouteDTO();
        uaiRouteDTO.setUaiFile(new UaiFileDTO("routePostTest.json", resource.getFile()));

        final UaiRequestDTO request = new UaiRequestDTO();
        request.setBodyRequired(true);
        request.setBodyValidationType(BodyValidationType.VALIDATE_IF_PRESENT_ONLY);

        uaiRouteDTO.setRequest(request);
        uaiRouteDTO.setResponse(new UaiResponseDTO());

        return new Gson().toJson(uaiRouteDTO);
    }
}
