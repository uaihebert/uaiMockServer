package com.uaihebert.uaimockserver.factory.undertow;

import com.uaihebert.uaimockserver.model.UaiWebSocketCallback;
import com.uaihebert.uaimockserver.server.UaiMockServerHandler;
import com.uaihebert.uaimockserver.servlet.AngularMapServlet;
import com.uaihebert.uaimockserver.servlet.CssMapServlet;
import com.uaihebert.uaimockserver.servlet.CssServlet;
import com.uaihebert.uaimockserver.servlet.UaiIndexServlet;
import com.uaihebert.uaimockserver.servlet.UaiPageServlet;
import com.uaihebert.uaimockserver.servlet.UaiRootConfigurationsServlet;
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
    private static final String SERVLET_CONTEXT_PATH = "/uai-mock-server-gui/";
    private static final String WEBSOCKET_CONTEXT_PATH = "/uai-mock-server-gui-ws";

    private static final List<HandleWrapper> HANDLE_WRAPPER_LIST = new ArrayList<HandleWrapper>();

    static {
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create("/fonts/glyphicons-halflings-regular.ttf"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create("/fonts/glyphicons-halflings-regular.woff"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create("/favicon.ico", "/images/favicon.png"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "rootConfig", "/pages/rootConfig/rootConfig.html"));

        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "javascript/angular", "/javascript/angular.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "javascript/angular.js.map", "/javascript/angular.min.js.map"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "javascript/angular-animate", "/javascript/angular-animate.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "javascript/angular-growl", "/javascript/angular-growl.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "javascript/angular-sanitize", "/javascript/angular-sanitize.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "javascript/bootstrap", "/javascript/bootstrap.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "javascript/index", "/javascript/index.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "javascript/jquery", "/javascript/jquery.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "javascript/log", "/javascript/log.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "javascript/tableSort", "/javascript/tableSort.js"));
        HANDLE_WRAPPER_LIST.add(HandleWrapperFactory.create(SERVLET_CONTEXT_PATH + "javascript/ui-bootstrap-tpls", "/javascript/ui-bootstrap-tpls.js"));
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

    // todo remove the servlets to serve static content, use the Undertow
    // todo refactor URL -> maybe remove the SERVLET_CONTEXT_PATH and use only "/index"
    private static HttpHandler createHtmlManager() throws ServletException {
        final DeploymentInfo deploymentInfo = Servlets.deployment()
                .setClassLoader(HttpServerUtil.class.getClassLoader())
                .setContextPath(SERVLET_CONTEXT_PATH)
                .setDeploymentName("uaiMockServer.war")
                .addServlets(
                        servlet("UaiIndexServlet", UaiIndexServlet.class).addMapping("/index"),
                        servlet("UaiPageServlet", UaiPageServlet.class).addMapping("/page"),
                        servlet("CssServlet", CssServlet.class).addMapping("/css"),
                        servlet("CssMapServlet", CssMapServlet.class).addMapping("/bootstrap.css.map"),
                        servlet("AngularMapServlet", AngularMapServlet.class).addMapping("/angular.js.map"),
                        servlet("UaiRootConfigurationsServlet", UaiRootConfigurationsServlet.class).addMapping("/rootConfigurations"),
                        servlet("UaiRouteServlet", UaiRouteServlet.class).addMapping("/uaiRoute")
                );

        final DeploymentManager manager = Servlets.defaultContainer().addDeployment(deploymentInfo);
        manager.deploy();

        return manager.start();
    }
}