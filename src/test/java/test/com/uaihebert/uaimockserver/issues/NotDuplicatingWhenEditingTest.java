package test.com.uaihebert.uaimockserver.issues;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.dto.response.IndexResponseDTO;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import test.com.uaihebert.uaimockserver.gui.AbstractTestServletTests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "issueNotDuplicatingWhenEditing.json")
public class NotDuplicatingWhenEditingTest extends AbstractTestServletTests {
    private static final String TO_BE_EDITED_NAME = "To Be Edited";

    @Test
    public void isNotDuplicating() {
        final int beforeUpdateCount = UaiRouteRepository.count();
        assertFalse("must have route", beforeUpdateCount < 1);

        final UaiRouteDTO toUpdateRoute = getRouteToBeEdited();
        final String oldPath = toUpdateRoute.getRequest().getPath();

        toUpdateRoute.getRequest().setPath("/");

        executePut(toUpdateRoute);

        // rollback
        toUpdateRoute.getRequest().setDescription(oldPath);
        executePut(toUpdateRoute);

        assertEquals("making sure that both names are equals", beforeUpdateCount, UaiRouteRepository.count());
    }


    private UaiRouteDTO getRouteToBeEdited() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(GUI_SERVLET_URL).request().get();

        final String bodyAsString = response.readEntity(String.class);

        final IndexResponseDTO toUpdateIndex = new Gson().fromJson(bodyAsString, IndexResponseDTO.class);

        for (UaiRouteDTO uaiRouteDTO : toUpdateIndex.getRouteList()) {
            if (TO_BE_EDITED_NAME.equals(uaiRouteDTO.getRequest().getName())) {
                return uaiRouteDTO;
            }
        }

        throw new IllegalArgumentException("route [" + TO_BE_EDITED_NAME + "] not found");
    }
}
