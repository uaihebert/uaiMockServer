package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.log.backend.Log;
import io.undertow.server.HttpServerExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Boiler code found on internet
 * Right now, do not need to be tested
 */
public final class RequestBodyUtil {
    private RequestBodyUtil() {
    }

    public static String convertToString(final HttpServerExchange exchange) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder( );

        try {
            exchange.startBlocking( );
            reader = new BufferedReader( new InputStreamReader( exchange.getInputStream( ) ) );

            String line;
            while( ( line = reader.readLine( ) ) != null ) {
                builder.append( line );
            }
        } catch( IOException e ) {
            Log.warn(e);
        } finally {
            if( reader != null ) {
                try {
                    reader.close( );
                } catch( IOException e ) {
                    Log.warn(e);
                }
            }
        }

        return builder.toString( );
    }
}
