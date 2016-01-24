package com.ojins.app.handler;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.multi.MultipleBarcodeReader;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Created by nwang on 24/01/16.
 */
public class BarcodeImageHandler extends BaseHandler implements WxMpMessageHandler{

    private static final Map<DecodeHintType,Object> HINTS;
    private static final Map<DecodeHintType,Object> HINTS_PURE;

    static {
        HINTS = new EnumMap<>(DecodeHintType.class);
        HINTS.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        HINTS.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));
        HINTS_PURE = new EnumMap<>(HINTS);
        HINTS_PURE.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
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
            return WxMpXmlOutMessage
                    .TEXT()
                    .content(results.stream()
                                    .map(Result::getText)
                                    .collect(Collectors.joining(", ")))
                    .fromUser(wxMpXmlMessage.getToUserName()) // now server is "from", client is "to"
                    .toUser(wxMpXmlMessage.getFromUserName())
                    .build();
        } else {
            return WxMpXmlOutMessage
                    .TEXT()
                    .content("Something wrong!")
                    .fromUser(wxMpXmlMessage.getToUserName()) // now server is "from", client is "to"
                    .toUser(wxMpXmlMessage.getFromUserName())
                    .build();
        }
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
