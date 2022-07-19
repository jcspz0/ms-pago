package com.diplo.application.mspago.dto.pago;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import java.util.ArrayList;
import java.util.List;

public class DeudaDTO {

	private String deudaId;
	private String estado;
	private String reservaId;
	private double total;
	private List<PagoDTO> ListaPagos;

	public DeudaDTO() {
		super();
	}

	public DeudaDTO(
		String deudaId,
		String estado,
		String reservaId,
		double total,
		List<PagoDTO> listaPagos
	) {
		super();
		this.deudaId = deudaId;
		this.estado = estado;
		this.reservaId = reservaId;
		this.total = total;
		ListaPagos = listaPagos;
	}

	public DeudaDTO(
		String estado,
		String reservaId,
		double total,
		List<PagoDTO> listaPagos
	) {
		super();
		this.estado = estado;
		this.reservaId = reservaId;
		this.total = total;
		ListaPagos = listaPagos;
	}

	public DeudaDTO(
		String deudaId,
		String estado,
		String reservaId,
		double total
	) {
		super();
		this.deudaId = deudaId;
		this.estado = estado;
		this.reservaId = reservaId;
		this.total = total;
		ListaPagos = new ArrayList<PagoDTO>();
	}

	public DeudaDTO(String estado, String reservaId, double total) {
		super();
		this.estado = estado;
		this.reservaId = reservaId;
		this.total = total;
		ListaPagos = new ArrayList<PagoDTO>();
	}

	public DeudaDTO(Deuda aux) {
		this.deudaId = aux.getId().toString();
		this.estado = aux.getEstado();
		this.reservaId = aux.getReservaId().toString();
		this.total = aux.getTotal().getMonto();
		this.ListaPagos = new ArrayList<PagoDTO>();
		for (Pago pago : aux.getListaPagos()) {
			ListaPagos.add(new PagoDTO(pago));
		}
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
