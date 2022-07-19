package com.diplo.infraestructure.mspago.entityframework.dbrepository.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class PagoEntityTest {

	@Test
	void testPagoEntity() {
		String PagoId = UUID.randomUUID().toString();
		double MontoPagado = 10;
		String detalle = "Detalle";
		String deudaId = UUID.randomUUID().toString();

		Pago pago = new Pago(
			UUID.fromString(PagoId),
			new Monto(MontoPagado),
			new DetallePago(detalle),
			UUID.fromString(deudaId)
		);
		DeudaEntity deudaEntity = new DeudaEntity();

		PagoEntity pagoEntity = new PagoEntity();
		pagoEntity.setDetalle(detalle);
		pagoEntity.setDeuda(deudaEntity);
		pagoEntity.setMontoPagado(MontoPagado);
		pagoEntity.setPagoId(PagoId);

		assertEquals(PagoId, pagoEntity.getPagoId());
		assertEquals(MontoPagado, pagoEntity.getMontoPagado());
		assertEquals(detalle, pagoEntity.getDetalle());
		assertEquals(deudaEntity, pagoEntity.getDeuda());

		pagoEntity = new PagoEntity(pago);
		assertEquals(PagoId, pagoEntity.getPagoId());
		assertEquals(MontoPagado, pagoEntity.getMontoPagado());
		assertEquals(detalle, pagoEntity.getDetalle());

		pagoEntity = new PagoEntity(pago, deudaEntity);
		assertEquals(PagoId, pagoEntity.getPagoId());
		assertEquals(MontoPagado, pagoEntity.getMontoPagado());
		assertEquals(detalle, pagoEntity.getDetalle());
		assertEquals(deudaEntity, pagoEntity.getDeuda());

		pagoEntity = new PagoEntity(PagoId, MontoPagado, detalle);
		assertEquals(PagoId, pagoEntity.getPagoId());
		assertEquals(MontoPagado, pagoEntity.getMontoPagado());
		assertEquals(detalle, pagoEntity.getDetalle());

		pagoEntity = new PagoEntity(PagoId, MontoPagado, detalle, deudaEntity);
		assertEquals(PagoId, pagoEntity.getPagoId());
		assertEquals(MontoPagado, pagoEntity.getMontoPagado());
		assertEquals(detalle, pagoEntity.getDetalle());
		assertEquals(deudaEntity, pagoEntity.getDeuda());
	}
}
