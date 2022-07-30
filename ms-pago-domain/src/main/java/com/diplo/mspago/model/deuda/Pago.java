package com.diplo.mspago.model.deuda;

import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharekernel.core.Entity;
import java.util.UUID;

public class Pago extends Entity<UUID> {

	private Monto MontoPagado;
	private Recibo Recibo;
	private UUID DeudaId;

	public Pago(Monto montoPagado, DetallePago detalle, UUID deudaId) {
		super();
		Id = UUID.randomUUID();
		MontoPagado = montoPagado;
		Recibo = new Recibo(detalle, montoPagado);
		this.DeudaId = deudaId;
	}

	/*public Pago(UUID deudaId ,Monto montoPagado, String detalle) {
		super();
		Id = deudaId;
		MontoPagado = montoPagado;
		Recibo = new Recibo(new DetallePago(detalle), montoPagado);
	}*/

	public Pago(
		UUID pagoId,
		Monto montoPagado,
		DetallePago detalle,
		UUID deudaId
	) {
		super();
		Id = pagoId;
		MontoPagado = montoPagado;
		Recibo = new Recibo(detalle, montoPagado);
		this.DeudaId = deudaId;
	}

	public Monto getMontoPagado() {
		return MontoPagado;
	}

	public Recibo getRecibo() {
		return Recibo;
	}

	public UUID getDeudaId() {
		return DeudaId;
	}

	public String ImprimirRecibo() {
		return Recibo.Imprimir();
	}

	public String ImprimirRecibo2() {
		return Recibo.Imprimir();
	}
}
