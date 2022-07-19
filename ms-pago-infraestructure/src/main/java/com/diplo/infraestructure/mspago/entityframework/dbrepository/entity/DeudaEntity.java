package com.diplo.infraestructure.mspago.entityframework.dbrepository.entity;

//import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.converter.PagoConverter;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "deuda")
public class DeudaEntity {

	@Id
	private String DeudaId;

	private String ReservaId;
	private double Total;
	private String Estado;

	@OneToMany(
		mappedBy = "deuda",
		fetch = FetchType.EAGER,
		cascade = CascadeType.ALL
	)
	//@Convert(converter = PagoConverter.class)
	private List<PagoEntity> ListaPagos;

	public DeudaEntity() {}

	public DeudaEntity(
		String deudaId,
		String reservaId,
		double total,
		String estado,
		List<PagoEntity> listaPagos
	) {
		super();
		DeudaId = deudaId;
		ReservaId = reservaId;
		this.Total = total;
		this.Estado = estado;
		ListaPagos = listaPagos;
	}

	public DeudaEntity(Deuda deuda) {
		super();
		DeudaId = deuda.getId().toString();
		ReservaId = deuda.getReservaId().toString();
		this.Estado = deuda.getEstado();
		this.Total = deuda.getTotal().getMonto();
		this.ListaPagos = new ArrayList<PagoEntity>();
		for (Pago pagoEntity : deuda.getListaPagos()) {
			this.ListaPagos.add(new PagoEntity(pagoEntity, this));
		}
	}

	public String getDeudaId() {
		return DeudaId;
	}

	public void setDeudaId(String deudaId) {
		DeudaId = deudaId;
	}

	public String getReservaId() {
		return ReservaId;
	}

	public void setReservaId(String reservaId) {
		ReservaId = reservaId;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		this.Total = total;
	}

	//@OneToMany(targetEntity=PagoEntity.class, mappedBy="pago", fetch=FetchType.EAGER)
	public List<PagoEntity> getListaPagos() {
		return ListaPagos;
	}

	public void setListaPagos(List<PagoEntity> listaPagos) {
		ListaPagos = listaPagos;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
}
