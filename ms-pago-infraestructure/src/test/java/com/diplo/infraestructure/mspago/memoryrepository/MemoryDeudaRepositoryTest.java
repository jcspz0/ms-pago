package com.diplo.infraestructure.mspago.memoryrepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.valueobjects.Monto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemoryDeudaRepositoryTest {

	@Mock
	MemoryDatabase _database;

	MemoryDeudaRepository memoryDeudaRepository;
	UUID deudaId;
	UUID reservaId;
	Monto monto;

	Deuda deuda;
	List<Deuda> lista;

	@BeforeEach
	void init() throws Exception {
		deudaId = UUID.randomUUID();
		reservaId = UUID.randomUUID();
		monto = new Monto(10);
		memoryDeudaRepository = new MemoryDeudaRepository(_database);
		deuda = new Deuda(deudaId, reservaId, monto);
		lista = new ArrayList();
		lista.add(deuda);
	}

	@Test
	void testFindByIdAsync() throws Exception {
		when(_database.get_deudas()).thenReturn(lista);
		Future<Deuda> resultado = memoryDeudaRepository.FindByIdAsync(deudaId);

		assertNotNull(resultado.get());
	}

	@Test
	void testCreateAsync() throws Exception {
		Future<Deuda> resultado = memoryDeudaRepository.CreateAsync(deuda);
		assertNotNull(resultado.get());
	}

	@Test
	void testUpdateAsync() throws Exception {
		Future<Deuda> resultado = memoryDeudaRepository.UpdateAsync(deuda);
		assertNotNull(resultado.get());
	}

	@Test
	void testFindByReservaIdAsync()
		throws InterruptedException, ExecutionException {
		when(_database.get_deudas()).thenReturn(lista);
		Future<Deuda> resultado = memoryDeudaRepository.FindByReservaIdAsync(
			reservaId.toString()
		);

		assertNotNull(resultado.get());
	}
}
