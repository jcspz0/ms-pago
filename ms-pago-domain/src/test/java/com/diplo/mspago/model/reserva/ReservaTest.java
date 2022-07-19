package com.diplo.mspago.model.reserva;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.mspago.valueobjects.Monto;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class ReservaTest {

	UUID reservaID;
	Monto precio;

	@Test
	void testReserva() {
		UUID reservaID = UUID.randomUUID();
		Monto precio = new Monto(10);

		Reserva reserva = new Reserva(precio);
		reserva = new Reserva(reservaID, precio);
		assertEquals(precio, reserva.getPrecio());
	}
}
