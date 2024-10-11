package com.example.quequeflow.domain.queue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QueueDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QueueDeleteRequest {
        Long boardIdx;
        Long userIdx;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QueueRankResponse {
        Long rank;
    }
}
