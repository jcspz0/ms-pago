package com.diplo.mspago.factory.pago;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;

public interface IPagoFactory {
	Pago Create(
		String pagoID,
		double totalPagado,
		String detalle,
		String deudaId
	);
}
