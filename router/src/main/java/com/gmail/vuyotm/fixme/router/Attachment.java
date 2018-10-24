package com.gmail.vuyotm.fixme.router;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

public class Attachment {

    private AsynchronousServerSocketChannel     serverChannel;
    private AsynchronousSocketChannel           clientChannel;
    private ByteBuffer                          buffer;
    private SocketAddress                       clientAddress;
    private boolean                             isRead;

    public AsynchronousServerSocketChannel getServerChannel() {
        return serverChannel;
    }

    public void setServerChannel(AsynchronousServerSocketChannel serverChannel) {
        this.serverChannel = serverChannel;
    }

    public AsynchronousSocketChannel getClientChannel() {
        return clientChannel;
    }

    public void setClientChannel(AsynchronousSocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public SocketAddress getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(SocketAddress clientAddress) {
        this.clientAddress = clientAddress;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

}
