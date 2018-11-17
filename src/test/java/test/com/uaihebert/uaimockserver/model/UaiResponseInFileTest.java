package test.com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.model.HttpStatusCode;
import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import com.uaihebert.uaimockserver.util.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.imageio.ImageIO;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import java.awt.image.BufferedImage;
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
        final String projectDir =
            UaiResponseInFileTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        final String url = "http://localhost:1234/uaiMockServer/bodyInResponseJpg";

        final Client client = ClientBuilder.newClient();
        final Response response = client.register(new ImageIconMessageBodyReader()).target(url).request().get();

        assertEquals("asserting success in the response", HttpStatusCode.OK.code, response.getStatus());
        assertEquals(
            "asserting that response the content-type is correct",
            "image/jpg",
            response.getHeaderString("Content-Type")
        );

        final InputStream receivedImage = response.readEntity(InputStream.class);

        final BufferedImage read1 = ImageIO.read(receivedImage);

        final InputStream desiredImage = getDesiredFile("sampleImage.jpg");

        final BufferedImage read2 = ImageIO.read(desiredImage);

        final String assertMessage = String.format("the image must be the same.", desiredImage, receivedImage);
        assertTrue(assertMessage, imagesAreEqual(read1, read2));
    }

    private boolean imagesAreEqual(final BufferedImage image1, final BufferedImage image2) {
        if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
            return false;
        }
        for (int x = 1; x < image2.getWidth(); x++) {
            for (int y = 1; y < image2.getHeight(); y++) {
                if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }


    @Test
    public void isReturningJson() {
        final String url = "http://localhost:1234/uaiMockServer/bodyInResponseJson";

        final Client client = ClientBuilder.newClient();
        final Response response = client.register(new ImageIconMessageBodyReader()).target(url).request().get();

        assertEquals("asserting success in the response", HttpStatusCode.OK.code, response.getStatus());
        assertEquals(
            "asserting that response the content-type is correct",
            "application/json",
            response.getHeaderString("Content-Type")
        );

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
        public boolean isReadable(final Class aClass,
                                  final Type type,
                                  final Annotation[] annotations,
                                  final MediaType mediaType) {
            return mediaType.getType().equals("image");
        }

        @Override
        public Object readFrom(final Class aClass,
                               final Type type,
                               final Annotation[] annotations,
                               final MediaType mediaType,
                               final MultivaluedMap multivaluedMap,
                               final InputStream inputStream) throws WebApplicationException {
            return inputStream;
        }
    }
}
