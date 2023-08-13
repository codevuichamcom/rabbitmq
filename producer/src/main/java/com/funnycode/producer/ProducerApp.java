package com.funnycode.producer;

import com.funnycode.common.Employee;
import com.funnycode.common.Picture;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProducerApp implements CommandLineRunner {
    EmployeeJsonProducer employeeJsonProducer;
    HumanResourceProducer humanResourceProducer;
    PictureProducer pictureProducer;
    PictureTopicProducer pictureTopicProducer;
    MyPictureProducer myPictureProducer;

    private final List<String> SOURCES = List.of("mobile", "web");

    private final List<String> TYPES = List.of("jpg", "png", "svg");

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }

    @Override
    public void run(String... args) {
        //Send to employee queue
        for (int i = 0; i < 5; i++) {
            Employee e = Employee.builder()
                    .employeeId("emp" + i)
                    .name("Employee" + i)
                    .birthDate(LocalDate.now())
                    .build();
            employeeJsonProducer.sendMessage(e);
        }

        //Send to x.hr fanout exchange
        for (int i = 0; i < 5; i++) {
            Employee e = Employee.builder()
                    .employeeId("emp" + i)
                    .name("Employee" + i)
                    .birthDate(LocalDate.now())
                    .build();
            humanResourceProducer.sendMessage(e);
        }

        //Send to x.picture direct exchange
        for (int i = 0; i < 10; i++) {
            Picture picture = Picture.builder()
                    .name("Picture " + i)
                    .size(ThreadLocalRandom.current().nextLong(1, 10001))
                    .source(SOURCES.get(i % SOURCES.size()))
                    .type(TYPES.get(i % TYPES.size()))
                    .build();
            pictureProducer.sendMessage(picture);
        }

        //Send to x.picture.topic topic exchange
        for (int i = 0; i < 20; i++) {
            Picture picture = Picture.builder()
                    .name("Picture " + i)
                    .size(ThreadLocalRandom.current().nextLong(1, 10001))
                    .source(SOURCES.get(i % SOURCES.size()))
                    .type(TYPES.get(i % TYPES.size()))
                    .build();
            pictureTopicProducer.sendMessage(picture);
        }

        //Send to x.my.picture fanout exchange
        //If Consumer has error then route to x.my.picture.dlx dead letter exchange
        for (int i = 0; i < 15; i++) {
            Picture picture = Picture.builder()
                    .name("Picture " + i)
                    .size(ThreadLocalRandom.current().nextLong(9001, 10001))
                    .source(SOURCES.get(i % SOURCES.size()))
                    .type(TYPES.get(i % TYPES.size()))
                    .build();
            myPictureProducer.sendMessage(picture);
        }
    }
}