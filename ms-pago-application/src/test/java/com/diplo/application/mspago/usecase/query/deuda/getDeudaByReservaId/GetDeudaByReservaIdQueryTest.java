package com.diplo.application.mspago.usecase.query.deuda.getDeudaByReservaId;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class GetDeudaByReservaIdQueryTest {

	@Test
	void testGetDeudaByReservaIdQuery() {
		String id = UUID.randomUUID().toString();
		GetDeudaByReservaIdQuery getDeudaByReservaIdQuery = new GetDeudaByReservaIdQuery();
		getDeudaByReservaIdQuery = new GetDeudaByReservaIdQuery(id);
		getDeudaByReservaIdQuery.setId(id);

		assertEquals(id, getDeudaByReservaIdQuery.getId());
	}
}
