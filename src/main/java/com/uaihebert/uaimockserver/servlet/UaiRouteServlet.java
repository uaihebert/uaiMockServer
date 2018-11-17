package com.uaihebert.uaimockserver.servlet;

import com.uaihebert.uaimockserver.dto.factory.UaiBasicConfigurationDTOFactory;
import com.uaihebert.uaimockserver.dto.factory.UaiRouteDTOFactory;
import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.dto.response.IndexResponseDTO;
import com.uaihebert.uaimockserver.model.BodyValidationType;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.repository.UaiRouteMapper;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;
import com.uaihebert.uaimockserver.service.UaiRouteService;
import com.uaihebert.uaimockserver.util.JsonUtil;
import com.uaihebert.uaimockserver.util.RequestBodyExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UaiRouteServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String selectedProject = request.getParameter("selectedProject");

        super.addDefaultHeaders(response);

        final String body = createIndexGetResponse(selectedProject);

        writeInResponse(response, body);
    }

    private String createIndexGetResponse(final String selectedProject) {
        final List<UaiRoute> uaiRouteList = UaiRouteRepository.listAllRoutes(selectedProject);

        final IndexResponseDTO indexResponseDTO = new IndexResponseDTO();

        indexResponseDTO.setRouteList(UaiRouteDTOFactory.create(uaiRouteList));
        indexResponseDTO.setDefaultProject(UaiRouteMapper.ALL_PROJECT);
        indexResponseDTO.setProjectList(UaiRouteMapper.extractProjectFromRoutes());
        indexResponseDTO.setRootConfiguration(UaiBasicConfigurationDTOFactory.create());
        indexResponseDTO.setBodyValidationTypeList(Arrays.asList(BodyValidationType.values()));

        return JsonUtil.toJson(indexResponseDTO);
    }

    @Override
    protected void doPut(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final UaiRouteDTO uaiRouteDTO = RequestBodyExtractor.extract(request, UaiRouteDTO.class);

        UaiRouteService.editRoute(uaiRouteDTO);

        send204Response(response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final UaiRouteDTO uaiRouteDTO = RequestBodyExtractor.extract(request, UaiRouteDTO.class);

        UaiRouteService.createRoute(uaiRouteDTO);

        send204Response(response);
    }

    @Override
    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String routeId = request.getParameter("routeId");

        UaiRouteService.deleteRoute(routeId);

        send204Response(response);
    }
}
