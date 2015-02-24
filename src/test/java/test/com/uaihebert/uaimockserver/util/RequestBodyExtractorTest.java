package test.com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.util.RequestBodyExtractor;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RequestBodyExtractorTest {

    @Test(expected = IllegalArgumentException.class)
    public void isAvoidingProblemWithBufferedReader() throws IOException {
        final HttpServletRequest httpMock = Mockito.mock(HttpServletRequest.class);
        Mockito.when(httpMock.getInputStream()).thenThrow(IllegalArgumentException.class);

        RequestBodyExtractor.extract(httpMock, RequestBodyExtractorTest.class);
    }

    @Test
    public void isClosingBufferedReader() throws IOException {
        final HttpServletRequest httpMock = Mockito.mock(HttpServletRequest.class);
        Mockito.when(httpMock.getInputStream()).thenReturn(new MockServletInputStream(new ByteArrayInputStream("{}".getBytes(StandardCharsets.UTF_8))));

        RequestBodyExtractor.extract(httpMock, UaiRouteDTO.class);
    }
}

class MockServletInputStream extends ServletInputStream {
    private final InputStream sourceStream;


    /**
     * Create a DelegatingServletInputStream for the given source stream.
     * @param sourceStream the source stream (never <code>null</code>)
     */
    public MockServletInputStream(InputStream sourceStream) {
        this.sourceStream = sourceStream;
    }

    /**
     * Return the underlying source stream (never <code>null</code>).
     */
    public final InputStream getSourceStream() {
        return this.sourceStream;
    }


    public int read() throws IOException {
        return this.sourceStream.read();
    }

    public void close() throws IOException {
        super.close();
        this.sourceStream.close();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(final ReadListener readListener) {
    }
}