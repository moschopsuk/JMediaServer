package com.jms.server.http;

import java.io.OutputStream;

public abstract class IHTTPResponse {

    public abstract void setResponseCode(int code);

    public abstract void setHeader(String key, String value);

    public abstract OutputStream getOutputStream();
}
