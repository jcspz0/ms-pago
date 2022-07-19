package com.diplo.sharekernel.core;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DomainEvent {

	public LocalDateTime OccuredOn;
	public UUID Id;

	public DomainEvent(LocalDateTime occuredOn) {
		super();
		OccuredOn = occuredOn;
	}

	public LocalDateTime getOccuredOn() {
		return OccuredOn;
	}

	public UUID getId() {
		return Id;
	}
}
