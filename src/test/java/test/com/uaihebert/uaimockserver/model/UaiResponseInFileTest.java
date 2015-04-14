package test.com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import com.uaihebert.uaimockserver.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "bodyPathTest.json")
public class UaiResponseInFileTest {

    @Test
    public void isReturningImage() throws IOException {
        final String url = "http://localhost:1234/uaiMockServer/bodyInResponseJpg";

        final Client client = ClientBuilder.newClient();
        final Response response = client.register(new ImageIconMessageBodyReader()).target(url).request().get();

        assertEquals("asserting success in the response", 200, response.getStatus());
        assertEquals("asserting that response the content-type is correct", "image/jpg", response.getHeaderString("Content-Type"));

        final InputStream receivedImage = response.readEntity(InputStream.class);

        final InputStream desiredImage = getDesiredFile("sampleImage.jpg");

        final boolean isSame = IOUtils.contentEquals(desiredImage, receivedImage);
        assertTrue(String.format("the image must be the same. desired [%s] received [%s]", desiredImage, receivedImage), isSame);
    }


    @Test
    public void isReturningJson() throws IOException {
        final String url = "http://localhost:1234/uaiMockServer/bodyInResponseJson";

        final Client client = ClientBuilder.newClient();
        final Response response = client.register(new ImageIconMessageBodyReader()).target(url).request().get();

        assertEquals("asserting success in the response", 200, response.getStatus());
        assertEquals("asserting that response the content-type is correct", "application/json", response.getHeaderString("Content-Type"));

        final String received = response.readEntity(String.class);

        final String desired = FileUtil.getFileContent("bodyPathTest.json");

        assertEquals("it should be the same file", desired, received);
    }

    private InputStream getDesiredFile(final String fileName) throws IOException {
        final File file = FileUtil.findFile(fileName);
        return new FileInputStream(file);
    }

    private class ImageIconMessageBodyReader implements MessageBodyReader {

        @Override
        public boolean isReadable(Class aClass, Type type, Annotation[] annotations, MediaType mediaType) {
            return mediaType.getType().equals("image");
        }

        @Override
        public Object readFrom(Class aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
            return inputStream;
        }
    }
}
