package com.funnycode.producer.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProducerController {
    RabbitTemplate rabbitTemplate;

    @PostMapping("/api/publish/{exchange}/{routingKey}")
    public ResponseEntity<String> publishMessage(@PathVariable String exchange,
                                            @PathVariable(required = false) String routingKey,
                                            @RequestBody String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);

        return ResponseEntity.ok("Publish message is successfully");
    }
}
