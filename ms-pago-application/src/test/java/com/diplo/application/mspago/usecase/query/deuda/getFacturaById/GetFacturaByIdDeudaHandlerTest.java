package com.diplo.application.mspago.usecase.query.deuda.getFacturaById;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.application.mspago.dto.pago.FacturaDTO;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.repository.IDeudaRepository;
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
class GetFacturaByIdDeudaHandlerTest {

	GetFacturaByIdDeudaHandler getFacturaByIdDeudaHandlerTest;

	@Mock
	GetFacturaByIdDeudaQuery getFacturaByIdDeudaQueryTest;

	@Mock
	IDeudaRepository _deudaRepositoryTest;

	Deuda deudaTest;
	UUID deudaIDTest;
	UUID reservaIdTest;
	Monto totalDeudaTest;

	@BeforeEach
	void init() throws Exception {
		getFacturaByIdDeudaHandlerTest =
			new GetFacturaByIdDeudaHandler(_deudaRepositoryTest);

		deudaIDTest = UUID.randomUUID();
		reservaIdTest = UUID.randomUUID();
		totalDeudaTest = new Monto(100);
		deudaTest = new Deuda(deudaIDTest, reservaIdTest, totalDeudaTest);

		when(getFacturaByIdDeudaQueryTest.getId()).thenReturn(deudaTest.getId());
		when(_deudaRepositoryTest.FindByIdAsync(any()))
			.thenReturn(CompletableFuture.completedFuture(deudaTest));
	}

	@Test
	void ObtenerFacturaPorIdDeDuda() {
		Future<FacturaDTO> resultado = getFacturaByIdDeudaHandlerTest.Handle(
			getFacturaByIdDeudaQueryTest
		);

		assertNotNull(resultado);
	}
}
