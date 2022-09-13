package com.diplo.mspago.model.deuda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.doubleThat;

import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharedkernel.core.Constant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeudaTest {

	Deuda deudaTest;
	Deuda deudaTest2;
	UUID ReservaIdTest;
	Monto TotalTest;
	List<Pago> ListaPagosTest;

	Pago pagoTest;
	UUID pagoIdTest;
	Monto montoPagadoTest;
	DetallePago detalleTest;
	UUID deudaIdTest;

	@BeforeEach
	void init() {
		ReservaIdTest = UUID.randomUUID();
		TotalTest = new Monto(100);
		ListaPagosTest = new ArrayList<>();
		deudaIdTest = UUID.randomUUID();

		deudaTest = new Deuda();

		deudaTest = new Deuda(ReservaIdTest, TotalTest);
		deudaTest2 = new Deuda(deudaTest);
		deudaTest =
			new Deuda(
				deudaIdTest,
				ReservaIdTest,
				TotalTest,
				null,
				ListaPagosTest
			);

		deudaTest = new Deuda(deudaIdTest, ReservaIdTest, TotalTest);
	}

	@Test
	void RealizarPagoExitosoTotalCambioAPagada() {
		pagoIdTest = UUID.randomUUID();
		montoPagadoTest = new Monto(100);
		detalleTest = new DetallePago("Detalle del pago");
		pagoTest =
			new Pago(
				pagoIdTest,
				montoPagadoTest,
				detalleTest,
				deudaTest.getId()
			);

		boolean resultado = deudaTest.RealizarPago(pagoTest);

		assertTrue(resultado);
		assertEquals(Constant.DEUDAESTADOPROCESANDO, deudaTest.getEstado());
	}

	@Test
	void RealizarPagoExitosoParcialMenorAlUmbralMantenerIniciada() {
		pagoIdTest = UUID.randomUUID();
		montoPagadoTest = new Monto(40);
		detalleTest = new DetallePago("Detalle del pago");
		pagoTest =
			new Pago(
				pagoIdTest,
				montoPagadoTest,
				detalleTest,
				deudaTest.getId()
			);

		boolean resultado = deudaTest.RealizarPago(pagoTest);

		assertTrue(resultado);
		assertEquals("INICIADA", deudaTest.getEstado());
	}

	@Test
	void RealizarPagoExitosoParcialMayorAlUmbralCambioAReservada() {
		pagoIdTest = UUID.randomUUID();
		montoPagadoTest = new Monto(60);
		detalleTest = new DetallePago("Detalle del pago");
		pagoTest =
			new Pago(
				pagoIdTest,
				montoPagadoTest,
				detalleTest,
				deudaTest.getId()
			);

		boolean resultado = deudaTest.RealizarPago(pagoTest);

		assertTrue(resultado);
		assertEquals("RESERVADA", deudaTest.getEstado());
	}

	@Test
	void RealizarPagoExitosoParcialIgualAlUmbralCambioAReservada() {
		pagoIdTest = UUID.randomUUID();
		montoPagadoTest = new Monto(50);
		detalleTest = new DetallePago("Detalle del pago");
		pagoTest =
			new Pago(
				pagoIdTest,
				montoPagadoTest,
				detalleTest,
				deudaTest.getId()
			);

		boolean resultado = deudaTest.RealizarPago(pagoTest);

		assertTrue(resultado);
		assertEquals("RESERVADA", deudaTest.getEstado());
	}

	@Test
	void RealizarDosPagosExitososConReduccionDeDeudaTotal() {
		pagoIdTest = UUID.randomUUID();
		montoPagadoTest = new Monto(40);
		detalleTest = new DetallePago("Detalle del pago");
		pagoTest =
			new Pago(
				pagoIdTest,
				montoPagadoTest,
				detalleTest,
				deudaTest.getId()
			);

		UUID pagoIdTest2 = UUID.randomUUID();
		Monto montoPagadoTest2 = new Monto(40);
		DetallePago detalleTest2 = new DetallePago("Detalle del pago 2");
		Pago pagoTest2 = new Pago(
			pagoIdTest2,
			montoPagadoTest2,
			detalleTest2,
			deudaTest.getId()
		);

		double deudaRestante =
			deudaTest.getTotal().getMonto() -
			pagoTest.getMontoPagado().getMonto() -
			pagoTest2.getMontoPagado().getMonto();

		boolean pagoRealizado1 = deudaTest.RealizarPago(pagoTest);
		boolean pagoRealizado2 = deudaTest.RealizarPago(pagoTest2);

		int cantidadDePagos = 2;

		assertTrue(pagoRealizado1 && pagoRealizado2);
		assertEquals(cantidadDePagos, deudaTest.getListaPagos().size());
		assertEquals(deudaRestante, deudaTest.consultarDeuda());
	}

	@Test
	void GenerarFacturaSatisfactoriamenteSinDeuda() throws Exception {
		String detalleFacturaTest = "Detalle de factura";
		int nitTest = 4456545;

		pagoIdTest = UUID.randomUUID();
		montoPagadoTest = new Monto(100);
		detalleTest = new DetallePago("Detalle del pago");
		pagoTest =
			new Pago(
				pagoIdTest,
				montoPagadoTest,
				detalleTest,
				deudaTest.getId()
			);

		boolean pagoRealizado = deudaTest.RealizarPago(pagoTest);
		deudaTest.confirmarPago();
		Factura facturaGenerada = deudaTest.GenerarFactura(
			detalleFacturaTest,
			nitTest
		);

		assertTrue(pagoRealizado);
		assertNotNull(facturaGenerada);
	}

	@Test
	void ErrorAlGenerarFacturaPorDeudaExistente() throws Exception {
		String detalleFacturaTest = "Detalle de factura";
		int nitTest = 4456545;

		pagoIdTest = UUID.randomUUID();
		montoPagadoTest = new Monto(10);
		detalleTest = new DetallePago("Detalle del pago");
		pagoTest =
			new Pago(
				pagoIdTest,
				montoPagadoTest,
				detalleTest,
				deudaTest.getId()
			);

		boolean pagoRealizado = deudaTest.RealizarPago(pagoTest);

		Factura facturaGenerada = deudaTest.GenerarFactura(
			detalleFacturaTest,
			nitTest
		);

		assertTrue(pagoRealizado);
		assertNull(facturaGenerada);
	}

	@Test
	void VencerDeudaPorDebajoDelUmbral() {
		pagoIdTest = UUID.randomUUID();
		montoPagadoTest = new Monto(40);
		detalleTest = new DetallePago("Detalle del pago");
		pagoTest =
			new Pago(
				pagoIdTest,
				montoPagadoTest,
				detalleTest,
				deudaTest.getId()
			);

		boolean pagoRealizado = deudaTest.RealizarPago(pagoTest);

		boolean deudaVencida = deudaTest.VencerDeuda();

		assertTrue(pagoRealizado);
		assertTrue(deudaVencida);
		assertEquals("VENCIDA", deudaTest.getEstado());
	}

	@Test
	void VencerDeudaFallidaPorEstarArribaDelUmbral() {
		pagoIdTest = UUID.randomUUID();
		montoPagadoTest = new Monto(60);
		detalleTest = new DetallePago("Detalle del pago");
		pagoTest =
			new Pago(
				pagoIdTest,
				montoPagadoTest,
				detalleTest,
				deudaTest.getId()
			);

		boolean pagoRealizado = deudaTest.RealizarPago(pagoTest);

		boolean deudaVencida = deudaTest.VencerDeuda();

		assertTrue(pagoRealizado);
		assertFalse(deudaVencida);
		assertNotEquals("VENCIDA", deudaTest.getEstado());
	}

	@Test
	void AnularPago() {
		ReservaIdTest = UUID.randomUUID();
		TotalTest = new Monto(100);
		ListaPagosTest = new ArrayList<>();
		deudaIdTest = UUID.randomUUID();
		deudaTest =
			new Deuda(
				deudaIdTest,
				ReservaIdTest,
				TotalTest,
				Constant.DEUDAESTADOINICIADA,
				ListaPagosTest
			);
		pagoIdTest = UUID.randomUUID();
		montoPagadoTest = new Monto(100);
		detalleTest = new DetallePago("Detalle del pago");
		pagoTest =
			new Pago(
				pagoIdTest,
				montoPagadoTest,
				detalleTest,
				deudaTest.getId()
			);

		double deudainicial = this.deudaTest.consultarDeuda();
		this.deudaTest.RealizarPago(pagoTest);
		double deudaconpago = this.deudaTest.consultarDeuda();
		this.deudaTest.anularPago(pagoIdTest.toString());
		double deudafinal = this.deudaTest.consultarDeuda();
		System.out.println("test ->" + deudainicial);
		System.out.println("test ->" + deudaconpago);
		System.out.println("test ->" + deudafinal);
		assertNotEquals(deudafinal, deudaconpago);
		assertEquals(deudainicial, deudafinal);
	}
}
