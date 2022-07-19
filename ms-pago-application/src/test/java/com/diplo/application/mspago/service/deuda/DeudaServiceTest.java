package com.diplo.application.mspago.service.deuda;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;

class DeudaServiceTest {

	@Test
	void testGenerarNroDeudaAsync() throws Exception {
		DeudaService deudaService = new DeudaService();
		Future<String> resultado = deudaService.GenerarNroDeudaAsync();
		assertNotNull(resultado.get());
	}
}
