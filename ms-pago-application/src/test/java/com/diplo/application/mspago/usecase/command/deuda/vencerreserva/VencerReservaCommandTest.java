package com.diplo.application.mspago.usecase.command.deuda.vencerreserva;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.application.mspago.usecase.command.deuda.vencerdeuda.VencerDeudaCommand;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class VencerReservaCommandTest {

	@Test
	void testVencerReservaCommand() {
		String deudaId = UUID.randomUUID().toString();
		VencerDeudaCommand vencerReservaCommand = new VencerDeudaCommand();
		vencerReservaCommand = new VencerDeudaCommand(deudaId);
		vencerReservaCommand.setDeudaId(deudaId);

		assertEquals(deudaId, vencerReservaCommand.getDeudaId());
	}
}
