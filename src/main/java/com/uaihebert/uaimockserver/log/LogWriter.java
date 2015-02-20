package com.uaihebert.uaimockserver.log;

/**
 * Will be the responsible for print the log data
 */
public interface LogWriter {

    public abstract void info(final String text);

    public abstract void infoFormatted(final String text, Object... parameterArray);

    public abstract void warn(final Exception exception);
}