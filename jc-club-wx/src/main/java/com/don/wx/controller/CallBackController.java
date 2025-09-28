package com.don.wx.controller;

import com.don.wx.handler.WxChatMsgFactory;
import com.don.wx.handler.WxChatMsgHandler;
import com.don.wx.utils.MessageUtil;
import com.don.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

/**
 * @author A
 * @date 2025/9/25
 **/
@RestController
@Slf4j
public class CallBackController {

    @Autowired
    private WxChatMsgFactory wxChatMsgFactory;

    private static final String token = "dsailklksad";

    @RequestMapping("/test")
    public String test(){
        return "hello world!";
    }


    /**
     * 回调消息校验
     * @return
     */

    @GetMapping("/callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr){
        log.info("get 验签请求参数: signature:{}, timestamp:{}, nonce:{}, echostr:{}",
                signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        if(signature.equals(shaStr))
            return echostr;
        return "蔡家树";
    }

    @PostMapping(value = "/callback", produces = "application/xml;charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature",required = false) String msgSignature
    ){
        log.info("接收到微信请求: requestBody:{}, signature:{}, timestamp:{}, nonce:{}",
                requestBody, signature, timestamp, nonce);
        Map<String, String> msgMap = MessageUtil.parseXml(requestBody);
        String msgType = msgMap.get("MsgType");
        String event = msgMap.get("Event");
        event = (event == null ? "" : "." + event);
        log.info("msgType:{}, event:{}", msgType, event);

        String msgTypeKey = msgType + event;
        WxChatMsgHandler wxChatMsgHandler = wxChatMsgFactory.getHandlerByMsgType(msgTypeKey);

        if(Objects.isNull(wxChatMsgHandler))
            return "unknown";

        String replyContent = wxChatMsgHandler.dealMsg(msgMap);
        log.info("replyContent:{}", replyContent);

        return replyContent;
    }

    @GetMapping("fen")
    public String fen(){
        log.info("正在访问 fen");
        return "<div style='text-align: center;'>美神降临<br/>\n" +
                "<img src='/beauty.jpeg' style='max-width: 50%; height: auto;'/></div>";
    }
}
//String msg = "<xml>\n" +
//        "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
//        "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
//        "  <CreateTime>12345678</CreateTime>\n" +
//        "  <MsgType><![CDATA[text]]></MsgType>\n" +
//        "  <Content><![CDATA[你好]]></Content>\n" +
//        "</xml>";