package com.jms.server.http;

import org.apache.logging.log4j.LogManager;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.*;

public class HTTPRequest implements IHTTPRequest {

    private String message;
    private Map<String, String> headers = new HashMap();

    public HTTPRequest(IoBuffer buf)
    {
        StringBuffer sb = new StringBuffer();
        while (buf.hasRemaining()) {
            sb.append((char)buf.get());
        }

        this.message = sb.toString();
        phaseHeaders();
    }

    private void phaseHeaders() {

        String[] splitHeaders = this.message
                .replace("\r\n", "\n")
                .replace("\r", "\n")
                .split("\n");

        if(headers != null) {
            for (int i = 0; i < splitHeaders.length; i++) {

                //Skip any blank lines
                if(splitHeaders[i].length() == 0) continue;

                //The first line must be phased differently to the
                //others as it follows a different pattern
                if(i == 0) {
                    String[] tempStr = splitHeaders[i].split(" ");
                    this.headers.put("uri", splitHeaders[i]);
                    this.headers.put("method", tempStr[0].toUpperCase());
                    this.headers.put("context", tempStr.length > 1 ? tempStr[1].substring(1) : "");
                    this.headers.put("protocol", tempStr.length > 2 ? tempStr[2] : "");
                } else {

                    String[] tempStr = splitHeaders[i].split(": ");

                    if (tempStr.length != 2) {
                        tempStr = splitHeaders[i].split(":");
                    }

                    if (tempStr.length == 2) {
                        this.headers.put(tempStr[0].toLowerCase(), tempStr[1]);
                    }
                }
            }
        }
    }

    @Override
    public Set<String> getHeaderNames() {
        HashSet headerKeys = new HashSet();
        headerKeys.addAll(this.headers.keySet());
        return headerKeys;
    }

    @Override
    public String getHeader(String key) {
        return this.headers.get(key.toLowerCase());
    }
}
