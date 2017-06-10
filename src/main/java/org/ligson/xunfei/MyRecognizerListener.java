package org.ligson.xunfei;

import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechError;

/**
 * Created by ligson on 17-6-10.
 */
//听写监听器
public class MyRecognizerListener implements RecognizerListener {


    //听写结果回调接口(返回Json格式结果，用户可参见附录)；
    //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
    //关于解析Json的代码可参见MscDemo中JsonParser类；
    //isLast等于true时会话结束。
    public void onResult(RecognizerResult results, boolean isLast){
        System.out.println(results);
    }
    //会话发生错误回调接口
    public void onError(SpeechError error) {
        System.out.println(error.getErrorDescription(true)); //获取错误码描述
    }
    //开始录音
    public void onBeginOfSpeech() {}
    //音量值0~30
    public void onVolumeChanged(int volume){}
    //结束录音
    public void onEndOfSpeech() {}
    //扩展用接口
    public void onEvent(int eventType,int arg1,int arg2,String msg) {}
}