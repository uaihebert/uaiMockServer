package com.uaihebert.uaimockserver.servlet;

import com.google.gson.Gson;
import com.uaihebert.uaimockserver.configuration.ProjectConfiguration;
import com.uaihebert.uaimockserver.dto.factory.UaiRouteDTOFactory;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.dto.response.IndexResponseDTO;
import com.uaihebert.uaimockserver.helper.UaiRouteHelper;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.RequestBodyExtractor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class UaiRouteServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("application/json");

        super.addDefaultHeaders(httpResponse);

        final String body = createIndexGetResponse();

        final OutputStream outputStream = httpResponse.getOutputStream();

        try {
            outputStream.write(body.getBytes(ProjectConfiguration.ENCODING.value));

            outputStream.flush();
        } finally {
            outputStream.close();
        }
    }

    private String createIndexGetResponse() {
        final List<UaiRoute> uaiRouteList = UaiMockServerConfig.listAllRoutes();
        final IndexResponseDTO indexResponseDTO = new IndexResponseDTO();
        indexResponseDTO.setRouteList(UaiRouteDTOFactory.create(uaiRouteList));

        return new Gson().toJson(indexResponseDTO);
    }

    @Override
    protected void doPut(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("application/json");
        httpResponse.setStatus(204);

        final UaiRouteDTO uaiRouteDTO = RequestBodyExtractor.extract(httpRequest, UaiRouteDTO.class);

        UaiRouteHelper.editRoute(uaiRouteDTO);

        final OutputStream outputStream = httpResponse.getOutputStream();
        outputStream.close();
    }

    @Override
    protected void doDelete(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("application/json");
        httpResponse.setStatus(204);

        final String routeId = httpRequest.getParameter("routeId");

        UaiMockServerConfig.deleteRoute(routeId);

        final OutputStream outputStream = httpResponse.getOutputStream();
        outputStream.close();
    }
}