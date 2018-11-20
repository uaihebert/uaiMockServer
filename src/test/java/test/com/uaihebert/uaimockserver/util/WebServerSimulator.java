package test.com.uaihebert.uaimockserver.util;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * The idea of this class is to simulate a web server like Tomcat or Undertow.
 * We start a server at any available port and wait for a request for some seconds.
 */
public class WebServerSimulator {
    private static final int DEFAULT_TIME_OUT = 5000;
    private static final int DEFAULT_STATUS_CODE = 204;

    private int statusCodeToReturn;

    private final ServerSocket serverSocket;

    private volatile boolean requestReceived;

    public WebServerSimulator() throws IOException {
        this(DEFAULT_STATUS_CODE);
    }

    public WebServerSimulator(final int statusCodeToReturn) throws IOException {
        this.statusCodeToReturn = statusCodeToReturn;

        // 0 means that it will chose any available port
        serverSocket = new ServerSocket(0);
        serverSocket.setSoTimeout(DEFAULT_TIME_OUT);
    }

    /**
     * We will wait for a request to arrive.
     * <p>
     * To check if the request was received, you can check the attribute #requestReceived
     * <p>
     * Right now, it only returns 204, make no distinction of URLs, and add no body on the response
     *
     * @throws IOException
     */
    public void waitForRequest() {
        final Thread thread = new Thread() {
            public void run() {
                startServerInBackground();
            }
        };

        thread.start();
    }

    private void startServerInBackground() {
        Socket client = null;
        PrintWriter writer = null;
        BufferedReader reader = null;

        try {
            client = serverSocket.accept();

            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream());
            printResponse(writer, reader);

            requestReceived = true;
        } catch (IOException ex) {
            Logger.getAnonymousLogger().warning("Error waiting for request: " + ex.getMessage());
        } finally {
            IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(client);
        }
    }

    public int getLocalPort() {
        return serverSocket.getLocalPort();
    }

    public boolean hasReceivedRequest() {
        return requestReceived;
    }

    private void printResponse(final PrintWriter writer, final BufferedReader reader) throws IOException {
        writer.print("HTTP/1.1 " + statusCodeToReturn + " \r\n");
        writer.print("Content-Type: application/json\r\n");
        writer.print("Connection: close\r\n");
        writer.print("\r\n");

        String line;

        while ((line = reader.readLine()) != null) {
            if (line.length() == 0) {
                break;
            }

            writer.print(line + "\r\n");
        }
    }
}
