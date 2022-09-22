package com.diplo.sharedkernel.integrationevents;

import com.diplo.sharedkernel.event.IntegrationEvent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

//public class IntegrationReservaCreada extends IntegrationEvent  {
public class IntegrationTripulacionCreada {

	/**
	 *
	 */
	private String tripulacionId;
	private String nombre;
	private int nroDoc;

	public IntegrationTripulacionCreada() {
		super();
	}

	public IntegrationTripulacionCreada(
		String tripulacionId,
		String nombre,
		int nroDoc
	) {
		super();
		this.tripulacionId = tripulacionId;
		this.nombre = nombre;
		this.nroDoc = nroDoc;
	}

	public String getTripulacionId() {
		return tripulacionId;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNroDoc() {
		return nroDoc;
	}
}
