package com.diplo.application.mspago.usecase.query.deuda.getDeudaById;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class GetDeudaByIdQueryTest {

	@Test
	void testGetDeudaByIdQuery() {
		UUID Id = UUID.randomUUID();
		GetDeudaByIdQuery getDeudaByIdQuery = new GetDeudaByIdQuery();
		getDeudaByIdQuery = new GetDeudaByIdQuery(Id);
		getDeudaByIdQuery.setId(Id);

		assertEquals(Id, getDeudaByIdQuery.getId());
	}
}
