package org.ligson.xunfei;


import org.ligson.xunfei.service.XunFeiService;

/**
 * Created by ligson on 17-6-10.
 */
public class Test {
    private static XunFeiService xunFeiService = XunFeiService.getInstance();

    public static void main(String[] args) {
        xunFeiService.init();
        xunFeiService.recognizer();

    }


}
