package com.funnycode.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnycode.common.Employee;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmployeeJsonProducer {
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();
    public void sendMessage(Employee emp) {
        try {
            var json = objectMapper.writeValueAsString(emp);
            rabbitTemplate.convertAndSend("course.employee", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
