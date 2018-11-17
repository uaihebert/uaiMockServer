package test.com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.model.HttpStatusCode;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "optionalQueryParamTest.json")
public class OptionalQueryParamTest {

    @Test
    public void testIsReturningCorrect() {
        final String noQueryParamUrl = "http://localhost:1234/uaiMockServer/optionalQueryParamTest";
        final Client noQueryParamClient = ClientBuilder.newClient();

        final Response noQueryParamResponse = noQueryParamClient
                .target(noQueryParamUrl)
                .request()
                .get();

        assertEquals(HttpStatusCode.CREATED.code, noQueryParamResponse.getStatus());

        final String url = "http://localhost:1234/uaiMockServer/optionalQueryParamTest?OPTIONAL_01=01&OPTIONAL_02=02";

        final Client client = ClientBuilder.newClient();
        final Response response = client
                .target(url)
                .request()
                .get();

        assertEquals(HttpStatusCode.NO_CONTENT.code, response.getStatus());
    }

    @Test
    public void isThrowingExceptionWhenOptionalQueryParamValueIsNotPresent() {
        final String url = "http://localhost:1234/uaiMockServer/optionalQueryParamTest?OPTIONAL_01=01&OPTIONAL_02=INVALID";

        final Client client = ClientBuilder.newClient();
        final Response response = client
                .target(url)
                .request()
                .get();

        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getStatus());
    }

    @Test
    public void isWorkingWithWildCard() {
        final String url = "http://localhost:1234/uaiMockServer/optionalQueryParamTest?OPTIONAL_03=ANY_VALUE";

        final Client client = ClientBuilder.newClient();
        final Response response = client
                .target(url)
                .request()
                .get();

        assertEquals(HttpStatusCode.ACCEPTED.code, response.getStatus());
    }
}
