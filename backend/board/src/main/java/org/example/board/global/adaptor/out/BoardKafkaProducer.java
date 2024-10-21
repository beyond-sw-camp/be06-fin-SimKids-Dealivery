package org.example.board.global.adaptor.out;

import lombok.RequiredArgsConstructor;
import org.example.board.domain.board.model.event.ProductBoardEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardKafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendBoardRegisterCompleteEvent(ProductBoardEvent.BoardRegisterCompleteEvent boardRegisterCompleteEvent) {
        kafkaTemplate.send("board-register", boardRegisterCompleteEvent);
    }
}

