package com.diplo.infraestructure.mspago.entityframework.tracker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DomainMessageTest {

	@Test
	void testDomainMessage() {
		Object message = new Object();
		String action = "accion";
		DomainMessage domainMessage = new DomainMessage(message, action);
		assertEquals(message, domainMessage.getMessage());
		assertEquals(action, domainMessage.getAction());
	}
}
