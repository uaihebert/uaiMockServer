package test.com.uaihebert.uaimockserver.gui;

import com.uaihebert.uaimockserver.dto.model.*;
import com.uaihebert.uaimockserver.dto.response.IndexResponseDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class UaiRouteDTOTestValidator {
    private UaiRouteDTOTestValidator() {
    }

    public static void hasAllAttributesPopulated(final IndexResponseDTO uaiRouteDTOList) {
        assertNotNull("must have a indexResponseDTO", uaiRouteDTOList);
        assertNotNull("must have a routList", uaiRouteDTOList.getRouteList());

        for (UaiRouteDTO uaiRouteDTO : uaiRouteDTOList.getRouteList()) {
            validate(uaiRouteDTO);
        }
    }

    private static void validate(final UaiRouteDTO uaiRouteDTO) {
        validateRequest(uaiRouteDTO.getRequest());
        validateResponse(uaiRouteDTO.getResponse());
    }

    private static void validateRequest(final UaiRequestDTO request) {
        assertTrue(StringUtils.isNotEmpty(request.getPath()));
        assertTrue(StringUtils.isNotEmpty(request.getMethod()));
        assertTrue(StringUtils.isNotEmpty(request.getRequiredContentType()));
        assertTrue(request.getHoldRequestInMilli() > 0);
        assertTrue(request.isBodyRequired());
        validateHeader(request.getRequiredHeaderList());
        validateQueryParam(request.getRequiredQueryParamList());
        assertFalse(request.getRequiredQueryParamList().isEmpty());
    }

    private static void validateHeader(final List<UaiHeaderDTO> requiredHeaderList) {
        assertNotNull("must have a requiredHeaderList", requiredHeaderList);

        for (UaiHeaderDTO uaiHeaderDTO : requiredHeaderList) {
            assertTrue(StringUtils.isNotEmpty(uaiHeaderDTO.getName()));
            assertNotNull("must have a valueList", uaiHeaderDTO.getValueList());
            assertFalse(uaiHeaderDTO.getValueList().isEmpty());
        }
    }

    private static void validateQueryParam(final List<UaiQueryParamDTO> requiredQueryParamList) {
        assertNotNull("must have a requiredQueryParamList", requiredQueryParamList);

        for (UaiQueryParamDTO uaiQueryParamDTO : requiredQueryParamList) {
            assertTrue(StringUtils.isNotEmpty(uaiQueryParamDTO.getName()));
            assertNotNull("must have a valueList", uaiQueryParamDTO.getValueList());
            assertFalse(uaiQueryParamDTO.getValueList().isEmpty());
        }
    }

    private static void validateResponse(final UaiResponseDTO response) {
        assertTrue(StringUtils.isNotEmpty(response.getBody()));
        assertTrue(StringUtils.isNotEmpty(response.getContentType()));
        assertTrue(response.getStatusCode() > 0);
        assertFalse(response.getHeaderList().isEmpty());
    }
}