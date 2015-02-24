package test.com.uaihebert.uaimockserver.gui;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.dto.response.IndexResponseDTO;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
public class UaiRouteServletDeleteTest {
    public long getTotalOfRoutesFound() {
        final IndexResponseDTO toUpdateIndex = listAll();

        if (toUpdateIndex.getRouteList() != null) {
            return toUpdateIndex.getRouteList().size();
        }

        return 0;
    }

    private IndexResponseDTO listAll() {
        final String url = "http://localhost:1234/uai-mock-server-gui/uaiRoute";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        final String bodyAsString = response.readEntity(String.class);

        return new Gson().fromJson(bodyAsString, IndexResponseDTO.class);
    }

    @Test
    public void isDeleting() {
        final long totalBeforeDelete = getTotalOfRoutesFound();

        final UaiRouteDTO uaiRouteDTO = listAll().getRouteList().get(0);

        final String url = "http://localhost:1234/uai-mock-server-gui/uaiRoute?routeId="+uaiRouteDTO.getId();

        final Client client = ClientBuilder.newClient();
        client.target(url).request().delete();

        final long totalAfterDelete = getTotalOfRoutesFound();
        assertEquals("making sure that only one was deleted", (totalBeforeDelete - 1), totalAfterDelete);
    }
}