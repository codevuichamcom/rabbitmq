package com.funnycode.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnycode.common.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class MarketingConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "q.hr.marketing")
    public void listen(String message) {
        Employee emp = null;

        try {
            emp = objectMapper.readValue(message, Employee.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("[Marketing consumer] - Employee is {}", emp);
    }
}
