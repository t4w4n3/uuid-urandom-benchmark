package fr.tawane.myapp.app;

import org.junit.jupiter.api.Test;

class MultihtreadedUuidsTest {

	@Test
	void fromUrandom() {
		new MultihtreadedUuids().fromUrandom();
	}
}