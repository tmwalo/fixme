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

        //   Test Values
        //   msg = "100200 list markets";
        //   msg = "100200 list 111222";
        //   msg = "8=FIX.4.0|9=86|35=D|49=100000|56=100000|34=1|52=20181022-07:09:54|11=1|21=1|55=NVDA|54=1|38=777|40=1|10=019|";
        //   msg = "8=FIX.4.0|9=85|35=D|49=777890|56=980123|34=1|52=20181022-09:20:28|11=1|21=1|55=AAPL|54=2|38=13|40=1|10=254|";
        //   msg = "unknown request";

    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        exc.printStackTrace();
    }

}
