package com.gmail.vuyotm.fixme.router;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Router {

    private static final String     HOST = "localhost";
    public static final int         BROKER_PORT = 5000;
    public static final int         MARKET_PORT = 5001;
    private static final int        NUM_OF_THREADS = 2;

    public static void main(String[] args) {

        ExecutorService     executorService;
        Runnable            brokerTask;
        Runnable            marketTask;

        executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
        brokerTask = new TaskListen(HOST, BROKER_PORT);
        marketTask = new TaskListen(HOST, MARKET_PORT);
        executorService.submit(brokerTask);
        executorService.submit(marketTask);
        executorService.shutdown();

    }

}
