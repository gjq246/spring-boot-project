package com.kpttech.web;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {
	
	private static final Logger logger = Logger.getLogger(RabbitMQReceiver.class);

	private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
