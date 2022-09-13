package com.diplo.sharedkernel.event;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.context.ApplicationEvent;

public abstract class DomainEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	public LocalDateTime OccuredOn;
	public UUID Id;
	public Object message;

	public DomainEvent(Object message, LocalDateTime occuredOn) {
		super(message);
		OccuredOn = occuredOn;
	}

	public LocalDateTime getOccuredOn() {
		return OccuredOn;
	}

	public UUID getId() {
		return Id;
	}

	public Object getMessage() {
		return message;
	}
}
