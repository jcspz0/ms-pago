package com.diplo.sharedkernel.amqp;

public class AmqpMessage implements IAmqpMessage {

	private Object message;

	public AmqpMessage(Object message) {
		super();
		this.message = message;
	}

	@Override
	public Object getMessage() {
		return message;
	}
}
