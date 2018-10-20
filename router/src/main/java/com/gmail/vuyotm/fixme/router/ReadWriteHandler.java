package com.gmail.vuyotm.fixme.router;

import java.io.IOException;
import java.nio.channels.CompletionHandler;

public class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {

    @Override
    public void completed(Integer result, Attachment attachment) {

        if (result == -1) {
            try {
                attachment.getClientChannel().close();
                System.out.println("Stopped listening to the client: " + attachment.getClientAddress());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (attachment.isRead()) {
            int     limits;
            byte[]  bytes;
            String  msg;

            attachment.getBuffer().flip();
            limits = attachment.getBuffer().limit();
            bytes = new byte[limits];
            attachment.getBuffer().get(bytes, 0, limits);
            msg = new String(bytes);
            System.out.println("Client at " + attachment.getClientAddress() + " says: " + msg);
            attachment.setRead(false);
            attachment.getBuffer().flip();
            attachment.getClientChannel().write(attachment.getBuffer(), attachment, this);
        }
        else {
            attachment.setRead(true);
            attachment.getBuffer().clear();
            attachment.getClientChannel().read(attachment.getBuffer(), attachment, this);
        }

    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        exc.printStackTrace();
    }

}
