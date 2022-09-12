package com.diplo.sharedkernel.integrationevents;

import com.diplo.sharedkernel.event.IntegrationEvent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

//public class IntegrationReservaCreada extends IntegrationEvent  {
public class IntegrationReservaConfirmada implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final String reservaId;
	private final int cantidadPasajeros;
	private final String hora;
	private final String vueloId;
	private final String destino;
	private final int nroDoc;
	private final int tipoDoc;
	private final String nombreCompletoPasajero;
	private final String pagoId;

	public IntegrationReservaConfirmada(
		String reservaId,
		String vueloId,
		int tipoDoc,
		int nroDoc,
		String nombreCompletoPasajero,
		String hora,
		String destino,
		int cantidadPasajeros,
		String pagoId
	) {
		//super("ReservaCreada",LocalDateTime.now().toString());
		this.reservaId = reservaId;
		this.cantidadPasajeros = cantidadPasajeros;
		this.hora = hora;
		this.vueloId = vueloId;
		this.destino = destino;
		this.nroDoc = nroDoc;
		this.tipoDoc = tipoDoc;
		this.nombreCompletoPasajero = nombreCompletoPasajero;
		this.pagoId = pagoId;
	}

	public String getReservaId() {
		return reservaId;
	}

	public int getCantidadPasajeros() {
		return cantidadPasajeros;
	}

	public String getHora() {
		return hora;
	}

	public String getVueloId() {
		return vueloId;
	}

	public String getDestino() {
		return destino;
	}

	public int getNroDoc() {
		return nroDoc;
	}

	public int getTipoDoc() {
		return tipoDoc;
	}

	public String getNombreCompletoPasajero() {
		return nombreCompletoPasajero;
	}

	public String getPagoId() {
		return pagoId;
	}
}
