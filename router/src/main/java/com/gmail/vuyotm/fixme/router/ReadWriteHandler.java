package com.gmail.vuyotm.fixme.router;

import java.io.IOException;
import java.nio.channels.CompletionHandler;
import com.gmail.vuyotm.fixme.core.*;

public class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {

    @Override
    public void completed(Integer result, Attachment attachment) {

        if (result == -1) {
            RoutingTable    routingTable;

            try {
                routingTable = RoutingTable.getInstance();
                routingTable.removeEntry(attachment);
                attachment.getClientChannel().close();
                System.out.println("Stopped listening to the client: " + attachment.getClientAddress());
            }
            catch (IOException e) {
                e.getMessage();
            }
        }

        if (attachment.isRead()) {
            String          msg;
            byte[]          byteMsg;
            Attachment      targetAttachment;

            msg = Routing.getBuffStr(attachment);
            targetAttachment = Routing.processRequest(msg);
            if (targetAttachment != null) {
                if (!RequestValidation.isListMarkets(msg)) {

                    byteMsg = msg.getBytes();
                    targetAttachment.getBuffer().put(byteMsg);
                    targetAttachment.getBuffer().flip();
                }
                targetAttachment.setRead(false);
                targetAttachment.getClientChannel().write(targetAttachment.getBuffer(), targetAttachment, this);
            }
        }
        else {
            attachment.setRead(true);
            attachment.getBuffer().clear();
            try {
                attachment.getClientChannel().read(attachment.getBuffer(), attachment, this);
            }
            catch(Exception e) {

            }
        }

    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        exc.printStackTrace();
    }

}
