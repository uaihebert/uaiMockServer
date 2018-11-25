package com.uaihebert.uaimockserver.servlet;

import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.dto.model.UaiFileDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRootConfigurationDTO;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.service.UaiRootContextService;
import com.uaihebert.uaimockserver.util.JsonUtil;
import com.uaihebert.uaimockserver.util.RequestBodyExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UaiRootConfigurationsServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        super.addDefaultHeaders(response);

        final String body = getRouteConfigurationBody();

        writeInResponse(response, body);
    }

    @Override
    protected void doPut(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final UaiRootConfigurationDTO uaiRouteConfigDTO = RequestBodyExtractor.extract(
            request,
            UaiRootConfigurationDTO.class
        );

        UaiRootContextService.update(uaiRouteConfigDTO);

        send204Response(response);
    }

    private String getRouteConfigurationBody() {
        final UaiRootConfigurationDTO configurationsDTO = new UaiRootConfigurationDTO();

        final UaiMockServerConfig mainConfig = UaiMockServerContext.getInstance().getUaiMockServerConfig();
        configurationsDTO.setHost(mainConfig.getHost());
        configurationsDTO.setPort(mainConfig.getPort());
        configurationsDTO.setContext(mainConfig.getContext());
        configurationsDTO.setFileLog(mainConfig.isFileLog());
        configurationsDTO.setConsoleLog(mainConfig.isConsoleLog());
        final UaiFileDTO file = new UaiFileDTO(
            mainConfig.getUaiFile().getName(),
            mainConfig.getUaiFile().getFullPath()
        );
        configurationsDTO.setUaiMainFile(file);
        configurationsDTO.setDefaultContentType(mainConfig.getDefaultContentTypeResponse());

        return JsonUtil.toJson(configurationsDTO);
    }
}
