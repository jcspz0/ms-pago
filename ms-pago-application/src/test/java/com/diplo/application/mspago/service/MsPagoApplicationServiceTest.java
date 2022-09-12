package com.diplo.application.mspago.service;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.application.mspago.service.deuda.IDeudaService;
import com.diplo.application.mspago.service.pago.IPagoService;
import com.diplo.mspago.factory.deuda.IDeudaFactory;
import com.diplo.mspago.factory.pago.IPagoFactory;
import com.diplo.sharedkernel.mediator.IMediator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MsPagoApplicationServiceTest {

	@InjectMocks
	MsPagoApplicationService test;

	@Mock
	IMediator _mediator;

	@Mock
	IDeudaService _deudaService;

	@Mock
	IDeudaFactory _DeudaFactory;

	@Mock
	IPagoService _pagoService;

	@Mock
	IPagoFactory _PagoFactory;

	@Test
	void testService() {
		test =
			new MsPagoApplicationService(
				_mediator,
				_deudaService,
				_pagoService,
				_DeudaFactory,
				_PagoFactory
			);
		test.AddApplication();
		test.setDeudaFactory(_DeudaFactory);
		test.setDeudaService(_deudaService);
		test.setMediator(_mediator);
		test.setPagoFactory(_PagoFactory);
		test.setPagoService(_pagoService);
		assertEquals(_DeudaFactory, test.getDeudaFactory());
		assertEquals(_deudaService, test.getDeudaService());
		assertEquals(_mediator, test.getMediator());
		assertEquals(_pagoService, test.getPagoService());
		assertEquals(_PagoFactory, test.getPagoFactory());
	}
}
