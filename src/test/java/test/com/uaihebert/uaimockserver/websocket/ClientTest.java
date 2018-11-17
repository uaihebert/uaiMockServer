package test.com.uaihebert.uaimockserver.websocket;


import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertNotSame;

@RunWith(UaiMockServerRunner.class)
public class ClientTest {
    private static final String NO_RESULT_RECEIVED = "";
    private static final String WS_URL = "ws://localhost:1234/uaiGui-ws";

    private final String[] log = {NO_RESULT_RECEIVED};

    private int giveUp;

    @Test
    public void isReceivingMessage() throws Exception {
        createWebSocketClient();

        executeRequestToGenerateLog();

        final int maxExpectedTries = 5;
        final int waitFor = 75;

        while (NO_RESULT_RECEIVED.equals(log[0]) && giveUp < maxExpectedTries) {
            Thread.sleep(waitFor);
            ++giveUp;
        }

        assertNotSame("must have an result", NO_RESULT_RECEIVED, log[0]);
    }

    private void executeRequestToGenerateLog() {
        final String url = "http://localhost:1234/uaiMockServer/";

        final Client client = ClientBuilder.newClient();
        client.target(url).request().get();
    }

    private ChatClientEndpoint createWebSocketClient() throws URISyntaxException, InterruptedException {
        final ChatClientEndpoint clientEndPoint = new ChatClientEndpoint(new URI(WS_URL));

        clientEndPoint.addMessageHandler(new ChatClientEndpoint.MessageHandler() {
            public void handleMessage(final String message) {
                log[0] = message;
            }
        });

        return clientEndPoint;
    }
}
