package org.ligson.xunfei.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechError;
import org.ligson.xunfei.service.XunFeiService;
import org.ligson.xunfei.vo.RecognizerResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 17-6-10.
 */
//听写监听器
public class MyRecognizerListener implements RecognizerListener {
    private static Logger logger = LoggerFactory.getLogger(MyRecognizerListener.class);
    private List<RecognizerResultVo> resultVos = new ArrayList<>();
    private ReceiveTextListener receiveTextListener = new ReceiveTextListener();
    private static XunFeiService xunFeiService = XunFeiService.getInstance();

    //听写结果回调接口(返回Json格式结果，用户可参见附录)；
    //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
    //关于解析Json的代码可参见MscDemo中JsonParser类；
    //isLast等于true时会话结束。
    public void onResult(RecognizerResult results, boolean isLast) {
        RecognizerResultVo recognizerResultVo = JSONObject.toJavaObject(JSON.parseObject(results.getResultString()), RecognizerResultVo.class);
        resultVos.add(recognizerResultVo);
        if (isLast) {
            StringBuilder builder = new StringBuilder();
            for (RecognizerResultVo resultVo : resultVos) {
                builder.append(resultVo.getResult());
            }
            receiveTextListener.onReceive(builder.toString());
            resultVos.clear();
        }
    }

    //会话发生错误回调接口
    public void onError(SpeechError error) {
        logger.error("语音识别错误:{}", error.getErrorDescription(true)); //获取错误码描述
        if (error.getErrorCode() == 20999) {

            xunFeiService.stopRecognizer();
            xunFeiService.recognizer();
        }
    }

    //开始录音
    public void onBeginOfSpeech() {
    }

    //音量值0~30
    public void onVolumeChanged(int volume) {
    }

    //结束录音
    public void onEndOfSpeech() {
    }

    //扩展用接口
    public void onEvent(int eventType, int arg1, int arg2, String msg) {
        logger.debug("听写事件类型:{},arg1:{},arg2:{},msg:{}", eventType, arg1, arg2, msg);
    }
}