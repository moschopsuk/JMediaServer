package com.jms.server;

public class ServerShutdownHook extends Thread {

    IServer server;

    public ServerShutdownHook(IServer server) {
        this.server = server;
    }

    public void run()
    {
        this.server.shutdown();
    }
}
