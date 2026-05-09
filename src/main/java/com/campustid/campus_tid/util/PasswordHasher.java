package com.campustid.campus_tid.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public final class PasswordHasher {

	private static final SecureRandom RNG = new SecureRandom();
	private static final Base64.Encoder B64 = Base64.getUrlEncoder().withoutPadding();
	private static final Base64.Decoder B64D = Base64.getUrlDecoder();

	private PasswordHasher() {}

	public static String hash(String rawPassword) {
		var salt = new byte[16];
		RNG.nextBytes(salt);
		var digest = sha256(concat(salt, rawPassword.getBytes(StandardCharsets.UTF_8)));
		return "v1$" + B64.encodeToString(salt) + "$" + B64.encodeToString(digest);
	}

	public static boolean matches(String rawPassword, String stored) {
		if (stored == null || stored.isBlank()) {
			return false;
		}
		var parts = stored.split("\\$");
		if (parts.length != 3) {
			return false;
		}
		if (!"v1".equals(parts[0])) {
			return false;
		}
		var salt = B64D.decode(parts[1]);
		var expected = B64D.decode(parts[2]);
		var actual = sha256(concat(salt, rawPassword.getBytes(StandardCharsets.UTF_8)));
		return MessageDigest.isEqual(expected, actual);
	}

	private static byte[] sha256(byte[] input) {
		try {
			return MessageDigest.getInstance("SHA-256").digest(input);
		} catch (Exception e) {
			throw new IllegalStateException("SHA-256 not available", e);
		}
	}

	private static byte[] concat(byte[] a, byte[] b) {
		var out = new byte[a.length + b.length];
		System.arraycopy(a, 0, out, 0, a.length);
		System.arraycopy(b, 0, out, a.length, b.length);
		return out;
	}
}
