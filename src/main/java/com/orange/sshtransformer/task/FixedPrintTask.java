package com.orange.sshtransformer.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

public class FixedPrintTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private int i;

    @Scheduled(cron = "*/15 * * * * ?")
    public void execute() {
        logger.info("thread id:{},FixedPrintTask execute times:{}", Thread.currentThread().getId(), ++i);
    }

}
