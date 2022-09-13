package com.diplo.sharedkernel.event;

import org.springframework.context.ApplicationEvent;

public class IntegrationMessage extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	Object message;
	String action;

	public IntegrationMessage(Object source, String action) {
		super(source);
		this.message = source;
		this.action = action;
	}

	public Object getMessage() {
		return this.message;
	}

	public String getAction() {
		return this.action;
	}
}
