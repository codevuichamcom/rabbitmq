package com.funnycode.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnycode.common.Picture;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MyPictureProducer {
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();
    public void sendMessage(Picture picture) {
        try {
            var json = objectMapper.writeValueAsString(picture);
            rabbitTemplate.convertAndSend("x.my.picture","", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
