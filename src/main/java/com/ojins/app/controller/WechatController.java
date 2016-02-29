package com.ojins.app.controller;

import com.ojins.app.handler.BarcodeImageHandler;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.*;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by nwang on 23/01/16.
 */
@RestController
@RequestMapping("/")
public class WechatController extends BaseController{
    WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
    private WxMpService wxMpService = new WxMpServiceImpl();
    private WxMpMessageRouter wxMpMessageRouter;
    BarcodeImageHandler barcodeImageHandler;

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Value("${app.controller.appId}")
    private String appId;

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Value("${app.controller.secretKey}")
    private String secretKey;

    @PostConstruct
    public void init() throws Exception {
        config.setAppId(appId); // 设置微信公众号的appid
        config.setSecret(secretKey); // 设置微信公众号的app corpSecret
        config.setToken("ojinscom"); // 设置微信公众号的token
        config.setAesKey("aoGcSLZpgW12i46uP8AZ2w6z707nSYmg1YLUsxj2Ee2"); // 设置微信公众号的EncodingAESKey

        wxMpService.setWxMpConfigStorage(config);

        WxMpMessageHandler handler =
                (wxMpXmlMessage, map, wxMpService1, wxSessionManager) -> WxMpXmlOutMessage
                        .TEXT()
                        .content("测试加密消息")
                        .fromUser(wxMpXmlMessage.getToUserName())
                        .toUser(wxMpXmlMessage.getFromUserName())
                        .build();

        wxMpMessageRouter = new WxMpMessageRouter(wxMpService);

        barcodeImageHandler = new BarcodeImageHandler(wxMpService);

        wxMpMessageRouter
                .rule()
                .async(true)
                .msgType("image") // forward anykind of image to barcode handler
                .handler(barcodeImageHandler)
                .end();

        // load the database
        logger.info("initialized handler.");
    }

    @RequestMapping(method = RequestMethod.GET,
                    produces = "text/html;charset=utf-8")
    public ResponseEntity<String> getVerificationResponse(
            @RequestParam(defaultValue = "") String signature,
            @RequestParam(defaultValue = "") String nonce,
            @RequestParam(defaultValue = "") String timestamp,
            @RequestParam(defaultValue = "") String echostr) throws IOException {
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            logger.warn("消息签名不正确");
            return new ResponseEntity<>("非法请求", HttpStatus.OK);
        }

        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            logger.info("一个仅仅用来验证的请求");
            return new ResponseEntity<>(echostr, HttpStatus.OK);
        }

        logger.warn("不可识别的加密类型");
        return new ResponseEntity<>("不可识别的加密类型", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
                    produces = "text/html;charset=utf-8")
    public ResponseEntity<String> getResponse(@RequestParam(defaultValue = "") String signature,
                                              @RequestParam(defaultValue = "") String nonce,
                                              @RequestParam(defaultValue = "") String timestamp,
                                              @RequestParam(defaultValue = "") String echostr,
                                              @RequestParam(defaultValue = "raw") String encrypt_type,
                                              @RequestParam(defaultValue = "") String msg_signature,
                                              @RequestBody(required = false) String request) throws IOException {
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            logger.warn("消息签名不正确");
            return new ResponseEntity<>("非法请求", HttpStatus.OK);
        }

        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            logger.info("一个仅仅用来验证的请求");
            return new ResponseEntity<>(echostr, HttpStatus.OK);
        }

        if ("raw".equals(encrypt_type)) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(
                    new ByteArrayInputStream(request.getBytes(StandardCharsets.UTF_8)));
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            if (outMessage == null) {
                logger.warn("Async is true, thats why out message is always null!");
                return new ResponseEntity<>(HttpStatus.OK);
            }
            logger.info("明文传输的消息, content = {}",((WxMpXmlOutTextMessage) outMessage).getContent());
            return new ResponseEntity<>(outMessage.toXml(), HttpStatus.OK);
        }

        if ("aes".equals(encrypt_type)) {
            // 是aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage
                    .fromEncryptedXml(
                            new ByteArrayInputStream(request.getBytes(StandardCharsets.UTF_8)),
                            config, timestamp, nonce, msg_signature);
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            if (outMessage == null) {
                logger.warn("Async is true, thats why out message is always null!");
                return new ResponseEntity<>(HttpStatus.OK);
            }
            logger.info("aes加密的消息, content = {}",((WxMpXmlOutTextMessage) outMessage).getContent());
            return new ResponseEntity<>(outMessage.toXml(), HttpStatus.OK);
        }

        logger.warn("不可识别的加密类型");
        return new ResponseEntity<>("不可识别的加密类型", HttpStatus.OK);
    }
}
