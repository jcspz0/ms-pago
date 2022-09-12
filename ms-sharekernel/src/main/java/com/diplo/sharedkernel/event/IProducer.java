package com.diplo.sharedkernel.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
//public interface IProducer<T extends ApplicationEvent> {
public interface IProducer<T> {
	@EventListener
	void onProcessEvent(T event);
}
