package com.diplo.sharedkernel.event;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.context.ApplicationEvent;

public class IntegrationEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	public String OccuredOn;
	public String Id;
	public Object message;

	public IntegrationEvent(Object message, String occuredOn) {
		super(message);
		this.message = message;
		OccuredOn = occuredOn;
	}

	public String getOccuredOn() {
		return OccuredOn;
	}

	public String getId() {
		return Id;
	}

	public Object getMessage() {
		return message;
	}
}
