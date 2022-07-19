package com.diplo.mspago.factory.pago;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.model.deuda.Pago;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class PagoFactoryTest {

	@Test
	void testCreate() {
		String pagoID = UUID.randomUUID().toString();
		double totalPagado = 10;
		String detalle = "detalle del pago";
		String deudaId = UUID.randomUUID().toString();
		PagoFactory pagoFactory = new PagoFactory();
		Pago resultado = pagoFactory.Create(
			pagoID,
			totalPagado,
			detalle,
			deudaId
		);

		assertEquals(pagoID, resultado.getId().toString());
		assertEquals(totalPagado, resultado.getMontoPagado().getMonto());
		assertEquals(detalle, resultado.getRecibo().getDetalle().getDetalle());
		assertEquals(deudaId, resultado.getDeudaId().toString());
	}
}
