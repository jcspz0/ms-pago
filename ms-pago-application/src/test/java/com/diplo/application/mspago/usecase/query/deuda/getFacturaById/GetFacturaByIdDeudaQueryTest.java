package com.diplo.application.mspago.usecase.query.deuda.getFacturaById;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class GetFacturaByIdDeudaQueryTest {

	@Test
	void testGetFacturaByIdDeudaQuery() {
		UUID Id = UUID.randomUUID();
		int nit = 123;
		String detalle = "Detalle";

		GetFacturaByIdDeudaQuery getFacturaByIdDeudaQuery = new GetFacturaByIdDeudaQuery();
		getFacturaByIdDeudaQuery =
			new GetFacturaByIdDeudaQuery(Id, nit, detalle);
		getFacturaByIdDeudaQuery.setDetalle(detalle);
		getFacturaByIdDeudaQuery.setId(Id);
		getFacturaByIdDeudaQuery.setNit(nit);

		assertEquals(Id, getFacturaByIdDeudaQuery.getId());
		assertEquals(nit, getFacturaByIdDeudaQuery.getNit());
		assertEquals(detalle, getFacturaByIdDeudaQuery.getDetalle());
	}
}
