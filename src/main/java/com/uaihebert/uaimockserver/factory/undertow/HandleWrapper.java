package com.uaihebert.uaimockserver.factory.undertow;

import io.undertow.server.handlers.resource.ResourceHandler;

class HandleWrapper {
    final String url;
    final ResourceHandler resourceHandler;

    public HandleWrapper(final String url, final ResourceHandler resourceHandler) {
        this.url = url;
        this.resourceHandler = resourceHandler;
    }
}