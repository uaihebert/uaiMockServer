package test.com.uaihebert.uaimockserver.websocket;

import com.uaihebert.uaimockserver.model.UaiWebSocketListener;
import io.undertow.websockets.core.StreamSourceFrameChannel;
import io.undertow.websockets.core.protocol.version07.WebSocket07Channel;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class UaiWebSocketListenerTest extends UaiWebSocketListener {

    @Test
    public void isClosingWithoutError() throws IOException {
        final UaiWebSocketListenerTest uaiWebSocketListenerTest = new UaiWebSocketListenerTest();

        uaiWebSocketListenerTest.onClose(Mockito.mock(WebSocket07Channel.class), Mockito.mock(StreamSourceFrameChannel.class));
    }
}