package com.uaihebert.uaimockserver.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class FaviconServlet extends AbstractServlet {

    @Override
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("image/png");

        super.addDefaultHeaders(httpResponse);

        writeFavIco(httpResponse.getOutputStream());
    }

    public void writeFavIco(final OutputStream outputStream) throws IOException {
        final BufferedImage bufferedImage = ImageIO.read(getClass().getResourceAsStream("/images/favicon.png"));

        ImageIO.write(bufferedImage, "png", outputStream);

        outputStream.close();
    }
}