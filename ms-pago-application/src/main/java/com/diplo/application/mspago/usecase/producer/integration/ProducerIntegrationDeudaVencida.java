package com.diplo.application.mspago.usecase.producer.integration;

import com.diplo.sharedkernel.amqp.AmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.event.IProducer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaCreada;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProducerIntegrationDeudaVencida
	implements IProducer<IntegrationDeudaVencida> {

	@Autowired
	private IAmqpProducer<IntegrationDeudaVencida> amqp;

	@Value("pago.deudavencida.reserva.vencerreserva")
	String queueName;

	@Value("pago.deudavencida.exchange")
	String exchange;

	@Value("pago.deudavencida.reserva.vencerreserva")
	private String routingkey;

	@EventListener
	public void onProcessEvent(IntegrationDeudaVencida event) {
		// TODO Auto-generated method stub
		System.out.println(
			"ProducerIntegrationDeudaVencida, llego evento de integracion para enviar " +
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
