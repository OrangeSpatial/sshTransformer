package com.orange.sshtransformer.service.impl;

import com.orange.sshtransformer.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncServiceImpl implements AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Override
    public void executeAsync() {
        logger.info("start executeAsync");
        try {
            System.out.println("当前运行的线程名称：" + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("end executeAsync");
    }
}
