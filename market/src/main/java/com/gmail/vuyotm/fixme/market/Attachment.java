package com.gmail.vuyotm.fixme.market;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class Attachment {

    private AsynchronousSocketChannel   clientChannel;
    private ByteBuffer                  buffer;
    private Thread                      mainThread;
    private boolean                     isRead;
    private boolean                     isMarketIdSet;

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

    public Thread getMainThread() {
        return mainThread;
    }

    public void setMainThread(Thread mainThread) {
        this.mainThread = mainThread;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isMarketIdSet() {
        return isMarketIdSet;
    }

    public void setMarketIdSet(boolean marketIdSet) {
        isMarketIdSet = marketIdSet;
    }

}
