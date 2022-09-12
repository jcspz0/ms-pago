package com.diplo.application.mspago.usecase.consumer.integration;

import com.diplo.application.mspago.service.deuda.IDeudaService;
import com.diplo.application.mspago.usecase.command.deuda.creardeuda.CrearDeudaCommand;
import com.diplo.application.mspago.usecase.command.deuda.creardeuda.CrearDeudaHandler;
import com.diplo.application.mspago.usecase.command.deuda.deudapagadarollback.DeudaPagadaRollbackCommand;
import com.diplo.application.mspago.usecase.command.deuda.deudapagadarollback.DeudaPagadaRollbackHandler;
import com.diplo.application.mspago.usecase.command.pago.confirmarpago.ConfirmarPagoCommand;
import com.diplo.application.mspago.usecase.command.pago.confirmarpago.ConfirmarPagoHandler;
import com.diplo.mspago.factory.deuda.IDeudaFactory;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import com.diplo.sharedkernel.amqp.IAmqpConsumer;
import com.diplo.sharedkernel.integrationevents.IntegrationCheckinCreado;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagadaRollback;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import com.diplo.sharedkernel.mediator.IMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerIntegrationDeudaPagadaRollback
	implements IAmqpConsumer<IntegrationDeudaPagadaRollback> {

	@Autowired
	private IMediator _mediator;

	@Autowired
	private IUnitOfWork _unitOfWork;

	@Autowired
	private IDeudaRepository _deudaRepository;

	@Override
	@EventListener
	public void Consume(IntegrationDeudaPagadaRollback event) {
		System.out.println(
			"ConsumerIntegrationDeudaPagadaRollback, llego evento de integracion " +
			event
		);
		try {
			DeudaPagadaRollbackCommand deudaPagadaRollbackCommand = new DeudaPagadaRollbackCommand(
				event.getReservaId(),
				event.getPagoId()
			);
			DeudaPagadaRollbackHandler deudaPagadaRollbackHandler = new DeudaPagadaRollbackHandler(
				_deudaRepository,
				_unitOfWork
			);
			_mediator.registrarComando(
				deudaPagadaRollbackCommand,
				deudaPagadaRollbackHandler
			);
			_mediator.Send(deudaPagadaRollbackCommand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
