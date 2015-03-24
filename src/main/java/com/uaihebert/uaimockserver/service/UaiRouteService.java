package com.uaihebert.uaimockserver.service;

import com.uaihebert.uaimockserver.dto.model.UaiRouteDTO;
import com.uaihebert.uaimockserver.factory.UaiRouteFactory;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.repository.UaiRouteRepository;

public final class UaiRouteService {
    private UaiRouteService() {
    }

    public static void editRoute(final UaiRouteDTO uaiRouteDTO) {
        final UaiRoute uaiRoute = UaiRouteRepository.findById(uaiRouteDTO.getId());

        UaiRouteFactory.setDTOValueToEntity(uaiRoute, uaiRouteDTO);

        UaiRouteRepository.update();
    }

    public static void createRoute(final UaiRouteDTO uaiRouteDTO) {
        final UaiRoute uaiRoute = UaiRouteFactory.create(uaiRouteDTO);

        UaiRouteRepository.create(uaiRoute);
    }

    public static void deleteRoute(final String routeId) {
        UaiRouteRepository.delete(routeId);
    }

    public static void clone(final String routeId) {
        final UaiRoute uaiRoute = UaiRouteRepository.findById(routeId);

        final UaiRoute clonedRoute = UaiRouteFactory.clone(uaiRoute);

        UaiRouteRepository.create(clonedRoute);
    }
}