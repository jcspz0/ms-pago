package com.diplo.application.mspago.usecase.command.deuda.vencerreserva;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class VencerReservaCommandTest {

	@Test
	void testVencerReservaCommand() {
		String deudaId = UUID.randomUUID().toString();
		VencerReservaCommand vencerReservaCommand = new VencerReservaCommand();
		vencerReservaCommand = new VencerReservaCommand(deudaId);
		vencerReservaCommand.setDeudaId(deudaId);

		assertEquals(deudaId, vencerReservaCommand.getDeudaId());
	}
}
