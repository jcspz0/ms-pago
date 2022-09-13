package com.diplo.mspago.model.deuda;

import com.diplo.mspago.event.DeudaCreada;
import com.diplo.mspago.event.DeudaPagada;
import com.diplo.mspago.event.DeudaVencida;
import com.diplo.mspago.event.PagoRealizado;
import com.diplo.mspago.event.ReservaConfirmada;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.mspago.valueobjects.Nit;
import com.diplo.sharedkernel.core.AggregateRoot;
import com.diplo.sharedkernel.core.Constant;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import java.time.LocalDateTime;
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
		Estado = Constant.DEUDAESTADOINICIADA;
		ReservaId = reservaId;
		Total = total;
		ListaPagos = new ArrayList<>();
		//crearDeuda();
	}

	public Deuda(UUID deudaID, UUID reservaId, Monto total) {
		super();
		Id = deudaID;
		Estado = Constant.DEUDAESTADOINICIADA;
		ReservaId = reservaId;
		Total = total;
		ListaPagos = new ArrayList<>();
		//crearDeuda();
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
		//crearDeuda();
	}

	public Deuda(Deuda obj) {
		this.Id = obj.getId();
		this.Estado = obj.getEstado();
		this.ReservaId = obj.getReservaId();
		this.Total = obj.getTotal();
		this.ListaPagos = new ArrayList<>(obj.getListaPagos());
		//crearDeuda();
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
		result = ActualizarEstado(consultarDeuda(), pago);
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

	/*
	public void crearDeuda() {
		AddDomainEvent(new DeudaCreada(this.Id, this.ReservaId, this.Total, this.Estado));
	}*/

	private boolean ActualizarEstado(double deuda, Pago pagoRealizado) {
		double pago = pagoRealizado.getMontoPagado().getMonto();
		System.out.println("deuda:" + deuda + ", pago:" + pago);
		if (
			(this.Estado.compareTo(Constant.DEUDAESTADOVENCIDA) == 0) ||
			deuda < pago ||
			pago == 0
		) {
			return false;
		}
		if (deuda - pago == 0) {
			if (this.Estado.compareTo(Constant.DEUDAESTADORESERVADA) != 0) {
				AddDomainEvent(new ReservaConfirmada(this.ReservaId));
			}
			//this.Estado = Constant.DEUDAESTADOPAGADA;
			this.Estado = Constant.DEUDAESTADOPROCESANDO;
			AddDomainEvent(new DeudaPagada(this.ReservaId));
			AddIntegrationEvent(
				new IntegrationEvent(
					new IntegrationDeudaPagada(
						this.ReservaId.toString(),
						pagoRealizado.getId().toString()
					),
					LocalDateTime.now().toString()
				)
			);
			return true;
		}
		if (deuda - pago <= this.Total.getMonto() / 2) {
			this.Estado = Constant.DEUDAESTADORESERVADA;
			AddDomainEvent(new ReservaConfirmada(this.ReservaId));
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
		if (this.Estado.equalsIgnoreCase(Constant.DEUDAESTADOPAGADA)) {
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
		if (this.Estado.compareTo(Constant.DEUDAESTADOINICIADA) == 0) {
			double umbral = 50;
			double deuda = consultarDeuda();
			double total = this.getTotal().getMonto();
			double totalDeudaPorcentual = ((total - deuda) * 100 / total);
			System.out.println("total en metodo VencerDeuda:" + total);
			System.out.println("deuda en metodo VencerDeuda:" + deuda);
			System.out.println(
				"totalDeudaPorcentual en metodo VencerDeuda:" +
				totalDeudaPorcentual
			);
			System.out.println("Estado en metodo VencerDeuda:" + this.Estado);
			if (totalDeudaPorcentual <= umbral) {
				this.Estado = Constant.DEUDAESTADOVENCIDA;
				System.out.println("Estado dentro del umbral:" + this.Estado);
				AddDomainEvent(new DeudaVencida(this.ReservaId));
				AddIntegrationEvent(
					new IntegrationEvent(
						new IntegrationDeudaVencida(this.ReservaId.toString()),
						LocalDateTime.now().toString()
					)
				);
				return true;
			}
		}
		return false;
	}

	public void confirmarPago() throws Exception {
		if (this.Estado.compareTo(Constant.DEUDAESTADOPROCESANDO) != 0) {
			throw new Exception(
				"La deuda no est� en el estado pendiente para ser completada"
			);
		}
		this.Estado = Constant.DEUDAESTADOPAGADA;
	}

	public void anularPago(String pagoId) {
		Pago aux = null;
		System.out.println("se va a anular el pagoId " + pagoId);
		System.out.println("tamano inicial " + this.ListaPagos.size());
		for (Pago pago : ListaPagos) {
			System.out.println("for de pagos, " + pago.getId().toString());
			if (pago.getId().toString().compareTo(pagoId) == 0) {
				System.out.println("se encontro el pago a anular");
				aux = pago;
				break;
			}
		}
		if (aux != null) {
			boolean eliminado = this.ListaPagos.remove(aux);
			System.out.println(
				"se anulo el pago " +
				aux.getId().toString() +
				" el resultado fue " +
				eliminado
			);
			if (this.Estado.compareTo(Constant.DEUDAESTADOPROCESANDO) == 0) {
				ActualizarEstadoRollback(consultarDeuda());
				System.out.println(
					"se actualizo el estado " +
					eliminado +
					" el estado actual es " +
					this.Estado
				);
				System.out.println("tamano final " + this.ListaPagos.size());
			}
		}
	}

	private void ActualizarEstadoRollback(double deudoActual) {
		if (deudoActual >= this.Total.getMonto() / 2) {
			this.Estado = Constant.DEUDAESTADOINICIADA;
		} else {
			this.Estado = Constant.DEUDAESTADORESERVADA;
		}
	}
}
