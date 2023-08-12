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
public class PictureTopicProducer {
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();
    public void sendMessage(Picture picture) {
        try {
            StringBuilder routingKey = new StringBuilder();

            routingKey.append(picture.getSource());
            routingKey.append(".");

            if (picture.getSize() > 4000) {
                routingKey.append("large");
            } else {
                routingKey.append("small");
            }

            routingKey.append(".");
            routingKey.append(picture.getType());

            String json = objectMapper.writeValueAsString(picture);
            rabbitTemplate.convertAndSend("x.picture.topic", routingKey.toString(), json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
