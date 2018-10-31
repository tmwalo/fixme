package com.gmail.vuyotm.fixme.router;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ConnectionHandler implements CompletionHandler<AsynchronousSocketChannel, Attachment> {

    private static int      routingId = 100000;

    @Override
    public void completed(AsynchronousSocketChannel client, Attachment attachment) {
        try {
            SocketAddress       clientAddress;
            Attachment          newAttachment;
            String              routingIdStr;
            byte[]              byteRoutingId;
            RoutingTable        routingTable;

            clientAddress = client.getRemoteAddress();
            System.out.println("Accepted connection from " + clientAddress);
            attachment.getServerChannel().accept(attachment, this);
            newAttachment = new Attachment();
            newAttachment.setServerChannel(attachment.getServerChannel());
            newAttachment.setClientChannel(client);
            newAttachment.setPort(attachment.getPort());
            newAttachment.setBuffer(ByteBuffer.allocate(2048));
            newAttachment.setRead(false);
            newAttachment.setClientAddress(clientAddress);
            routingIdStr = Integer.toString(routingId);
            ++routingId;
            newAttachment.setId(routingIdStr);
            routingTable = RoutingTable.getInstance();
            routingTable.addEntry(newAttachment);
            byteRoutingId = newAttachment.getId().getBytes();
            newAttachment.getBuffer().put(byteRoutingId);
            newAttachment.getBuffer().flip();
            System.out.println("Assign routingId: " + newAttachment.getId());
            client.write(newAttachment.getBuffer(), newAttachment, new ReadWriteHandler());
        }
        catch (IOException e) {
            e.getMessage();
        }
    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        System.out.println("Failed to accept a connection.");
    }

}
