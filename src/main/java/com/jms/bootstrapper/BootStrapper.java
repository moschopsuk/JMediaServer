package com.jms.bootstrapper;

import com.jms.server.Server;
import com.jms.server.vhost.ThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BootStrapper {
    public static final String VERSION = "0.0.1";
    static final Logger logger = LogManager.getLogger();
    private static Server server;

    public static void main (String args[]){
        logger.debug("BootStrapper.main: Starting Server");
        server = new Server();
        server.start();
        return;
     }
}
