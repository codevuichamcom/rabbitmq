package producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FixedRateProducer {
    private final RabbitTemplate rabbitTemplate;

    private int increase = 0;

    @Scheduled(fixedRate = 500) // Schedule to run every 500 milliseconds
    public void sendMessage() {
        increase++;
        log.info("Sending message: {}", increase);
        rabbitTemplate.convertAndSend("course.fixedrate", "Message: " + increase);
    }
}
