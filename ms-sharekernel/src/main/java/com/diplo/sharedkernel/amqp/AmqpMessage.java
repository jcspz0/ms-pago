package com.diplo.sharedkernel.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AmqpMessage implements IAmqpMessage {

	private Object message;
	ObjectMapper Obj = new ObjectMapper();

	public AmqpMessage(Object message) {
		super();
		this.message = message;
	}

	@Override
	public Object getMessage() {
		return message;
	}
}
