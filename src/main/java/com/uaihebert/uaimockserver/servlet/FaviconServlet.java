package com.uaihebert.uaimockserver.servlet;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class FaviconServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("image/png");

        super.addDefaultHeaders(httpResponse);

        writeFavIco(httpResponse);
    }

    private void writeFavIco(final HttpServletResponse httpResponse) throws IOException {
        final URL resource = FaviconServlet.class.getResource("/images/favicon.png");

        final File file = new File(resource.getFile());

        final BufferedImage bufferedImage = ImageIO.read(file);

        final OutputStream outputStream = httpResponse.getOutputStream();

        ImageIO.write(bufferedImage, "png", outputStream);

        outputStream.close();
    }
}