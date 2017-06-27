package org.ligson.xunfei.listener;

import org.ligson.xunfei.service.XunFeiService;
import org.ligson.xunfei.tuling.TLChatServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ligson on 17-6-14.
 */
public class ReceiveTextListener {
    private static TLChatServiceImpl tlChatService = TLChatServiceImpl.getInstance();
    private static XunFeiService xunFeiService = XunFeiService.getInstance();
    private static Logger logger = LoggerFactory.getLogger(ReceiveTextListener.class);

    public void onReceive(String msg) {
        logger.debug("接收到消息:{}", msg);
        if (msg.equals("退出。")) {
            xunFeiService.text2Audio("再见", -1);
        } else {
            String result = tlChatService.sendMsg(msg);
            xunFeiService.text2Audio(result, 0);
        }
    }
}
