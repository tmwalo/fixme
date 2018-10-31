package com.gmail.vuyotm.fixme.market;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Market {

    public static void main(String[] args) {

        AsynchronousSocketChannel   clientChannel;
        Future<Void>                result;
        Attachment                  attachment;

        try {
        clientChannel = AsynchronousSocketChannel.open();
        result = clientChannel.connect(new InetSocketAddress("localhost", 5001));
        result.get();
        System.out.println("Connected to router");
        attachment = new Attachment();
        attachment.setClientChannel(clientChannel);
        attachment.setBuffer(ByteBuffer.allocate(2048));
        attachment.setRead(true);
        attachment.setMarketIdSet(false);
        attachment.setMainThread(Thread.currentThread());
        clientChannel.read(attachment.getBuffer(), attachment, new ConnectionHandler());

        attachment.getMainThread().join();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (ExecutionException e) {
            System.out.println(e.getMessage());
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

}
