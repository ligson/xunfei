package org.ligson.xunfei.tuling;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.ligson.xunfei.util.ClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/6/27.
 */
public class TLChatServiceImpl {
    private static final String apiKey = "f835f4c0880c4ec5bc8eab730ad579c0";
    // private static final String apiKey = "key";
    private static final String url = "http://www.tuling123.com/openapi/api";
    //private static final String secret = "6ba1beb0f02c3ae1";
    // private static final String secret = "123";
    private static Logger logger = LoggerFactory.getLogger(TLChatServiceImpl.class);

    public static Header[] headers = new Header[]{new BasicHeader("Content-type", "text/html;charset=utf-8")};

    private static TLChatServiceImpl chatService;

    public synchronized static TLChatServiceImpl getInstance() {
        if (chatService == null) {
            chatService = new TLChatServiceImpl();
        }
        return chatService;
    }

    private TLChatServiceImpl() {

    }

    public String sendMsg(String msg) {
        String apiUrl = url + "?key=" + apiKey + "&info=" + msg + "&userid=123456";
        HttpResponse response = ClientUtils.get(apiUrl);
        if (response.getStatusLine().getStatusCode() == 200) {
            try {
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                logger.debug("rec info : {}", jsonStr);
                JSONObject jsonObject = JSONObject.parseObject(jsonStr);
                return jsonObject.getString("text");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TLChatServiceImpl chatService = new TLChatServiceImpl();
        System.out.println(chatService.sendMsg("你好"));
    }
}
