package test.com.uaihebert.uaimockserver.factory;

import com.uaihebert.uaimockserver.util.FileUtil;
import org.junit.Test;

public class FileUtilTest {

    @Test(expected = IllegalArgumentException.class)
    public void isThrowingExceptionWhenFileIsNotFound() {
        FileUtil.findFile("INVALID");
    }
}
