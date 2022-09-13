package com.diplo.infraestructure.mspago.entityframework.dbrepository.entity;

import com.diplo.mspago.model.deuda.Pago;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pago")
public class PagoEntity {

	@Id
	private String PagoId;

	private double MontoPagado;
	private String detalle;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "deuda_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private DeudaEntity deuda;

	public PagoEntity() {
		super();
	}

	public PagoEntity(String pagoId, double montoPagado, String detalle) {
		super();
		PagoId = pagoId;
		MontoPagado = montoPagado;
		this.detalle = detalle;
	}

	public PagoEntity(
		String pagoId,
		double montoPagado,
		String detalle,
		DeudaEntity deuda
	) {
		super();
		PagoId = pagoId;
		MontoPagado = montoPagado;
		this.detalle = detalle;
		this.deuda = deuda;
	}

	public PagoEntity(Pago pago) {
		super();
		this.PagoId = pago.getId().toString();
		this.MontoPagado = pago.getMontoPagado().getMonto();
		this.detalle = pago.getRecibo().getDetalle().getDetalle();
	}

	public PagoEntity(Pago pago, DeudaEntity deuda) {
		super();
		this.PagoId = pago.getId().toString();
		this.MontoPagado = pago.getMontoPagado().getMonto();
		this.detalle = pago.getRecibo().getDetalle().getDetalle();
		this.deuda = deuda;
	}

	public String getPagoId() {
		return PagoId;
	}

	public void setPagoId(String pagoId) {
		PagoId = pagoId;
	}

	public double getMontoPagado() {
		return MontoPagado;
	}

	public void setMontoPagado(double montoPagado) {
		MontoPagado = montoPagado;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public DeudaEntity getDeuda() {
		return deuda;
	}

	public void setDeuda(DeudaEntity deuda) {
		this.deuda = deuda;
	}
}
