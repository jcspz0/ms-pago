package com.diplo.application.mspago.usecase.command.pago.realizarpago;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class RealizarPagoReturnInfoCommandTest {

	@Test
	void testRealizarPagoReturnInfoCommand() {
		String deudaId = UUID.randomUUID().toString();
		String pagoId = UUID.randomUUID().toString();
		double totalPagado = 10;
		String detalle = "Detalle";
		RealizarPagoReturnInfoCommand realizarPagoReturnInfoCommand = new RealizarPagoReturnInfoCommand();
		realizarPagoReturnInfoCommand.setDetalle(detalle);
		realizarPagoReturnInfoCommand.setDeudaId(deudaId);
		realizarPagoReturnInfoCommand.setPagoId(pagoId);
		realizarPagoReturnInfoCommand.setTotalPagado(totalPagado);

		assertEquals(deudaId, realizarPagoReturnInfoCommand.getDeudaId());
		assertEquals(pagoId, realizarPagoReturnInfoCommand.getPagoId());
		assertEquals(
			totalPagado,
			realizarPagoReturnInfoCommand.getTotalPagado()
		);
		assertEquals(detalle, realizarPagoReturnInfoCommand.getDetalle());

		realizarPagoReturnInfoCommand =
			new RealizarPagoReturnInfoCommand(totalPagado, detalle);
		assertEquals(
			totalPagado,
			realizarPagoReturnInfoCommand.getTotalPagado()
		);
		assertEquals(detalle, realizarPagoReturnInfoCommand.getDetalle());

		realizarPagoReturnInfoCommand =
			new RealizarPagoReturnInfoCommand(deudaId, totalPagado, detalle);
		assertEquals(deudaId, realizarPagoReturnInfoCommand.getDeudaId());
		assertEquals(
			totalPagado,
			realizarPagoReturnInfoCommand.getTotalPagado()
		);
		assertEquals(detalle, realizarPagoReturnInfoCommand.getDetalle());

		realizarPagoReturnInfoCommand =
			new RealizarPagoReturnInfoCommand(
				pagoId,
				deudaId,
				totalPagado,
				detalle
			);

		assertEquals(deudaId, realizarPagoReturnInfoCommand.getDeudaId());
		assertEquals(pagoId, realizarPagoReturnInfoCommand.getPagoId());
		assertEquals(
			totalPagado,
			realizarPagoReturnInfoCommand.getTotalPagado()
		);
		assertEquals(detalle, realizarPagoReturnInfoCommand.getDetalle());
	}
}
