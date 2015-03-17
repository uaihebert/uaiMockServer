package test.com.uaihebert.uaimockserver.gui;

import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.factory.UaiRouteFactory;
import com.uaihebert.uaimockserver.model.UaiFile;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.model.UaiResponse;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "routePostTest.json")
public class UaiRouteServletClonePostTest {
    @Test
    public void isCloning() {
        final String requestName = UUID.randomUUID().toString();
        final String clonedName = UaiRouteFactory.CLONE_PREFIX + requestName;

        final UaiRequest request = new UaiRequest(requestName, null, null, null, null, null, null, null, null);

        final UaiRoute uaiRoute = new UaiRoute(new UaiFile("any", "any"), request, new UaiResponse());
        uaiRoute.createId();

        UaiRouteRepository.create(uaiRoute);


        final String url = AbstractTestServletTests.GUI_URL + "uaiRoute/clone?routeId=" + uaiRoute.getId();

        final Client client = ClientBuilder.newClient();
        final Response post = client.target(url).request().post(Entity.entity("", MediaType.APPLICATION_JSON_TYPE));

        assertEquals(204, post.getStatus());

        final List<UaiRoute> routeList = UaiMockServerContext.getInstance().uaiMockServerConfig.getRouteList();
        for (UaiRoute route : routeList) {
            final UaiRequest uaiRequest = route.getRequest();
            if (clonedName.equals(uaiRequest.getName())) {
                return;
            }
        }

        fail("it should have find the the cloned route by the name of: " + clonedName);
    }
}