package com.example.quequeflow.domain.waitingRoom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class WaitingRoomDto {
	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class WaitingRoomResponse {
		Long rank;
	}
}
