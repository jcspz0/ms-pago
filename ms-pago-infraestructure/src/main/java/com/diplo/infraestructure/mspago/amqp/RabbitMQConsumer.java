package com.diplo.infraestructure.mspago.amqp;

import com.diplo.sharedkernel.amqp.AmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpConsumer;
import com.diplo.sharedkernel.event.IListenerIntegrationConsumer;
import com.diplo.sharedkernel.event.IListenerIntegrationTracker;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.integrationevents.IntegrationCheckinCreado;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagadaRollback;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

	@Autowired
	private IListenerIntegrationConsumer consumer;

	ObjectMapper mapper = new ObjectMapper();

	/*@RabbitListener(queues = "reserva.reservacreada.pago.creardeuda")
	public void reservaCreada(IntegrationReservaCreada event) {
		System.out.println("Recieved Message From RabbitMQ: " + event);
		System.out.println("Recieved Message ReservaId " + event.getReservaId());
		consumer.consume(new IntegrationEvent(event, null));
	}*/

	@RabbitListener(queues = "reserva.reservacreada.pago.creardeuda")
	public void reservaCreada(String event) {
		IntegrationReservaCreada message;
		try {
			message = mapper.readValue(event, IntegrationReservaCreada.class);
			System.out.println("Recieved Message From RabbitMQ: " + message);
			System.out.println(
				"Recieved Message ReservaId " + message.getReservaId()
			);
			consumer.consume(new IntegrationEvent(message, null));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RabbitListener(queues = "checkin.chekincreado.pago.confirmarpago")
	public void checkinCreado(String event) {
		IntegrationCheckinCreado message;
		try {
			message = mapper.readValue(event, IntegrationCheckinCreado.class);
			System.out.println("Recieved Message From RabbitMQ: " + event);
			System.out.println(
				"Recieved Message para confimarPago con la ReservaId " +
				message.getReservaId()
			);
			consumer.consume(new IntegrationEvent(message, null));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RabbitListener(
		queues = "reserva.deudapagadarollback.pago.deudapagadarollback"
	)
	public void deudaPagadaRollback(String event) {
		IntegrationDeudaPagadaRollback message;
		try {
			message =
				mapper.readValue(event, IntegrationDeudaPagadaRollback.class);
			System.out.println("Recieved Message From RabbitMQ: " + event);
			System.out.println(
				"Recieved Message para deudaPagadaRollback con la ReservaId " +
				message.getReservaId() +
				" y el pagoId " +
				message.getPagoId()
			);
			consumer.consume(new IntegrationEvent(message, null));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
