package test.com.uaihebert.uaimockserver.websocket;

import com.uaihebert.uaimockserver.context.UaiWebSocketContext;
import io.undertow.websockets.core.protocol.version07.WebSocket07Channel;
import org.junit.Test;
import org.mockito.Mockito;

public class UaiWebSocketContextTest {

    @Test
    public void isRemovingWithoutError() {
        final WebSocket07Channel closed = Mockito.mock(WebSocket07Channel.class);
        UaiWebSocketContext.addClient(closed);

        final WebSocket07Channel opened = Mockito.mock(WebSocket07Channel.class);
        Mockito.when(opened.isOpen()).thenReturn(true);

        UaiWebSocketContext.addClient(opened);

        UaiWebSocketContext.removeClosed();

        UaiWebSocketContext.removeClosed();

        UaiWebSocketContext.removeAllClients();
    }
}