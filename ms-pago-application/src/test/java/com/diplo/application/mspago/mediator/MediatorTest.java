package com.diplo.application.mspago.mediator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.mediator.request.IRequestHandler;
import com.diplo.application.mspago.service.deuda.IDeudaService;
import com.diplo.application.mspago.usecase.command.deuda.creardeuda.CrearDeudaCommand;
import com.diplo.application.mspago.usecase.command.deuda.creardeuda.CrearDeudaHandler;
import com.diplo.application.mspago.usecase.query.deuda.getDeudaById.GetDeudaByIdHandler;
import com.diplo.application.mspago.usecase.query.deuda.getDeudaById.GetDeudaByIdQuery;
import com.diplo.mspago.factory.deuda.IDeudaFactory;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import com.diplo.mspago.valueobjects.Monto;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MediatorTest {

	//crearVueloHandlerTest;

	@Mock
	IDeudaRepository _deudaRepository;

	IRequestHandler requestHandler;
	GetDeudaByIdQuery getDeudaByIdQuery;
	GetDeudaByIdHandler getDeudaByIdHandler;

	@BeforeEach
	void init() throws Exception {
		getDeudaByIdQuery = new GetDeudaByIdQuery();
		getDeudaByIdHandler = new GetDeudaByIdHandler(_deudaRepository);
	}

	@Test
	void probarMediator() throws Exception {
		when(_deudaRepository.FindByIdAsync(any()))
			.thenReturn(
				CompletableFuture.completedFuture(
					new Deuda(
						UUID.randomUUID(),
						UUID.randomUUID(),
						new Monto(10)
					)
				)
			);

		Map<Class<?>, IRequestHandler> _lista = null;

		Mediator mediator = new Mediator(_lista);
		mediator = new Mediator();

		mediator.registrarComando(getDeudaByIdQuery, getDeudaByIdHandler);

		mediator.Send(getDeudaByIdQuery);

		assertNotNull(mediator);
	}
}
