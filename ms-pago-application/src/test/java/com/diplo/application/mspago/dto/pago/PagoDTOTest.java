package com.diplo.application.mspago.dto.pago;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class PagoDTOTest {

	@Test
	void testPagoDTO() {
		String pagoId = UUID.randomUUID().toString();
		double MontoPagado = 10;
		String detalle = "detalle";
		String deudaId = UUID.randomUUID().toString();
		Pago pago = new Pago(
			UUID.fromString(pagoId),
			new Monto(MontoPagado),
			new DetallePago(detalle),
			UUID.fromString(deudaId)
		);
		PagoDTO pagoDTO = new PagoDTO();

		pagoDTO.setDetalle(detalle);
		pagoDTO.setDeudaId(deudaId);
		pagoDTO.setMontoPagado(MontoPagado);
		pagoDTO.setPagoId(pagoId);

		assertEquals(pagoId, pagoDTO.getPagoId());
		assertEquals(MontoPagado, pagoDTO.getMontoPagado());
		assertEquals(detalle, pagoDTO.getDetalle());
		assertEquals(deudaId, pagoDTO.getDeudaId());

		pagoDTO = new PagoDTO(pago);

		assertEquals(pagoId, pagoDTO.getPagoId());
		assertEquals(MontoPagado, pagoDTO.getMontoPagado());
		assertEquals(detalle, pagoDTO.getDetalle());
		assertEquals(deudaId, pagoDTO.getDeudaId());

		pagoDTO = new PagoDTO(MontoPagado, detalle, deudaId);

		assertEquals(MontoPagado, pagoDTO.getMontoPagado());
		assertEquals(detalle, pagoDTO.getDetalle());
		assertEquals(deudaId, pagoDTO.getDeudaId());

		pagoDTO = new PagoDTO(pagoId, MontoPagado, detalle, deudaId);

		assertEquals(pagoId, pagoDTO.getPagoId());
		assertEquals(MontoPagado, pagoDTO.getMontoPagado());
		assertEquals(detalle, pagoDTO.getDetalle());
		assertEquals(deudaId, pagoDTO.getDeudaId());
	}
}
