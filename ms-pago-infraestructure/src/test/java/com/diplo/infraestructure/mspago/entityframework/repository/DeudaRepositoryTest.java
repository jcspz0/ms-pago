package com.diplo.infraestructure.mspago.entityframework.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.valueobjects.Monto;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;

class DeudaRepositoryTest {

	@Test
	void testFindByIdAsync() throws Exception {
		Deuda deuda = new Deuda(
			UUID.randomUUID(),
			UUID.randomUUID(),
			new Monto(10)
		);
		DeudaRepository deudaRepository = new DeudaRepository();
		Future<Deuda> resultado = deudaRepository.FindByIdAsync(deuda.getId());
		assertNotNull(resultado.get());
	}

	@Test
	void testCreateAsync() throws Exception {
		Deuda deuda = new Deuda(
			UUID.randomUUID(),
			UUID.randomUUID(),
			new Monto(10)
		);
		DeudaRepository deudaRepository = new DeudaRepository();
		Future<Deuda> resultado = deudaRepository.CreateAsync(deuda);
		assertNotNull(resultado.get());
	}

	@Test
	void testUpdateAsync() throws Exception {
		Deuda deuda = new Deuda(
			UUID.randomUUID(),
			UUID.randomUUID(),
			new Monto(10)
		);
		DeudaRepository deudaRepository = new DeudaRepository();
		Future<Deuda> resultado = deudaRepository.UpdateAsync(deuda);
		assertNotNull(resultado.get());
	}

	@Test
	void testFindByReservaIdAsync() throws Exception {
		Deuda deuda = new Deuda(
			UUID.randomUUID(),
			UUID.randomUUID(),
			new Monto(10)
		);
		DeudaRepository deudaRepository = new DeudaRepository();
		Future<Deuda> resultado = deudaRepository.FindByReservaIdAsync(
			deuda.getId().toString()
		);
		assertNotNull(resultado.get());
	}
}
