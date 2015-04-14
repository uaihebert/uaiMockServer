package test.com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(PowerMockRunner.class)
@PrepareForTest(IOUtils.class)
public class FileUtilByteWrapperTest {

    @Test
    public void isThrowingTheCorrectException() throws IOException {
        PowerMockito.mockStatic(IOUtils.class);
        PowerMockito.when(IOUtils.toByteArray(Mockito.any(FileInputStream.class))).thenThrow(new IOException());

        try {
            FileUtil.getFileAsByteBuffer("configWithoutLog.json");
            fail("An Exception should happen");
        } catch (IllegalArgumentException ex) {
            // this is the exception that should be thrown
            assertEquals(ex.getCause().getClass(), IOException.class);
        }
    }
}