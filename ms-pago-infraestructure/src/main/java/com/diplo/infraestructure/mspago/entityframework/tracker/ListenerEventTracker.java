package com.diplo.infraestructure.mspago.entityframework.tracker;

import com.diplo.mspago.model.deuda.Deuda;
import java.util.ArrayList;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerEventTracker {

	ArrayList<MessageEvent> lista = new ArrayList<MessageEvent>();

	@EventListener
	public void onApplicationEvent(MessageEvent event) {
		//Deuda aux = (Deuda) event.getMessage();
		//System.out.println("ListenerEventTracker->Se recibió el evento " + aux.getId() + " con elevento "+event.getMessage());
		lista.add(event);
		System.out.println(
			"ListenerEventTracker->Cantidad en lista " + lista.size()
		);
	}

	public ArrayList<MessageEvent> getTrackersCargados() {
		return lista;
	}

	public void clearTracker() {
		lista = new ArrayList<MessageEvent>();
	}
}
