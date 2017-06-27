package org.ligson.xunfei.listener;

import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SynthesizerListener;
import org.ligson.xunfei.service.XunFeiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ligson on 17-6-14.
 */
public class MySynthesizerListener implements SynthesizerListener {
    private static Logger logger = LoggerFactory.getLogger(MySynthesizerListener.class);
    private static XunFeiService xunFeiService = XunFeiService.getInstance();
    private static boolean exit = false;

    public void wait2exit() {
        exit = true;
    }

    @Override
    public void onBufferProgress(int i, int i1, int i2, String s) {

    }

    @Override
    public void onSpeakBegin() {

    }

    @Override
    public void onSpeakProgress(int i, int i1, int i2) {

    }

    @Override
    public void onSpeakPaused() {

    }

    @Override
    public void onSpeakResumed() {

    }

    @Override
    public void onCompleted(SpeechError speechError) {
        logger.error("文字转语音错误:{}", speechError.getErrorDescription(true));
        if (exit) {
            System.exit(0);
        } else {
            xunFeiService.recognizer();
        }
    }

    @Override
    public void onEvent(int i, int i1, int i2, int i3, Object o, Object o1) {

    }
}
