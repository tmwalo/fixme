package com.gmail.vuyotm.fixme.router;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

public class TaskListen implements Runnable {

    private final String    host;
    private final int       port;

    public TaskListen(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        AsynchronousServerSocketChannel     server;
        InetSocketAddress                   socketAddress;
        Attachment                          attachment;

        try {
            server = AsynchronousServerSocketChannel.open();
            socketAddress = new InetSocketAddress(host, port);
            server.bind(socketAddress);
            System.out.format("Server is listening at %s%n", socketAddress);
            attachment = new Attachment();
            attachment.setServerChannel(server);
            attachment.setPort(port);
            server.accept(attachment, new ConnectionHandler());
            Thread.currentThread().join();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
