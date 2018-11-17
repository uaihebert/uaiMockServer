package test.com.uaihebert.uaimockserver.validation.body;

import com.uaihebert.uaimockserver.validator.body.XmlUnitWrapper;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class XmlUnitWrapperTest {

    @Test
    public void testWithLines() throws IOException, SAXException {
        final String xml1 = "<person>"
            + "    <id>1</id>"
            + "</person>";
        final String xml2 = "<person><id>1</id></person>";

        assertTrue("it should be identical", XmlUnitWrapper.isIdentical(xml1, xml2));
    }

    @Test
    public void testWithAttributesOutOfOrder() throws IOException, SAXException {
        final String xml1 = ""
            + "<person>"
            + "    <age>33</age>"
            + "    <id>1</id>"
            + "</person>";
        final String xml2 = ""
            + "<person>"
            + "    <id>1</id>"
            + "    <age>33</age>"
            + "</person>";

        assertTrue("it should be identical", XmlUnitWrapper.isSimilar(xml1, xml2));
    }
}
