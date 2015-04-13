package test.com.uaihebert.uaimockserver.validation.body;

import com.uaihebert.uaimockserver.validator.body.XmlUnitWrapper;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(PowerMockRunner.class)
@PrepareForTest(XMLUnit.class)
public class XmlUnitWrapperExceptionTest {

    @Before
    public void before() {
        PowerMockito.mockStatic(XMLUnit.class);
    }

    @Test
    public void isHandlingSAXException() throws NoSuchFieldException, IllegalAccessException, IOException, SAXException {
        PowerMockito.when(XMLUnit.compareXML(Mockito.anyString(), Mockito.anyString())).thenThrow(new SAXException());

        try {
            XmlUnitWrapper.isIdentical("<a></a>", "<a></a>");
            fail("An Exception should happen");
        } catch (IllegalArgumentException ex) {
            // this is the exception that should be thrown
            assertEquals(ex.getCause().getClass(), SAXException.class);
        }
    }

    @Test
    public void isHandlingIOException() throws NoSuchFieldException, IllegalAccessException, IOException, SAXException {
        PowerMockito.when(XMLUnit.compareXML(Mockito.anyString(), Mockito.anyString())).thenThrow(new IOException());

        try {
            XmlUnitWrapper.isIdentical("<a></a>", "<a></a>");
            fail("An Exception should happen");
        } catch (IllegalArgumentException ex) {
            // this is the exception that should be thrown
            assertEquals(ex.getCause().getClass(), IOException.class);
        }
    }
}