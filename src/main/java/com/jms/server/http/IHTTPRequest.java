package com.jms.server.http;

import java.util.Set;

public interface IHTTPRequest {

    public abstract Set<String> getHeaderNames();

    public abstract String getHeader(String paramString);

}
