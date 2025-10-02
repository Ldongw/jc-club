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

    private static final String LUCK = "luck";


    private final String[] sentences = {
            "今天的你好像需要一个温暖的抱抱呢...加油加油，一切都会好起来的！",
            "今天的你像被乌云遮住的小星星...但是闪闪发光的你一定会被发现的！",
            "呜哇...今天的你有点小沮丧呢，不过没关系，明天会更好的！",
            "哎呀呀~今天要小心一点哦，不要像我一样总是摔倒呢~",
            "今天的你稍微有点小迷糊呢...不过可爱度满分！💕",
            "嗯哼~今天要加油哦！相信自己是最棒的！✨",
            "不错不错~今天也是元气满满的一天呢！💪",
            "今天的你闪闪发光呢！🌟 像小太阳一样温暖 everyone 吧！",
            "哇~今天简直是主角光环附体呢！✨ 说不定会遇到命中注定的人哦~",
            "今天的你是被神明眷顾的幸运儿呢！✨ 做什么都顺利，连空气都为你让路哦～",
            "天哪！这是什么！你今天的运势是满分！相信自己去做自己想做的事情吧！"
    };

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

        if (!content.matches(".*" + KEY_WORD + ".*")) {
            if(content.matches(".*运势.*")) {
                String context = null;
                if (redisUtil.get(LUCK + fromUserName) != null) {
                    String score = redisUtil.get(LUCK + fromUserName);
                    context = buildTextResponse(fromUserName, toUserName, "你今天已经测过啦！\n你今天的运势分数是：" + score + "\n" + sentences[Integer.parseInt(score) / 10]);
                } else {
                    int score = new Random().nextInt(101);
                    redisUtil.setNx(LUCK + fromUserName, String.valueOf(score), 1L, TimeUnit.DAYS);
                    context = buildTextResponse(fromUserName, toUserName, "你今天的运势分数是：" + score + "\n" + sentences[score / 10]);
                }
                return context;
            }
            return buildTextResponse(fromUserName, toUserName, "脑婆我喜欢你啊！");
            /*"<xml>\n" +
                    "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                    "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                    "  <CreateTime>12345678</CreateTime>\n" +
                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                    "  <Content><![CDATA[脑婆我喜欢你啊！]]></Content>\n" +
                    "</xml>";*/
        }

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


    private String buildTextResponse(String fromUserName, String toUserName, String content) {
        return "<xml>\n" +
                "  <ToUserName><![CDATA[" + toUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + content + "]]></Content>\n" +
                "</xml>";
    }

}
