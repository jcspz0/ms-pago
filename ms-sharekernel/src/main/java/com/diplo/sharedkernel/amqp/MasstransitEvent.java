package com.diplo.sharedkernel.amqp;

import java.util.List;

public class MasstransitEvent {

	private List<String> messageType;
	private Object message;

	public List<String> getMessageType() {
		return messageType;
	}

	public void setMessageType(List<String> messageType) {
		this.messageType = messageType;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
}
