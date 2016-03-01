package com.ojins.app.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by nwang on 24/01/16.
 */
public class BaseHandler {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static Executor executor = Executors.newSingleThreadExecutor();
}
