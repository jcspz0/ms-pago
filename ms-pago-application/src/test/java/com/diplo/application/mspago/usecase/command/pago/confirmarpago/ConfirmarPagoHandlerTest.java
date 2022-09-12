package com.diplo.application.mspago.usecase.command.pago.confirmarpago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.application.mspago.usecase.command.deuda.deudapagadarollback.DeudaPagadaRollbackCommand;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharedkernel.core.Constant;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConfirmarPagoHandlerTest {

	@InjectMocks
	ConfirmarPagoHandler handler;

	@Mock
	IDeudaRepository _deudaRepository;

	@Mock
	IUnitOfWork _unitOfWork;

	@Test
	void test() {
		try {
			UUID deudaIdTest = UUID.randomUUID();
			UUID reservaIdTest = UUID.randomUUID();
			Monto totalTest = new Monto(100);
			ArrayList<Pago> listaPagosTest = new ArrayList<Pago>();
			Deuda deudaTest = new Deuda(deudaIdTest, reservaIdTest, totalTest);

			DetallePago detallePagoTest = new DetallePago("Detalle del pago");
			Monto totalPagoTest = new Monto(100);
			Pago pagoTest = new Pago(
				totalPagoTest,
				detallePagoTest,
				deudaTest.getId()
			);
			deudaTest.RealizarPago(pagoTest);

			when(_deudaRepository.FindByReservaIdAsync(any()))
				.thenReturn(CompletableFuture.completedFuture(deudaTest));

			handler.Handle(new ConfirmarPagoCommand(reservaIdTest.toString()));

			assertEquals(Constant.DEUDAESTADOPAGADA, deudaTest.getEstado());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
