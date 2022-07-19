package com.diplo.infraestructure.mspago.entityframework.tracker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MessageEventTest {

	@Test
	void testMessageEvent() {
		Object message = new Object();
		String accion = "accion";
		MessageEvent messageEvent = new MessageEvent(message, accion);

		assertEquals(message, messageEvent.getMessage());
		assertEquals(accion, messageEvent.getAction());
	}
}
