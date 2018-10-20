package com.gmail.vuyotm.fixme.router;

import com.sun.xml.internal.ws.api.message.Attachment;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

public class Router {

    public static void main(String[] args) throws IOException, InterruptedException {

        AsynchronousServerSocketChannel     serverChannel;
        InetSocketAddress                   serverAddress;
        Attachment attachment;

        serverChannel = AsynchronousServerSocketChannel.open();
        serverAddress = new InetSocketAddress("localhost", 5000);
        serverChannel.bind(serverAddress);
        System.out.println("Server is listening at " + serverAddress + " ...");
        attachment = new Attachment();
        attachment.setServerChannel(serverChannel);
        serverChannel.accept(attachment, new ConnectionHandler());
        Thread.currentThread().join();

    }

}
