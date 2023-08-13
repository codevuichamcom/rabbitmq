package com.funnycode.producer.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainExchangeAndDeadLetterExchangeConfig {
    @Bean
    public Queue myPictureQueue() {
        return QueueBuilder.durable("q.my.picture")
                .deadLetterExchange("x.my.picture.dlx")
                .ttl(6000)
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue("q.my.picture.dlq");
    }

    @Bean
    public FanoutExchange myPictureFanoutExchange() {
        return new FanoutExchange("x.my.picture");
    }

    @Bean
    public FanoutExchange deadLetterFanoutExchange() {
        return new FanoutExchange("x.my.picture.dlx");
    }

    @Bean
    public Binding myPictureBinding(Queue myPictureQueue, FanoutExchange myPictureFanoutExchange) {
        return BindingBuilder.bind(myPictureQueue).to(myPictureFanoutExchange);
    }

    @Bean
    public Binding deadLetterBinding(Queue deadLetterQueue, FanoutExchange deadLetterFanoutExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterFanoutExchange);
    }
}
