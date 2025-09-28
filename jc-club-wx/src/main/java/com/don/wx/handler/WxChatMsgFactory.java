package com.don.wx.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author A
 * @date 2025/9/27
 **/
@Component
public class WxChatMsgFactory implements InitializingBean {

    @Autowired
    private List<WxChatMsgHandler> wxChatMsgHandlerList;

    private Map<WxChatMsgTypeEnum, WxChatMsgHandler> handlerMap = new HashMap<>();

    @Override
    public void afterPropertiesSet(){
        for(WxChatMsgHandler wxChatMsgHandler : wxChatMsgHandlerList)
            handlerMap.put(wxChatMsgHandler.getMsgType(), wxChatMsgHandler);
    }
    public WxChatMsgHandler getHandlerByMsgType(String msgType){
        WxChatMsgTypeEnum msgTypeEnum = WxChatMsgTypeEnum.getByMsgType(msgType);
        return handlerMap.get(msgTypeEnum);
    }
}
