package org.ligson.xunfei;


import com.iflytek.cloud.speech.*;

/**
 * Created by ligson on 17-6-10.
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("-0000");
        // 将“XXXXXXXX”替换成您申请的APPID
        SpeechUtility.createUtility("appid=593b9670");
        //1.创建SpeechRecognizer对象
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer();
//2.设置听写参数，详见《MSC Reference Manual》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
//3.开始听写
        mIat.startListening(new MyRecognizerListener());
    }

}
