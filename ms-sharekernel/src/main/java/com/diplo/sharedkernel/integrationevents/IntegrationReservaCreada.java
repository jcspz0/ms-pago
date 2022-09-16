package com.diplo.sharedkernel.integrationevents;

import com.diplo.sharedkernel.event.IntegrationEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

//public class IntegrationReservaCreada extends IntegrationEvent  {
public class IntegrationReservaCreada {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("nroReserva")
	private String nroReserva;

	@JsonProperty("reservaId")
	private String reservaId;

	@JsonProperty("vueloId")
	private String vueloId;

	@JsonProperty("pasajero")
	private String pasajero;

	@JsonProperty("hora")
	private String hora;

	@JsonProperty("cantidadPasajeros")
	private int cantidadPasajeros;

	@JsonProperty("total")
	private double total;

	public IntegrationReservaCreada() {
		super();
	}

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
		this.nroReserva = nroReserva;
		this.reservaId = reservaId;
		this.vueloId = vueloId;
		this.pasajero = pasajeroId;
		this.hora = hora;
		this.cantidadPasajeros = cantidadPasajeros;
		this.total = total;
	}

	public String getNroReserva() {
		return nroReserva;
	}

	public String getReservaId() {
		return reservaId;
	}

	public String getVueloId() {
		return vueloId;
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
