package com.funnycode.producer.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {
    @Bean
    public Queue pictureFilterQueue() {
        return new Queue("q.picture.filter");
    }

    @Bean
    public Queue pictureLogQueue() {
        return new Queue("q.picture.log");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("x.picture.topic");
    }

    @Bean
    public Binding pictureImageBindingTopicWithTypePng(Queue pictureImageQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(pictureImageQueue).to(topicExchange).with("*.*.png");
    }

    @Bean
    public Binding pictureImageBindingTopicWithTypeJpg(Queue pictureImageQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(pictureImageQueue).to(topicExchange).with("#.jpg");
    }

    @Bean
    public Binding pictureVectorBindingTopicWithTypeSvg(Queue pictureVectorQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(pictureVectorQueue).to(topicExchange).with("*.*.svg");
    }

    @Bean
    public Binding pictureFilterBindingTopicWithSourceMobile(Queue pictureFilterQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(pictureFilterQueue).to(topicExchange).with("mobile.#");
    }

    @Bean
    public Binding pictureLogBindingTopicWithSizeLog(Queue pictureLogQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(pictureLogQueue).to(topicExchange).with("*.large.svg");
    }
}
