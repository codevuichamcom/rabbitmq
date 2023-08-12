package com.funnycode.producer.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutExchangeConfig {
    @Bean
    public Queue accountingQueue() {
        return new Queue("q.hr.accounting");
    }

    @Bean
    public Queue marketingQueue() {
        return new Queue("q.hr.marketing");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("x.hr");
    }

    @Bean
    public Binding accountingBinding(Queue accountingQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(accountingQueue).to(fanoutExchange);
    }

    @Bean
    public Binding marketingBinding(Queue marketingQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(marketingQueue).to(fanoutExchange);
    }
}
