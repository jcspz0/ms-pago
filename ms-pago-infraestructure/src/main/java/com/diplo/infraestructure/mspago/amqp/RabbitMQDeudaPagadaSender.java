package com.diplo.infraestructure.mspago.amqp;

import com.diplo.sharedkernel.amqp.IAmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQDeudaPagadaSender
	implements IAmqpProducer<IntegrationDeudaPagada> {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	ObjectMapper Obj = new ObjectMapper();

	@Override
	public void sendMessage(
		IAmqpMessage message,
		String exchange,
		String queue,
		String routingkey
	) {
		//rabbitTemplate.convertAndSend(this.exchange, "", message.getMessage());
		try {
			rabbitTemplate.convertAndSend(
				exchange,
				routingkey,
				Obj.writeValueAsString(
					(IntegrationDeudaPagada) message.getMessage()
				)
			);
			System.out.println(
				"RabbitMQDeudaPagadaSender, Send msg = " + message.getMessage()
			);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AmqpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
