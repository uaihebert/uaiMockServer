package com.uaihebert.uaimockserver.factory.undertow;

import com.uaihebert.uaimockserver.model.UaiWebSocketCallback;
import com.uaihebert.uaimockserver.server.UaiMockServerHandler;
import com.uaihebert.uaimockserver.servlet.UAiAngularMapServlet;
import com.uaihebert.uaimockserver.servlet.UaiCssMapServlet;
import com.uaihebert.uaimockserver.servlet.UaiCssServlet;
import com.uaihebert.uaimockserver.servlet.UaiJavascriptServlet;
import com.uaihebert.uaimockserver.servlet.UaiIndexServlet;
import com.uaihebert.uaimockserver.servlet.UaiPageServlet;
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

import static io.undertow.servlet.Servlets.servlet;

public final class PathHandlerFactory {
    private static final String SERVLET_CONTEXT_PATH = "/uaiGui/";
    private static final String WEBSOCKET_CONTEXT_PATH = "/uaiGui-ws";

    private PathHandlerFactory() {
    }

    public static PathHandler create() throws ServletException {
        return Handlers.path(Handlers.redirect(SERVLET_CONTEXT_PATH))
                .addPrefixPath(SERVLET_CONTEXT_PATH, createHtmlManager())
                .addPrefixPath(WEBSOCKET_CONTEXT_PATH, Handlers.websocket(new UaiWebSocketCallback()))
                .addPrefixPath("/", new UaiMockServerHandler());
    }

    private static HttpHandler createHtmlManager() throws ServletException {
        final DeploymentInfo deploymentInfo = Servlets.deployment()
                .setClassLoader(HttpServerUtil.class.getClassLoader())
                .setContextPath(SERVLET_CONTEXT_PATH)
                .setDeploymentName("uaiMockServer.war")
                .addServlets(
                        servlet("UaiIndexServlet", UaiIndexServlet.class).addMapping("/index"),
                        servlet("UaiPageServlet", UaiPageServlet.class).addMapping("/page"),
                        servlet("JavascriptServlet", UaiJavascriptServlet.class).addMapping("/javascript"),
                        servlet("CssServlet", UaiCssServlet.class).addMapping("/css"),
                        servlet("CssMapServlet", UaiCssMapServlet.class).addMapping("/bootstrap.css.map"),
                        servlet("AngularMapServlet", UAiAngularMapServlet.class).addMapping("/angular.js.map"),
                        servlet("UaiRouteServlet", UaiRouteServlet.class).addMapping("/uaiRoute"),
                        servlet("UaiRouteCloneServlet", UaiRouteCloneServlet.class).addMapping("/uaiRoute/clone"),
                        servlet("UaiRootConfigurationsServlet", UaiRootConfigurationsServlet.class).addMapping("/rootConfigurations")
                );

        final DeploymentManager manager = Servlets.defaultContainer().addDeployment(deploymentInfo);
        manager.deploy();

        return manager.start();
    }
}