package test.com.uaihebert.uaimockserver.gui;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public abstract class AbstractTestServletTests {
    public static final String GUI_URL = "http://localhost:1234/uaiGui/";
    public static final String GUI_SERVLET_URL = GUI_URL + "uaiRoute";

    protected void executePut(final UaiRouteDTO toUpdateRoute) {
        final String url = GUI_SERVLET_URL;

        final Client client = ClientBuilder.newClient();
        final Entity<String> body = Entity.entity(new Gson().toJson(toUpdateRoute), MediaType.APPLICATION_JSON_TYPE);
        final Response response = client.target(url).request().put(body);

        // reading it to avoid exception
        response.readEntity(String.class);
    }

    protected void executePost(final UaiRouteDTO toUpdateRoute) {
        final String url = GUI_SERVLET_URL;

        final Client client = ClientBuilder.newClient();
        final Entity<String> body = Entity.entity(new Gson().toJson(toUpdateRoute), MediaType.APPLICATION_JSON_TYPE);
        final Response response = client.target(url).request().post(body);

        // reading it to avoid exception
        response.readEntity(String.class);
    }
}
