package com.diplo.application.mspago.usecase.command.deuda.deudapagadarollback;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class DeudaPagadaRollbackCommandTest {

	@Test
	void testDeudaPagadaRollbackCommand() {
		String reservaId = UUID.randomUUID().toString();
		String pagoId = UUID.randomUUID().toString();
		DeudaPagadaRollbackCommand command = new DeudaPagadaRollbackCommand();
		command.setPagoId(pagoId);
		command.setReservaId(reservaId);
		assertEquals(reservaId, command.getReservaId());
		assertEquals(pagoId, command.getPagoId());
		command = new DeudaPagadaRollbackCommand(reservaId, pagoId);
		assertEquals(reservaId, command.getReservaId());
		assertEquals(pagoId, command.getPagoId());
	}
}
