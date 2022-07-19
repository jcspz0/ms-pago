package com.diplo.application.mspago.service.deuda;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class DeudaService implements IDeudaService {

	@Override
	public Future<String> GenerarNroDeudaAsync() {
		return CompletableFuture.supplyAsync(() -> {
			return UUID.randomUUID().toString();
		});
	}
}
