package com.diplo.mspago.model.deuda;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.mspago.valueobjects.Nit;
import org.junit.jupiter.api.Test;

class FacturaTest {

	@Test
	void Imprimir() {
		DetallePago detalleTest = new DetallePago("Detalle del pago");
		Monto totalTest = new Monto(10);
		Nit nitTest = new Nit(545454);

		Factura facturaTest = new Factura(detalleTest, totalTest, nitTest);

		String resultado = facturaTest.Imprimir();

		String esperado =
			"Imprimiendo Factura del pago por concepto de: " +
			detalleTest.getDetalle() +
			" con el monto: " +
			totalTest.getMonto() +
			" al nit: " +
			nitTest.getNroNit();

		assertEquals(detalleTest, facturaTest.getDetalle());
		assertEquals(totalTest, facturaTest.getTotal());
		assertEquals(nitTest, facturaTest.getNit());
		assertNotNull(facturaTest.getCodigoFactura());
		assertEquals(esperado, resultado);
	}
}
