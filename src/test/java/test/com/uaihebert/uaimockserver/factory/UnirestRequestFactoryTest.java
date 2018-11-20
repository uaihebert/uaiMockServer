package test.com.uaihebert.uaimockserver.factory;

import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.uaihebert.uaimockserver.constants.UaiHttpMethod;
import com.uaihebert.uaimockserver.factory.UnirestRequestFactory;
import com.uaihebert.uaimockserver.model.UaiCallback;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class UnirestRequestFactoryTest {
    public static final List<UaiHttpMethod> BODY_ALLOWED = Arrays.asList(
        UaiHttpMethod.POST,
        UaiHttpMethod.DELETE,
        UaiHttpMethod.PATCH,
        UaiHttpMethod.PUT
    );

    private static final List<UaiHttpMethod> NO_BODY_ALLOWED = Arrays.asList(UaiHttpMethod.GET, UaiHttpMethod.HEAD);

    @Test
    public void isCreatingForRequestWithoutBody() {
        for (final UaiHttpMethod method : NO_BODY_ALLOWED) {
            final UaiCallback callback = new UaiCallback();
            callback.setHttpMethod(method);

            final HttpRequest request = UnirestRequestFactory.create(callback);
            assertTrue(request instanceof GetRequest);
        }
    }

    @Test
    public void isCreatingForRequestWithBody() {
        for (final UaiHttpMethod method : BODY_ALLOWED) {
            final UaiCallback callback = new UaiCallback();
            callback.setHttpMethod(method);

            final HttpRequest request = UnirestRequestFactory.create(callback);
            assertTrue(request instanceof HttpRequestWithBody);
        }
    }
}
