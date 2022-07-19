package com.diplo.mspago.model.deuda;

import com.diplo.mspago.valueobjects.CodigoFactura;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.mspago.valueobjects.Nit;
import com.diplo.sharekernel.core.Entity;
import java.util.UUID;

public class Factura extends Entity<UUID> {

	private DetallePago Detalle;
	private Monto Total;
	private Nit Nit;
	private CodigoFactura CodigoFactura;

	public Factura(DetallePago detalle, Monto total, Nit nit) {
		super();
		CodigoFactura = new CodigoFactura(UUID.randomUUID().toString());
		Detalle = detalle;
		Total = total;
		Nit = nit;
	}

	public DetallePago getDetalle() {
		return Detalle;
	}

	public Monto getTotal() {
		return Total;
	}

	public Nit getNit() {
		return Nit;
	}

	public CodigoFactura getCodigoFactura() {
		return CodigoFactura;
	}

	public String Imprimir() {
		System.out.print(
			"Imprimiendo Factura del pago por concepto de: " +
			this.Detalle.getDetalle() +
			" con el monto: " +
			this.Total.getMonto() +
			" al nit: " +
			this.Nit.getNroNit()
		);
		return (
			"Imprimiendo Factura del pago por concepto de: " +
			this.Detalle.getDetalle() +
			" con el monto: " +
			this.Total.getMonto() +
			" al nit: " +
			this.Nit.getNroNit()
		);
	}
}
