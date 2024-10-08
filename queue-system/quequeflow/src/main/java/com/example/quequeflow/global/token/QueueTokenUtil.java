package com.example.quequeflow.global.token;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class QueueTokenUtil {

	public Boolean isAllowedByToken(final Long boardIdx, final Long userIdx, final String token) {
		String generatedToken = this.generateToken(boardIdx, userIdx);
		return generatedToken.equalsIgnoreCase(token);
	}

	public String generateToken(final Long boardIdx, final Long userId) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			var input = "user-queue-%d-%d".formatted(boardIdx, userId);
			byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

			StringBuilder hexString = new StringBuilder();
			for (byte aByte : encodedHash) {
				hexString.append(String.format("%02x", aByte));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
