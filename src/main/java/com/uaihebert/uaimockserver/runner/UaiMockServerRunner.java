package com.uaihebert.uaimockserver.runner;

import com.uaihebert.uaimockserver.server.UaiMockServer;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class UaiMockServerRunner extends BlockJUnit4ClassRunner {
    private final UaiRunnerMockServerConfiguration configuration;

    public UaiMockServerRunner(Class<?> testClass) throws InitializationError {
        super(testClass);

        configuration = testClass.getAnnotation(UaiRunnerMockServerConfiguration.class);
    }

    @Override
    public void run(final RunNotifier notifier) {
        final UaiMockServer uaiMockServer = startServer();
        try {
            super.run(notifier);
        } finally {
            uaiMockServer.shutdown();
        }
    }

    private UaiMockServer startServer() {
        if (configuration == null) {
            return UaiMockServer.start();
        }

        return UaiMockServer.start(configuration.configurationFile());
    }
}