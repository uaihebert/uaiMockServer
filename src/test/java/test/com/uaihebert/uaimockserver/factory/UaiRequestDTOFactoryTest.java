package test.com.uaihebert.uaimockserver.factory;

import com.uaihebert.uaimockserver.dto.factory.UaiRequestDTOFactory;
import com.uaihebert.uaimockserver.dto.model.UaiRequestDTO;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.model.UaiRequest;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertNull;

public class UaiRequestDTOFactoryTest {

    @Test
    public void isSettingRequestInMilliToNull() {
        final UaiRequest uaiRequest = new UaiRequest(null, null, null, 0, false, Collections.<UaiHeader>emptyList(), Collections.<UaiQueryParam>emptyList());
        final UaiRequestDTO uaiRequestDTO = UaiRequestDTOFactory.create(uaiRequest);
        assertNull("the request in milli should be null and not 0", uaiRequestDTO.getHoldRequestInMilli());
    }
}
