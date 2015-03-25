package com.uaihebert.uaimockserver.servlet;

import com.uaihebert.uaimockserver.dto.factory.UaiBasicConfigurationDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiRouteDTOFactory;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.dto.response.IndexResponseDTO;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.repository.UaiRouteMapper;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;
import com.uaihebert.uaimockserver.service.UaiRouteService;
import com.uaihebert.uaimockserver.util.JsonUtil;
import com.uaihebert.uaimockserver.util.RequestBodyExtractor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UaiRouteServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        final String selectedProject = httpRequest.getParameter("selectedProject");

        super.addDefaultHeaders(httpResponse);

        final String body = createIndexGetResponse(selectedProject);

        writeInResponse(httpResponse, body);
    }

    private String createIndexGetResponse(final String selectedProject) {
        final List<UaiRoute> uaiRouteList = UaiRouteRepository.listAllRoutes(selectedProject);

        final IndexResponseDTO indexResponseDTO = new IndexResponseDTO();

        indexResponseDTO.setRouteList(UaiRouteDTOFactory.create(uaiRouteList));
        indexResponseDTO.setDefaultProject(UaiRouteMapper.ALL_PROJECT);
        indexResponseDTO.setProjectList(UaiRouteMapper.extractProjectFromRoutes());
        indexResponseDTO.setRootConfiguration(UaiBasicConfigurationDTOFactory.create());

        return JsonUtil.toJson(indexResponseDTO);
    }

    @Override
    protected void doPut(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        final UaiRouteDTO uaiRouteDTO = RequestBodyExtractor.extract(httpRequest, UaiRouteDTO.class);

        UaiRouteService.editRoute(uaiRouteDTO);

        send204Response(httpResponse);
    }

    @Override
    protected void doPost(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        final UaiRouteDTO uaiRouteDTO = RequestBodyExtractor.extract(httpRequest, UaiRouteDTO.class);

        UaiRouteService.createRoute(uaiRouteDTO);

        send204Response(httpResponse);
    }

    @Override
    protected void doDelete(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        final String routeId = httpRequest.getParameter("routeId");

        UaiRouteService.deleteRoute(routeId);

        send204Response(httpResponse);
    }
}