package com.diplo.infraestructure.mspago.entityframework.tracker;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.model.deuda.Deuda;
import org.junit.jupiter.api.Test;

class ListenerEventTrackerTest {

	@Test
	void testOnApplicationEvent() {
		ListenerEventTracker listenerEventTracker = new ListenerEventTracker();

		listenerEventTracker.clearTracker();
		listenerEventTracker.onApplicationEvent(
			new MessageEvent(listenerEventTracker, "accion")
		);

		assertNotNull(listenerEventTracker);
		assertNotNull(listenerEventTracker.getTrackersCargados());
	}
}
