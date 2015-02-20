package test.com.uaihebert.uaimockserver.factory;

import com.uaihebert.uaimockserver.dto.factory.UaiRouteDTOFactory;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UaiRouteDTOFactoryTest {

    @Test
    public void isReturningEmptyListIfNoValueIsPresent() {
        final List<UaiRouteDTO> uaiRouteDTOList = UaiRouteDTOFactory.create(null);
        assertNotNull(uaiRouteDTOList);
        assertTrue(uaiRouteDTOList.isEmpty());
    }
}
