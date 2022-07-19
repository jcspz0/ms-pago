package com.diplo.mspago.factory.pago;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import java.util.UUID;

public class PagoFactory implements IPagoFactory {

	@Override
	public Pago Create(
		String pagoID,
		double totalPagado,
		String detalle,
		String deudaId
	) {
		return new Pago(
			UUID.fromString(pagoID),
			new Monto(totalPagado),
			new DetallePago(detalle),
			UUID.fromString(deudaId)
		);
	}
}
