package com.diplo.mspago.model.deuda;

import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharedkernel.core.Entity;
import java.util.UUID;

public class Recibo extends Entity<UUID> {

	private DetallePago Detalle;
	private Monto Total;

	public Recibo(DetallePago detalle, Monto total) {
		super();
		Detalle = detalle;
		Total = total;
	}

	public DetallePago getDetalle() {
		return Detalle;
	}

	public Monto getTotal() {
		return Total;
	}

	public String Imprimir() {
		return (
			"Imprimiendo recibo del pago por concepto de: " +
			this.Detalle.getDetalle() +
			" con el monto: " +
			this.Total.getMonto()
		);
	}
}
