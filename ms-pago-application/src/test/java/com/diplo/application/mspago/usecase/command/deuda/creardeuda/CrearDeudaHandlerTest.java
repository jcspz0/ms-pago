package com.diplo.application.mspago.usecase.command.deuda.creardeuda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.application.mspago.service.deuda.IDeudaService;
import com.diplo.mspago.factory.deuda.IDeudaFactory;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import com.diplo.mspago.valueobjects.Monto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CrearDeudaHandlerTest {

	CrearDeudaHandler crearDeudaHandler;

	@Mock
	IDeudaService _deudaServiceTest;

	@Mock
	IDeudaFactory _deudaFactoryTest;

	@Mock
	IDeudaRepository _deudaRepositoryTest;

	@Mock
	IUnitOfWork _unitOfWorkTest;

	@Mock
	CrearDeudaCommand crearDeudaCommandTest;

	UUID deudaIdTest;
	UUID reservaIdTest;
	Monto totalTest;
	List<Pago> listaPagosTest;

	Deuda deudaTest;

	@BeforeEach
	void init() throws Exception {
		deudaIdTest = UUID.randomUUID();
		reservaIdTest = UUID.randomUUID();
		totalTest = new Monto(100);
		listaPagosTest = new ArrayList<Pago>();

		deudaTest = new Deuda(deudaIdTest, reservaIdTest, totalTest);

		crearDeudaHandler =
			new CrearDeudaHandler(
				_deudaServiceTest,
				_deudaFactoryTest,
				_deudaRepositoryTest,
				_unitOfWorkTest
			);

		when(_deudaServiceTest.GenerarNroDeudaAsync())
			.thenReturn(
				CompletableFuture.completedFuture(deudaIdTest.toString())
			);
		when(_deudaFactoryTest.Create(anyString(), anyString(), anyDouble()))
			.thenReturn(deudaTest);
		when(crearDeudaCommandTest.getReservaId())
			.thenReturn(deudaTest.getReservaId().toString());
		when(crearDeudaCommandTest.getTotal())
			.thenReturn(deudaTest.getTotal().getMonto());
	}

	@Test
	void CrearDeuda() throws Exception {
		Future<UUID> resultado = crearDeudaHandler.Handle(
			crearDeudaCommandTest
		);

		assertNotNull(resultado);
		assertEquals(deudaIdTest, resultado.get());
	}
}
