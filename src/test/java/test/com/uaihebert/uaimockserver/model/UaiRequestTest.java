package test.com.uaihebert.uaimockserver.model;

import com.uaihebert.uaimockserver.model.BodyValidationType;
import com.uaihebert.uaimockserver.model.UaiRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UaiRequestTest {

    @Test
    public void isSettingDefaultBodyValidationStrategy() {
        final UaiRequest uaiRequest = new UaiRequest.UaiRequestBuilder().isBodyRequired(true).build();
        assertEquals(BodyValidationType.VALIDATE_IF_PRESENT_ONLY, uaiRequest.getBodyValidationType());
    }
}
