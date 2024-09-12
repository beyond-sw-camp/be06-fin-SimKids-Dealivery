package org.example.backend.global.common.constants;

import lombok.Getter;

@Getter
public enum BoardStatus {
	READY("진행 전"),
	OPEN("진행 중"),
	DONE("진행 완료");

	private final String status;

	BoardStatus(String status) {
		this.status = status;
	}
}
