package com.jms.server.net.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;

import java.nio.ByteBuffer;

/**
 * Created by Luke on 06/07/2014.
 */
public class HandshakeFilter extends IoFilterAdapter {

    private static final Logger log = LogManager.getLogger();

    public void messageReceived(NextFilter nextFilter, IoSession session, Object message)
            throws Exception {

        ByteBuffer buf = (ByteBuffer)message;

        //C0 Value should be 0x3
        if(buf.get() == 0x3) {

            //C1 Should be 1536 random values
            if (buf.remaining() >= 1536) {
                //We need to create a buffer to store s0, c1 and s1
                ByteBuffer out = ByteBuffer.allocate(3073);
                ByteBuffer in = buf.slice();


            }
        }
    }



}
