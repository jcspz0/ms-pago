package com.diplo.application.mspago.dto.pago;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class DeudaDTOTest {

	@Test
	void testDeudaDTO() {
		String deudaId = UUID.randomUUID().toString();
		String estado = "VALIDO";
		String reservaId = UUID.randomUUID().toString();
		double total = 10;
		List<PagoDTO> ListaPagos = new ArrayList<PagoDTO>();

		DeudaDTO deudaDTO = new DeudaDTO(estado, reservaId, total);

		assertEquals(estado, deudaDTO.getEstado());
		assertEquals(reservaId, deudaDTO.getReservaId());
		assertEquals(total, deudaDTO.getTotal());

		deudaDTO = new DeudaDTO(estado, reservaId, total, ListaPagos);

		assertEquals(estado, deudaDTO.getEstado());
		assertEquals(reservaId, deudaDTO.getReservaId());
		assertEquals(total, deudaDTO.getTotal());
		assertEquals(ListaPagos, deudaDTO.getListaPagos());

		deudaDTO = new DeudaDTO(deudaId, estado, reservaId, total);

		assertEquals(deudaId, deudaDTO.getDeudaId());
		assertEquals(estado, deudaDTO.getEstado());
		assertEquals(reservaId, deudaDTO.getReservaId());
		assertEquals(total, deudaDTO.getTotal());

		deudaDTO = new DeudaDTO(deudaId, estado, reservaId, total, ListaPagos);

		assertEquals(deudaId, deudaDTO.getDeudaId());
		assertEquals(estado, deudaDTO.getEstado());
		assertEquals(reservaId, deudaDTO.getReservaId());
		assertEquals(total, deudaDTO.getTotal());
		assertEquals(ListaPagos, deudaDTO.getListaPagos());

		deudaDTO = new DeudaDTO();
		deudaDTO.setDeudaId(deudaId);
		deudaDTO.setEstado(estado);
		deudaDTO.setListaPagos(ListaPagos);
		deudaDTO.setReservaId(reservaId);
		deudaDTO.setTotal(total);

		assertEquals(deudaId, deudaDTO.getDeudaId());
		assertEquals(estado, deudaDTO.getEstado());
		assertEquals(reservaId, deudaDTO.getReservaId());
		assertEquals(total, deudaDTO.getTotal());
		assertEquals(ListaPagos, deudaDTO.getListaPagos());
	}
}
