package test.com.uaihebert.uaimockserver.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The idea of this class is to simulate a web server like Tomcat or Undertow.
 * We start a server at any available port and wait for a request for some seconds.
 */
public class WebServerSimulator {
    private static final int DEFAULT_TIME_OUT = 2000;

    private final ServerSocket serverSocket;

    public WebServerSimulator() throws IOException {
        // 0 means that it will chose any available port
        serverSocket = new ServerSocket(0);
        serverSocket.setSoTimeout(DEFAULT_TIME_OUT);
    }

    /**
     * We will wait for a request to arrive. If no request is received a timeout exception will be fired.
     *
     * Right now, it only returns 200, make no distinction of URLs, and add no different body
     *
     * @throws IOException
     */
    public void waitForRequest() throws IOException {
        final Socket client = serverSocket.accept();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        final PrintWriter writer = new PrintWriter(client.getOutputStream());

        writer.print("HTTP/1.1 200 \r\n");
        writer.print("Content-Type: text/plain\r\n");
        writer.print("Connection: close\r\n");
        writer.print("\r\n");

        String line;

        while ((line = reader.readLine()) != null) {
            if (line.length() == 0) {
                break;
            }

            writer.print(line + "\r\n");
        }

        writer.close();
        reader.close();
        client.close();
    }

    public int getLocalPort() {
        return serverSocket.getLocalPort();
    }
}
