package com.diplo.infraestructure.mspago.entityframework.dbrepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.DeudaEntity;
import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.repository.DeudaEntityRepository;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
class DbDeudaRepositoryTest {

	@Mock
	ApplicationEventPublisher applicationEventPublisherTest;

	@Mock
	DeudaEntityRepository deudaEntityRepositoryTest;

	@InjectMocks
	DbDeudaRepository dbDeudaRepositoryTest;

	DeudaEntity deudaEntityTest;
	Deuda deudaTest;
	UUID deudaIdTest;
	UUID reservaIdTest;
	Monto totalTest;
	List<Pago> ListaPagos;

	@BeforeEach
	void init() throws Exception {
		deudaIdTest = UUID.randomUUID();
		reservaIdTest = UUID.randomUUID();
		totalTest = new Monto(100);
		ListaPagos = new ArrayList<Pago>();
		ListaPagos.add(
			new Pago(new Monto(10), new DetallePago("Detalle"), deudaIdTest)
		);

		deudaTest = new Deuda(deudaIdTest, reservaIdTest, totalTest);
		deudaTest =
			new Deuda(
				deudaIdTest,
				reservaIdTest,
				totalTest,
				"INICIADA",
				ListaPagos
			);

		deudaEntityTest = new DeudaEntity(deudaTest);
	}

	@Test
	void FindByIdAsync() throws InterruptedException, ExecutionException {
		when(deudaEntityRepositoryTest.findById(anyString()))
			.thenReturn(Optional.of(deudaEntityTest));

		Future<Deuda> resultado = dbDeudaRepositoryTest.FindByIdAsync(
			deudaIdTest
		);

		assertNotNull(resultado);
		assertEquals(deudaIdTest, resultado.get().getId());
	}

	@Test
	void CreateAsync() throws InterruptedException, ExecutionException {
		Future<Deuda> resultado = dbDeudaRepositoryTest.CreateAsync(deudaTest);

		assertNotNull(resultado);
		assertEquals(deudaIdTest, resultado.get().getId());
	}

	@Test
	void UpdateAsync() throws InterruptedException, ExecutionException {
		Future<Deuda> resultado = dbDeudaRepositoryTest.UpdateAsync(deudaTest);

		assertNotNull(resultado);
		assertEquals(deudaIdTest, resultado.get().getId());
	}

	@Test
	void FindByReservaIdAsync()
		throws InterruptedException, ExecutionException {
		when(deudaEntityRepositoryTest.findDeudaByReservaId(anyString()))
			.thenReturn(deudaEntityTest);

		Future<Deuda> resultado = dbDeudaRepositoryTest.FindByReservaIdAsync(
			deudaIdTest.toString()
		);

		assertNotNull(resultado);
		assertEquals(deudaIdTest, resultado.get().getId());
	}
}
