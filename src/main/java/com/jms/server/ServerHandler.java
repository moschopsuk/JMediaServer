package com.jms.server;

import com.jms.server.http.HTTPRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

public class ServerHandler extends IoHandlerAdapter {

    private static Logger log = LogManager.getLogger();

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        log.debug("ServerHandler.sessionCreated");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.debug("ServerHandler.sessionOpened");
    }

    @Override
     public void sessionClosed(IoSession session) throws Exception {
        log.debug("ServerHandler.sessionClosed");
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        log.debug("ServerHandler.messageSent");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        log.debug("ServerHandler.messageReceived");
        log.info("Message is: " + message.toString());

        if (message instanceof IoBuffer) {
        }
    }
}
