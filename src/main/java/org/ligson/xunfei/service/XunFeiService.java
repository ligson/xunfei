package org.ligson.xunfei.service;

import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SpeechUtility;
import org.ligson.xunfei.listener.MyRecognizerListener;
import org.ligson.xunfei.listener.MySynthesizerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/6/27.
 */
public class XunFeiService {
    private static final String appId = "593b9670";
    private static Logger logger = LoggerFactory.getLogger(XunFeiService.class);
    private static SpeechRecognizer recognizer;
    private static SpeechSynthesizer synthesizer;
    private MySynthesizerListener synthesizerListener = new MySynthesizerListener();
    private MyRecognizerListener recognizerListener = new MyRecognizerListener();
    private static XunFeiService xunFeiService;

    public synchronized static XunFeiService getInstance() {
        if (xunFeiService == null) {
            xunFeiService = new XunFeiService();
        }
        return xunFeiService;
    }

    private XunFeiService() {

    }

    public void init() {
        // 将“XXXXXXXX”替换成您申请的APPID
        SpeechUtility.createUtility("appid=" + appId);
        recognizer = SpeechRecognizer.createRecognizer();
        //2.设置听写参数，详见《MSC Reference Manual》SpeechConstant类
        recognizer.setParameter(SpeechConstant.DOMAIN, "iat");
        recognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        recognizer.setParameter(SpeechConstant.ACCENT, "mandarin ");

        synthesizer = SpeechSynthesizer.createSynthesizer();
        //2.合成参数设置,详见《MSC Reference Manual》SpeechSynthesizer 类
        //synthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        synthesizer.setParameter(SpeechConstant.SPEED, "50");//设置语速
        synthesizer.setParameter(SpeechConstant.VOLUME, "100");//设置音量,范围 0~100
        //设置合成音频保存位置(可自定义保存位置),保存在“./tts_test.pcm”
        //如果不需要保存合成音频,注释该行代码
        //synthesizer.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./tts_test.pcm");
    }

    public void text2Audio(String text, int operation) {
        logger.debug("文字转语音:{}", text);
        if (operation == -1) {
            synthesizerListener.wait2exit();
        }
        synthesizer.startSpeaking(text, synthesizerListener);
    }


    public void recognizer() {
        if (recognizer.isListening()) {
            recognizer.cancel();
            recognizer.stopListening();
        }
        logger.debug("开始识别:{}", recognizer.isListening());
        recognizer.startListening(recognizerListener);
    }

    public void stopRecognizer() {
        recognizer.stopListening();
    }
}
