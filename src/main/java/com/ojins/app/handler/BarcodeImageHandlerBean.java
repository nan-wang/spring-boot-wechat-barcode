package com.ojins.app.handler;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.ojins.app.model.Product;
import com.ojins.app.service.ProductService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Created by nwang on 24/01/16.
 */
@Component
public class BarcodeImageHandlerBean extends BaseHandler implements WxMpMessageHandler{

    private static final Map<DecodeHintType,Object> HINTS;
    private static final Map<DecodeHintType,Object> HINTS_PURE;

    private WxMpService wxMpService;
    private ProductService productService;

    static {
        HINTS = new EnumMap<>(DecodeHintType.class);
        HINTS.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        HINTS.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));
        HINTS_PURE = new EnumMap<>(HINTS);
        HINTS_PURE.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
    }

    public BarcodeImageHandlerBean() {}

    public BarcodeImageHandlerBean(WxMpService wxMpService, ProductService productService) {
        this.wxMpService = wxMpService;
        this.productService = productService;
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map, WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {

        long startTime = System.currentTimeMillis();

        logger.info("Media Id: {}", wxMpXmlMessage.getMediaId());
        logger.info("Media URL: {}", wxMpXmlMessage.getPicUrl());
        logger.info("Downloading media from server...");
        // FIXME: The out message is already returned before the downloading complete.
        File file = wxMpService.mediaDownload(wxMpXmlMessage.getMediaId());
        logger.info("Done!");

        Collection<Result> results = null;
        // load file into in-memory-image
        try {
            BufferedImage img = ImageIO.read(file);
            logger.info("Recognizing barcode...");
            results = processImage(img);
            logger.info("Done!");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        long estimatedTime = System.currentTimeMillis() - startTime;

        logger.info("Server takes {} ms", estimatedTime);

        if (results != null) {

            String resultMsg = results.stream()
                    .map(Result::getText)
                    .collect(Collectors.joining(", "));

            Product product = productService.findProductByBarcode(resultMsg);

            if (product == null) {
                logger.warn("Barcode {} is not found in database.", resultMsg);
                return WxMpXmlOutMessage
                        .TEXT()
                        .content("抱歉没有找到该产品")
                        .fromUser(wxMpXmlMessage.getToUserName()) // now server is "from", client is "to"
                        .toUser(wxMpXmlMessage.getFromUserName())
                        .build();
            }
            sendAdvanceMessage(wxMpXmlMessage.getFromUserName(),
                    wxMpXmlMessage.getPicUrl(),
                    product);

            // below is for syncronized version (under 5s, depreciated)
            return WxMpXmlOutMessage
                    .TEXT()
                    .content(resultMsg)
                    .fromUser(wxMpXmlMessage.getToUserName()) // now server is "from", client is "to"
                    .toUser(wxMpXmlMessage.getFromUserName())
                    .build();
        } else {

            String resultMsg = "No barcode in your photo!";

            wxMpService.customMessageSend(WxMpCustomMessage
                    .TEXT()
                    .toUser(wxMpXmlMessage.getFromUserName())
                    .content(resultMsg)
                    .build());


            return WxMpXmlOutMessage
                    .TEXT()
                    .content(resultMsg)
                    .fromUser(wxMpXmlMessage.getToUserName()) // now server is "from", client is "to"
                    .toUser(wxMpXmlMessage.getFromUserName())
                    .build();
        }
    }


    private void sendCustomMessage(String toUser, String barCode) throws WxErrorException {

        wxMpService.customMessageSend(WxMpCustomMessage
                .TEXT()
                .toUser(toUser)
                .content(barCode)
                .build());
    }


    private void sendAdvanceMessage(String toUser, String largePicUrl, Product product) throws WxErrorException {

        WxMpCustomMessage.WxArticle article1 = new WxMpCustomMessage.WxArticle();
        article1.setUrl(product.getLink());
        article1.setPicUrl(product.getImg());
        article1.setTitle(product.getBrand() + " " + product.getName());

        WxMpCustomMessage.WxArticle article2 = new WxMpCustomMessage.WxArticle();
        article2.setUrl(product.getLink());
        article2.setPicUrl("https://static-s.aa-cdn.net/img/ios/883163015/154896985ec342eebff742a2cbca02d0?v=1");
        article2.setTitle(product.getDescription_cn());

        WxMpCustomMessage.WxArticle article3 = new WxMpCustomMessage.WxArticle();
        article3.setUrl(product.getLink());
        article3.setPicUrl("https://static-s.aa-cdn.net/img/ios/883163015/154896985ec342eebff742a2cbca02d0?v=1");
        article3.setTitle("价格: "+ product.getPrice());

        wxMpService.customMessageSend(WxMpCustomMessage.NEWS()
                .toUser(toUser)
                .addArticle(article1)
                .addArticle(article2)
                .addArticle(article3)
                .build());
    }


    private void sendAdvanceMessage(String toUser, String largePicUrl, String barCode) throws WxErrorException {

        WxMpCustomMessage.WxArticle article1 = new WxMpCustomMessage.WxArticle();
        article1.setUrl("http://ojins.com");
        article1.setPicUrl(largePicUrl);
        article1.setTitle("条形码:"+ barCode + "上面应该放开箱渲染图");

        WxMpCustomMessage.WxArticle article2 = new WxMpCustomMessage.WxArticle();
        article2.setUrl("http://ojins.com");
        article2.setPicUrl("http://lorempixel.com/200/200/cats/");
        article2.setTitle("两行字的高度正好对齐200px");

        wxMpService.customMessageSend(WxMpCustomMessage.NEWS()
                .toUser(toUser)
                .addArticle(article1)
                .addArticle(article2)
                .build());
    }

    private Collection<Result> processImage(BufferedImage image) {

        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
        Collection<Result> results = new ArrayList<>(1);

        try {
            Reader reader = new MultiFormatReader();
            ReaderException savedException = null;
            try {
                // Look for multiple barcodes
                MultipleBarcodeReader multiReader = new GenericMultipleBarcodeReader(reader);
                Result[] theResults = multiReader.decodeMultiple(bitmap, HINTS);
                if (theResults != null) {
                    results.addAll(Arrays.asList(theResults));
                }
            } catch (ReaderException re) {
                savedException = re;
            }

            if (results.isEmpty()) {
                try {
                    // Look for pure barcode
                    Result theResult = reader.decode(bitmap, HINTS_PURE);
                    if (theResult != null) {
                        results.add(theResult);
                    }
                } catch (ReaderException re) {
                    savedException = re;
                }
            }

            if (results.isEmpty()) {
                try {
                    // Look for normal barcode in photo
                    Result theResult = reader.decode(bitmap, HINTS);
                    if (theResult != null) {
                        results.add(theResult);
                    }
                } catch (ReaderException re) {
                    savedException = re;
                }
            }

            if (results.isEmpty()) {
                try {
                    // Try again with other binarizer
                    BinaryBitmap hybridBitmap = new BinaryBitmap(new HybridBinarizer(source));
                    Result theResult = reader.decode(hybridBitmap, HINTS);
                    if (theResult != null) {
                        results.add(theResult);
                    }
                } catch (ReaderException re) {
                    savedException = re;
                }
            }

            if (results.isEmpty()) {
                try {
                    throw savedException == null ? NotFoundException.getNotFoundInstance() : savedException;
                } catch (FormatException | ChecksumException e) {
                    logger.error(e.getMessage());
                } catch (ReaderException e) {
                    logger.error(e.getMessage());
                }
                return null;
            }

        } catch (RuntimeException re) {
            // Call out unexpected errors in the log clearly
            logger.error("Unexpected exception from library: {}", re);

        }

        return results;
    }
}
