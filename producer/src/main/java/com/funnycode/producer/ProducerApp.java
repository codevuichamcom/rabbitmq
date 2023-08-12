package com.funnycode.producer;

import com.funnycode.common.Employee;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProducerApp implements CommandLineRunner {
    EmployeeJsonProducer employeeJsonProducer;

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            Employee e = Employee.builder()
                    .employeeId("emp"+i)
                    .name("Employee"+i)
                    .birthDate(LocalDate.now())
                    .build();
            employeeJsonProducer.sendMessage(e);
        }
    }
}