package test.com.uaihebert.uaimockserver.validation;

import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.validator.BodyValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BodyValidatorTest {

    @Test
    public void isWorkingWhenConfigIsNull() {
        final UaiRequest uaiRequest = new UaiRequest.UaiRequestBuilder().build();
        final boolean isInvalid = new BodyValidator().isInvalid(uaiRequest, null);

        assertFalse("it should be valid", isInvalid);
    }

    @Test
    public void isWorkingWhenConfigIsFalse() {
        final UaiRequest uaiRequest = new UaiRequest.UaiRequestBuilder().isBodyRequired(false).build();
        final boolean isInvalid = new BodyValidator().isInvalid(uaiRequest, null);

        assertFalse("it should be valid", isInvalid);
    }
}