package com.jms.server;

import com.jms.server.vhost.HostPort;
import com.jms.server.vhost.ThreadPool;
import com.jms.server.vhost.VHost;
import com.jms.server.vhost.VHostLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class Server implements IServer {
    private ThreadPool transportPool;
    private ServerShutdownHook shutdownHook;
    private ArrayList<VHost> vhosts;

    @Override
    public void start(){
        this.startShutdownHook();

        this.transportPool = new ThreadPool("TransportPool");
        this.transportPool.init();

        this.vhosts = VHostLoader.loadVHosts("config/VHosts.xml");

        HostPort hostPort = new HostPort();
        hostPort.setIpAddress("127.0.0.1");
        hostPort.setPort(1935);

        this.startVHost(hostPort);
    }

    @Override
    public void stop() {
        /**
         * Shut down transport pools
         */
        if (this.transportPool != null) {
            try
            {
                this.transportPool.stop();
            }
            catch (Exception ex) {}
        }
    }

    @Override
    public void shutdown() {
        this.stop();
    }

    public void startShutdownHook() {
        this.shutdownHook = new ServerShutdownHook(this);
        this.shutdownHook.setDaemon(false);
        Runtime.getRuntime().addShutdownHook(this.shutdownHook);
        LogManager.getLogger().info("Server.startShutdownHook: Shutdown hook started");
    }

    public void startVHost(HostPort host) {
        InetAddress address = host.getAddress();
        int port = host.getPort();

        try {
            IoAcceptor acceptor = new NioSocketAcceptor();
            acceptor.getFilterChain().addLast("logger", new LoggingFilter());
            acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(transportPool.getExecutor()));
            acceptor.setHandler(new ServerHandler());

            LogManager.getLogger().debug("Server.startVHost: Attempting bind {}", host.toString());
            acceptor.bind(new InetSocketAddress(address, port));

        } catch(Exception e) {
            LogManager.getLogger().error("Server.startVHost: Bind failed {} {}", host.toString(), e.toString());
        }
    }
}
