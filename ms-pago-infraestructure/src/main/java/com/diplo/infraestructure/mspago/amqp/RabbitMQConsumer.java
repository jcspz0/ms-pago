package com.diplo.infraestructure.mspago.amqp;

import com.diplo.sharedkernel.amqp.AmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpConsumer;
import com.diplo.sharedkernel.event.IListenerIntegrationConsumer;
import com.diplo.sharedkernel.event.IListenerIntegrationTracker;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.integrationevents.IntegrationCheckinCreado;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagadaRollback;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

	@Autowired
	private IListenerIntegrationConsumer consumer;

	@RabbitListener(queues = "reserva.reservacreada.pago.creardeuda")
	public void reservaCreada(IntegrationReservaCreada event) {
		System.out.println("Recieved Message From RabbitMQ: " + event);
		System.out.println("Recieved Message ReservaId " + event.getReservaId());
		consumer.consume(new IntegrationEvent(event, null));
	}

	@RabbitListener(queues = "checkin.chekincreado.pago.confirmarpago")
	public void checkinCreado(IntegrationCheckinCreado event) {
		System.out.println("Recieved Message From RabbitMQ: " + event);
		System.out.println(
			"Recieved Message para confimarPago con la ReservaId " +
			event.getReservaId()
		);
		consumer.consume(new IntegrationEvent(event, null));
	}

	@RabbitListener(
		queues = "reserva.deudapagadarollback.pago.deudapagadarollback"
	)
	public void deudaPagadaRollback(IntegrationDeudaPagadaRollback event) {
		System.out.println("Recieved Message From RabbitMQ: " + event);
		System.out.println(
			"Recieved Message para deudaPagadaRollback con la ReservaId " +
			event.getReservaId() +
			" y el pagoId " +
			event.getPagoId()
		);
		consumer.consume(new IntegrationEvent(event, null));
	}
}
