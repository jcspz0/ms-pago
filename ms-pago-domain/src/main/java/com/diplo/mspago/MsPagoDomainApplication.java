package com.diplo.mspago;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.model.reserva.Reserva;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsPagoDomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPagoDomainApplication.class, args);

		try {
			Reserva objReserva1 = new Reserva(new Monto(100));

			///---------
			Deuda objDeuda1 = new Deuda(objReserva1.getId(), new Monto(100));
			System.out.println(
				"deuda: " +
				objDeuda1.consultarDeuda() +
				", estado: " +
				objDeuda1.getEstado()
			);

			objDeuda1.RealizarPago(
				new Pago(
					new Monto(40),
					new DetallePago("pago de 40"),
					objDeuda1.getId()
				)
			);
			System.out.println(
				"deuda: " +
				objDeuda1.consultarDeuda() +
				", estado: " +
				objDeuda1.getEstado()
			);

			Pago objpago = new Pago(
				new Monto(40),
				new DetallePago("pago de 40"),
				objDeuda1.getId()
			);

			objDeuda1.RealizarPago(objpago);
			System.out.println(
				"deuda: " +
				objDeuda1.consultarDeuda() +
				", estado: " +
				objDeuda1.getEstado()
			);

			//objDeuda1.ImprimirFactura("pago de viaje", 123456);

			objDeuda1.RealizarPago(
				new Pago(
					new Monto(20),
					new DetallePago("pago de 20"),
					objDeuda1.getId()
				)
			);
			System.out.println(
				"deuda: " +
				objDeuda1.consultarDeuda() +
				", estado: " +
				objDeuda1.getEstado()
			);

			objDeuda1.GenerarFactura("pago de viaje", 123456);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
