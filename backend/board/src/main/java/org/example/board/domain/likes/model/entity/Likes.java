package org.example.board.domain.likes.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.board.domain.board.model.entity.ProductBoard;
import org.example.board.domain.user.model.entity.User;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="user_idx")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="product_board_idx")
	private ProductBoard productBoard;
}
