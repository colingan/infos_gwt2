package com.github.colingan.util;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DevLog {

    private final Logger logger;
    private final AtomicLong seq = new AtomicLong();

    public DevLog(String name) {
        logger = LoggerFactory.getLogger(name);
    }

    public void warn(long startTime, String msg) {
        if (logger.isWarnEnabled()) {
            logger.warn("thread=" + Thread.currentThread().getId() + ", seq=" + seq.getAndIncrement() + ", time=("
                    + startTime + "," + (System.currentTimeMillis() - startTime) + "), msg=" + msg);
        }
    }

    public void error(long startTime, String msg) {
        if (logger.isErrorEnabled()) {
            logger.error("thread=" + Thread.currentThread().getId() + ", seq=" + seq.getAndIncrement() + ", time=("
                    + startTime + "," + (System.currentTimeMillis() - startTime) + "), msg=" + msg);
        }
    }

    public void error(long startTime, String msg, Throwable e) {
        if (logger.isErrorEnabled()) {
            logger.error("thread=" + Thread.currentThread().getId() + ", seq=" + seq.getAndIncrement() + ", time=("
                    + startTime + "," + (System.currentTimeMillis() - startTime) + "), msg=" + msg, e);
        }
    }

    public void info(long startTime, String msg) {
        if (logger.isInfoEnabled()) {
            logger.info("thread=" + Thread.currentThread().getId() + ", seq=" + seq.getAndIncrement() + ", time=("
                    + startTime + "," + (System.currentTimeMillis() - startTime) + "), msg=" + msg);
        }
    }

    public void info(String msg) {
        if (logger.isInfoEnabled()) {
            logger.info("thread=" + Thread.currentThread().getId() + ", seq=" + seq.getAndIncrement() + ", msg=" + msg);
        }
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

}
