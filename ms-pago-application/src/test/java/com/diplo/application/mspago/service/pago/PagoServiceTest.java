package com.diplo.application.mspago.service.pago;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;

class PagoServiceTest {

	@Test
	void testGenerarNroPagoAsync() {
		PagoService pagoService = new PagoService();
		Future<String> resultado = pagoService.GenerarNroPagoAsync();
		assertNotNull(resultado);
	}
}
