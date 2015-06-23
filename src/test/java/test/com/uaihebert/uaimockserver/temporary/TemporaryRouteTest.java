package test.com.uaihebert.uaimockserver.temporary;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.dto.model.UaiFileDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRequestDTO;
import com.uaihebert.uaimockserver.dto.model.UaiResponseDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import test.com.uaihebert.uaimockserver.gui.AbstractTestServletTests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "temporaryRouteTest.json")
public class TemporaryRouteTest {
    private int totalTemporaryReplies = 3;

    @BeforeClass
    public static void beforeClass() {
        UaiRouteRepository.clearData();
    }

    @Test
    public void isTemporaryWorking() {
        createRouteUsingSuite();

        validateThatThereIsTemporaryRoute();

        fireRequestsToTheTemporaryRequest();

        validateThatThereIsNoRequestRecorded();
    }

    private void validateThatThereIsNoRequestRecorded() {
        for (UaiRoute uaiRoute : UaiRouteRepository.listAllRoutes()) {
            if (uaiRoute.isTemporary()) {
                fail("it should have none temporary request");
            }
        }
    }

    private void fireRequestsToTheTemporaryRequest() {
        for (int i = 0; i < totalTemporaryReplies; i++) {
            final String url = "http://localhost:1234/uaiMockServer/temporaryRequest";

            final Client client = ClientBuilder.newClient();
            final Response response = client
                    .target(url)
                    .request()
                    .get();

            assertEquals(204, response.getStatus());
        }
    }

    private void validateThatThereIsTemporaryRoute() {
        for (UaiRoute uaiRoute : UaiRouteRepository.listAllRoutes()) {
            if (uaiRoute.isTemporary() && uaiRoute.getTemporaryRepliesTotal() == totalTemporaryReplies) {
                return;
            }
        }

        fail("it should have a temporary route in the database");
    }

    private void createRouteUsingSuite() {
        final String entity = createEntity();
        final String url = AbstractTestServletTests.GUI_URL + "uaiRoute";

        final Client client = ClientBuilder.newClient();
        final Response post = client.target(url).request().post(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(204, post.getStatus());
    }

    private String createEntity() {
        final URL resource = UaiMockServerConfig.class.getResource("/temporaryRouteTest.json");

        final UaiRouteDTO uaiRouteDTO = new UaiRouteDTO();
        uaiRouteDTO.setUaiFile(new UaiFileDTO("temporaryRouteTest.json", resource.getFile()));

        UaiRequestDTO request = new UaiRequestDTO();
        request.setMethod("GET");
        request.setPath("/temporaryRequest");

        uaiRouteDTO.setRequest(request);
        final UaiResponseDTO response = new UaiResponseDTO();
        response.setStatusCode(204);

        uaiRouteDTO.setResponse(response);

        uaiRouteDTO.setTemporary(true);
        uaiRouteDTO.setTemporaryRepliesTotal(totalTemporaryReplies);

        return new Gson().toJson(uaiRouteDTO);
    }
}
