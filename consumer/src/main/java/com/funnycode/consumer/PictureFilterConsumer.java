package com.funnycode.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnycode.common.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class PictureFilterConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "q.picture.filter")
    public void listen(String message) {
        Picture picture = null;

        try {
            picture = objectMapper.readValue(message, Picture.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("[Picture filter consumer] - Picture is {}", picture);
    }
}
