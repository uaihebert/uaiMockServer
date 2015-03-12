package test.com.uaihebert.uaimockserver.websocket;

import com.uaihebert.uaimockserver.context.UaiWebSocketContext;
import io.undertow.websockets.core.protocol.version07.WebSocket07Channel;
import org.junit.Test;
import org.mockito.Mockito;

public class UaiWebSocketContextTest {

    @Test
    public void isRemovingWithoutError() {
        final WebSocket07Channel closed1 = Mockito.mock(WebSocket07Channel.class);
        UaiWebSocketContext.addClient(closed1);

        final WebSocket07Channel closed2 = Mockito.mock(WebSocket07Channel.class);
        UaiWebSocketContext.addClient(closed2);

        final WebSocket07Channel opened2 = Mockito.mock(WebSocket07Channel.class);
        Mockito.when(opened2.isOpen()).thenReturn(true);

        final WebSocket07Channel opened1 = Mockito.mock(WebSocket07Channel.class);
        Mockito.when(opened1.isOpen()).thenReturn(true);

        UaiWebSocketContext.addClient(opened1);
        UaiWebSocketContext.addClient(opened2);

        UaiWebSocketContext.removeClosed();

        UaiWebSocketContext.removeClosed();

        UaiWebSocketContext.removeAllClients();
    }
}