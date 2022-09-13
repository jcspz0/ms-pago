package com.diplo.mspago.event;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class DeudaVencidaTest {

	@Test
	void testDeudaVencida() {
		UUID ReservaId = UUID.randomUUID();
		DeudaVencida test = new DeudaVencida(ReservaId);
		assertEquals(ReservaId, test.getReservaId());
	}
}
