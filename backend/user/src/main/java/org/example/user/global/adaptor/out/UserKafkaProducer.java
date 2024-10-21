package org.example.user.global.adaptor.out;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserKafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendSignupMessage(Object signupData) {
        kafkaTemplate.send("user_signup_complete", signupData);
    }
}
