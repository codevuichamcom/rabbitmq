package com.funnycode.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnycode.common.Picture;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class MyPictureConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "q.my.picture")
    public void listen(Message message, Channel channel) throws IOException {
        Picture picture = objectMapper.readValue(message.getBody(), Picture.class);
        if (picture.getSize() > 9000) {
//            throw new AmqpRejectAndDontRequeueException("Sending to dead letter queue");
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }

        log.info("[My picture image consumer] - Picture is {}", picture);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
