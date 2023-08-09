package com.funnycode.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class FixedRateConsumer {

    @RabbitListener(queues = "course.fixedrate", concurrency = "3")
    public void receiveMessage(String message) {

        log.info("Received message: {} on thread : {}", message, Thread.currentThread().getName());
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
