package org.ligson.xunfei;


import com.iflytek.cloud.speech.*;

/**
 * Created by ligson on 17-6-10.
 */
public class Test {

    static {
        // 将“XXXXXXXX”替换成您申请的APPID
        SpeechUtility.createUtility("appid=593b9670");

    }

    public static void recognizer() {
        //1.创建SpeechRecognizer对象
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer();
//2.设置听写参数，详见《MSC Reference Manual》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
        //3.开始听写
        mIat.startListening(new MyRecognizerListener());
    }

    public static void synthesizer() {
        //1.创建 SpeechSynthesizer 对象
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
        //2.合成参数设置,详见《MSC Reference Manual》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "100");//设置音量,范围 0~100
        //设置合成音频保存位置(可自定义保存位置),保存在“./tts_test.pcm”
        //如果不需要保存合成音频,注释该行代码
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./tts_test.pcm");
        //3.开始合成
        mTts.startSpeaking("刘远秀是大坏蛋，天天偷吃大米饭，哈哈哈", new MySynthesizerListener());
    }

    public static void main(String[] args) {
        synthesizer();

    }


}
