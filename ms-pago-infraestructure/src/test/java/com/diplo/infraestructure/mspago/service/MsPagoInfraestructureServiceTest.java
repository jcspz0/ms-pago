package com.diplo.infraestructure.mspago.service;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.application.mspago.service.MsPagoApplicationService;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class MsPagoInfraestructureServiceTest {

	@Mock
	MsPagoApplicationService service;

	@Mock
	IDeudaRepository _deudaRepository;

	@Mock
	IUnitOfWork _unitOfWork;

	@Test
	void testMsPagoInfraestructureService() {
		MsPagoInfraestructureService msPagoInfraestructureService = new MsPagoInfraestructureService();
		msPagoInfraestructureService =
			new MsPagoInfraestructureService(
				service,
				_deudaRepository,
				_unitOfWork
			);
		msPagoInfraestructureService.set_unitOfWork(_unitOfWork);
		msPagoInfraestructureService.setDeudaRepository(_deudaRepository);
		msPagoInfraestructureService.setService(service);

		assertEquals(service, msPagoInfraestructureService.getService());
		assertEquals(
			_deudaRepository,
			msPagoInfraestructureService.getDeudaRepository()
		);
		assertEquals(
			_unitOfWork,
			msPagoInfraestructureService.get_unitOfWork()
		);
	}
}
