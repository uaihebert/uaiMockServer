package test.com.uaihebert.uaimockserver.factory;

import com.uaihebert.uaimockserver.factory.TypeSafeConfigFactory;
import org.junit.Test;

public class TypeSafeConfigFactoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void isThrowingExceptionWhenFileIsNotFound() {
        TypeSafeConfigFactory.loadConfiguration("INVALID");
    }
}
