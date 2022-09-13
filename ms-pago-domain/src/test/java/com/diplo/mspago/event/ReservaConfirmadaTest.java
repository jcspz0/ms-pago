package com.diplo.mspago.event;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class ReservaConfirmadaTest {

	@Test
	void testReservaConfirmada() {
		UUID ReservaId = UUID.randomUUID();
		ReservaConfirmada test = new ReservaConfirmada(ReservaId);
		assertEquals(ReservaId, test.getReservaId());
	}
}
