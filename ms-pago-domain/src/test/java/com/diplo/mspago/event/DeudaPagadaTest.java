package com.diplo.mspago.event;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class DeudaPagadaTest {

	@Test
	void testDeudaPaga() {
		UUID ReservaId = UUID.randomUUID();
		DeudaPagada test = new DeudaPagada(ReservaId);
		assertEquals(ReservaId, test.getReservaId());
	}
}
