package test.com.uaihebert.uaimockserver.factory;

import com.uaihebert.uaimockserver.util.FileUtil;
import org.junit.Test;

import java.io.File;

public class FileUtilTest {

    @Test(expected = IllegalArgumentException.class)
    public void isThrowingExceptionWhenFileIsNotFound() {
        FileUtil.findFile("INVALID");
    }

    @Test(expected = IllegalArgumentException.class)
    public void isThrowingExceptionWhenFileContentIsNotFound() {
        FileUtil.getFileContent(new File("INVALID"));
    }
}
