package test.com.uaihebert.uaimockserver.gui;

import com.uaihebert.uaimockserver.dto.model.UaiFileDTO;
import com.uaihebert.uaimockserver.dto.model.UaiHeaderDTO;
import com.uaihebert.uaimockserver.dto.model.UaiQueryParamDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRequestDTO;
import com.uaihebert.uaimockserver.dto.model.UaiResponseDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRootConfigurationDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.dto.response.IndexResponseDTO;
import com.uaihebert.uaimockserver.util.StringUtils;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class UaiRouteDTOTestValidator {
    private UaiRouteDTOTestValidator() {
    }

    public static void hasAllAttributesPopulated(final IndexResponseDTO uaiRouteDTOList) {
        validateIndex(uaiRouteDTOList);

        for (UaiRouteDTO uaiRouteDTO : uaiRouteDTOList.getRouteList()) {
            validate(uaiRouteDTO);
        }
    }

    private static void validateIndex(final IndexResponseDTO uaiRouteDTOList) {
        assertNotNull("must have a indexResponseDTO", uaiRouteDTOList);
        assertNotNull("must have a routList", uaiRouteDTOList.getRouteList());
        assertNotNull("must have a routList", uaiRouteDTOList.getHttpMethodArray());

        assertTrue("Must have httpMethods", uaiRouteDTOList.getHttpMethodArray().length > 0);
        assertFalse("Must have other project", uaiRouteDTOList.getProjectList().isEmpty());
        assertTrue("Must have default project", StringUtils.isNotBlank(uaiRouteDTOList.getDefaultProject()));

        assertFalse("Must have body validation type", uaiRouteDTOList.getBodyValidationTypeList().isEmpty());

        validateBasicConfiguration(uaiRouteDTOList);
    }

    private static void validateBasicConfiguration(final IndexResponseDTO uaiRouteDTOList) {
        final UaiRootConfigurationDTO basicConfiguration = uaiRouteDTOList.getRootConfiguration();
        assertNotNull("must have basicConfiguration", basicConfiguration);

        assertTrue(basicConfiguration.getPort() >= 0);

        assertTrue(StringUtils.isNotBlank(basicConfiguration.getContext()));
        assertTrue(StringUtils.isNotBlank(basicConfiguration.getDefaultContentType()));
        assertTrue(StringUtils.isNotBlank(basicConfiguration.getHost()));

        final UaiFileDTO uaiMainFile = basicConfiguration.getUaiMainFile();
        assertNotNull(uaiMainFile);
        assertTrue(StringUtils.isNotBlank(uaiMainFile.getName()));
        assertTrue(StringUtils.isNotBlank(uaiMainFile.getFullPath()));
    }

    private static void validate(final UaiRouteDTO uaiRouteDTO) {
        assertTrue(StringUtils.isNotBlank(uaiRouteDTO.getId()));
        assertTrue(StringUtils.isNotBlank(uaiRouteDTO.getProject()));
        validateUaiFile(uaiRouteDTO.getUaiFile());
        validateRequest(uaiRouteDTO.getRequest());
        validateResponse(uaiRouteDTO.getResponse());
    }

    private static void validateUaiFile(final UaiFileDTO uaiFile) {
        assertNotNull("must have a uaiFile", uaiFile);
        assertTrue(StringUtils.isNotBlank(uaiFile.getName()));
        assertTrue(StringUtils.isNotBlank(uaiFile.getFullPath()));
    }

    private static void validateRequest(final UaiRequestDTO request) {
        assertTrue(StringUtils.isNotBlank(request.getName()));
        assertTrue(StringUtils.isNotBlank(request.getPath()));
        assertTrue(StringUtils.isNotBlank(request.getMethod()));
        assertTrue(StringUtils.isNotBlank(request.getDescription()));
        assertTrue(StringUtils.isNotBlank(request.getRequiredContentType()));
        assertTrue(request.getHoldRequestInMilli() > 0);
        assertTrue(request.isBodyRequired());
        validateHeader(request.getRequiredHeaderList());
        validateHeader(request.getOptionalHeaderList());
        validateQueryParam(request.getRequiredQueryParamList());
        validateQueryParam(request.getOptionalQueryParamList());
        assertFalse(request.getRequiredQueryParamList().isEmpty());
    }

    private static void validateHeader(final List<UaiHeaderDTO> requiredHeaderList) {
        assertNotNull("must have a requiredHeaderList", requiredHeaderList);

        for (UaiHeaderDTO uaiHeaderDTO : requiredHeaderList) {
            assertTrue(StringUtils.isNotBlank(uaiHeaderDTO.getName()));
            assertNotNull("must have a valueList", uaiHeaderDTO.getValueList());
            assertFalse(uaiHeaderDTO.getValueList().isEmpty());
        }
    }

    private static void validateQueryParam(final List<UaiQueryParamDTO> requiredQueryParamList) {
        assertNotNull("must have a requiredQueryParamList", requiredQueryParamList);

        for (UaiQueryParamDTO uaiQueryParamDTO : requiredQueryParamList) {
            assertTrue(StringUtils.isNotBlank(uaiQueryParamDTO.getName()));
            assertNotNull("must have a valueList", uaiQueryParamDTO.getValueList());
            assertFalse(uaiQueryParamDTO.getValueList().isEmpty());
        }
    }

    private static void validateResponse(final UaiResponseDTO response) {
        assertTrue(StringUtils.isNotBlank(response.getBody()));
        assertTrue(StringUtils.isNotBlank(response.getContentType()));
        assertTrue(response.getStatusCode() > 0);
        assertFalse(response.getHeaderList().isEmpty());
    }
}
