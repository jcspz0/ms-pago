package com.diplo.mspago.model.deuda;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReciboTest {

	Recibo reciboTest;
	DetallePago detalleTest;
	Monto totalTest;

	@BeforeEach
	void init() {
		detalleTest = new DetallePago("detalle del pago");
		totalTest = new Monto(100);

		reciboTest = new Recibo(detalleTest, totalTest);
	}

	@Test
	void testImprimir() {
		String esperado =
			"Imprimiendo recibo del pago por concepto de: " +
			detalleTest.getDetalle() +
			" con el monto: " +
			totalTest.getMonto();

		String resultado = reciboTest.Imprimir();
		assertEquals(totalTest, reciboTest.getTotal());
		assertEquals(esperado, resultado);
	}
}
