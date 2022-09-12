package com.diplo.infraestructure.mspago.entityframework.tracker;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.infraestructure.mspago.tracker.ListenerEventTrackerInfra;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.sharedkernel.event.MessageEvent;
import org.junit.jupiter.api.Test;

class ListenerEventTrackerTest {

	@Test
	void testOnApplicationEvent() {
		ListenerEventTrackerInfra listenerEventTracker = new ListenerEventTrackerInfra();

		listenerEventTracker.clearTracker();
		listenerEventTracker.publish(
			new MessageEvent(listenerEventTracker, "accion")
		);

		assertNotNull(listenerEventTracker);
		assertNotNull(listenerEventTracker.getTrackersCargados());
	}
}
