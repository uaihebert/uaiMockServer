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
public class UaiRouteServletPutSecondaryMappingTest extends AbstractTestServletTests {

    @Test
    public void isUpdatingRecord() {
        final String oldRouteName = "Invoke URL from other file";
        final String newName = "UPDATED NAME";

        final UaiRouteDTO toUpdateRoute = getRouteFromServer(oldRouteName);
        toUpdateRoute.getRequest().setName(newName);

        executePut(toUpdateRoute);

        final String updatedName = getRouteFromServer(newName).getRequest().getName();

        // rollback
        toUpdateRoute.getRequest().setName(oldRouteName);
        executePut(toUpdateRoute);

        assertEquals("making sure that both names are equals", newName, updatedName);
    }

    private UaiRouteDTO getRouteFromServer(final String routeName) {
        final String url = AbstractTestServletTests.GUI_URL + "uaiRoute";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        final String bodyAsString = response.readEntity(String.class);

        final IndexResponseDTO toUpdateIndex = new Gson().fromJson(bodyAsString, IndexResponseDTO.class);

        for (UaiRouteDTO uaiRouteDTO : toUpdateIndex.getRouteList()) {
            if (uaiRouteDTO.getRequest().getName().equals(routeName)) {
                return uaiRouteDTO;
            }
        }

        throw new IllegalArgumentException("route [" + routeName + "] not found");
    }
}
