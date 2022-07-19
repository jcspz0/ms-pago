package com.diplo.mspago.factory.deuda;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.model.deuda.Deuda;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class DeudaFactoryTest {

	@Test
	void testCreate() {
		String deudaID = UUID.randomUUID().toString();
		String reservaId = UUID.randomUUID().toString();
		double total = 100;
		DeudaFactory deudaFactory = new DeudaFactory();
		Deuda resultado = deudaFactory.Create(deudaID, reservaId, total);

		assertEquals(deudaID, resultado.getId().toString());
		assertEquals(reservaId, resultado.getReservaId().toString());
		assertEquals(total, resultado.getTotal().getMonto());
	}
}
