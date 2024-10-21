package org.example.orders.global.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.example.orders.global.exception.InvalidCustomException;

import java.util.Arrays;

@Getter
public enum BoardStatus {
	READY("진행 전"),
	OPEN("진행 중"),
	DONE("진행 완료");

	private final String status;

	BoardStatus(String status) {
		this.status = status;
	}

	@JsonCreator
	public static BoardStatus from(String status) {
		return Arrays.stream(BoardStatus.values())
			.filter(bStatus -> bStatus.getBoardStatus().equals(status))
			.findFirst()
			.orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.PRODUCT_BOARD_LIST_FAIL));
	}

	@JsonValue
	public String getBoardStatus() {
		return status;
	}
}
