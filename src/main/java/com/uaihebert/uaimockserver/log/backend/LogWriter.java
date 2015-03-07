package com.uaihebert.uaimockserver.log.backend;

/**
 * Will be the responsible for print the log data
 */
interface LogWriter {

    public abstract void info(final String text);

    public abstract void warn(final String text);

    public abstract void warn(final Exception exception);
}