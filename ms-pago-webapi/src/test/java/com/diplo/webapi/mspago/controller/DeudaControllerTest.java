package com.diplo.webapi.mspago.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.dto.pago.FacturaDTO;
import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.application.mspago.usecase.command.deuda.creardeuda.CrearDeudaCommand;
import com.diplo.application.mspago.usecase.command.deuda.vencerdeuda.VencerDeudaCommand;
import com.diplo.application.mspago.usecase.command.pago.realizarpago.RealizarPagoReturnInfoCommand;
import com.diplo.application.mspago.usecase.query.deuda.getDeudaById.GetDeudaByIdQuery;
import com.diplo.application.mspago.usecase.query.deuda.getDeudaByReservaId.GetDeudaByReservaIdQuery;
import com.diplo.application.mspago.usecase.query.deuda.getFacturaById.GetFacturaByIdDeudaQuery;
import com.diplo.sharedkernel.mediator.IMediator;
import com.diplo.webapi.mspago.service.MsPagoWebApiService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RequestParam;

@ExtendWith(MockitoExtension.class)
class DeudaControllerTest {

	@Mock
	MsPagoWebApiService webApiServiceTest;

	@Mock
	IMediator _mediatorTest;

	@InjectMocks
	DeudaController deudaControllerTest;

	DeudaDTO deudaDTOTest;
	String deudaIdTest;
	String estadoTest;
	String reservaIdTest;
	double totalTest;
	List<PagoDTO> listaPagosTest;

	@BeforeEach
	void init() {
		deudaIdTest = UUID.randomUUID().toString();
		estadoTest = "VALIDO";
		reservaIdTest = UUID.randomUUID().toString();
		totalTest = 100;
		listaPagosTest = new ArrayList();

		deudaDTOTest =
			new DeudaDTO(
				deudaIdTest,
				estadoTest,
				reservaIdTest,
				totalTest,
				listaPagosTest
			);

		when(webApiServiceTest.getMediator()).thenReturn(_mediatorTest);
	}

	@Test
	void CreateDeuda() throws Exception {
		when(webApiServiceTest.getMediator().Send(any(CrearDeudaCommand.class)))
			.thenReturn(UUID.fromString(deudaIdTest));
		when(webApiServiceTest.getMediator().Send(any(GetDeudaByIdQuery.class)))
			.thenReturn(deudaDTOTest);

		DeudaDTO resultado = deudaControllerTest.CreateDeuda(deudaDTOTest);

		assertNotNull(resultado);
		assertEquals(deudaIdTest.toString(), resultado.getDeudaId().toString());
	}

	@Test
	void FindDeudaById() throws Exception {
		when(webApiServiceTest.getMediator().Send(any(GetDeudaByIdQuery.class)))
			.thenReturn(deudaDTOTest);

		DeudaDTO resultado = deudaControllerTest.FindDeudaById(deudaIdTest);

		assertNotNull(resultado);
		assertEquals(deudaIdTest, resultado.getDeudaId());
	}

	@Test
	void RealizarPago() throws Exception {
		String pagoIdTest = UUID.randomUUID().toString();
		double montoPagadoTest = 20;
		String detalleTest = "Detalle del pago";
		PagoDTO pagoDTOTest = new PagoDTO(
			pagoIdTest,
			montoPagadoTest,
			detalleTest,
			deudaIdTest
		);
		when(
			webApiServiceTest
				.getMediator()
				.Send(any(RealizarPagoReturnInfoCommand.class))
		)
			.thenReturn(pagoDTOTest);

		PagoDTO resultado = deudaControllerTest.RealizarPago(pagoDTOTest);

		assertNotNull(resultado);
		assertEquals(pagoIdTest, resultado.getPagoId());
	}

	@Test
	void GenerarFacturaById() throws Exception {
		String detalleTest = "Detalle";
		int nitTest = 4731768;
		FacturaDTO facturaDTOTest = new FacturaDTO(
			detalleTest,
			100,
			nitTest,
			UUID.randomUUID().toString()
		);
		when(
			webApiServiceTest
				.getMediator()
				.Send(any(GetFacturaByIdDeudaQuery.class))
		)
			.thenReturn(facturaDTOTest);

		FacturaDTO resultado = deudaControllerTest.GenerarFacturaById(
			deudaIdTest,
			detalleTest,
			nitTest
		);

		assertNotNull(resultado);
	}

	@Test
	void VencerDeudaById() throws Exception {
		deudaDTOTest.setEstado("VENCIDA");
		when(webApiServiceTest.getMediator().Send(any(VencerDeudaCommand.class)))
			.thenReturn(UUID.fromString(deudaIdTest));
		when(webApiServiceTest.getMediator().Send(any(GetDeudaByIdQuery.class)))
			.thenReturn(deudaDTOTest);

		DeudaDTO resultado = deudaControllerTest.VencerDeudaById(deudaIdTest);

		assertNotNull(resultado);
		assertEquals(deudaIdTest, resultado.getDeudaId());
		assertEquals("VENCIDA", resultado.getEstado());
	}

	@Test
	void FindDeudaByReservaId() throws Exception {
		when(
			webApiServiceTest
				.getMediator()
				.Send(any(GetDeudaByReservaIdQuery.class))
		)
			.thenReturn(deudaDTOTest);

		DeudaDTO resultado = deudaControllerTest.FindDeudaByReservaId(
			reservaIdTest
		);

		assertNotNull(resultado);
		assertEquals(reservaIdTest, resultado.getReservaId());
	}
}
