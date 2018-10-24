package com.gmail.vuyotm.fixme.market;

import java.nio.channels.CompletionHandler;

public class ConnectionHandler implements CompletionHandler<Integer, Attachment> {

    @Override
    public void completed(Integer result, Attachment attachment) {

        if (attachment.isRead()) {
            int                     limits;
            byte[]                  routerRequestBytes;
            String                  routerRequest;

            attachment.getBuffer().flip();
            limits = attachment.getBuffer().limit();
            routerRequestBytes = new byte[limits];
            attachment.getBuffer().get(routerRequestBytes, 0, limits);
            routerRequest = new String(routerRequestBytes);
            System.out.println("router Request: " + routerRequest);
            if (!attachment.isMarketIdSet()) {
                MarketData.setMarketId(routerRequest);
                attachment.setMarketIdSet(true);
                System.out.print("Market Id: ");
            }
            System.out.println(routerRequest);

            attachment.getBuffer().clear();
            attachment.getClientChannel().read(attachment.getBuffer(), attachment, new ReadWriteHandler());
        }

    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        exc.printStackTrace();
    }

}
