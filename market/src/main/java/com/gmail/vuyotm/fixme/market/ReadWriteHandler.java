package com.gmail.vuyotm.fixme.market;

import com.gmail.vuyotm.fixme.market.request_process_chain.RequestProcessChain;

import java.nio.channels.CompletionHandler;

public class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {

    @Override
    public void completed(Integer result, Attachment attachment) {

        if (attachment.isRead()) {
            int                     limits;
            byte[]                  routerRequestBytes;
            String                  routerRequest;
            Request                 request;
            RequestProcessChain     requestProcessChain;

            attachment.getBuffer().flip();
            limits = attachment.getBuffer().limit();
            routerRequestBytes = new byte[limits];
            attachment.getBuffer().get(routerRequestBytes, 0, limits);
            routerRequest = new String(routerRequestBytes);
            System.out.println(routerRequest);

            request = new Request(attachment, routerRequest);
            requestProcessChain = new RequestProcessChain();
            requestProcessChain.getProcessChain1().execute(request);
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
