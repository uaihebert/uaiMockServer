package test.com.uaihebert.uaimockserver.validation;

import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.validator.BodyValidator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BodyValidatorTest {
    private final BodyValidator bodyValidator = new BodyValidator();


    @Test
    public void isWorkingWhenConfigIsNull() {
        final UaiRequest uaiRequest = new UaiRequest.UaiRequestBuilder().build();
        final RequestValidatorFacade.RequestAnalysisResult result = new RequestValidatorFacade.RequestAnalysisResult();

        bodyValidator.validate(uaiRequest, null, result);

        assertTrue("it should be valid", result.isValid());
    }

    @Test
    public void isWorkingWhenConfigIsFalse() {
        final UaiRequest uaiRequest = new UaiRequest.UaiRequestBuilder().isBodyRequired(false).build();
        final RequestValidatorFacade.RequestAnalysisResult result = new RequestValidatorFacade.RequestAnalysisResult();

        bodyValidator.validate(uaiRequest, null, result);

        assertTrue("it should be valid", result.isValid());
    }
}