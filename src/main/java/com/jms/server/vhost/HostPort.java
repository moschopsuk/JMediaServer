package com.jms.server.vhost;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;

/**
 * Created by Luke on 25/06/2014.
 */
public class HostPort {
    private InetAddress ipAddress;
    private int port;

    public void setIpAddress(String ip) {
        try
        {
            if (ip.equals("*")) {
                this.ipAddress = null;
            } else if (ip != null) {
                this.ipAddress = InetAddress.getByName(ip);
            }
        }
        catch (Exception localException)
        {
            LogManager.getLogger(HostPort.class).error("HostPort.setIpAddress: " + localException.toString());
        }
    }

    public InetAddress getAddress() {
        return this.ipAddress;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[IPAddress:" + ipAddress + ", ");
        sb.append("port:" + port + "]");

        return sb.toString();
    }
}
