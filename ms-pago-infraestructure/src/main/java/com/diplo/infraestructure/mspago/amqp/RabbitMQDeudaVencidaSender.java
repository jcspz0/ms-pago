package com.diplo.infraestructure.mspago.amqp;

import com.diplo.sharedkernel.amqp.IAmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQDeudaVencidaSender
	implements IAmqpProducer<IntegrationDeudaVencida> {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Override
	public void sendMessage(
		IAmqpMessage message,
		String exchange,
		String queue,
		String routingkey
	) {
		//rabbitTemplate.convertAndSend(this.exchange, "", message.getMessage());
		rabbitTemplate.convertAndSend(
			exchange,
			routingkey,
			message.getMessage()
		);
		System.out.println(
			"RabbitMQDeudaVencidaSender, Send msg = " + message.getMessage()
		);
	}
}
