package org.ligson.xunfei.vo;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by ligson on 17-6-10.
 */
public class RecognizerResultVo {
    private int sn;
    private boolean ls;
    private int bg;
    private int ed;
    private List<WSVo> ws;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public boolean isLs() {
        return ls;
    }

    public void setLs(boolean ls) {
        this.ls = ls;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public int getEd() {
        return ed;
    }

    public void setEd(int ed) {
        this.ed = ed;
    }

    public List<WSVo> getWs() {
        return ws;
    }

    public void setWs(List<WSVo> ws) {
        this.ws = ws;
    }

    public String getResult(){
        if(CollectionUtils.isNotEmpty(getWs())){
            StringBuilder builder = new StringBuilder();
            for(WSVo ws:getWs()){
                if(CollectionUtils.isNotEmpty(ws.getCw())){
                    builder.append(ws.getCw().get(0).getW());
                }
            }
            return builder.toString();
        }
        return null;
    }
}
