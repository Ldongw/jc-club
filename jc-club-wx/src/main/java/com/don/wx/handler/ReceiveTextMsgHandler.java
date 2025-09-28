package com.don.wx.handler;

import com.don.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author A
 * @date 2025/9/27
 **/
@Component
@Slf4j
public class ReceiveTextMsgHandler implements WxChatMsgHandler{

    private static final String KEY_WORD = "验证码";

    private static final String LOGIN_PREFIX = "loginCode";

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return WxChatMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String dealMsg(Map<String, String> msgMap) {
        log.info("接收到文本消息事件！");
        String content = msgMap.get("Content");

        String fromUserName = msgMap.get("FromUserName");
        String toUserName = msgMap.get("ToUserName");

        if (!content.matches(".*" + KEY_WORD + ".*"))
            return "<xml>\n" +
                    "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                    "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                    "  <CreateTime>12345678</CreateTime>\n" +
                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                    "  <Content><![CDATA[脑婆我喜欢你啊！]]></Content>\n" +
                    "</xml>";

        int authentication = new Random().nextInt(1000);
        String authenticationKey = redisUtil.buildKey(LOGIN_PREFIX, String.valueOf(authentication));
        redisUtil.setNx(authenticationKey, fromUserName, 5L, TimeUnit.MINUTES);
        String authContent = "您当前的验证码是：" + authentication + "，5分钟内有效！";
        return  "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + authContent + "]]></Content>\n" +
                "</xml>";
    }
}
