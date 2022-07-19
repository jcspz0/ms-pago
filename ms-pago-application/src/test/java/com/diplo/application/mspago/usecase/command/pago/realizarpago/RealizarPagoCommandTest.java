package com.diplo.application.mspago.usecase.command.pago.realizarpago;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class RealizarPagoCommandTest {

	@Test
	void testRealizarPagoCommand() {
		String deudaId = UUID.randomUUID().toString();
		String pagoId = UUID.randomUUID().toString();
		double totalPagado = 10;
		String detalle = "Detalle";
		RealizarPagoCommand realizarPagoCommand = new RealizarPagoCommand();
		realizarPagoCommand.setDetalle(detalle);
		realizarPagoCommand.setDeudaId(deudaId);
		realizarPagoCommand.setPagoId(pagoId);
		realizarPagoCommand.setTotalPagado(totalPagado);

		assertEquals(deudaId, realizarPagoCommand.getDeudaId());
		assertEquals(pagoId, realizarPagoCommand.getPagoId());
		assertEquals(totalPagado, realizarPagoCommand.getTotalPagado());
		assertEquals(detalle, realizarPagoCommand.getDetalle());

		realizarPagoCommand = new RealizarPagoCommand(totalPagado, detalle);
		assertEquals(totalPagado, realizarPagoCommand.getTotalPagado());
		assertEquals(detalle, realizarPagoCommand.getDetalle());

		realizarPagoCommand =
			new RealizarPagoCommand(deudaId, totalPagado, detalle);
		assertEquals(deudaId, realizarPagoCommand.getDeudaId());
		assertEquals(totalPagado, realizarPagoCommand.getTotalPagado());
		assertEquals(detalle, realizarPagoCommand.getDetalle());

		realizarPagoCommand =
			new RealizarPagoCommand(pagoId, deudaId, totalPagado, detalle);

		assertEquals(deudaId, realizarPagoCommand.getDeudaId());
		assertEquals(pagoId, realizarPagoCommand.getPagoId());
		assertEquals(totalPagado, realizarPagoCommand.getTotalPagado());
		assertEquals(detalle, realizarPagoCommand.getDetalle());
	}
}
