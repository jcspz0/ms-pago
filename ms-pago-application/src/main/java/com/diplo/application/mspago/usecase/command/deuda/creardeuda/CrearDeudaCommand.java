package com.diplo.application.mspago.usecase.command.deuda.creardeuda;

import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.application.mspago.mediator.request.IRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrearDeudaCommand implements IRequest<UUID> {

	public CrearDeudaCommand() {}

	private String deudaId;
	private String estado;
	private String reservaId;
	private double total;
	private List<PagoDTO> ListaPagos;

	public CrearDeudaCommand(
		String reservaId,
		double total,
		List<PagoDTO> listaPagos
	) {
		super();
		this.reservaId = reservaId;
		this.total = total;
		ListaPagos = listaPagos;
	}

	public CrearDeudaCommand(
		String deudaId,
		String reservaId,
		double total,
		List<PagoDTO> listaPagos
	) {
		super();
		this.deudaId = deudaId;
		this.reservaId = reservaId;
		this.total = total;
		ListaPagos = listaPagos;
	}

	public CrearDeudaCommand(String reservaId, double total) {
		super();
		this.reservaId = reservaId;
		this.total = total;
		ListaPagos = new ArrayList<PagoDTO>();
	}

	public CrearDeudaCommand(String deudaId, String reservaId, double total) {
		super();
		this.deudaId = deudaId;
		this.reservaId = reservaId;
		this.total = total;
		ListaPagos = new ArrayList<PagoDTO>();
	}

	public String getDeudaId() {
		return deudaId;
	}

	public void setDeudaId(String deudaId) {
		this.deudaId = deudaId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getReservaId() {
		return reservaId;
	}

	public void setReservaId(String reservaId) {
		this.reservaId = reservaId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<PagoDTO> getListaPagos() {
		return ListaPagos;
	}

	public void setListaPagos(List<PagoDTO> listaPagos) {
		ListaPagos = listaPagos;
	}
}
