package test.com.uaihebert.uaimockserver.model;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(UaiMockServerRunner.class)
public class ResponseBodyTest {

    @Test
    public void isExtractingJson() {
        final String url = "http://localhost:1234/uaiMockServer/giveMeABody";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        final String replace = response.readEntity(String.class);
        final ResponseTestWrapper wrapper = new Gson().fromJson(replace, ResponseTestWrapper.class);

        assertNotNull("it must have a bodyWrapper", wrapper);
        assertNotNull("it must have a body", wrapper.mockBody);
        assertFalse("it must have a title", StringUtils.isBlank(wrapper.mockBody.title));
    }

    private class ResponseTestWrapper {
        public final MockBody mockBody;

        public ResponseTestWrapper(final MockBody mockBody) {
            this.mockBody = mockBody;
        }
    }

    private class MockBody {
        public final String title;

        public MockBody(final String title) {
            this.title = title;
        }
    }
}