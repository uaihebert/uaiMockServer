package com.uaihebert.uaimockserver.servlet;

import com.uaihebert.uaimockserver.context.UaiMockServerContext;
import com.uaihebert.uaimockserver.dto.model.UaiFileDTO;
import com.uaihebert.uaimockserver.dto.model.UaiRootConfigurationDTO;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.service.UaiRootContextService;
import com.uaihebert.uaimockserver.util.JsonUtil;
import com.uaihebert.uaimockserver.util.RequestBodyExtractor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UaiRootConfigurationsServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("application/json");

        super.addDefaultHeaders(httpResponse);

        final String body = getRouteConfigurationBody();
        
        writeInResponse(httpResponse, body);
    }

    @Override
    protected void doPut(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        final UaiRootConfigurationDTO uaiRouteConfigDTO = RequestBodyExtractor.extract(httpRequest, UaiRootConfigurationDTO.class);

        UaiRootContextService.update(uaiRouteConfigDTO);

        send204Response(httpResponse);
    }

    public String getRouteConfigurationBody() {
        final UaiRootConfigurationDTO configurationsDTO = new UaiRootConfigurationDTO();

        final UaiMockServerConfig mainConfig = UaiMockServerContext.INSTANCE.uaiMockServerConfig;
        configurationsDTO.setHost(mainConfig.getHost());
        configurationsDTO.setPort(mainConfig.getPort());
        configurationsDTO.setContext(mainConfig.getContext());
        configurationsDTO.setFileLog(mainConfig.isFileLog());
        configurationsDTO.setConsoleLog(mainConfig.isConsoleLog());
        configurationsDTO.setUaiMainFile(new UaiFileDTO(mainConfig.getUaiFile().getName(), mainConfig.getUaiFile().getFullPath()));
        configurationsDTO.setDefaultContentType(mainConfig.getDefaultContentTypeResponse());

        return JsonUtil.toJson(configurationsDTO);
    }
}