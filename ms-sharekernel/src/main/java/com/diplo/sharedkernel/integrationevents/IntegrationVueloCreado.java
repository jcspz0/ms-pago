package com.diplo.sharedkernel.integrationevents;

import com.diplo.sharedkernel.event.IntegrationEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

//public class IntegrationReservaCreada extends IntegrationEvent  {
public class IntegrationVueloCreado {

	/**
	 *
	 */
	//private static final long serialVersionUID = 1L;
	@JsonProperty("IdVuelo")
	private String IdVuelo;

	@JsonProperty("Origen")
	private String Origen;

	@JsonProperty("Destino")
	private String Destino;

	@JsonProperty("HoraPartida")
	private String HoraPartida;

	@JsonProperty("HoraLlegada")
	private String HoraLlegada;

	@JsonProperty("IdAeronave")
	private String IdAeronave;

	//private int cantidadAsientosDisponibles;
	@JsonProperty("Timestamp")
	private String Timestamp;

	@JsonProperty("CantidadAsientos")
	private int CantidadAsientos;

	public IntegrationVueloCreado() {
		super();
	}

	public IntegrationVueloCreado(
		String idVuelo,
		String origen,
		String destino,
		String horaPartida,
		String horaLlegada,
		String idAeronave,
		String timestamp,
		int cantidadAsientos
	) {
		super();
		IdVuelo = idVuelo;
		Origen = origen;
		Destino = destino;
		HoraPartida = horaPartida;
		HoraLlegada = horaLlegada;
		IdAeronave = idAeronave;
		Timestamp = timestamp;
		CantidadAsientos = cantidadAsientos;
	}

	public String getIdVuelo() {
		return IdVuelo;
	}

	public String getOrigen() {
		return Origen;
	}

	public String getDestino() {
		return Destino;
	}

	public String getHoraPartida() {
		return HoraPartida;
	}

	public String getHoraLlegada() {
		return HoraLlegada;
	}

	public String getIdAeronave() {
		return IdAeronave;
	}

	public String getTimestamp() {
		return Timestamp;
	}

	public int getCantidadAsientosDisponibles() {
		return CantidadAsientos;
	}

	public void setCantidadAsientosDisponibles(int cantidadAsientosDisponibles) {
		this.CantidadAsientos = cantidadAsientosDisponibles;
	}
}
