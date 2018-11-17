package test.com.uaihebert.uaimockserver.gui;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.dto.response.IndexResponseDTO;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "routeDeleteTest.json")
public class UaiRouteServletDeleteTest extends AbstractTestServletTests {
    public long getTotalOfRoutesFound() {
        final IndexResponseDTO toUpdateIndex = listAll();

        if (toUpdateIndex.getRouteList() != null) {
            return toUpdateIndex.getRouteList().size();
        }

        return 0;
    }

    @Test
    public void isDeleting() {
        final long totalBeforeDelete = getTotalOfRoutesFound();

        final UaiRouteDTO deleted = listAll().getRouteList().get(0);

        final String url = AbstractTestServletTests.GUI_URL + "uaiRoute?routeId=" + deleted.getId();

        final Client client = ClientBuilder.newClient();
        client.target(url).request().delete();

        final long totalAfterDelete = getTotalOfRoutesFound();

        // rollback
        executePost(deleted);

        assertEquals("making sure that only one was deleted", totalBeforeDelete - 1, totalAfterDelete);
    }

    private IndexResponseDTO listAll() {
        final String url = AbstractTestServletTests.GUI_URL + "uaiRoute";

        final WebTarget client = ClientBuilder.newClient().target(url);
        final Response response = client.request().get();

        final String bodyAsString = response.readEntity(String.class);

        return new Gson().fromJson(bodyAsString, IndexResponseDTO.class);
    }
}
