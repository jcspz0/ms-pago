package com.diplo.application.mspago.usecase.command.deuda.creardeuda;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.application.mspago.dto.pago.PagoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CrearDeudaCommandTest {

	@Test
	void testCrearDeudaCommand() {
		String deudaId = UUID.randomUUID().toString();
		String estado = "VALIDO";
		String reservaId = UUID.randomUUID().toString();
		double total = 100;
		List<PagoDTO> ListaPagos = new ArrayList<PagoDTO>();
		CrearDeudaCommand crearDeudaCommand = new CrearDeudaCommand();
		crearDeudaCommand.setDeudaId(deudaId);
		crearDeudaCommand.setListaPagos(ListaPagos);
		crearDeudaCommand.setReservaId(reservaId);
		crearDeudaCommand.setTotal(total);
		crearDeudaCommand.setEstado(estado);

		assertEquals(deudaId, crearDeudaCommand.getDeudaId());
		assertEquals(estado, crearDeudaCommand.getEstado());
		assertEquals(reservaId, crearDeudaCommand.getReservaId());
		assertEquals(total, crearDeudaCommand.getTotal());
		assertEquals(ListaPagos, crearDeudaCommand.getListaPagos());

		crearDeudaCommand = new CrearDeudaCommand(reservaId, total);

		assertEquals(reservaId, crearDeudaCommand.getReservaId());
		assertEquals(total, crearDeudaCommand.getTotal());

		crearDeudaCommand = new CrearDeudaCommand(reservaId, total, ListaPagos);

		assertEquals(reservaId, crearDeudaCommand.getReservaId());
		assertEquals(total, crearDeudaCommand.getTotal());
		assertEquals(ListaPagos, crearDeudaCommand.getListaPagos());

		crearDeudaCommand = new CrearDeudaCommand(deudaId, reservaId, total);
		assertEquals(deudaId, crearDeudaCommand.getDeudaId());
		assertEquals(reservaId, crearDeudaCommand.getReservaId());
		assertEquals(total, crearDeudaCommand.getTotal());

		crearDeudaCommand =
			new CrearDeudaCommand(deudaId, reservaId, total, ListaPagos);
		assertEquals(deudaId, crearDeudaCommand.getDeudaId());
		assertEquals(reservaId, crearDeudaCommand.getReservaId());
		assertEquals(total, crearDeudaCommand.getTotal());
		assertEquals(ListaPagos, crearDeudaCommand.getListaPagos());
	}
}
