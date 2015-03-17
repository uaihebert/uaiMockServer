package com.uaihebert.uaimockserver.factory.undertow;

import com.uaihebert.uaimockserver.model.UaiWebSocketCallback;
import com.uaihebert.uaimockserver.server.UaiMockServerHandler;
import com.uaihebert.uaimockserver.servlet.UaiRootConfigurationsServlet;
import com.uaihebert.uaimockserver.servlet.UaiRouteCloneServlet;
import com.uaihebert.uaimockserver.servlet.UaiRouteServlet;
import com.uaihebert.uaimockserver.util.HttpServerUtil;
import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

import static io.undertow.servlet.Servlets.servlet;

public final class PathHandlerFactory {
    private static final String SERVLET_CONTEXT_PATH = "/uaiGui/";
    private static final String WEBSOCKET_CONTEXT_PATH = "/uaiGui-ws";

    private static final String CSS_CONTEXT_PATH = SERVLET_CONTEXT_PATH + "css/";
    private static final String JAVASCRIPT_CONTEXT_PATH = SERVLET_CONTEXT_PATH + "javascript/";

    private static final List<HandleWrapper> HANDLE_WRAPPER_LIST = new ArrayList<HandleWrapper>();

    static {
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create("/fonts/glyphicons-halflings-regular.ttf"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create("/fonts/glyphicons-halflings-regular.woff"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create("/favicon.ico", "/images/favicon.png"));

        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(JAVASCRIPT_CONTEXT_PATH + "log", "/javascript/log.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(JAVASCRIPT_CONTEXT_PATH + "index", "/javascript/index.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(JAVASCRIPT_CONTEXT_PATH + "jquery", "/javascript/jquery.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(JAVASCRIPT_CONTEXT_PATH + "angular", "/javascript/angular.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(JAVASCRIPT_CONTEXT_PATH + "bootstrap", "/javascript/bootstrap.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(JAVASCRIPT_CONTEXT_PATH + "rootConfig", "/javascript/rootConfig.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(JAVASCRIPT_CONTEXT_PATH + "tableSort", "/javascript/tableSort.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(JAVASCRIPT_CONTEXT_PATH + "angular-growl", "/javascript/angular-growl.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(JAVASCRIPT_CONTEXT_PATH + "angular.js.map", "/javascript/angular.min.js.map"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(JAVASCRIPT_CONTEXT_PATH + "angular-animate", "/javascript/angular-animate.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(JAVASCRIPT_CONTEXT_PATH + "angular-sanitize", "/javascript/angular-sanitize.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(JAVASCRIPT_CONTEXT_PATH + "ui-bootstrap-tpls", "/javascript/ui-bootstrap-tpls.js"));

        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(CSS_CONTEXT_PATH + "log", "/css/log.css"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(CSS_CONTEXT_PATH + "index", "/css/index.css"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(CSS_CONTEXT_PATH + "growl", "/css/growl.css"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(CSS_CONTEXT_PATH + "tableSort", "/css/tableSort.css"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(CSS_CONTEXT_PATH + "bootstrap", "/css/bootstrap.css"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(CSS_CONTEXT_PATH + "bootstrap.css.map", "/css/bootstrap.css.map"));

        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "log", "/pages/log/log.html"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "index", "/pages/index/index.html"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "rootConfig", "/pages/rootConfig/rootConfig.html"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "page/headerMenu", "/pages/common/headerMenu.html"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "page/indexTable", "/pages/index/indexTable.html"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "page/routeDialog", "/pages/index/routeDialog.html"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "page/requestPanel", "/pages/index/requestPanel.html"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "page/deleteDialog", "/pages/index/deleteDialog.html"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "page/responsePanel", "/pages/index/responsePanel.html"));
    }

    private PathHandlerFactory() {
    }

    public static PathHandler create() throws ServletException {
        final PathHandler path = Handlers.path(Handlers.redirect(SERVLET_CONTEXT_PATH))
                .addPrefixPath(SERVLET_CONTEXT_PATH, createHtmlManager())
                .addPrefixPath(WEBSOCKET_CONTEXT_PATH, Handlers.websocket(new UaiWebSocketCallback()))
                .addPrefixPath("/", new UaiMockServerHandler());

        for (HandleWrapper handleWrapper : HANDLE_WRAPPER_LIST) {
            path.addPrefixPath(handleWrapper.url, handleWrapper.resourceHandler);
        }

        return path;
    }

    private static HttpHandler createHtmlManager() throws ServletException {
        final DeploymentInfo deploymentInfo = Servlets.deployment()
                .setClassLoader(HttpServerUtil.class.getClassLoader())
                .setContextPath(SERVLET_CONTEXT_PATH)
                .setDeploymentName("uaiMockServer.war")
                .addServlets(
                        servlet("UaiRouteServlet", UaiRouteServlet.class).addMapping("/uaiRoute"),
                        servlet("UaiRouteCloneServlet", UaiRouteCloneServlet.class).addMapping("/uaiRoute/clone"),
                        servlet("UaiRootConfigurationsServlet", UaiRootConfigurationsServlet.class).addMapping("/rootConfigurations")
                );

        final DeploymentManager manager = Servlets.defaultContainer().addDeployment(deploymentInfo);
        manager.deploy();

        return manager.start();
    }
}