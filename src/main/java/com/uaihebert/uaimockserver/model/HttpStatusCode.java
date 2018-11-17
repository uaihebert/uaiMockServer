package com.uaihebert.uaimockserver.model;

public enum HttpStatusCode {
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NO_CONTENT(204),
    NON_AUTHORITATIVE(203),
    INTERNAL_SERVER_ERROR(500);

    public final int code;

    HttpStatusCode(final int code) {
        this.code = code;
    }
}
