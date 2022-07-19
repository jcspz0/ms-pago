package com.diplo.application.mspago.service.pago;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class PagoService implements IPagoService {

	@Override
	public Future<String> GenerarNroPagoAsync() {
		return CompletableFuture.supplyAsync(() -> {
			return UUID.randomUUID().toString();
		});
	}
}
