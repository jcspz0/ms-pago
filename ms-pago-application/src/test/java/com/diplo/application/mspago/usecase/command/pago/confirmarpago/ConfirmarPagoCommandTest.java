package com.diplo.application.mspago.usecase.command.pago.confirmarpago;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class ConfirmarPagoCommandTest {

	@Test
	void testConfirmarPagoCommand() {
		String reservaId = UUID.randomUUID().toString();
		ConfirmarPagoCommand command = new ConfirmarPagoCommand();
		command.setReservaId(reservaId);
		assertEquals(reservaId, command.getReservaId());
		command = new ConfirmarPagoCommand(reservaId);
		assertEquals(reservaId, command.getReservaId());
	}
}
