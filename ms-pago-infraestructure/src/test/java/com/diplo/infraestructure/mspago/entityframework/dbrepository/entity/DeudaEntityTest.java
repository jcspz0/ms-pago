package com.diplo.infraestructure.mspago.entityframework.dbrepository.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class DeudaEntityTest {

	@Test
	void testDeudaEntity() {
		String DeudaId = UUID.randomUUID().toString();
		String ReservaId = UUID.randomUUID().toString();
		double Total = 100;
		String Estado = "INICIADA";
		List<Pago> ListaPagos = new ArrayList<Pago>();
		ListaPagos.add(
			new Pago(
				new Monto(1),
				new DetallePago("Detalle"),
				UUID.fromString(DeudaId)
			)
		);
		PagoEntity pagoEntity = new PagoEntity(ListaPagos.get(0));
		List<PagoEntity> ListaPagoEntity = new ArrayList();
		ListaPagoEntity.add(pagoEntity);

		Deuda deuda = new Deuda(
			UUID.fromString(DeudaId),
			UUID.fromString(ReservaId),
			new Monto(Total),
			Estado,
			ListaPagos
		);
		DeudaEntity deudaEntity = new DeudaEntity();
		deudaEntity = new DeudaEntity(deuda);
		deudaEntity.setDeudaId(DeudaId);
		deudaEntity.setEstado(Estado);
		deudaEntity.setListaPagos(ListaPagoEntity);
		deudaEntity.setReservaId(ReservaId);
		deudaEntity.setTotal(Total);

		assertEquals(DeudaId, deudaEntity.getDeudaId());
		assertEquals(ReservaId, deudaEntity.getReservaId());
		assertEquals(Total, deudaEntity.getTotal());
		assertEquals(Estado, deudaEntity.getEstado());
		assertEquals(ListaPagoEntity, deudaEntity.getListaPagos());

		deudaEntity =
			new DeudaEntity(DeudaId, ReservaId, Total, Estado, ListaPagoEntity);
		assertEquals(DeudaId, deudaEntity.getDeudaId());
		assertEquals(ReservaId, deudaEntity.getReservaId());
		assertEquals(Total, deudaEntity.getTotal());
		assertEquals(Estado, deudaEntity.getEstado());
		assertEquals(ListaPagoEntity, deudaEntity.getListaPagos());
	}
}
