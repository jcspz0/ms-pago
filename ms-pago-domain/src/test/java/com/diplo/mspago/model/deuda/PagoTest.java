package com.diplo.mspago.model.deuda;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class PagoTest {

	Monto montoPagadoTest;
	DetallePago detalleTest;
	UUID deudaIdTest;

	Pago pagoTest;

	@Test
	void ImprimirRecibo() {
		montoPagadoTest = new Monto(10);
		detalleTest = new DetallePago("Detalle del pago");
		deudaIdTest = UUID.randomUUID();
		pagoTest = new Pago(montoPagadoTest, detalleTest, deudaIdTest);

		String esperado =
			"Imprimiendo recibo del pago por concepto de: " +
			pagoTest.getRecibo().getDetalle().getDetalle() +
			" con el monto: " +
			pagoTest.getMontoPagado().getMonto();

		String resultado = pagoTest.ImprimirRecibo();
		assertNotNull(pagoTest.getId());
		assertEquals(esperado, resultado);
	}
}
