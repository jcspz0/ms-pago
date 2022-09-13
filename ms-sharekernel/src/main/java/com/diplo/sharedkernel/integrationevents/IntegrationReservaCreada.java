package com.diplo.sharedkernel.integrationevents;

import com.diplo.sharedkernel.event.IntegrationEvent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

//public class IntegrationReservaCreada extends IntegrationEvent  {
public class IntegrationReservaCreada implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final String NroReserva;
	private final String ReservaId;
	private final String VueloId;
	private final String pasajero;
	private final String hora;
	private final int cantidadPasajeros;
	private final double total;

	public IntegrationReservaCreada(
		String reservaId,
		String nroReserva,
		String vueloId,
		int cantidadPasajeros,
		String pasajeroId,
		String hora,
		double total
	) {
		//super("ReservaCreada",LocalDateTime.now().toString());
		NroReserva = nroReserva;
		ReservaId = reservaId;
		VueloId = vueloId;
		this.pasajero = pasajeroId;
		this.hora = hora;
		this.cantidadPasajeros = cantidadPasajeros;
		this.total = total;
	}

	public String getNroReserva() {
		return NroReserva;
	}

	public String getReservaId() {
		return ReservaId;
	}

	public String getVueloId() {
		return VueloId;
	}

	public String getPasajero() {
		return pasajero;
	}

	public String getHora() {
		return hora;
	}

	public int getCantidadPasajeros() {
		return cantidadPasajeros;
	}

	public double getTotal() {
		return total;
	}
}
