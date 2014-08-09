package com.jms.server.vhost;

public class VHostWorkerThread extends Thread {

    public VHostWorkerThread(Runnable r) {
        super(r);
    }
}