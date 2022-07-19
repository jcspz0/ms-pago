package com.diplo.application.mspago.usecase.command.pago.realizarpago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.diplo.application.mspago.service.pago.IPagoService;
import com.diplo.mspago.factory.pago.IPagoFactory;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import com.diplo.mspago.valueobjects.DetallePago;
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
class RealizarPagoHandlerTest {

	RealizarPagoHandler realizarPagoHandlerTest;

	@Mock
	RealizarPagoCommand realizarPagoCommandTest;

	@Mock
	IPagoService _pagoServiceTest;

	@Mock
	IPagoFactory _pagoFactoryTest;

	@Mock
	IDeudaRepository _deudaRepositoryTest;

	@Mock
	IUnitOfWork _unitOfWorkTest;

	Deuda deudaTest;
	UUID deudaIDTest;
	UUID reservaIdTest;
	Monto totalDeudaTest;

	Pago pagoTest;
	Monto totalPagoTest;
	DetallePago detallePagoTest;

	@BeforeEach
	void init() throws Exception {
		realizarPagoHandlerTest =
			new RealizarPagoHandler(
				_pagoServiceTest,
				null,
				_pagoFactoryTest,
				_deudaRepositoryTest,
				_unitOfWorkTest
			);

		deudaIDTest = UUID.randomUUID();
		reservaIdTest = UUID.randomUUID();
		totalDeudaTest = new Monto(100);

		detallePagoTest = new DetallePago("Detalle del pago");
		totalPagoTest = new Monto(50);

		deudaTest = new Deuda(deudaIDTest, reservaIdTest, totalDeudaTest);

		pagoTest = new Pago(totalPagoTest, detallePagoTest, deudaIDTest);

		when(_pagoServiceTest.GenerarNroPagoAsync())
			.thenReturn(
				CompletableFuture.completedFuture(pagoTest.getId().toString())
			);
		when(_deudaRepositoryTest.FindByIdAsync(any()))
			.thenReturn(CompletableFuture.completedFuture(deudaTest));
		when(
			_pagoFactoryTest.Create(
				anyString(),
				anyDouble(),
				anyString(),
				anyString()
			)
		)
			.thenReturn(pagoTest);
		when(realizarPagoCommandTest.getDeudaId())
			.thenReturn(deudaIDTest.toString());
		when(realizarPagoCommandTest.getDetalle())
			.thenReturn(detallePagoTest.getDetalle());
		when(realizarPagoCommandTest.getTotalPagado())
			.thenReturn(totalPagoTest.getMonto());
	}

	@Test
	void RealizarPagoRetornarUUID() throws Exception {
		Future<UUID> resultado = realizarPagoHandlerTest.Handle(
			realizarPagoCommandTest
		);

		assertNotNull(resultado);
	}
}
