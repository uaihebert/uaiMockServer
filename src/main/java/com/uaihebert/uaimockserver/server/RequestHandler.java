package com.uaihebert.uaimockserver.server;

import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.RequestHolder;

public class RequestHandler {
    public void processRequest(UaiRoute uaiRoute) {
        RequestHolder.holdTheRequest(uaiRoute.getRequest().holdTheRequestInMilli);

        uaiRoute.finishRequest();
    }
}
