package com.gmail.vuyotm.fixme.market;

import java.nio.channels.CompletionHandler;

public class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {

    @Override
    public void completed(Integer result, Attachment attachment) {

        if (attachment.isRead()) {
            int                     limits;
            byte[]                  routerRequestBytes;
            String                  routerRequest;
            String                  marketResponse;
            byte[]                  byteMarketResponse;

            attachment.getBuffer().flip();
            limits = attachment.getBuffer().limit();
            routerRequestBytes = new byte[limits];
            attachment.getBuffer().get(routerRequestBytes, 0, limits);
            routerRequest = new String(routerRequestBytes);
            if (!attachment.isMarketIdSet()) {
                MarketData.setMarketId(routerRequest);
                attachment.setMarketIdSet(true);
                System.out.print("Market Id: ");
            }
            System.out.println(routerRequest);



            attachment.getBuffer().clear();
            byteMarketResponse = marketResponse.getBytes();
            attachment.getBuffer().put(byteMarketResponse);
            attachment.getBuffer().flip();
            attachment.setRead(false);
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
