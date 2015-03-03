package test.com.uaihebert.uaimockserver.model;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
public class ResponseBodyTest {

    @Test
    public void isExtractingJson() {
        final String url = "http://localhost:1234/uaiMockServer/giveMeABody";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();


        // todo refactor here
        assertEquals(200, response.getStatus());
        final String replace = response.readEntity(String.class);
        System.out.println(replace);
        final ResponseTestWrapper mockBody = new Gson().fromJson(replace, ResponseTestWrapper.class);
        System.out.println(mockBody.getMockBody().getTitle());
    }

    private class ResponseTestWrapper {
        private MockBody mockBody;

        public MockBody getMockBody() {
            return mockBody;
        }
    }

    private class MockBody {
        private String title;

        public String getTitle() {
            return title;
        }
    }
}