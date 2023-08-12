package com.funnycode.producer.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {
    @Bean
    public Queue pictureImageQueue() {
        return new Queue("q.picture.image");
    }

    @Bean
    public Queue pictureVectorQueue() {
        return new Queue("q.picture.vector");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("x.picture");
    }

    @Bean
    public Binding pictureImageBindingPng(Queue pictureImageQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(pictureImageQueue).to(directExchange).with("png");
    }

    @Bean
    public Binding pictureImageBindingJpg(Queue pictureImageQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(pictureImageQueue).to(directExchange).with("jpg");
    }

    @Bean
    public Binding pictureVectorBindingSvg(Queue pictureVectorQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(pictureVectorQueue).to(directExchange).with("svg");
    }
}
