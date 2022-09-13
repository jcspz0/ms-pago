package com.diplo.mspago.factory.pago;

import com.diplo.mspago.event.DeudaCreada;
import com.diplo.mspago.event.PagoRealizado;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharedkernel.event.DomainEvent;
import java.util.UUID;

public class PagoFactory implements IPagoFactory {

	@Override
	public Pago Create(
		String pagoID,
		double totalPagado,
		String detalle,
		String deudaId
	) {
		Pago obj = new Pago(
			UUID.fromString(pagoID),
			new Monto(totalPagado),
			new DetallePago(detalle),
			UUID.fromString(deudaId)
		);

		DomainEvent domainEvent = new PagoRealizado(
			obj.getId(),
			obj.getDeudaId(),
			obj.getMontoPagado(),
			obj,
			deudaId
		);

		obj.AddDomainEvent(domainEvent);

		return obj;
	}
}
