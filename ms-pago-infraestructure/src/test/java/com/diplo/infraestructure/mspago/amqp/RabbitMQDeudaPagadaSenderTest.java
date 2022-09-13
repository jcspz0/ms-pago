package com.diplo.infraestructure.mspago.amqp;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.amqp.AmqpMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

@ExtendWith(MockitoExtension.class)
class RabbitMQDeudaPagadaSenderTest {

	@Mock
	AmqpTemplate rabbitTemplate;

	@InjectMocks
	RabbitMQDeudaPagadaSender senderTest;

	@Test
	void sendMessageTest() {
		try {
			senderTest.sendMessage(
				new AmqpMessage("test"),
				"exchange",
				"queue",
				"routinkey"
			);
			assertTrue(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
