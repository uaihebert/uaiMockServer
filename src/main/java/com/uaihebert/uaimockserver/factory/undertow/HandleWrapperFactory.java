package com.uaihebert.uaimockserver.factory.undertow;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.FileResourceManager;
import io.undertow.server.handlers.resource.ResourceHandler;

import java.io.File;
import java.net.URL;

public final class HandleWrapperFactory {
    private static final int MIN_SIZE_TO_TRANSFER = 0;

    private HandleWrapperFactory() {
    }

    public static HandleWrapper create(final String fileRelativePath) {
        return create(fileRelativePath, fileRelativePath);
    }

    public static HandleWrapper create(final String url, final String fileRelativePath) {
        final ResourceHandler resourceHandler = createResourceHandler(fileRelativePath);

        return new HandleWrapper(url, resourceHandler);
    }

    private static ResourceHandler createResourceHandler(final String fileRelativePath) {
        final URL fileUrl = Undertow.class.getResource(fileRelativePath);
        final File file = new File(fileUrl.getFile());

        return Handlers.resource(new FileResourceManager(file, MIN_SIZE_TO_TRANSFER));
    }
}