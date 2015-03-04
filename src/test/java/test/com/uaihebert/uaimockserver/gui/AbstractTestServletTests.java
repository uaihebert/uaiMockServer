package test.com.uaihebert.uaimockserver.gui;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class AbstractTestServletTests {
    protected void executePut(final UaiRouteDTO toUpdateRoute) {
        final String url = "http://localhost:1234/uai-mock-server-gui/uaiRoute";

        Client client = ClientBuilder.newClient();
        client.target(url).request().put(Entity.entity(new Gson().toJson(toUpdateRoute), MediaType.APPLICATION_JSON_TYPE));
    }

    protected void executePost(final UaiRouteDTO toUpdateRoute) {
        final String url = "http://localhost:1234/uai-mock-server-gui/uaiRoute";

        Client client = ClientBuilder.newClient();
        client.target(url).request().post(Entity.entity(new Gson().toJson(toUpdateRoute), MediaType.APPLICATION_JSON_TYPE));
    }
}
