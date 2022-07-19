package com.diplo.mspago.model.deuda;

import com.diplo.mspago.event.ConfirmarReserva;
import com.diplo.mspago.event.DeudaCreada;
import com.diplo.mspago.event.PagoRealizado;
import com.diplo.mspago.event.VencerReserva;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.mspago.valueobjects.Nit;
import com.diplo.sharekernel.core.AggregateRoot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Deuda extends AggregateRoot<UUID> {

	private String Estado;
	private UUID ReservaId;
	private Monto Total;
	private List<Pago> ListaPagos;

	public Deuda() {
		super();
	}

	public Deuda(UUID reservaId, Monto total) {
		super();
		Estado = "INICIADA";
		ReservaId = reservaId;
		Total = total;
		ListaPagos = new ArrayList<>();
		crearDeuda();
	}

	public Deuda(UUID deudaID, UUID reservaId, Monto total) {
		super();
		Id = deudaID;
		Estado = "INICIADA";
		ReservaId = reservaId;
		Total = total;
		ListaPagos = new ArrayList<>();
		crearDeuda();
	}

	public Deuda(
		UUID deudaID,
		UUID reservaId,
		Monto total,
		String estado,
		List pagos
	) {
		super();
		Id = deudaID;
		Estado = estado;
		ReservaId = reservaId;
		Total = total;
		ListaPagos = pagos;
		crearDeuda();
	}

	public Deuda(Deuda obj) {
		this.Id = obj.getId();
		this.Estado = obj.getEstado();
		this.ReservaId = obj.getReservaId();
		this.Total = obj.getTotal();
		this.ListaPagos = new ArrayList<>(obj.getListaPagos());
		crearDeuda();
	}

	public String getEstado() {
		return Estado;
	}

	public UUID getReservaId() {
		return ReservaId;
	}

	public Monto getTotal() {
		return Total;
	}

	public List<Pago> getListaPagos() {
		/*List<Pago> unmodifiableList = (List<Pago>) Collections.unmodifiableList(ListaPagos);
		return unmodifiableList;*/
		return this.ListaPagos;
	}

	public boolean RealizarPago(Pago pago) {
		boolean result = false;
		result =
			ActualizarEstado(
				consultarDeuda(),
				pago.getMontoPagado().getMonto()
			);
		if (result) {
			ListaPagos.add(pago);
			AddDomainEvent(
				new PagoRealizado(
					this.Id,
					this.ReservaId,
					this.Total,
					pago,
					this.Estado
				)
			);
		}
		return result;
	}

	public void crearDeuda() {
		AddDomainEvent(
			new DeudaCreada(this.Id, this.ReservaId, this.Total, this.Estado)
		);
	}

	private boolean ActualizarEstado(double deuda, double pago) {
		System.out.println("deuda:" + deuda + ", pago:" + pago);
		if (
			(this.Estado.compareTo("VENCIDA") == 0) || deuda < pago || pago == 0
		) {
			return false;
		}
		if (deuda - pago == 0) {
			if (this.Estado.compareTo("RESERVADA") != 0) {
				AddDomainEvent(new ConfirmarReserva(this.ReservaId));
			}
			this.Estado = "PAGADA";
			return true;
		}
		if (deuda - pago <= this.Total.getMonto() / 2) {
			this.Estado = "RESERVADA";
			AddDomainEvent(new ConfirmarReserva(this.ReservaId));
			return true;
		}

		return true;
	}

	public double consultarDeuda() {
		if (ListaPagos.isEmpty()) {
			return Total.getMonto();
		} else {
			double deuda = this.Total.getMonto();
			for (
				Iterator<Pago> pagos = ListaPagos.iterator();
				pagos.hasNext();
			) {
				Pago pago = (Pago) pagos.next();
				deuda = deuda - pago.getMontoPagado().getMonto();
			}
			return deuda;
		}
	}

	public Factura GenerarFactura(String detalle, int nit) {
		if (this.Estado.equalsIgnoreCase("PAGADA")) {
			Factura factura = new Factura(
				new DetallePago(detalle),
				this.Total,
				new Nit(nit)
			);
			return factura;
		} else {
			System.out.println(
				"No se puede generar la factura debido a que la deuda no ha sido cancela al 100%"
			);
			return null;
		}
	}

	public boolean VencerDeuda() {
		System.out.println("Estado en metodo VencerDeuda:" + this.Estado);

		if (this.Estado.compareTo("PAGADA") == 0) {
			return false;
		}
		double umbral = 50;
		double deuda = consultarDeuda();
		double total = this.getTotal().getMonto();
		double totalDeudaPorcentual = ((total - deuda) * 100 / total);
		System.out.println("total en metodo VencerDeuda:" + total);
		System.out.println("deuda en metodo VencerDeuda:" + deuda);
		System.out.println(
			"totalDeudaPorcentual en metodo VencerDeuda:" + totalDeudaPorcentual
		);
		System.out.println("Estado en metodo VencerDeuda:" + this.Estado);
		if (totalDeudaPorcentual <= umbral) {
			this.Estado = "VENCIDA";
			System.out.println("Estado dentro del umbral:" + this.Estado);
			AddDomainEvent(new VencerReserva(this.ReservaId));
			return true;
		}
		return false;
	}
}
