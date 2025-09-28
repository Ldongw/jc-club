package com.don.wx.handler;

import java.util.Map;

/**
 * @author A
 * @date 2025/9/27
 **/
public interface WxChatMsgHandler {

    WxChatMsgTypeEnum getMsgType();

    String dealMsg(Map<String, String> msgMap);



}
