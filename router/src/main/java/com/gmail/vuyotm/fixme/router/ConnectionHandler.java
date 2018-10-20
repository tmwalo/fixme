package com.gmail.vuyotm.fixme.router;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ConnectionHandler implements CompletionHandler<AsynchronousSocketChannel, Attachment> {

    @Override
    public void completed(AsynchronousSocketChannel client, Attachment attachment) {
        try {
            SocketAddress       clientAddress;
            ReadWriteHandler    readWriteHandler;
            Attachment          newAttachment;

            clientAddress = client.getRemoteAddress();
            System.out.println("Accepted connection from " + clientAddress);
            attachment.getServerChannel().accept(attachment, this);               // WHY?
            newAttachment = new Attachment();
            newAttachment.setServerChannel(attachment.getServerChannel());
            newAttachment.setClientChannel(client);
            newAttachment.setBuffer(ByteBuffer.allocate(2048));
            newAttachment.setRead(true);
            newAttachment.setClientAddress(clientAddress);
            readWriteHandler = new ReadWriteHandler();
            client.read(newAttachment.getBuffer(), newAttachment, readWriteHandler);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        System.out.println("Failed to accept a connection.");
        exc.printStackTrace();
    }

}
