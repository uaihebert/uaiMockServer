package com.uaihebert.uaimockserver.log.backend;

/**
 * Will be the responsible for print the log data.
 */
interface LogWriter {

    void info(String text);

    void warn(String text);

    void warn(Exception exception);
}
