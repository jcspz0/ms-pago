package com.diplo.application.mspago.usecase.command.deuda.vencerreserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import com.diplo.mspago.valueobjects.Monto;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VencerReservaHandlerTest {

	VencerReservaHandler vencerReservaHandlerTest;

	@Mock
	VencerReservaCommand vencerReservaCommandTest;

	@Mock
	IDeudaRepository _deudaRepositoryTest;

	@Mock
	IUnitOfWork _unitOfWorkTest;

	Deuda deudaTest;
	UUID deudaIDTest;
	UUID reservaIdTest;
	Monto totalTest;

	@BeforeEach
	void init() throws Exception {
		vencerReservaHandlerTest =
			new VencerReservaHandler(_deudaRepositoryTest, _unitOfWorkTest);

		deudaIDTest = UUID.randomUUID();
		reservaIdTest = UUID.randomUUID();
		totalTest = new Monto(100);

		deudaTest = new Deuda(deudaIDTest, reservaIdTest, totalTest);

		when(_deudaRepositoryTest.FindByIdAsync(any()))
			.thenReturn(CompletableFuture.completedFuture(deudaTest));
		when(vencerReservaCommandTest.getDeudaId())
			.thenReturn(deudaIDTest.toString());
	}

	@Test
	void VencerDeuda() throws Exception {
		Future<UUID> resultado = vencerReservaHandlerTest.Handle(
			vencerReservaCommandTest
		);

		assertNotNull(resultado);
		assertEquals(deudaIDTest, resultado.get());
	}
}
