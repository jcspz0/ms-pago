package com.diplo.application.mspago.usecase.producer.integration;

import com.diplo.sharedkernel.amqp.AmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.event.IProducer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaCreada;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProducerIntegrationDeudaPagada
	implements IProducer<IntegrationDeudaPagada> {

	@Autowired
	private IAmqpProducer<IntegrationDeudaPagada> amqp;

	@Value("pago.deudapagada.reserva.confirmarreserva")
	String queueName;

	@Value("pago.deudapagada.exchange")
	String exchange;

	@Value("pago.deudapagada.reserva.confirmarreserva")
	private String routingkey;

	@EventListener
	public void onProcessEvent(IntegrationDeudaPagada event) {
		// TODO Auto-generated method stub
		System.out.println(
			"ProducerIntegrationDeudaPagada, llego evento de integracion para enviar " +
			event
		);
		amqp.sendMessage(
			new AmqpMessage(event),
			exchange,
			queueName,
			routingkey
		);
	}
}
