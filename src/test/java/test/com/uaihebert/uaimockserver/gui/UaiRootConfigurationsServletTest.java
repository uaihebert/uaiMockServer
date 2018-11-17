package test.com.uaihebert.uaimockserver.gui;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.dto.model.UaiRootConfigurationDTO;
import com.uaihebert.uaimockserver.model.HttpStatusCode;
import com.uaihebert.uaimockserver.util.JsonUtil;
import com.uaihebert.uaimockserver.util.StringUtils;
import org.junit.Test;
import test.com.uaihebert.uaimockserver.TestAbstract;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class UaiRootConfigurationsServletTest extends TestAbstract {

    @Test
    public void isReturning200OnIndex() {
        final String url = AbstractTestServletTests.GUI_URL +  "rootConfigurations";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        // must read the entity or an NIOException will raise
        response.readEntity(String.class);

        assertEquals(HttpStatusCode.OK.code, response.getStatus());
    }

    @Test
    public void isReturningContentAsJsonType() {
        final String url = AbstractTestServletTests.GUI_URL +  "rootConfigurations";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());
    }

    @Test
    public void isReturningContent() {
        final String url = AbstractTestServletTests.GUI_URL +  "rootConfigurations";

        final Client client = ClientBuilder.newClient();
        final Response response = client.target(url).request().get();

        final String bodyAsString = response.readEntity(String.class);

        final UaiRootConfigurationDTO configurationsDTO = new Gson()
            .fromJson(bodyAsString, UaiRootConfigurationDTO.class);

        assertNotNull(configurationsDTO);
        assertNotNull(configurationsDTO.getPort());
        assertNotNull(configurationsDTO.getConsoleLog());
        assertNotNull(configurationsDTO.getUaiMainFile());
        assertFalse(StringUtils.isBlank(configurationsDTO.getHost()));
        assertFalse(StringUtils.isBlank(configurationsDTO.getContext()));
        assertFalse(StringUtils.isBlank(configurationsDTO.getContext()));
    }

    @Test
    public void isWritingWithoutError() {
        final String url = AbstractTestServletTests.GUI_URL +  "rootConfigurations";

        final Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        final String bodyAsString = response.readEntity(String.class);

        final UaiRootConfigurationDTO configurationsDTO = new Gson()
            .fromJson(bodyAsString, UaiRootConfigurationDTO.class);

        final Entity<String> body = Entity.entity(JsonUtil.toJson(configurationsDTO), MediaType.APPLICATION_JSON_TYPE);
        response = client.target(url).request().put(body);

        assertEquals("the return should be 204", HttpStatusCode.NO_CONTENT.code, response.getStatus());
    }
}
