package com.uaihebert.uaimockserver.runner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that will hold data to set up the tests
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface UaiServerConfiguration {
    /**
     * You can set the configuration file using relative path or full Path.
     * <p/>
     * If your configuration file is in the test/resources directory, just write the file name
     */
    String configurationFile() default "uaiMockServer.config";
}