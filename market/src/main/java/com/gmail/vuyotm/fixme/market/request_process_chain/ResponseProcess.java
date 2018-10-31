package com.gmail.vuyotm.fixme.market.request_process_chain;

import com.gmail.vuyotm.fixme.market.Attachment;
import com.gmail.vuyotm.fixme.market.ReadWriteHandler;
import com.gmail.vuyotm.fixme.market.Request;

public class ResponseProcess extends ProcessChain {

    @Override
    public void execute(Request request) {
        Attachment  attachment;
        String      marketResponse;
        byte[]      byteMarketResponse;

        attachment = request.getAttachment();
        marketResponse = request.getResponse();
        attachment.getBuffer().clear();
        byteMarketResponse = marketResponse.getBytes();
        attachment.getBuffer().put(byteMarketResponse);
        attachment.getBuffer().flip();
        attachment.setRead(false);
        attachment.getClientChannel().write(attachment.getBuffer(), attachment, new ReadWriteHandler());
        if (this.getNextProcess() != null)
            this.getNextProcess().execute(request);
    }

}
