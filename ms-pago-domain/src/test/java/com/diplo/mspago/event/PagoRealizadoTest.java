package com.diplo.mspago.event;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharedkernel.core.Constant;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class PagoRealizadoTest {

	@Test
	void testPagoRealizadoEvent() {
		UUID DeudaId = UUID.randomUUID();
		UUID ReservaId = UUID.randomUUID();
		Monto Total = new Monto(10);
		Pago Pago = new Pago(Total, new DetallePago("detalle test"), DeudaId);
		String estado = Constant.DEUDAESTADOINICIADA;
		PagoRealizado pagoTest = new PagoRealizado(
			DeudaId,
			ReservaId,
			Total,
			Pago,
			estado
		);
		assertEquals(DeudaId, pagoTest.getDeudaId());
		assertEquals(ReservaId, pagoTest.getReservaId());
		assertEquals(Total, pagoTest.getTotal());
		assertEquals(Pago, pagoTest.getPago());
		assertEquals(estado, pagoTest.getEstado());
	}
}
