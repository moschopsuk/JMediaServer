package com.jms.server.http;

import com.jms.bootstrapper.BootStrapper;

public class HTTPServerVersion {

    public void onHTTPRequest() {

        String serverIdentifer = "Java Media Server:" + BootStrapper.VERSION;

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><title>");
        sb.append(serverIdentifer);
        sb.append("</title></head><body>");
        sb.append(serverIdentifer);
        sb.append("</body></html>");

        sb.toString().getBytes();
    }
}
