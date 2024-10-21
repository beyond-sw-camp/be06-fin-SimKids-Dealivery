package org.example.auth.global.adaptor.out;

import lombok.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthKafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;


}
