package com.diplo.mspago.event;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharedkernel.core.Constant;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class DeudaCreadaTest {

	@Test
	void testDeudaCreada() {
		UUID DeudaId = UUID.randomUUID();
		String Estado = Constant.DEUDAESTADOINICIADA;
		UUID ReservaId = UUID.randomUUID();
		Monto Total = new Monto(10);
		DeudaCreada test = new DeudaCreada(DeudaId, ReservaId, Total, Estado);
		assertEquals(DeudaId, test.getDeudaId());
		assertEquals(Estado, test.getEstado());
		assertEquals(Total, test.getTotal());
		assertEquals(ReservaId, test.getReservaId());
	}
}
