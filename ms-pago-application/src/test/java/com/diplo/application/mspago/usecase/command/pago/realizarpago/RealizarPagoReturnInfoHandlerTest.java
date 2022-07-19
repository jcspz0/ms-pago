package com.diplo.application.mspago.usecase.command.pago.realizarpago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.application.mspago.service.pago.IPagoService;
import com.diplo.mspago.factory.deuda.IDeudaFactory;
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
class RealizarPagoReturnInfoHandlerTest {

	RealizarPagoReturnInfoHandler realizarPagoReturnInfoHandlerTest;

	@Mock
	RealizarPagoReturnInfoCommand realizarPagoReturnInfoCommand;

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
		realizarPagoReturnInfoHandlerTest =
			new RealizarPagoReturnInfoHandler(
				_pagoServiceTest,
				null,
				_pagoFactoryTest,
				_deudaRepositoryTest,
				_unitOfWorkTest
			);

		deudaIDTest = UUID.randomUUID();
		reservaIdTest = UUID.randomUUID();
		totalDeudaTest = new Monto(100);
		deudaTest = new Deuda(deudaIDTest, reservaIdTest, totalDeudaTest);

		detallePagoTest = new DetallePago("Detalle del pago");
		totalPagoTest = new Monto(50);

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
		when(realizarPagoReturnInfoCommand.getDeudaId())
			.thenReturn(deudaIDTest.toString());
		when(realizarPagoReturnInfoCommand.getDetalle())
			.thenReturn(detallePagoTest.getDetalle());
		when(realizarPagoReturnInfoCommand.getTotalPagado())
			.thenReturn(totalPagoTest.getMonto());
	}

	@Test
	void RealizarPagoRetornarInfo() throws Exception {
		Future<PagoDTO> resultado = realizarPagoReturnInfoHandlerTest.Handle(
			realizarPagoReturnInfoCommand
		);

		assertNotNull(resultado);
		assertEquals(pagoTest.getId().toString(), resultado.get().getPagoId());
	}
}
