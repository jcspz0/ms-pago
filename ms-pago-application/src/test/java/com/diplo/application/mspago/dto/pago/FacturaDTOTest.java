package com.diplo.application.mspago.dto.pago;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.model.deuda.Factura;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.mspago.valueobjects.Nit;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class FacturaDTOTest {

	@Test
	void testFacturaDTO() {
		String detalle = "detalle";
		double total = 10;
		int nit = 47315;
		String codigoFactura = UUID.randomUUID().toString();
		Factura factura = new Factura(
			new DetallePago(detalle),
			new Monto(total),
			new Nit(nit)
		);
		FacturaDTO facturaDTO = new FacturaDTO(factura);
		assertEquals(detalle, facturaDTO.getDetalle());
		assertEquals(total, facturaDTO.getTotal());
		assertEquals(nit, facturaDTO.getNit());

		facturaDTO = new FacturaDTO(detalle, total, nit, codigoFactura);
		assertEquals(detalle, facturaDTO.getDetalle());
		assertEquals(total, facturaDTO.getTotal());
		assertEquals(nit, facturaDTO.getNit());
		assertEquals(codigoFactura, facturaDTO.getCodigoFactura());

		facturaDTO = new FacturaDTO();
		facturaDTO.setCodigoFactura(codigoFactura);
		facturaDTO.setDetalle(detalle);
		facturaDTO.setNit(nit);
		facturaDTO.setTotal(total);

		assertEquals(detalle, facturaDTO.getDetalle());
		assertEquals(total, facturaDTO.getTotal());
		assertEquals(nit, facturaDTO.getNit());
		assertEquals(codigoFactura, facturaDTO.getCodigoFactura());
	}
}
