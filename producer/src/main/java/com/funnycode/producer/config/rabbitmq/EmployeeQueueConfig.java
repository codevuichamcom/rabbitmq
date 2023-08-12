package com.funnycode.producer.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeQueueConfig {
    @Bean
    public Queue employeeQueue() {
        return new Queue("course.employee");
    }
}
