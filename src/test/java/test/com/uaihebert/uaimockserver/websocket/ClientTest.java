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

    private int giveUp = 0;

    @Test
    public void isReceivingMessage() throws Exception {
        createWebSocketClient();

        executeRequestToGenerateLog();

        while (NO_RESULT_RECEIVED.equals(log[0]) && giveUp < 5) {
            Thread.sleep(75);
            ++giveUp;
        }
        
        assertNotSame("must have an result", NO_RESULT_RECEIVED, log[0]);
    }

    private void executeRequestToGenerateLog() {
        final String url = "http://localhost:1234/uaiMockServer/";

        Client client = ClientBuilder.newClient();
        client.target(url).request().get();
    }

    private ChatClientEndpoint createWebSocketClient() throws URISyntaxException, InterruptedException {
        final ChatClientEndpoint clientEndPoint = new ChatClientEndpoint(new URI(WS_URL));

        clientEndPoint.addMessageHandler(new ChatClientEndpoint.MessageHandler() {
            public void handleMessage(String message) {
                log[0] = message;
            }
        });

        return clientEndPoint;
    }
}