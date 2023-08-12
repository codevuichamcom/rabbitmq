package com.funnycode.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue fixedRateQueue() {
        return new Queue("course.fixedrate");
    }

    @Bean
    public Queue employeeQueue() {
        return new Queue("course.employee");
    }

    @Bean
    public Queue accountingQueue() {
        return new Queue("q.hr.accounting");
    }

    @Bean
    public Queue marketingQueue() {
        return new Queue("q.hr.marketing");
    }

    @Bean
    public Queue pictureImageQueue() {
        return new Queue("q.picture.image");
    }

    @Bean
    public Queue pictureVectorQueue() {
        return new Queue("q.picture.vector");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("x.hr");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("x.picture");
    }

    @Bean
    public Binding accountingBinding(Queue accountingQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(accountingQueue).to(fanoutExchange);
    }

    @Bean
    public Binding marketingBinding(Queue marketingQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(marketingQueue).to(fanoutExchange);
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
