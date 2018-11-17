package test.com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.util.RequestBodyExtractor;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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
        final ByteArrayInputStream byteStream = new ByteArrayInputStream("{}".getBytes(StandardCharsets.UTF_8));
        final MockServletInputStream stream = new MockServletInputStream(byteStream);
        Mockito.when(httpMock.getInputStream()).thenReturn(stream);

        RequestBodyExtractor.extract(httpMock, UaiRouteDTO.class);
    }
}
