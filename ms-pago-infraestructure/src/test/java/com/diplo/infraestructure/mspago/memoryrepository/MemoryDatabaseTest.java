package com.diplo.infraestructure.mspago.memoryrepository;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.model.deuda.Deuda;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class MemoryDatabaseTest {

	@Test
	void testMemoryDatabase() {
		List<Deuda> lista = new ArrayList();
		MemoryDatabase memoryDatabase = new MemoryDatabase(lista);
		assertEquals(lista, memoryDatabase.get_deudas());
	}
}
