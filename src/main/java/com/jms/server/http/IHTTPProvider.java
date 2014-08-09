package com.jms.server.http;

import com.jms.server.vhost.HostPort;

/**
 * Created by Luke on 27/06/2014.
 */
public abstract interface IHTTPProvider {

    public abstract void onBind(HostPort hostPort);

    public abstract void onHTTPRequest(IHTTPRequest request, IHTTPResponse response);

    public abstract void onUnBind(HostPort hostPort);
}
