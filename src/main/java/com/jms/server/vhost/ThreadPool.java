package com.jms.server.vhost;

import org.apache.logging.log4j.LogManager;

import java.util.concurrent.*;

public class ThreadPool {
    private String name;
    private ThreadPoolExecutor threadPool;
    private LinkedBlockingQueue queue;

    public ThreadPool(String name) {
        this.name = name;
    }

    /**
     * Create the java threads factory to handle responses
     * from web requests
     */
    public void init() {
        ThreadFactory threadFactory = new ThreadFactory()
        {
            public Thread newThread(Runnable r) {
                VHostWorkerThread vHostWorkerThread = null;
                try
                {
                    vHostWorkerThread = new VHostWorkerThread(r);
                    vHostWorkerThread.setPriority(10);
                    vHostWorkerThread.setName("Thread Pool");
                }
                catch (Exception ex)
                {
                    LogManager.getLogger(ThreadPool.class).error("ThreadPool: newThread: " + ex.toString());
                }
                if (vHostWorkerThread == null) {
                    LogManager.getLogger(ThreadPool.class).error("ThreadPool: newThread: null thread");
                }
                return vHostWorkerThread;
            }
        };

        this.queue = new LinkedBlockingQueue();
        this.threadPool = new ThreadPoolExecutor(100, 100, 500, TimeUnit.MILLISECONDS, this.queue);
        this.threadPool.setThreadFactory(threadFactory);
    }

    public Executor getExecutor()
    {
        return this.threadPool;
    }

    public void stop()
    {
        if (this.threadPool != null) {
            try
            {
                this.threadPool.shutdown();
            }
            catch (Exception localException)
            {
                LogManager.getLogger(ThreadPool.class).error("terminate: " + localException.toString());
            }
        }
    }

    public void execute(Runnable r)
    {
        if (this.threadPool != null) {
            try
            {
                this.threadPool.execute(r);
            }
            catch (Exception localException)
            {
                LogManager.getLogger(ThreadPool.class).error("execute: " + localException.toString());
            }
        }
    }
}