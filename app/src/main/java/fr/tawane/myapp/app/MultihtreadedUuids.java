package fr.tawane.myapp.app;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultihtreadedUuids {

	SecureRandom nonBlockingSecureRandom;

	public MultihtreadedUuids() {
		try {
			nonBlockingSecureRandom = SecureRandom.getInstance("NativePRNGNonBlocking");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public List<UUID> fromRandom() {
		return IntStream.rangeClosed(0, 1000000).parallel().mapToObj(e -> UUID.randomUUID()).collect(Collectors.toList());
	}

	public List<UUID> fromUrandom() {
		return IntStream.rangeClosed(0, 1000000).parallel().mapToObj(e -> generateWithUrandom()).collect(Collectors.toList());
	}

	public UUID generateWithUrandom() {
		byte[] randomBytes = new byte[16];
		nonBlockingSecureRandom.nextBytes(randomBytes);
		randomBytes[6] &= 0x0f;  /* clear version        */
		randomBytes[6] |= 0x40;  /* set to version 4     */
		randomBytes[8] &= 0x3f;  /* clear variant        */
		randomBytes[8] |= 0x80;  /* set to IETF variant  */
		long msb = 0;
		long lsb = 0;
		for (int i = 0; i < 8; i++)
			msb = (msb << 8) | (randomBytes[i] & 0xff);
		for (int i = 8; i < 16; i++)
			lsb = (lsb << 8) | (randomBytes[i] & 0xff);
		return new UUID(msb, lsb);
	}
}
